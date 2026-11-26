package com.guilherme.assistente_ia;

import com.google.genai.Chat;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    private final ChatClient chatClient;
    public FeedbackService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public AnaliseFeedback coletar (DadosFeedback dados) {
        return new AnaliseFeedback(
                "Positivo - feedback de " + dados.cliente() + ": " + dados.comentario(),
                    false
                );

    }

}
