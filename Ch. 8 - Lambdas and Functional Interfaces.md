##### OCP EXAM OBJECTIVES COVERED IN THIS
- Using Object-Oriented Concepts in Java
	- Understand variable scopes, apply encapsulation, and create immutable objects. Use local variable type inference.
	- Create and use interfaces, identify functional interfaces, and utilize private, static, and default interface methods.

###### *Lambdas*
Lambdas are a new piece of syntax -> allows you to specify code to run later in the program
A *Lambda expression* are a block of code that gets passed around. like an unnamed method existing inside an anonymous class. Often referred to as a *closures*
The syntax contains 3 pieces
	``` a -> a.canHop()	```

- A single parameter in the example above *a*
- The arrow operator to seperate the parameter and body
- A body that calls a single method and returns the result of that method in the example above *a.canHop()*

Lambdas can have multiple parameters but you don't need to use all of them.

###### *Functional Interfaces*
Functional interfaces are interfaces that have a single abstract method. SAM(Single abstract method) rule states that a functional interface has exactly one method.
Syntax:
```
@FunctionalInterface
public interace Tree {
	void photosynthesis()
}
```
Inherited methods are included in the interface, even if not @Override is used.
All classes inherit certain methods from ```Object``` These are the exceptions the SAM rule. Be wary that these the methods can be overridden in parent classes.

###### *Method References*
Method references can make the code more readable
```
System.out::println
```
Use the ```::``` operator to tell java to call the method later. 
This syntax shortens unnecessary lambdas.
There are 4 formats for method references.
- static methods
- instance methods on a particular object
- Instance methods on a parameter to be determined at runtime
- Constructors

*****The tables below outline functional interfaces and class methods. They  will need to be memorised for the exam - For each of the functional interfaces:
	-> number of parameters
	-> types
	-> return value
	-> method name 

| Functional Interface | Return Type | Method Name  | # of Parameters | Parameter Types | Use Case                                                         |     |
| -------------------- | ----------- | ------------ | --------------- | --------------- | ---------------------------------------------------------------- | --- |
| Supplier<T>          | T           | get()        | 0               | -               | Supplying a value                                                |     |
| Consumer<T>          | void        | accept(T)    | 1               | T               | Do something to an object/field but don't return it              |     |
| BiConsumer<T, U>     | void        | accept(T, U) | 2               | T, U            | Same as Consumer except two parameters                           |     |
| Predicate<T>         | boolean     | test(T)      | 1               | T               | Filter/matching                                                  |     |
| BiPredicate<T, U>    | boolean     | test(T, U)   | 2               | T, U            | Same as Predicate but two parameters                             |     |
| Function<T, R>       | R           | apply(T)     | 1               | T               | Transforming -Do something to an object/field and return it      |     |
| BiFunction<T, U, R>  | R           | apply(T, U)  | 2               | T, U            | Same as Funtion except two parameters                            |     |
| UnaryOperator<T>     | T           | apply(T)     | 1               | T               | Special case of Function where all parameters are the same type. |     |
| BinaryOperator<T>    | T           | apply(T, T)  | 2               | T, T            | Merge two values into one of the same type                       |     |



| Interface | Instance Method    | Return Type | Method Name | Parameters |
| --------- | ------------------ | ----------- | ----------- | ---------- |
| Consumer  | Consumer.andThen() | Consumer    | andThen     | Consumer   |
| Function  | Function.andThen() | Function    | andThen     | Function   |
| Function  | Function.compose() | Function    | compose     | Function   |
| Predicate | Predicate.and()    | Predicate   | and         | Predicate  |
| Predicate | Predicate.negate() | —           | negate      | —          |
| Predicate | Predicate.or()     | Predicate   | or          | Predicate  |

There are functional interfaces for primitive types. Mostly int, double, and long types.

| Functional Interface | Return Type | Abstract Method | # of Parameters | Parameter Types |
| -------------------- | ----------- | --------------- | --------------- | --------------- |
| DoubleSupplier       | double      | getAsDouble()   | 0               | —               |
| IntSupplier          | int         | getAsInt()      | 0               | —               |
| LongSupplier         | long        | getAsLong()     | 0               | —               |
| DoubleConsumer       | void        | accept(double)  | 1               | double          |
| IntConsumer          | void        | accept(int)     | 1               | int             |
| LongConsumer         | void        | accept(long)    | 1               | long            |
| DoublePredicate      | boolean     | test(double)    | 1               | double          |
| IntPredicate         | boolean     | test(int)       | 1               | int             |
| LongPredicate        | boolean     | test(long)      | 1               | long            |
| DoubleFunction<R>    | R           | apply(double)   | 1               | double          |
| IntFunction<R>       | R           | apply(int)      | 1               | int             |
| LongFunction<R>      | R           | apply(long)     | 1               | long            |
| DoubleUnaryOperator  | double      | applyAsDouble() | 1               | double          |
| IntUnaryOperator     | int         | applyAsInt()    | 1               | int             |
| LongUnaryOperator    | long        | applyAsLong()   | 1               | long            |
| DoubleBinaryOperator | double      | applyAsDouble() | 2               | double, double  |
| IntBinaryOperator    | int         | applyAsInt()    | 2               | int, int        |
| LongBinaryOperator   | long        | applyAsLong()   | 2               | long, long      |



| Functional Interface       | Return Type | Abstract Method   | # of Parameters | Parameter Types     |
|-----------------------------|-------------|-------------------|-----------------|---------------------|
| ToDoubleFunction<T>         | double      | applyAsDouble()   | 1               | T                   |
| ToIntFunction<T>            | int         | applyAsInt()      | 1               | T                   |
| ToLongFunction<T>           | long        | applyAsLong()     | 1               | T                   |
| ToDoubleBiFunction<T, U>    | double      | applyAsDouble()   | 2               | T, U                |
| ToIntBiFunction<T, U>       | int         | applyAsInt()      | 2               | T, U                |
| ToLongBiFunction<T, U>      | long        | applyAsLong()     | 2               | T, U                |
| DoubleToIntFunction         | int         | applyAsInt()      | 1               | double              |
| DoubleToLongFunction        | long        | applyAsLong()     | 1               | double              |
| IntToDoubleFunction         | double      | applyAsDouble()   | 1               | int                 |
| IntToLongFunction           | long        | applyAsLong()     | 1               | int                 |
| LongToDoubleFunction        | double      | applyAsDouble()   | 1               | long                |
| LongToIntFunction           | int         | applyAsInt()      | 1               | long                |
| ObjDoubleConsumer<T>        | void        | accept()          | 2               | T, double           |
| ObjIntConsumer<T>           | void        | accept()          | 2               | T, int              |
| ObjLongConsumer<T>          | void        | accept()          | 2               | T, long             |
