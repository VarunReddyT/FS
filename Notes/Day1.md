# Java Constructor Chaining, Static & Instance Initializers, Anonymous Classes, Method Overriding, Nested Classes, Overloading

---

## 1. Constructor Chaining Basics

### Example 1: Missing Default Constructor
```java
class Base {
    // No default constructor
    public Base(int x) {
        System.out.println("Base class constructor called with value: " + x);
    }
}
class Derived extends Base {
    public Derived(int x) {
        // super(x); // ERROR: Will not compile if not called, as there is no default constructor in Base.
        System.out.println("Derived class constructor called with value: " + x);
    }
}
public class Main1 {
    public static void main(String[] args) {
        // Base obj1 = new Derived(10); // Compilation error if super(x) is not called
    }
}
```
**Error:**
- If the `Base` class does not have a default constructor, the first statement in the `Derived` constructor must be a call to `super(...)` with appropriate arguments.
- Otherwise, Java tries to call `super()` by default, which does not exist, causing a compilation error.

### Example 2: Correct Chaining with super(x)
```java
class Base {
    public Base(int x) {
        System.out.println("Base class constructor called with value: " + x);
    }
}
class Derived extends Base {
    public Derived(int x) {
        super(x); // OK: Calls Base(int x)
        System.out.println("Derived class constructor called with value: " + x);
    }
}
public class Main2 {
    public static void main(String[] args) {
        Base obj1 = new Derived(10); // Output: Base... then Derived...
    }
}
```

### Example 3: Default Constructor in Base
```java
class Base {
    public Base() {
        System.out.println("Base class constructor called");
    }
}
class Derived extends Base {
    public Derived(int x) {
        System.out.println("Derived class constructor called with value: " + x);
    }
}
public class Main3 {
    public static void main(String[] args) {
        Base obj1 = new Derived(10); // Output: Base... then Derived...
    }
}
```
---

## 2. Constructor Chaining with this() and super()

### Example 4: Chaining in Same Class
```java
class Base {
    public Base() {
        System.out.println("Base class constructor called");
    }
    public Base(int x) {
        this(); // Calls Base()
        System.out.println("Base class constructor called with value: " + x);
    }
}
class Derived extends Base {
    public Derived(int x) {
        super(x);
        System.out.println("Derived class constructor called with value: " + x);
    }
}
public class Main4 {
    public static void main(String[] args) {
        Base obj1 = new Derived(10);
    }
}
```

### Example 5: Chaining in Derived Class
```java
class Derived extends Base {
    public Derived(int x) {
        super(x);
        System.out.println("Derived class constructor called with value: " + x);
    }
    public Derived(int x, int y) {
        this(x); // Calls Derived(int x)
        System.out.println("Derived class constructor called with values: " + x + ", " + y);
    }
}
public class Main5 {
    public static void main(String[] args) {
        Derived d = new Derived(10, 20);
    }
}
```

---

## 3. Static and Instance Initializer Blocks

### Example 6: Static Initializer
```java
class Base {
    static {
        System.out.println("Base class static block executed");
    }
    public Base() {
        System.out.println("Base class constructor called");
    }
}
public class Main6 {
    public static void main(String[] args) {
        Base b = new Base();
    }
}
```
- Static blocks run once when the class is loaded.

### Example 7: Instance Initializer
```java
class Base {
    { // Instance initializer
        System.out.println("Base class instance initializer block executed");
    }
    public Base() {
        System.out.println("Base class constructor called");
    }
}
public class Main7 {
    public static void main(String[] args) {
        Base b = new Base();
    }
}
```
- Instance blocks run every time an object is created, before the constructor.

---

## 4. Execution Order: Static, Instance, Constructors

### Example 8: Full Order
```java
class Base {
    static { System.out.println("Base static"); }
    { System.out.println("Base instance"); }
    public Base() { System.out.println("Base constructor"); }
}
class Derived extends Base {
    static { System.out.println("Derived static"); }
    { System.out.println("Derived instance"); }
    public Derived() { System.out.println("Derived constructor"); }
}
public class Main8 {
    public static void main(String[] args) {
        Base obj = new Derived();
    }
}
```
**Output:**
```
Base static
Derived static
Base instance
Base constructor
Derived instance
Derived constructor
```

---

## 5. Static Variable Initialization Order

### Example 9: Static Variable Initialization
```java
class Base {
    static int a = 10;
    static int b;
    static {
        System.out.println("Static block: a=" + a + ", b=" + b);
        b = 20;
    }
    static {
        System.out.println("Static block 2: b=" + b);
    }
}
public class Main9 {
    public static void main(String[] args) {
        System.out.println(Base.a);
        System.out.println(Base.b);
    }
}
```
- Static variables are initialized in the order they appear.
- Static blocks can access static variables, but variables declared after the block are not yet initialized.

---

## 6. Common Errors and Gotchas

- If a base class does not have a default constructor, every derived class constructor must explicitly call a base constructor with arguments.
- Only one call to `super()` or `this()` is allowed and must be the first statement in a constructor.
- Static blocks run only once per classloader, even if multiple objects are created.
- Instance blocks run every time an object is created, before the constructor.
- Static variables must be initialized before use in static blocks.
- Final static variables can be initialized in static blocks.

---

## 7. Additional Example: Multiple Constructors and Initializers

```java
class Example {
    static { System.out.println("Static block"); }
    { System.out.println("Instance block"); }
    public Example() { System.out.println("Default constructor"); }
    public Example(int x) { this(); System.out.println("Constructor with x: " + x); }
}
public class Main10 {
    public static void main(String[] args) {
        Example e1 = new Example();
        Example e2 = new Example(5);
    }
}
```
**Output:**
```
Static block
Instance block
Default constructor
Instance block
Default constructor
Constructor with x: 5
```

---

## 8. Why and What: When to Use Each

- **Constructor chaining**: To avoid code duplication and ensure proper initialization order.
- **Static blocks**: For static initialization logic, e.g., loading drivers, initializing static resources.
- **Instance blocks**: For common code shared by all constructors, before constructor logic.

---

## 9. Summary Table

| Feature                | When Runs                | How Often         | Use Case                        |
|------------------------|--------------------------|-------------------|----------------------------------|
| Static block           | Class load               | Once per class    | Static resource setup            |
| Instance block         | Object creation          | Every object      | Common init for all constructors |
| Constructor            | Object creation          | Every object      | Custom object setup              |
| Constructor chaining   | Constructor call         | As needed         | Avoid code duplication           |

---

## 10. Practice: Predict the Output

```java
class A {
    static { System.out.println("A static"); }
    { System.out.println("A instance"); }
    public A() { System.out.println("A constructor"); }
}
class B extends A {
    static { System.out.println("B static"); }
    { System.out.println("B instance"); }
    public B() { System.out.println("B constructor"); }
}
public class Main11 {
    public static void main(String[] args) {
        A obj = new B();
    }
}
```

---

## 11) Anonymous Classes in Java

Anonymous classes in Java are used for creating a one-time, unnamed subclass of a class or implementing an interface on the fly. They are typically used when you need to override methods or provide specific behavior for a short-lived object, often as an argument to a method or for event handling, without formally declaring a new class.

### Common Use Cases
- **Event listeners** (e.g., in GUI programming)
- **Runnable or Callable** implementations for threads
- **Customizing behavior** of objects for a single use

### Example: Event Listener
```java
// This code requires a GUI framework like Swing or JavaFX to run.
// Button btn = new Button();
// btn.addActionListener(new ActionListener() {
//     @Override
//     public void actionPerformed(ActionEvent e) {
//         System.out.println("Button clicked!");
//     }
// });
```

### Example: Anonymous Class Extending a Class
```java
class Base {
    static int baseStaticVar = 10;
    static {
        System.out.println("Base class static block executed");
    }
    {   
        System.out.println(baseStaticVar2); // Will print as static variables are already initialized
        System.out.println("Base class instance initializer block executed");
    }
    static final int baseStaticVar2 = 20;
    public Base() {
        System.out.println("Base class constructor called");
    }
    public Base(int x) {
        this(); // Calls the default constructor
        System.out.println("Base class constructor called with value: " + x);
    }
}
class Derived extends Base {
    static {
        System.out.println("Derived class static block executed");
    }
    {
        System.out.println("Derived class instance initializer block executed");
    }
    public Derived(int x) {
        super(x);
        System.out.println("Derived class constructor called with value: " + x);
    }
    public Derived(int x, int y) {
        this(x);
        System.out.println("Derived class constructor called with values: " + x + ", " + y);
    }
}
public class Main12 {
    public static void main(String[] args) {
        // Anonymous class extending Derived for extra initialization
        Base obj1 = new Derived(10) {
            {
                System.out.println("Anonymous class extending Derived created");
            }
        };
    }
}
```

### Additional Example: Runnable
```java
public class Main13 {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running in a thread!");
            }
        };
        new Thread(r).start();
    }
}
```

---

## 12. Access Modifiers and Packages

```java
public class Main14 {
    // public - accessible from anywhere
    // private - accessible only within the same class
    // protected - accessible within the same package and subclasses
    // default (no modifier) - accessible within the same package and not subclasses
    public static void main(String[] args) {
        // Packages equivalent to namespaces in Java
        // They help organize classes and avoid naming conflicts
    }
}
```

---

## 13. Covariant Return Types

```java
class Api {
    Number value() { return 42; }
}
class Impl extends Api {
    @Override
    Integer value() { return 7; } // Integer is a subclass of Number
}
public class Main15 {
    public static void main(String[] args) {
        Api api = new Impl();
        System.out.println(api.value()); // 7
    }
}
```

---

## 14. Narrowing Visibility (Not Allowed)

```java
class Api {
    protected Number value() { return 42; }
}
class Impl extends Api {
    // @Override
    // private Integer value() { return 7; } // ERROR: Cannot reduce visibility
    public Integer value() { return 7; }
}
public class Main16 {
    public static void main(String[] args) {
        Api api = new Impl();
        System.out.println(api.value()); // 7
    }
}
```

---

## 15. Protected Methods and Package Access

```java
// pkg1
// package pkg1;
class Base {
    protected void hook() { System.out.println("Base hook"); }
}
// pkg2
// package pkg2;
// import pkg1.Base;
class Derived extends Base {
    void test(Derived otherDerived, Base baseRef) {
        this.hook(); // OK
        otherDerived.hook(); // OK
        // baseRef.hook(); // ERROR: Not accessible if baseRef is not a Derived
    }
}
public class Main17 {
    public static void main(String[] args) {
        Derived d = new Derived();
        d.test(d, d);
    }
}
```

---

## 16. Method Overriding and Access

```java
class A {
    public void f() { System.out.println("A.f"); }
    public void call() { f(); }
}
class B extends A {
    public void f() { System.out.println("B.f"); }
}
public class Main18 {
    public static void main(String[] args) {
        A x = new B();
        x.call(); // Prints B.f
    }
}
```

---

## 17. Overriding with Different Return Types

```java
class A {
    public void f() { System.out.println("A.f"); }
    public void call() { f(); }
}
class B extends A {
    public void f() { System.out.println("B.f"); }
    // public int call() { f(); return 0; } // Allowed if return type is compatible
}
public class Main19 {
    public static void main(String[] args) {
        B b = new B();
        b.call();
    }
}
```

---

## 18. Overriding and Method Resolution

```java
class A {
    public void f() { System.out.println("A.f"); }
    public void call() { f(); }
}
class B extends A {
    public void f() { System.out.println("B.f"); }
    public void call() { f(); }
}
public class Main20 {
    public static void main(String[] args) {
        A x = new B();
        x.call(); // Prints B.f
    }
}
```

---

## 19. Package-Private and Private Constructors

```java
// lib
// package lib;
class Helper { public Helper(){} }
// pkg2
// package pkg2;
// import lib.Helper;
public class Main21 {
    public static void main(String[] args) {
        // new Helper(); // ERROR: Not accessible if Helper is package-private and in a different package
    }
}
```

```java
// package lib;
public class Helper2 { private Helper2(){} }
// ...
public class Main22 {
    public static void main(String[] args) {
        // new Helper2(); // ERROR: Constructor is private
    }
}
```

---

## 20. Inner and Nested Classes

```java
public class Outer {
    private int secret = 99;
    class Inner {
        private int innerSecret = 7;
        void show() {
            System.out.println("Outer class secret: " + secret);
            System.out.println("Inner class secret: " + innerSecret);
        }
    }
    static class Nested {
        void show() {
            System.out.println("Outer class secret: " + new Outer().secret);
        }
    }
    void probe() {
        Inner inner = new Inner();
        System.out.println(inner.innerSecret); // Accessible
    }
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.probe();
        new Nested().show();
        Inner inner = outer.new Inner();
        inner.show();
    }
}
```

---

## 21. Method Overloading and Resolution

```java
public class Main23 {
    static void f(long x) { System.out.println("long"); }
    static void f(Integer x) { System.out.println("Integer"); }
    static void f(int... x) { System.out.println("int..."); }
    public static void main(String[] args) {
        f(1); // int -> long (widening)
        f((short)1); // short -> long (widening)
        f(Integer.valueOf(1)); // Integer
        f((byte)1); // byte -> long (widening)
        f(new int[]{1}); // int[] -> int... (varargs)
    }
}
```
**Output:**
```
long
long
Integer
long
int...
```

---

## 22. Java’s Overload Resolution Priority

```java
public class Main24 {
    static void f(long x) { System.out.println("long"); }
    static void f(Integer x) { System.out.println("Integer"); }
    static void f(int... x) { System.out.println("int..."); }
    public static void main(String[] args) {
        f(1); // int → long (widening)
        f((short)1); // short → long (widening)
        f(Integer.valueOf(1)); // Integer
        f((byte)1); // byte → long (widening)
        f(new int[]{1}); // int[] → int... (varargs)
    }
}
```
**Output:**
```
long
long
Integer
long
int...
```

---
