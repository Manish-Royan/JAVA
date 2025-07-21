# 🔖 Types and Vocabulary of Java Methods

## 📚 Overview
Java methods are more than syntax; they express intent. Classifying methods by role (**query**, **command**, **factory**, etc.) improves readability, testing, and design. Below are precise definitions, short example code, when to use each type, and practical notes for design and testing.

### 1. Query Methods
* **Definition**: Return information about object state without causing observable side effects.
* **Characteristics**: Pure or nearly pure, should not modify object state, frequently used in expressions and assertions.
    #### 📌 Example:
    ```java
    public class Account {
        private final int balance;
        public int getBalance() { return balance; }
    }
    ```
#### ◻ When to use: Expose state or computed values; implement equals/hashCode/getters.
↳ Query methods are your go-to tools whenever you need to ask an object for information without changing it. Use them to:

* **Expose internal state**
    - Return field values via getters (`getName()`, `getBalance()`).
* **Compute derived values**
    - Calculate on the fly (e.g., `getFullName()` concatenates first and last name; `getAge()` computes years since birth).

* **Support equality and hashing**
    - `equals(Object)` returns a boolean comparing significant fields
    - `hashCode()` returns an int based on those same fields to keep containers happy

#### 👉 Notes: Prefer immutability and idempotence; name with get/is/has.
#### ◻ Naming Conventions: get-, is-, has-
↳ Consistency in naming turns your methods into a self-documenting API:
* `getXxx()`
    * Standard for any non-boolean property.
    * Example: `getBalance()`, `getItems()`

* `isXxx()`
    * Preferred for boolean flags.
    * Example: `isActive()`, `isEmpty()`

* `hasXxx()`
    * Conveys possession or availability.
    * Example: `hasChildren()`, `hasErrors()`

#### 👉 Notes: Picking the right prefix helps automated tools (like JavaBeans introspectors) and colleagues immediately grasp purpose.

----

### 2. Command Methods
* **Definition**: Perform actions or mutate state; their value is the effect, not a return value.
* **Characteristics**: May modify fields, trigger I/O, or send events.
    #### 📌 Example:
    ```java
    public void withdraw(int amount) {
        if (amount > 0) balance -= amount;
    }
    ```
* **When to use**: Any operation that changes state or interacts with external systems.
#### 👉 Notes: Name with verbs (set, add, delete, send); keep side effects documented and minimal.

----

### 3. Factory Methods
* **Definition**: Create and return instances, often hiding constructor complexity or implementing caching/singleton logic.
* **Characteristics**: Static or instance methods that encapsulate object creation and configuration.

    #### 📌 Example: Static Factory with Caching
    ```java
    import java.util.Map;
    import java.util.concurrent.ConcurrentHashMap;
    
    public final class Color {
        private final int red;
        private final int green;
        private final int blue;
    
        // Cache to reuse Color instances
        private static final Map<String, Color> CACHE = new ConcurrentHashMap<>();
    
        // Private constructor hides instantiation details
        private Color(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }
    
        // Static factory method
        public static Color of(int red, int green, int blue) {
            String key = red + "-" + green + "-" + blue;
            // computeIfAbsent handles caching: returns existing or creates new
            return CACHE.computeIfAbsent(key, k -> new Color(red, green, blue));
        }
    
        @Override
        public String toString() {
            return "Color(" + red + ", " + green + ", " + blue + ")";
        }
    }
    
    // Usage:
    // Color c1 = Color.of(255, 0, 0);
    // Color c2 = Color.of(255, 0, 0);
    // c1 == c2  // true, same cached instance
    ```

    #### 📌 Example: Instance-Based Factory
    ```java
    public class ShapeFactory {
        private final String theme;

        // Constructor configures the factory
        public ShapeFactory(String theme) {
            this.theme = theme;
        }

        // Factory method for Circle
        public Shape createCircle(double radius) {
            // Hides the complexity of choosing a Circle subclass or setting defaults
            return new Circle(theme, radius);
        }

        // Factory method for Rectangle
        public Shape createRectangle(double width, double height) {
            return new Rectangle(theme, width, height);
        }
    }

    // Supporting classes
    interface Shape {
        void draw();
    }

    class Circle implements Shape {
        private final String theme;
        private final double radius;

        Circle(String theme, double radius) {
            this.theme = theme;
            this.radius = radius;
        }

        @Override
        public void draw() {
            System.out.println("Drawing " + theme + " circle of radius " + radius);
        }
    }

    class Rectangle implements Shape {
        private final String theme;
        private final double width;
        private final double height;

        Rectangle(String theme, double width, double height) {
            this.theme = theme;
            this.width = width;
            this.height = height;
        }

        @Override
        public void draw() {
            System.out.println("Drawing " + theme + " rectangle " + width + "x" + height);
        }
    }

    // Usage:
    // ShapeFactory factory = new ShapeFactory("Cartoon");
    // Shape circle = factory.createCircle(5.0);
    // circle.draw();
    ```

* **When to use**: Replace multiple constructors, implement dependency injection, control lifecycle.

#### 👉 Notes: Use descriptive names (of, from, create, newInstance); document ownership and mutability of returned objects.

----

### 4. Convenience and Helper Methods
* **Definition**: Small methods that simplify common tasks or combine multiple calls into one readable operation.

    #### ◻ What They Are
    ↳ Convenience and helper methods are lightweight, focused routines designed to make frequent tasks more readable and concise. Rather than forcing callers to orchestrate multiple low-level calls or repeat boilerplate logic, a single helper packs that sequence into a named method that clearly conveys the intent.

* **Characteristics**: Reduce duplication, improve expressiveness, often delegated to underlying methods.
    - They reduce code duplication by centralizing recurring sequences of operations.
    -They improve expressiveness: method names describe what you’re doing instead of how.
    - They rarely hold their own business logic; instead they delegate to core, authoritative methods.
    - They live in utility classes or alongside the classes they help, keeping related functionality together.
* **When to use**: Avoid repeating logic; provide clearer API surface.
```java
public class StringUtils {
    // Core method doing the stripping
    public static String stripPrefix(String input, String prefix) {
        if (input.startsWith(prefix)) {
            return input.substring(prefix.length());
        }
        return input;
    }

    // Convenience method combining strip and lowercase
    public static String stripAndLower(String input, String prefix) {
        // Delegate to the core stripPrefix method
        String stripped = stripPrefix(input, prefix);
        return stripped.toLowerCase();
    }
} //Here, stripAndLower saves callers from writing two lines each time and clearly states the intent.
```
#### 👉 Notes: Keep them thin; they should call other core methods rather than duplicate logic.

----

### 5. Assertion Methods
* **Definition**: Validate invariants, preconditions, postconditions, and throw exceptions when validations fail.
    #### ◻ What They Are
    ↳ Assertion methods validate that certain conditions are met before or after an operation. They form the backbone of defensive programming by making your code’s expectations explicit and fail-fast.
* **Characteristics**: Used for defensive programming and explicit contract checks.
    - They check inputs, outputs, or internal state and throw exceptions if a check fails.
    - They live alongside your business logic, often as small private or utility methods.
    - They improve debug-ability by pinpointing the exact contract that was violated.
    - They are more reliable than the Java `assert` keyword because they stay active in all runtime environments (unless someone explicitly catches them).

* **When to use**: At public API boundaries, inside constructors, before critical operations.

    ### ✓ Java assert Keyword vs. Explicit Exceptions 
    - The `assert` keyword can be disabled at runtime (with `-ea`/`-da` flags), making it unsuitable for enforcing essential validation.
    - Explicit exceptions like `IllegalArgumentException`, `NullPointerException`, or custom exceptions always run, ensuring that invalid conditions are never silently ignored.
    - Reserve `assert` for internal sanity checks during development; use exceptions for public-facing contracts and critical validations.
    #### 📌 Example:
    ```java
    public class User {
        private final String email;
    
        public User(String email) {
            // Precondition: email must not be null or empty
            requireNonEmpty(email, "email");
            // Postcondition: email contains '@'
            requireCondition(email.contains("@"), "email must contain @");
    
            this.email = email;
        }
    
        private void requireNonEmpty(String value, String name) {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException(name + " must not be null or empty");
            }
        }
    
        private void requireCondition(boolean condition, String message) {
            if (!condition) {
                throw new IllegalStateException("Assertion failed: " + message);
            }
        }
    }
    ```

#### 👉 Notes: Distinguish from Java assert keyword; prefer explicit exceptions for argument validation.

----

### 6. Primitive and Composed Methods
#### ◻ Primitive Methods (Atomic Methods): 
↳ Primitive (atomic) methods: Fundamental, small, directly implement behavior.
* **Definition**: These are the smallest building blocks in your code. Each one performs a single, well-defined task.
* **Purpose**: They directly implement behavior without relying on other methods.
    #### 📌 Example: A method that checks if a string is null or empty.
    ```java
    public boolean isEmpty(String input) {
    return input == null || input.isBlank();
    }
    ```

#### ◻ Composed Methods: 
↳ Composed methods: Built by calling primitives; coordinate multiple primitives to implement high-level behavior.
* **Definition**: These are higher-level methods that combine multiple primitive methods to perform a more complex task.
* **Purpose**: They orchestrate behavior by delegating work to primitives.
    #### 📌 Example: A method that validates a user’s input by calling several primitive checks.
    ```java
    public boolean isValidUser(String name, String email) {
    return isEmpty(name) && isValidEmail(email);
    }
    ```

#### ◻ Bonus Analogy: Cooking
* Primitive Methods = chopping vegetables, boiling water, measuring spices.
* Composed Methods = cooking a full dish by combining those steps.

#### ◻ Characteristics: Primitives are simple and tested individually; composed methods orchestrate them.
| Feature              | Primitive Methods                          | Composed Methods                              |
|----------------------|--------------------------------------------|-----------------------------------------------|
| Scope                | Very narrow, focused                       | Broader, coordinates multiple primitives      |
| Testability          | Easy to test in isolation                  | Tested via integration or workflow scenarios  |
| Reusability          | High—used in many places                   | Medium—specific to a feature or flow          |
| Complexity           | Low                                         | Moderate to high                              |
| Maintenance          | Easy—minimal logic                         | Easier if primitives are well-defined         |

#### ✅ When to Use Them: Decompose complex logic into testable units.
* **Primitive Methods**:
    * When implementing basic logic (e.g., string checks, math operations, null checks).
    * When you want reusable, testable units.
    * When documenting core behaviors.

* **Composed Methods**:
    * When building workflows or feature logic.
    * When you want readable, high-level operations.
    * When coordinating multiple steps (e.g., validation, transformation, persistence).

#### ◻ Best Practices
* **For Primitive Methods**:
    * Keep them minimal—one job, no side effects.
    * Name them clearly: `isEmpty`, `isValidEmail`, `calculateTax`.
    * Document them well—describe what they check or compute.
#### ◻ For Composed Methods:
    * Use them to express intent: `registerUser`, `processOrder`, `validateForm`.
    * Avoid duplicating logic—always delegate to primitives.
    * Keep them readable—don’t bury logic in nested calls.

#### 👉 Notes: Keep primitives minimal and well-documented; use composition to build features

----

