
**OCP EXAM OBJECTIVES COVERED IN THIS**
**CHAPTER:**
- [ ] Handling Date, Time, Text, Numeric and Boolean Values
	- [ ] Use primitives and wrapper classes. Evaluate arithmetic and boolean expressions, using the Math API and by applying precedence rules, type conversions, and casting.

*Operators* are special symbol that can be applied to a set of variables, values, or literals. *Operands* are the values/variables that Operators work on. The *Result* is output of the operation.

*3 types of operators*
	- Unary
	- Binary
	- Ternary
	
Operators are not evaluated in left to right order. Operator precedence is determining the order of evaluation. Java mostly follows maths in this regard. 
Order of operations applies.
BEDOMAOS= 
If two operators are the same, java guarantees left to right

| Category                        | Operators                                     | Associativity |
| ------------------------------- | --------------------------------------------- | ------------- |
| Post-unary operators            | `expression++`, `expression--`                | Left-to-right |
| Pre-unary operators             | `++expression`, `--expression`, `-expression` | Right-to-left |
| Other unary operators           | `-`, `!`, `~`, `+`, `(type)`                  | Right-to-left |
| Cast                            | `(Type)reference`                             | Right-to-left |
| Multiplication / Division / Mod | `*`, `/`, `%`                                 | Left-to-right |
| Addition / Subtraction          | `+`, `-`                                      | Left-to-right |
| Shift operators                 | `<<`, `>>`, `>>>`                             | Left-to-right |
| Relational operators            | `<`, `>`, `<=`, `>=`, `instanceof`            | Left-to-right |
| Equality operators              | `==`, `!=`                                    | Left-to-right |
| Logical AND                     | `&`                                           | Left-to-right |
| Logical exclusive OR            | `^`                                           | Left-to-right |
| Logical inclusive OR            | \|                                            | Left-to-right |
| Conditional AND                 | `&&`                                          | Left-to-right |
| Conditional OR                  | \|\|                                          | Left-to-right |

*Ternary operators*

| Category             | Operators / Examples                                                      | Associativity |
| -------------------- | ------------------------------------------------------------------------- | ------------- |
| Ternary operator     | `booleanExpression ? expression1 : expression2`                           | Right-to-left |
| Assignment operators | `=`, `+=`, `-=`, `*=`, `/=`, `%=` , `&=`, `^=`, \|=`, `<<=`, `>>=`, `>>>= | Right-to-left |
| Arrow operator       | `->`                                                                      | Right-to-left |

*Unary Operators*

| Operator       | Examples     | Description                                                                 |
|----------------|--------------|-----------------------------------------------------------------------------|
| Logical complement | `!a`       | Inverts a boolean’s logical value                                           |
| Bitwise complement | `~b`       | Inverts all 0s and 1s in a number                                           |
| Plus           | `+c`         | Indicates a number is positive (numbers are positive by default in Java)     |
| Negation / Minus | `-d`       | Indicates a literal number is negative or negates an expression              |
| Increment      | `++e`, `f++` | Increments a value by 1 (prefix or postfix)                                  |
| Decrement      | `--f`, `h--` | Decrements a value by 1 (prefix or postfix)                                  |
| Cast           | `(String)i`  | Casts a value to a specific type                                             |

The *logical compliment operator* ! flips the value of a boolean expression. Known as a bang in javascript
The *bitwise negation operator* ~ turns all the zeros into ones and vice versa.
The *negation operator* -  reverses the sign of a numeric expression.

Brackets change the order of operation. Be weary of these on the exam.

Modulus % return the remainder. Negative numbers alter the behaviour. If the divisor is negative, the negative sign is ignored. 
****Review a breath of Modulus questions for exam***

*Numeric Promotion Rules* (Casting up)
1. If two values have different data types. The smaller will be promoted to the larger value.
2. If one of the values is integral and the other is floating point, the int will be promoted to a float.
3. Smaller data types ```byte, short, and char``` are first promoted to int any time they are used in binary arithmetic operations with a variable, even if neither of the operands is int.
4. After all promotion has occurred and the operands have the same data type, the resulting value will have the same data type as its promoted operands.

***Assigning Values***
*Assignment Operator* is a binary operator that modifies or assigns.
Always evaluated left to right.

*Casting* is the explicit typing of a value
The compiler will throw an error when attempting to put a larger type in a smaller type.
*Overflow* is when a number is so large that it will no longer fit with the data type.
*Underflow* is the inverse of this.
