package org.surojit;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.data.message.UserMessage;

public class AiTest {
    public static void main(String[] args) {

        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .build();

        try {
            var response = model.generate(UserMessage.from("Explain AI agents in simple terms for a Java developer"));

            System.out.println(response.content().text());
        } catch (Exception e) {
            System.out.println("Error calling OpenAI API: " + e.getMessage());
            System.out.println("Please check your API key and quota at https://platform.openai.com/account/api-keys");
        }
    }
}
