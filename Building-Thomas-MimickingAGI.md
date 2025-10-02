# Building a Semi Autonomous Bluesky Agent with Persistent Memory
I’ve been working on an AI agent that runs locally on my laptop and connects to Bluesky. Powered by TinyLlama. It's a fun little chatbot that's a context-aware agent with persistent memory.

Inspired by @Void.comind.network, Thomas (my AI agent) will remember past interactions, update its memory, and reflects over time. It will compress memory, rewrite or forget what’s no longer relevant. Think of it as a digital entity with long-term memory and soft self-awareness.

### Architecture Overview

The ToolDispatcher is the core of Thomas’ intelligence. After each interaction, it can:
- Update memory
- Summarize recent events
- Reflect on older memories to tidy itself

Over time, this enables a consistent internal narrative, a small step toward self-reflection.

The project is local, lightweight, and fully under my control—no cloud, no external dependencies. Just a little AI on my desk, wired into Bluesky.

![assets/Thomas-Tech-Design.png](https://github.com/hawk0120/blog/blob/main/assets/Thomas-Tech-Design.png)
