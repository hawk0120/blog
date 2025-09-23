# How does Thomas' memory work? 
I've been thinking about how to the AI agent's(I'm calling Thomas) what memory should look like. So far I've come up with these requirements. 

- It should be fast. Blazingly so. There should be minimal delay. My reasoning is that the LLM client isn't very responsive with limited resources and that's where I want to allocate the slack in the system. If there's an issue with I/O of the model, the database will need to be queried several times.  
- The database should store text blocks well. If it doesn't support a blob, it's not for me.  
- I prefer relational databases. It's easier for me to get my head around the structures. I think they are better for the developer experience. 
- I should have the options of adding a vector database later. Improving a systems memory storage is never a bad thing. This isn't a hard requirement but definitely a nice to have.

ChatGPT pointed me toward Postgres, and I agree - it’s the clear winner. Here’s why:

* Speed is great, especially with indexes (timestamps, importance scores, etc.).
* Text handling is strong (`TEXT`, `BYTEA`, or even `JSONB` for flexible structures).
* It fits the relational mindset perfectly.
* And with `pgvector`, I can add embeddings later without re-architecting.

Where I diverged from ChatGPT was in the schema design. Their sketch felt too vague, so I put together my own.


![assets/Thomas-Build-DataScheme.svg](https://github.com/hawk0120/blog/blob/main/assets/Thomas-Build-Data-Scheme.svg)

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

The CoreMemory table will store data related to personality, safety protocols, and the principles that define who the bot “believes” it is. I’ll also create a Persona table to capture all interactions with new entities, which allows me to manage or blacklist troublesome ones. The memory model loosely mirrors human cognition - short-term and long-term memory separated. It’s an intentional oversimplification to keep the MVP lightweight; I’d rather build something functional now than over-engineer. Over time, I can refine it further, but this foundation gives the system a clear psychological architecture: identity, memory, relationships, and reflections.

Anyways, I'm back from my vacation and should make progress on this again.
