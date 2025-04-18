# What Are Interned Strings❓
#### ➡️ Interning is Java’s way of optimizing memory by storing only one copy of each distinct string value in a special area called the String Pool (part of the heap).


### 🤔Why❓
➡️ Because strings are used a lot in Java programs. If you create "Hello" a thousand times, Java says: “*Why waste memory? Let’s store one copy and reuse it!*”

## 🧐 How It Works❓
```JAVA
public class InternDemo {
    public static void main(String[] args) {
        String s1 = "Java";              // goes into String Pool
        String s2 = new String("Java");  // new object in heap
        String s3 = s2.intern();         // refers to the pool version

        System.out.println(s1 == s2); // false – different memory locations
        System.out.println(s1 == s3); // true – both point to the pool
    }
}
```

### 💡 Explanation:
- "*Java*" is a string literal, so it’s automatically interned.
- `new String("Java")` creates a new object in the heap.
- `intern()` checks the pool:
    - If "*Java*" is already there, it returns the pooled reference.
    - If not, it adds it to the pool and returns that reference.

### 🤔Why Use Interning❓
- ✅ **Memory Efficiency**: Avoids duplicate string objects.
- ✅ **Faster Comparisons**: You can use `==` safely on interned strings.
- ⚠️ **Caution**: Don’t overuse `intern()` with dynamic strings (e.g., user input) in large-scale apps—it can bloat the pool.