package org.surojit.tool.calculator;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.surojit.tool.calculator.Assistant;
import org.surojit.tool.calculator.CalculatorTool;

public class Test {

    public static void main(String[] args) {

        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY")) // keep simple for now
                .modelName("gpt-4o-mini")
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .tools(new CalculatorTool()) // 👈 attach tool
                .build();

        String response = assistant.chat("What's the result of 12 * (5 + 3)?");

        System.out.println(response);
    }
}
