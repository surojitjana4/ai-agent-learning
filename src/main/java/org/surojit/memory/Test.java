package org.surojit.memory;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.surojit.Assistant;
import org.surojit.tool.calculator.CalculatorTool;
import org.surojit.tool.weather.WeatherTool;

public class Test {

    public static void main(String[] args) {

        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .tools(new WeatherTool(), new CalculatorTool())
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10)) // 👈 memory
                .build();

        System.out.println(assistant.chat("If I buy 3 items of 100 each and add 18% GST, what is total?"));

    }
}
