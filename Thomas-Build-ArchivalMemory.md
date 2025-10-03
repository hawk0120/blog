# More on Archival Memory
I’ve let the idea sit for a couple of days, and I think the best solution right now is to give the model a memory search tool. This will let the model query its past memory for something relevant, then pull those results back into the current session.

I’ll also add a quick removal mechanism, so if the model decides a result isn’t relevant, it can discard it immediately. I’m leaning heavily on the model’s ability to infer what it actually needs. This feels problematic with a local model like Gemma2.

The model itself is the real limiter here. A newer or larger model would likely give better results, so I’ll explore that. In the meantime, I’ll archive working memory into those oversized Postgres text fields.

The simplified memory architecture will eventually run into problems. Externalizing more of the memory logic could help the model infer better, so it may be worth experimenting with sooner rather than later.
