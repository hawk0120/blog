# Building a Semi Autonomous Bluesky Agent with Persistent Memory
Lately I’ve been working on an AI agent that runs on my laptop and connects to Bluesky. It’s powered by TinyLlama.

The goal isn’t just to build a chatbot. I want this agent to be context-aware, and with persistent memory. Its heavily inspired by [@Void.comind.network ](https://bsky.app/profile/void.comind.network).That means the agent remembers past interactions, updates its own memory, and reflects over time. It' will compress memory by rewriting, or forgetting what no longer matters. Think of it like a digital entity with a long-term memory and the ability to reflect on its own behavior.

Here’s the rough architecture I’m working with:

![McF33ly.Technical.Design.png](https://github.com/hawk0120/blog/blob/main/McF33ly.Technical.Design.png)

The HooksDispatcher is where the magic happens. After each interaction, the agent can update its memory, summarize what just happened, or even reflect on older memories to keep things tidy. Over time, this will allow it to develop a consistent internal narrative, a step toward soft self-awareness.

This project stays local, lightweight, and fully under my control no cloud, no external dependencies. Just a little self-aware AI on my desk, wired into Bluesky.

(More updates soon - I’ll add a Bluesky link here once it’s live.)
