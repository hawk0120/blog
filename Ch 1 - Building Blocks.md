I'm tackling the Oracle Certified Practitioner Java 21 exam. There is an element to my reasoning that my employer wants me to take it and there's a study group at workplace. I'm also interested in more credentialing. With AI here and to stay, in the long term it will be helpful to have a strong foundation in a common language. I'm a masochist, like writing the first level of the Chartered Financial Analyst (CFA) exam . I like hard challenges and learning deep details of a domain. 

Whatever my reasoning for doing this, I begin my journey of this enormous topic reviewing the foundational elements of the language at Chapter 1.

These are of my study notes from the OCP java 21 prep. It assumes at least a passing familiarity with writing and reading java syntax.
### Ch 1 - Building Blocks
**OCP EXAM OBJECTIVES COVERED IN THIS CHAPTER:**
- [x] Handling Date, Time, Text, Numeric and Boolean values
	- [x] Use primitives and wrapper classes. Evaluate arithmetic and boolean expressions, using the Math API and by applying precedence rules, type conversions, and casting.
- [x] Using Object-Oriented Concepts in Java
	- [x] Declare and instantiate Java objects including nested class objects, and explain the object life-cycle including creation, reassigning references, and garbage collection. Understand variable scopes, apply encapsulation, and create immutable objects. Use local variable type inference.


-> Three important commands
```
javac: Converts only .java source files into .class bytecode. 
java: Executes the program
jar: Packages files together
javadoc: Generates documentation
```
Classes our foundational to how software is built (Similar to molecules). A runtime instance of class is called an *object*. All the various objects of your program is called the *state* A *reference* is a variable that points to your object. 

Classes contain fields, methods, and comments. Methods are often called functions in other languages (Python, Javascript, etc.). Fields are commonly known as variables. Methods contains behaviour that is executed on the state of the program. If it's important to remember this state, it is stored in a field.

A keyword is a reserved word by the java language. 

Methods have an access modifier (public, private, package-private), return type, method name,  and parameter name and type. Collectively known as the signature.

Comments are not executed so they can be placed almost anywhere in the code. There are 3 types of comments single line, multi line, and javadoc comments.

Public class types must match the filename. However, if you have
```
public class Foo {}
class Bar {} 
```
This is allowed to do to the implied class types. Java compiles Foo as public and Bar as package-private.

***main() Method***
Execution of the program starts at the main() method. The compiler looks for this method at compilation time. The signature of this method must be 
```
public static void main(String[] args) {}
```
The *static* keyword binds a method to the class so it can be called just by the class. The parameter type is an array of String values, any parameter name that isn't on the reserved words list is fine. 
```
String[] args
String options[]
String… friends
```
The parameter name is important as it hints at what the value is. Therefore, using *args* we understand that there are arguments read in by the JVM. 

***Packages Declarations & Imports***
Java organises classes into packages. Similar to a file cabinet, we put the each class in folder so its easier to understand and find each class. 
To import classes from folders, use the *import* keyword along with where to find them. 
```
import java.util.Random;
```
We use wild cards * to import the entirety of the package. Import statements are sorted like mailing addresses. First we look at the country code, then the city, and finally the street address.

When compiling, by default the *javac* places the bytecode in the same package as the source code. You can change this by using -d flag on *javac*. It allows you to specify which directory to place the bytecode.

Java classes are ordered by package declaration, import statements, top-level type declaration, field declaration, then method declaration.

***Creating Objects***
To create a new instance of the class, call the constructor.
The name of the constructor is the same as the class - always, and do not have return types. If a constructor is not defined, the compile defines a "Do Nothing" constructor as default. 

Code blocks are defined by braces. The following is a single code block
```
{
code
some more code
some more code x2
}
```
Fields and Instance initializers are ran in the order in which they appear in the file. The constructor follows after fields and objects are initialized. Order matters.

***Data Types***
There are two data types - reference and primitive types.

There are 3 key differences between the two types:
Primitive types are lowercase.
References can be used to call methods.
Reference types can be *null*.

Each primitive type has a wrapper class. All number wrapper classes extend Number.  These classes are not always the most accurate. 
***Primitive types***
Java has 8 built in types:

| Keyword | Type                  | Min value      | Max value     | Default  | Example     |
| ------- | --------------------- | -------------- | ------------- | -------- | ----------- |
| boolean | value                 | n/a            | n/a           | false    | true, false |
| byte    | 8-bit integral        | -128           | 127           | 0        | 123         |
| short   | 16-bit integral       | -32,768        | 32,767        | 0        | 123         |
| int     | 32-bit integral       | -2,147,483,648 | 2,147,483,647 | 0        | 123         |
| long    | 64-bit integral       | -2⁶³           | 2⁶³–1         | 0L       | 123L        |
| float   | 32-bit floating-point | n/a            | n/a           | 0.0f     | 123.45f     |
| double  | 64-bit floating-point | n/a            | n/a           | 0.0      | 123.456     |
| char    | 16-bit Unicode        | 0              | 65,535        | '\u0000' | 'a'         |
<u>*Sizing will not be on the exam*</u>

When a number is present in the code, it's known as a *literal*
By default, the compiler assumes it is an int unless its too large. In which case, it's a long.
We can also change the base number - Hex, octal, and binary. The following prefixes are given for each base case.
```
Hex -> 0
Octal -> 0x or 0X
Binary -> 0B or 0b
```
You can use underscores in literals with some caveats. The underscore cannot appear at the beginning, ending, or near a decimal.

***Reference types***
This type refers to an object. The type points to the value in memory. A reference can be changed to point to the same type in memory or assigned to a new object using *new*. 
Objects are not references. Don't confuse the two. An object sits on the heap and doesn't have a name. All references are the same and can be reassigned. 

***Declaring Variables***
Identifier names are precise. 

The rules of naming follow:
- Identifiers must begin with a letter, a currency symbol, or a symbol.
- Identifiers can include numbers but cannot start with them.
- A single underscore is not allowed as an indentifier
- Reserved words aren't allowed to be used
<u>***These rules will almost definitely be on the exam***</u>

Multiple variables can be defined on a single line as long as the types are the same. 
```
String cat = "cat", dog = "dog";
```
*final* can be applied to either data types
*var*  is local variable type inference. Meaning it must be a local variable, and the compiler will infer the type. Interpreted language features in java. WTF. This is different. The compiler infers the type so once its defined. It doesn't change. It can throw an error if you attempt to put a different type into it. Ex. boolean into an int. 
Similar to a union in typescript.

<u>***Scoping variables will be tested on the exam***</u>

***Garbage Collection***
Garbage collection is like **leaves falling from a tree in autumn**.  
The tree decides when to let go of the leaves - it doesn’t happen the moment a leaf “isn’t needed” anymore. You can see the leaf turn brown and stop providing value, but the timing of when it actually drops to the ground is up to the tree’s natural process.
There is a built in method but java decides, the compiler may ignore you. 
The JVM decides to collect the garbage with these two reasons:
- The object has no more references to it.
- All references have gone out of scope. 
