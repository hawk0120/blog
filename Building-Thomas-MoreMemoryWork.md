## Simplifying Thomas’ Memory: Working vs Archival

I’ve been hard at work getting this project up and running. In the end, I decided to start with a much simpler memory architecture: **working memory** and **archival memory**.

* **Working memory** handles all the current conversation context—the last reply, what was said before that, and so on.
* **Archival memory** will save entire sessions for later reference.

---

### The Context Window Problem

Conversations (which I’ll now call **sessions**) can get long. Right now, there’s a loop that feeds data into the model and outputs a response. Working memory stores all prior replies and session details. If a session runs long, the prompts can balloon in size.

I’m currently testing with **Gemma2:2B**, which has an 8192-token context window. Tokens aside, that’s roughly **30,000 characters**. Long term, I’ll either need a model with a larger context window or an algorithm to sort and prioritize what’s truly relevant.

That’s a **later problem**. First, I just want to get Thomas running reliably on the network. Memory optimizations can come after.

---

### Archival Memory in Postgres

The next feature is **archival memory**. Each session will be saved in my Postgres database.

Postgres makes this pretty simple:

* The `text` type can store up to **1 GB** in a single cell.
* Since `1 char = 1 byte`, that means roughly **1 billion characters** per cell.
* In other words, I can store an entire novel’s worth of chat history in one row.

That’s… ludicrous.
***Insert Austin Powers meme here.***

So storage isn’t the problem. I’ll save each session in a single row. The **context window** will be the real limiter, not Postgres.

---

### What to Retrieve?

The real challenge is retrieval. What’s relevant to the current session?

For now, I think the simplest path is to pull in **personas** and **prior sessions** when needed. That should be enough for an MVP. Down the road, I’ll work on a smarter retrieval algorithm—sI am hard at work getting this project up and running. In the end I went for a much simpler memory architecture - working memory and archival memory. Working memory is built so it *remember* all the current conversation context, last reply and what was said before that, etc.

I will run into a problem later when our conversations run too long, I'll call conversations from now on sessions. Currently there's a loop that feeds data into the model and outputs a response. The active working memory stores all prior replies, and information about the current session, so if a session theoretically went long the prompts could get quite larger. Currently, I'm testing with Gemma2:2b. It has 8192 token context window. Don't worry about the tokens unit, it's roughly 30,000 characters. So in the long term, a model with a bigger context would solve this. Alternatively, I find a strategy to sort what's more relevant. I'm not sure yet. It is a problem for later. First and foremost lets get this project running on the network. I can improve memory later.

Archival memory is the next feature to be implemented. So far, it is proving to be a complex problem. Remember those things called sessions(conversations). I'll save these in a table in my Postgres database too. However, Postgres has a ridiculous text type, I can store up to **1 GB** of data in a single cell. I'll store only text. I'll need to sort anything else prior to it getting inserted into the database. 
```
1 char =  1 byte
1GB = 1 billion bytes
```
I can roughly save **1 billion** characters in a single cell. That's ludicrous.
***Insert Austin powers meme***

Storage isn't going to be a problem, clearly. I'll save each session in a single row. The context window will be the eventual limiter. 

I'm not sure what will be relevant to the current session so algorithm for retrieval will be challenging. I think retrieving relevant personas and those prior sessions will be the easiest and simplest implementation. I'll think about a better solution this week.omething that balances relevance with efficiency.

That’s where I’ll focus later this week.
