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
    public TriagemChamado analisar (String descricaoProblema) {
        // 1. Devolvemos uma resposta simulada (Mock) para testar sem gastar cota:
        return new TriagemChamado("Infraestrutura", true, "Reiniciar roteador");
        // 2. O código real fica guardado aqui embaixo (comentado) para quando você quiser ativar:
      //  return chatClient.prompt()
        //        .system("Você é um analista de suporte. Classifique o chamado informando o departamento responsável, se é urgente e a primeira ação recomendada.")
          //      .user(descricaoProblema)
            //    .call()
              //  .entity(TriagemChamado.class); // Transforma a resposta da IA direto no seu Record!
    }
}
