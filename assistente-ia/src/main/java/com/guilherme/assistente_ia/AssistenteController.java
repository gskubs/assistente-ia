package com.guilherme.assistente_ia;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Etiqueta 1: Avisa ao Spring que esta classe atende pedidos da Web
public class AssistenteController {

    private final ChatClient chatClient;
    private final TriagemService triagemService;
    private final FeedbackService feedbackService;


    // Construtor: O próprio Spring entrega o ChatClient montado aqui dentro
    public AssistenteController(ChatClient.Builder builder, TriagemService triagemService, FeedbackService feedbackService) {
        this.chatClient = builder.build();
        this.triagemService = triagemService;
        this.feedbackService = feedbackService;
    }

    // Etiqueta 2: Cria o endereço http://localhost:8080/perguntar
    @GetMapping("/perguntar")
    public String perguntarParaIA(@RequestParam String mensagem) {

        // Envia a mensagem para a IA e devolve a resposta em texto
        return chatClient.prompt()
                .user(mensagem)
                .call()
                .content();
    }
        @GetMapping("/traduzir")
    public String traduzir(@RequestParam String mensagem) {
        return chatClient.prompt()
                .system("Você é um tradutor. Traduza o texto do usuário para o português de forma natural, devolvendo apenas a tradução.")
                .user(mensagem)
                .call()
                .content();
    }
        // Novo guichê: apenas repassa o trabalho para o especialista (Service)
        @PostMapping("/triagem")
        public TriagemChamado fazerTriagem(@RequestBody SolicitacaoTriagem dados) {
            return triagemService.analisar(dados);
        }

    // Novo guichê: apenas repassa o trabalho para o especialista (Service)
    @PostMapping("/feedback")
    public AnaliseFeedback analisarFeedback(@RequestBody DadosFeedback dados) {
        return feedbackService.coletar(dados);
    }

}

