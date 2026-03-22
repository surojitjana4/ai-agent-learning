package org.surojit.rag;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;

import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.util.List;


public class RAGExample {
    public static void main(String[] args) {

        // LLM
        OpenAiChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .build();

        // Embedding model
        EmbeddingModel embeddingModel = OpenAiEmbeddingModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .build();

        // Store
        InMemoryEmbeddingStore<TextSegment> store = new InMemoryEmbeddingStore<>();

        // Your knowledge
        String text = """
                Project: Order Management System
                APIs:
                - Create Order
                - Cancel Order
                - Get Order Details
                """;

        TextSegment segment = TextSegment.from(text);
        store.add(embeddingModel.embed(segment).content(), segment);

        // User question
        String query = "What APIs are available?";

        // 🔍 Retrieve relevant data
        List<EmbeddingMatch<TextSegment>> matches =
                store.findRelevant(embeddingModel.embed(query).content(), 3);

        String context = matches.stream()
                .map(m -> m.embedded().text())
                .reduce("", (a, b) -> a + "\n" + b);

        // 🧠 Combine with question
        String prompt = """
                            - Use context ONLY if it helps answer the question
                            - Otherwise answer using your own knowledge
                        Context:
                        """ + context + """
                        
                        Question: """ + query;

        // 🤖 Generate answer
        String answer = chatModel.generate(prompt);

        System.out.println("Answer:\n" + answer);
    }
}
