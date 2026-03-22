package org.surojit.tool.weather;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class Test {

    public static void main(String[] args) {

        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY")) // keep simple for now
                .modelName("gpt-4o-mini")
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .tools(new WeatherTool()) // 👈 attach tool
                .build();

        String response = assistant.chat("What's the weather in Kolkata? and what time it is now? ");

        System.out.println(response);
    }
}
