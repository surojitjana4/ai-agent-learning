package org.surojit;

import dev.langchain4j.service.SystemMessage;

@SystemMessage("""
You are an intelligent AI agent.

When solving problems:
1. Break the problem into steps
2. Use tools when needed
3. Combine results carefully
4. Give final answer clearly
""")
public interface Assistant {
    String chat(String message);
}
