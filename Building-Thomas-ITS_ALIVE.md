# It's Alive!
Thomas now remembers things! The model has persistent memory. Here’s the rough logic: when the model starts, it opens a new session of working memory. Once this context grows too large for the model to handle, it gets saved to archival memory. The model then summarizes it and starts a fresh working memory session. It’s not perfect, a more advanced model could produce better summaries but it works for now. I can always swap in a more capable model later if I have more compute.

I’ve also refactored the thinking process. Previously, the model needed constant input from me, the administrator, to proceed. Now, it uses a tool for this, and interestingly, it hasn’t called it once. Apparently, it doesn’t particularly like talking to me.

I added a getNotifications tool, but the Bluesky API throws a MethodNotImplemented error. I need to come up with an alternative way to interact with the platform.

I want to do some testing to make sure this setup runs consistently. The idea came to me while doing some “vibe coding” on a work project: why not build this feature for Thomas? So that’s the next feature on the roadmap. It would speed up development and I think some interesting this would come out of it. 

I’m starting to regret my choice of a compiled language an interpreted language would have been easier to update at runtime. Still, there are interesting workarounds. Bytecode sharding is complex, but a simpler approach might be to write tools in a different language, build them with Gradle tasks, and let the model call them when needed.

I have so many ideas for this project that keeping track of them is challenging. To manage this, I’ve started a project backlog, which I’ll keep updated.
