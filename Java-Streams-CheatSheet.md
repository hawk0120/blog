# Java 8 Streams
I recently went through a hiring cycle for a Java Software Engineer position. Everything seemed promising - the company appeared to have a great culture, interesting projects, and the team seemed genuine. The initial interview rounds went smoothly but when it came to the technical round, it took a turn.

Now, I consider myself compentent programmer with a portfolio to reflect that. However, there's something about an interview that makes programming infinitely more challenging. Whether its the weight of the moment or someone watching my every keystroke, its like navigating in a canoe down white water.

Unfortunately, I stumbled during the interview and ultimately, didn't receive an offer. It was rough, I could've solved the problem quickly if it was a take-home assignment. I realize the importance of knowing this stuff inside and out, but it still stings. 

So here's my attempt at locking the Java Streams API my long-term memory to ensure that this doesn't happen again. 


**Stream Creation**:

- Creating an empty stream:
     ```java
     Stream<String> streamEmpty = Stream.empty();
     ```
- Creating a stream from a collection:
     ```java
     Collection<String> collection = Arrays.asList("a", "b", "c");
     Stream<String> streamOfCollection = collection.stream();
     ```

- Creating a stream from an array:
     ```java
     Stream<String> streamOfArray = Stream.of("a", "b", "c");
     ```

- Using Stream.builder():
     ```java
     Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();
     ```

- Generating a stream using generate():
     ```java
     Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);
     ```

- Creating an infinite stream with iterate():
     ```java
     Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);
     ```

- Creating streams of primitives:
     ```java
     IntStream intStream = IntStream.range(1, 3);
     LongStream longStream = LongStream.rangeClosed(1, 3);
     ```

- Using String as a source:
     ```java
     IntStream streamOfChars = "abc".chars();
     ```

- Creating a stream from a file:
     ```java
     Path path = Paths.get("C:\\file.txt");
     Stream<String> streamOfStrings = Files.lines(path);
     ```

**Referencing a Stream**: Streams can be referenced until a terminal operation is called. Attempting to reuse a stream after a terminal operation results in an IllegalStateException.

**Stream Pipeline**: Stream pipelines consist of a source, intermediate operations, and a terminal operation. Example:
   ```java
   List<String> list = Arrays.asList("abc1", "abc2", "abc3");
   long size = list.stream().skip(1)
     .map(element -> element.substring(0, 3)).sorted().count();
   ```

**Lazy Invocation**: Intermediate operations are lazy and are invoked only if necessary for terminal operation execution.

**Order of Execution**: Proper order of operations in stream pipelines is crucial for performance optimization. Example:
   ```java
   long size = list.stream().map(element -> {
       wasCalled();
       return element.substring(0, 3);
   }).skip(2).count();
   ```

**Stream Reduction**: Terminal operations for aggregating streams, including count(), max(), min(), sum(), and custom reduction using reduce() and collect() methods, are discussed.

**Parallel Streams**: Parallel streams allow for concurrent processing. Example:
   ```java
   Stream<Product> streamOfCollection = productList.parallelStream();
   boolean isParallel = streamOfCollection.isParallel();
   ```

I hope you enjoyed the basics and examples of Java Streams.  
