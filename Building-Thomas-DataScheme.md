# How does Thomas' memory work? 
I've been thinking about how to the AI agent's(I'm calling Thomas) what memory should look like. So far I've come up with these requirements. 

- It should be fast. Blazingly so. There should be minimal delay. My reasoning is that the LLM client isn't very responsive with limited resources and that's where I want to allocate the slack in the system. If there's an issue with I/O of the model, the database will need to be queried several times.  
- The database should store text blocks well. If it doesn't support a blob, it's not for me.  
- I prefer relational databases. It's easier for me to get my head around the structures. I think they are better for the developer experience. 
- I should have the options of adding a vector database later. Improving a systems memory storage is never a bad thing. This isn't a hard requirement but definitely a nice to have.

I put this into ChatGPT and this is the options I received.

```
These requirements line up _really_ well with Postgres—you’re basically describing its sweet spot. Let’s break them down against your needs:

**1. Speed**

- Postgres can easily handle the kind of I/O you’re talking about, especially if it’s local.
    
- You’ll want **indexes** on things like timestamps, importance scores, or interaction IDs.
    
- For blazing-fast reads, caching layers (Redis or even an in-process LRU cache) can sit on top—but honestly, with tuned indexes and small-scale local usage, Postgres should feel snappy.
    

**2. Text Block Storage**

- Postgres has `TEXT` and `BYTEA` types. `TEXT` handles arbitrarily large text (up to 1GB per field). That’s plenty for storing memory chunks, summaries, reflections, or even entire interactions.
    
- If you want some structure, `JSONB` is a great middle ground—structured storage, but still flexible.
    

**3. Relational DB Preference**

- Postgres shines here. You can design memory around relationships:
    
    - `interactions` table (raw conversations)
        
    - `summaries` table (compressed memories referencing interactions)
        
    - `reflections` table (longer-term insights referencing summaries)
        
- This relational model makes it easier to query across layers (e.g., “What past events led to this reflection?”).
    

**4. Vector DB Option**

- The `pgvector` extension lets you store embeddings right inside Postgres. No need to spin up Pinecone, Weaviate, etc.
    
- You could add an `embeddings` column (`VECTOR(1536)`) tied to each memory block.
    
- That future-proofs your schema without committing to it right away.
```

I agree with ChatGPT. Postgres is the clear winner here. An importance score is a good idea. I don't like its ideas for a data scheme, I think it needs more details. I sketched something up below.


![Thomas-Build-DataScheme.svg](https://github.com/hawk0120/blog/blob/main/Thomas-Build-Data-Scheme.svg)

```
DROP TABLE ShortTermMemory; 
DROP TABLE LongTermMemory; 
DROP TABLE CoreMemory; 
DROP TABLE Persona; 

CREATE TABLE Persona (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(50),
    last_change TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE CoreMemory (
    id SERIAL PRIMARY KEY,
    memory_type VARCHAR(50),
    memory TEXT CHECK (char_length(memory) <= 1000),
    persona VARCHAR(50),
    persona_id INT,
    last_change TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ShortTermMemory (
    id SERIAL PRIMARY KEY,
    memory TEXT CHECK (char_length(memory) <= 1000),
    persona_id VARCHAR(50),
    last_change TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_persona
        FOREIGN KEY(persona_id)
        REFERENCES Persona(id)
);

CREATE TABLE LongTermMemory (
    id SERIAL PRIMARY KEY,
    memory TEXT CHECK (char_length(memory) <= 1000),
    persona_id VARCHAR(50),
    last_change TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_persona
        FOREIGN KEY(persona_id)
        REFERENCES Persona(id)
);
```

As you can see, the CoreMemory table will hold all data related to personality, safety protocols, and protocols of who the bot "believes" it is. I want to store all interactions with a new entity so I'll create a Personas table. This way I can blacklist entities that troublesome. Then we give it more or less, the same memory make up of a human. It's an oversimplification, working memory combines long term and short term memory into one area but hey, it's an MVP. I don't want to over engineer it and not build it. I can always add it later. This will give it a psychological architecture - identity, memory, relationships, and reflections.


Anyways, I'm back from my vacation and should make progress on this again.
