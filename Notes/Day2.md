# Java Advanced Interface and Inheritance Notes

---

## 1. Multiple Interface Default Methods: Conflict Resolution

If a class implements two interfaces with the same default method, you must override the method and explicitly choose which default to call.

```java
interface A {
    default void ping() { System.out.println("A"); }
}
interface B {
    default void ping() { System.out.println("B"); }
}
class C implements A, B {
    public void ping() {
        A.super.ping(); // Call A's ping
        B.super.ping(); // Call B's ping
    }
}
public class Main {
    public static void main(String[] args) {
        new C().ping();
    }
}
```
**Error if not overridden:**
> class C inherits unrelated defaults for ping() from types A and B

**Why:**
- Java requires you to resolve ambiguity when multiple interfaces provide the same default method.

---

## 2. Static Methods in Interfaces

- Static methods in interfaces are not inherited by implementing classes.
- You must call them using the interface name.

```java
interface Util {
    static String id() { return "Util ID"; }
}
class X implements Util {
    static String id() { return "X ID"; }
}
public class Main {
    public static void main(String[] args) {
        System.out.println(X.id());      // X ID
        System.out.println(Util.id());   // Util ID
        // System.out.println(u.id());   // Error: Cannot call static method on instance
    }
}
```

---

## 3. Private Methods in Interfaces

- Private methods in interfaces are only accessible within the interface itself.
- They cannot be called from implementing classes.

```java
interface Parser {
    default String sanitize(String s) { return trimAndLower(s); }
    private String trimAndLower(String s) { return s.trim().toLowerCase(); }
}
class Impl implements Parser {
    public String demo(String s) {
        // return Parser.trimAndLower(s); // Error: Not accessible
        return sanitize(s);
    }
}
```

---

## 4. Method Overloading vs Overriding with Interfaces

- Overloading: Same method name, different parameter types.
- Overriding: Same method name and parameter types.

```java
interface I { void m(Number n); }
class A { public void m(Integer i) { System.out.println("A"); } }
class B extends A implements I {
    @Override
    public void m(Number n) { System.out.println("B"); }
}
public class Main {
    public static void main(String[] args) {
        new B().m(10);         // A (overloading)
        ((I) new B()).m(10);   // B (overriding via interface reference)
    }
}
```
**Why:**
- Reference type determines which method signatures are visible at compile time.

---

## 5. Covariant Return Types in Interfaces

- You can override a method and return a subtype.

```java
class Shape {}
class Circle extends Shape {}
interface Factory { Shape create(); }
class CircleFactory implements Factory {
    @Override
    public Circle create() { return new Circle(); }
}
```

---

## 6. Exception Compatibility in Overriding

- When a class implements an interface and extends a class, the overriding method cannot throw broader checked exceptions than those declared in both.

```java
import java.io.IOException;
import java.sql.SQLException;
abstract class A { abstract void run() throws IOException; }
interface I { void run() throws SQLException; }
class C extends A implements I {
    @Override
    public void run() { System.out.println("C"); } // No checked exceptions allowed
}
```
**Why:**
- Only unchecked exceptions are allowed if the checked exceptions in the parent and interface do not overlap.

---

## 7. Sealed and Non-Sealed Interfaces

- `sealed` restricts which classes can implement the interface.
- `non-sealed` allows further extension.

```java
sealed interface Animal permits Dog, Cat {}
non-sealed interface Dog extends Animal {}
interface Cat extends Animal {}
class Persian implements Dog {}
```

---

## 8. Static Methods Cannot Be Overridden

- Static methods belong to the class, not the instance.

```java
class P { static void hello() { System.out.println("Hello from P"); } }
class Q { static void hello() { System.out.println("Hello from Q"); } }
P.hello(); // Hello from P
Q.hello(); // Hello from Q
```

---

## 9. Generics and Type Erasure

- Raw types cause warnings; use parameterized types for safety.

```java
interface Box<T> { T get(); }
class StringBox implements Box<String> {
    @Override
    public String get() { return "Hello"; }
}
Box b = new StringBox(); // Warning: unchecked
Box<String> b2 = new StringBox(); // Safe
```

---

## 10. Overloading: Most Specific Method Chosen

```java
class Overload {
    void test(String s) { System.out.println("String"); }
    void test(Object o) { System.out.println("Object"); }
}
new Overload().test(null); // String
```
**Why:**
- The most specific applicable method is chosen.

---

## 11. Overloading vs Overriding: Parameter Types

```java
class Parent { void show(Number n) { System.out.println("Parent"); } }
class Child extends Parent { void show(Integer n) { System.out.println("Child"); } }
Parent p = new Child();
p.show(10); // Parent
```
**Why:**
- Overloading is resolved at compile time based on reference type and parameter types.

---

## 12. Final Methods Cannot Be Overridden

```java
class Parent { public final void ping() { System.out.println("Parent"); } }
class Child extends Parent { /* public void ping() { ... } // Error */ }
```

---

## 13. Class vs Interface Default Method Precedence

- If a class (or superclass) provides a method, it overrides any interface default method.

```java
interface I { default void ping() { System.out.println("I"); } }
class Super { public void ping() { System.out.println("Super"); } }
class Sub extends Super implements I {}
new Sub().ping(); // Super
```
**Why:**
- Class method always takes precedence over interface default method.

---

*These notes cover advanced interface and inheritance features in Java, with explanations, errors, and reasoning for each topic!*