package org.surojit.memory;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.surojit.Assistant;
import org.surojit.tool.calculator.CalculatorTool;
import org.surojit.tool.weather.WeatherTool;

import java.util.Scanner;

public class ChatBot {

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

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("exit")){
                System.out.println("AI: Goodbye!");
                break;
            }
            String response = assistant.chat(input);
            System.out.println("AI: " + response);
        }
    }
}
