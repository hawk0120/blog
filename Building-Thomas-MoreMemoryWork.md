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

That’s ludicrous. Did some one say 1 billion?\

![Austin Powers Meme](https://upload.wikimedia.org/wikipedia/en/1/16/Drevil_million_dollars.jpg)


So storage isn’t the problem. I’ll save each session in a single row. The **context window** will be the real limiter, not Postgres.

---

### What to Retrieve?

The real challenge is retrieval. What’s relevant to the current session?

For now, I think the simplest path is to pull in personas and prior sessions when needed. That should be enough for an MVP. Down the road, I’ll work on a smarter retrieval algorithm—something that balances relevance with efficiency.

That’s where I’ll focus later this week.
