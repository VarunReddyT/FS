# Java Constructor Chaining, Static & Instance Initializers: Explained

This note covers constructor chaining, static and instance initializer blocks, and their execution order in Java. It includes code examples, explanations, common errors, and best practices.

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
public class Chaining {
    public static void main(String[] args) {
        Base obj1 = new Derived(10); // Compilation error if super(x) is not called
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
public class Chaining {
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
public class Chaining {
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
public class Chaining {
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
public class Test {
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

Try to predict the output for the following code:
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
public class Test {
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
Button btn = new Button();
btn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked!");
    }
});
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
public class Chaining {
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

#### Output Explanation
- All static blocks execute first (Base, then Derived).
- Instance initializer blocks execute before constructors.
- The anonymous class block executes after the Derived constructor.

#### Why Use Anonymous Classes?
- To quickly override or extend behavior for a single use without creating a named class.
- Useful for callbacks, event handling, or customizing library classes on the fly.

#### Common Errors
- Anonymous classes cannot have explicit constructors, but can use instance initializer blocks `{ ... }` for setup.
- Cannot declare static initializers or static members (except static final constants) inside anonymous classes.

---

### Additional Example: Runnable
```java
Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running in a thread!");
    }
};
new Thread(r).start();
```

---

*Anonymous classes are a powerful feature for concise, one-off customizations in Java!*
