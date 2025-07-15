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

