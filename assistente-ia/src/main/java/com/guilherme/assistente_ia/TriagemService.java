package com.guilherme.assistente_ia;

import com.google.genai.Chat;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class TriagemService {

    private final ChatClient chatClient;

    public TriagemService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }
    public TriagemChamado analisar(SolicitacaoTriagem dados) {
        // Resposta simulada (Mock) devolvendo os dados recebidos no POST:
        return new TriagemChamado(
                "Suporte N1",
                true,
                "Contatar " + dados.solicitante() + " para verificar: " + dados.descricaoProblema()
        );

        // Código real com Gemini (mantido comentado para poupar cota):
        // return chatClient.prompt()
        //         .system("Você é um analista de suporte. Classifique o chamado informando o departamento responsável, se é urgente e a primeira ação recomendada.")
        //         .user("Solicitante: " + dados.solicitante() + " | Problema: " + dados.descricaoProblema())
        //         .call()
        //         .entity(TriagemChamado.class);
    }
}