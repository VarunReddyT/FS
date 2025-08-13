# Java Advanced Interface, Inheritance and Polymorphism Notes

---

## 1. Multiple Interface Default Methods: Conflict Resolution

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
public class Main1 {
    public static void main(String[] args) {
        new C().ping();
    }
}
// If you remove the ping() override in C, you'll get a compile error:
// class C inherits unrelated defaults for ping() from types A and B
```

---

## 2. Static Methods in Interfaces

```java
interface Util {
    static String id() { return "Util ID"; }
}
class X implements Util {
    static String id() { return "X ID"; }
}
public class Main2 {
    public static void main(String[] args) {
        Util u = new X();
        System.out.println(X.id());      // X ID
        System.out.println(Util.id());   // Util ID
        // System.out.println(u.id());   // Error: Cannot call static method on instance
    }
}
```

---

## 3. Private Methods in Interfaces

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
public class Main3 {
    public static void main(String[] args) {
        Impl impl = new Impl();
        System.out.println(impl.sanitize("  Hello World  ")); // hello world
    }
}
```

---

## 4. Method Overloading vs Overriding with Interfaces

```java
interface I { void m(Number n); }
class A { public void m(Integer i) { System.out.println("A"); } }
class B extends A implements I {
    @Override
    public void m(Number n) { System.out.println("B"); }
}
public class Main4 {
    public static void main(String[] args) {
        new B().m(10);         // A (overloading)
        ((I) new B()).m(10);   // B (overriding via interface reference)
    }
}
```

---

## 5. Covariant Return Types in Interfaces

```java
class Shape {}
class Circle extends Shape {}
interface Factory { Shape create(); }
class CircleFactory implements Factory {
    @Override
    public Circle create() { return new Circle(); }
}
public class Main5 {
    public static void main(String[] args) {
        Factory factory = new CircleFactory();
        Shape shape = factory.create();
        System.out.println(shape.getClass().getSimpleName()); // Circle
    }
}
```

---

## 6. Exception Compatibility in Overriding

```java
import java.io.IOException;
import java.sql.SQLException;
abstract class A { abstract void run() throws IOException; }
interface I { void run() throws SQLException; }
class C extends A implements I {
    @Override
    public void run() { System.out.println("C"); } // No checked exceptions allowed
}
public class Main6 {
    public static void main(String[] args) {
        new C().run();
    }
}
```

---

## 7. Sealed and Non-Sealed Interfaces

```java
sealed interface Animal permits Dog, Cat {}
non-sealed interface Dog extends Animal {}
interface Cat extends Animal {}
class Persian implements Dog {}
public class Main7 {
    public static void main(String[] args) {
        Dog dog = new Persian();
        System.out.println(dog.getClass().getSimpleName()); // Persian
    }
}
```

---

## 8. Static Methods Cannot Be Overridden

```java
class P { static void hello() { System.out.println("Hello from P"); } }
class Q { static void hello() { System.out.println("Hello from Q"); } }
public class Main8 {
    public static void main(String[] args) {
        P.hello(); // Hello from P
        Q.hello(); // Hello from Q
    }
}
```

---

## 9. Generics and Type Erasure

```java
interface Box<T> { T get(); }
class StringBox implements Box<String> {
    @Override
    public String get() { return "Hello"; }
}
public class Main9 {
    public static void main(String[] args) {
        Box b = new StringBox(); // Warning: unchecked
        Box<String> b2 = new StringBox(); // Safe
        System.out.println(b.get());
        System.out.println(b2.get());
    }
}
```

---

## 10. Overloading: Most Specific Method Chosen

```java
class Overload {
    void test(String s) { System.out.println("String"); }
    void test(Object o) { System.out.println("Object"); }
}
public class Main10 {
    public static void main(String[] args) {
        new Overload().test(null); // String
    }
}
```

---

## 11. Overloading vs Overriding: Parameter Types

```java
class Parent { void show(Number n) { System.out.println("Parent"); } }
class Child extends Parent { void show(Integer n) { System.out.println("Child"); } }
public class Main11 {
    public static void main(String[] args) {
        Parent p = new Child();
        p.show(10); // Parent
    }
}
```

---

## 12. Final Methods Cannot Be Overridden

```java
class Parent { public final void ping() { System.out.println("Parent"); } }
class Child extends Parent { /* public void ping() { ... } // Error */ }
public class Main12 {
    public static void main(String[] args) {
        new Child().ping(); // Error if uncommented
    }
}
```

---

## 13. Class vs Interface Default Method Precedence

```java
interface I { default void ping() { System.out.println("I"); } }
class Super { public void ping() { System.out.println("Super"); } }
class Sub extends Super implements I {}
public class Main13 {
    public static void main(String[] args) {
        new Sub().ping(); // Super
    }
}
```

---

## 14. Method Overloading: Widening vs Boxing

```java
class Demo {
    void a(long i) { System.out.println("long"); }
    void a(Integer i) { System.out.println("Integer"); }
}
public class Main14 {
    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.a(5); // long
    }
}
```

---

## 15. Overloading: Widening vs Boxing with Floating Point

```java
class Demo {
    void a(Long i) { System.out.println("Long"); }
    void a(double i) { System.out.println("double"); }
}
public class Main15 {
    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.a(5L); // double
    }
}
```

---

## 16. Interface Default Method Ambiguity

```java
interface T1 { default void m() { System.out.println("T1"); } }
interface T2 { void m(); }
class C implements T1, T2 {} // Error: class C inherits unrelated defaults for m() from types T1 and T2
// To fix:
class C2 implements T1, T2 {
    @Override
    public void m() { T1.super.m(); }
}
public class Main16 {
    public static void main(String[] args) {
        new C2().m(); // T1
    }
}
```

---

## 17. Abstract Class vs Interface Default Method

```java
interface I { default void m() { System.out.println("I"); } }
abstract class A { abstract void m(); }
class D extends A implements I {} // Error: D is not abstract and does not override abstract method m() in A
// To fix:
class D2 extends A implements I {
    @Override
    public void m() { I.super.m(); }
}
public class Main17 {
    public static void main(String[] args) {
        new D2().m(); // I
    }
}
```

---

## 18. Overloading with Null: Ambiguity

```java
public class Main18 {
    void g(Integer x) { System.out.println("Integer"); }
    void g(Long x) { System.out.println("Long"); }
    public static void main(String[] args) {
        // new Main18().g(null); // Error: reference to g is ambiguous
    }
}
```

---

## 19. Interface Fields and Hiding

```java
interface K { int X = 1; }
class Imp1 implements K { public int X = 2; }
public class Main19 {
    public static void main(String[] args) {
        K k = new Imp1();
        Imp1 imp1 = new Imp1();
        System.out.println(k.X + " " + imp1.X); // 1 2
    }
}
```

---

## 20-22. Overloading with Varargs and Widening

```java
public class Main20 {
    static void f(long s) { System.out.println("long"); }
    static void f(int... s) { System.out.println("varargs"); }
    static void f(Integer... s) { System.out.println("varargs Integer"); }
    public static void main(String[] args) {
        byte n = 5;
        f(n); // long
        f((short)n); // long
    }
}
```

---

## 23. Method References and Overloading

```java
import java.util.function.Function;
class S {
    String m(Object o) { return "O"; }
    String m(String s) { return "S"; }
}
public class Main23 {
    public static void main(String[] args) {
        Function<String, String> f = new S()::m;
        Function<Object, String> f2 = new S()::m;
        System.out.println(f.apply(null)); // S
        System.out.println(f2.apply(null)); // O
    }
}
```

---

## 24. Sealed and Non-Sealed Interfaces: Ambiguity

```java
sealed interface A permits B, C {}
non-sealed interface B extends A { default void hello() { System.out.println("Hello from B"); } }
non-sealed interface C extends A { default void hello() { System.out.println("Hello from C"); } }
class D implements B, C {
    @Override
    public void hello() { B.super.hello(); } // or C.super.hello();
}
public class Main24 {
    public static void main(String[] args) {
        new D().hello(); // Hello from B
    }
}
```

---

## 25. Overriding and Narrowing Visibility

```java
interface Box<T> { T get(); }
class Sbox implements Box<String> {
    // protected String get() { return "X"; } // Error: Cannot reduce visibility
    public String get() { return "X"; }
}
public class Main25 {
    public static void main(String[] args) {
        Box<String> b = new Sbox();
        System.out.println(b.get()); // X
    }
}
```

---

## 26. Static Initialization Order

```java
class J {
    static { System.out.println("Init"); }
    static final int A = 1;
    static final Integer B = 2;
}
public class Main26 {
    public static void main(String[] args) {
        System.out.println(J.A); // 1
        System.out.println(J.B); // 2
    }
}
```

---

## 27. Exception Overriding: Subclass Exception

```java
import java.io.FileNotFoundException;
import java.io.IOException;
class A { void read() throws IOException { System.out.println("A"); } }
class B extends A { void read() throws FileNotFoundException { System.out.println("B"); } }
public class Main27 {
    public static void main(String[] args) throws IOException {
        A a = new B();
        a.read(); // B
    }
}
```

---

## 28-29. Multi-Catch Variable is Final

```java
public class Main28 {
    public static void main(String[] args) {
        try {
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException | NullPointerException e) {
            // e = null; // Error: Variable in multi-catch cannot be assigned
            System.out.println("Caught: ");
        }
    }
}
```

---

## 30-32. AutoCloseable, try-with-resources, and Suppressed Exceptions

```java
class R implements AutoCloseable {
    @Override
    public void close() { throw new RuntimeException("close"); }
}
public class Main30 {
    public static void main(String[] args) {
        try (R r = new R()) {
            throw new RuntimeException("body");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage()); // body
            for (Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed: " + t.getMessage()); // close
            }
        }
    }
}
```

---

## 33. Static Initializer Exception

```java
public class Main33 {
    static { if (true) throw new RuntimeException("init"); }
    public static void main(String[] args) { System.out.println("main"); }
}
// Output: Exception in thread "main": init
// The main method is never reached if static initialization fails.
```

---

## 34. Lambda and Checked Exceptions

```java
import java.io.IOException;
import java.util.*;
public class Main34 {
    public static void main(String[] args) {
        List<String> list = List.of("a", "b");
        list.forEach(s -> {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println(s);
            }
        });
    }
}
```

---
