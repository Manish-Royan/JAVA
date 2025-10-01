# What are the Static Factory Methods in Java❓

➡️ A static factory method is a static method on a class that returns an instance of that class or of a related type instead of requiring callers to use the `new` keyword and a public constructor. The class itself controls how instances are created, whether new objects are made or existing objects are reused, and which concrete implementation is returned. Static factories are simply a named, centralized way to obtain objects.

➡️ A static factory method is a static method inside a class that creates and returns an instance of that class (or a subtype). Instead of calling `new MyClass(...)`, you call `MyClass.someFactory(...)`. The class hides its constructors and controls how instances are made, reused, or which concrete subtype to return.

## Q. What you get with static factories❓

- **Named creation**: method names explain intent (e.g., User.guest(), User.of(...)).  
- **Reuse / caching**: the method can return the same object every time (singleton or common instances).  
- **Subtype selection**: the method can return different classes that share the same API (e.g., IPv4 vs IPv6) while callers just see the common type.  
- **Encapsulated logic**: parsing, validation, I/O, caching live inside the factory, not with every caller.  

---

### 📌 Demonstrating: single class with named factories

```java
// File: UserExample.java
public final class UserExample {
  private final String name;
  private final int id;

  // Constructor is private so callers cannot use new UserExample(...)
  private UserExample(String name, int id) 
  {
    this.name = name;
    this.id = id;
  }

  // Static factory: named and clear about intent
  public static UserExample of(String name, int id) 
  {
    return new UserExample(name, id);
  }

  // Static factory: returns a common "guest" instance (cached)
  private static final UserExample GUEST = new UserExample("guest", 0);
  public static UserExample guest() {
    return GUEST; // same object returned each call
  }

  @Override
  public String toString() {
    return "UserExample{name='" + name + "', id=" + id + "}";
  }

  // Demo
  public static void main(String[] args) {
    UserExample a = UserExample.of("Manish", 42);
    UserExample b = UserExample.of("Manish", 42);
    UserExample g1 = UserExample.guest();
    UserExample g2 = UserExample.guest();

    System.out.println(a);           // distinct instance with same data
    System.out.println(a == b);      // false: of() creates new instances

    // guest singleton
    System.out.println(g1);          // UserExample{name='guest', id=0}
    System.out.println(g1 == g2);    // true: guest() returns the same cached instance so identity is equal.
  }
}
```

#### 💡 What this shows:
- The constructor is hidden (private). Callers must use static factories.
- `of(...)` creates new instances; `guest()` returns a cached single instance.
- Code reading `UserExample.guest()` is clearer than `new UserExample("guest", 0)`.
- `private UserExample(String name, int id)`: The constructor is private so external code cannot call `new UserExample(...)`. All creation is forced through the class’s static methods. This is the key mechanism that lets the class fully control how instances are made.
- `public static UserExample of(String name, int id)`: This is a static factory method. It is named to express intent: "create a UserExample from these values." Inside it simply calls the private constructor and returns a new instance every time it is invoked. Callers write `UserExample.of(...)`, not `new UserExample(...)`.
- `private static final UserExample GUEST = new UserExample("guest", 0);`: A single instance created once and stored in a private static field.
- `public static UserExample guest() { return GUEST; }`: Each call returns the same pre-created object (a singleton for the guest user). This saves allocations and ensures identity equality for the guest instance.


### Q. Why classes provide static factories instead of public constructors❓

- **Control over concrete type returned**  
  The factory can return different concrete subclasses depending on input. The caller only sees the declared return type and does not need to know which subclass was instantiated.

- **Caching and reuse**  
  The factory can return cached instances, singletons, pooled objects, or immutable shared instances to save memory and construction cost.

- **Named construction**  
  A factory method can have a descriptive name that documents the meaning of arguments and intent better than overloaded constructors with identical signatures.

- **Flexible argument interpretation**  
  Multiple factory methods can accept the same parameter types but interpret them differently, something constructors cannot do because of signature conflicts.

- **Encapsulation of complex creation logic**  
  Parsing, validation, network lookups, or selection logic are kept inside the factory rather than duplicated by every caller.

- **Security and lifecycle policies**  
  The class can enforce access control or return special instances when a security manager is present.

---

### 📴 Typical forms and trade-offs

- Typical signatures:
  - static MyClass of(Type a, Type b)
  - static MyClass from(String s)
  - static MyClass getInstance(...)
  - static MyClass valueOf(...)

- Advantages:
  - Better readability and discoverability.
  - Ability to return subclass/interface implementations.
  - Performance gains via caching or pooling.
  - Hides complex or error-prone creation steps.

- Disadvantages:
  - Subclassing becomes harder because constructors may be non-public.
  - Callers cannot rely on "new" to always produce distinct objects.
  - API discoverability can be worse if many static methods exist and names are inconsistent.

## Q. How this applies to `java.net.InetAddress`❓

- **Protected constructors**  
  `InetAddress` does not expose public constructors. That prevents callers from directly instantiating InetAddress or choosing between IPv4 and IPv6 implementations.

- **Static factory methods supplied**  
  Methods like `getByName`, `getAllByName`, `getByAddress`, and `getLocalHost` create and return InetAddress instances. Callers request an address through these methods rather than using `new`.

- **Why InetAddress uses factories**
  - **Subclass selection**: The factory decides whether to return an Inet4Address or Inet6Address based on the input string or byte array.
  - **Name resolution and I/O**: Creating an `InetAddress` may involve DNS lookups or reverse resolution, operations that can fail or be expensive; the factory can perform resolution and handle exceptions.
  - **Caching and policy**: The factory can and does participate in caching resolved names and respecting system security or TTL policies.
  - **Consistent error handling**: The factory centralizes `UnknownHostException` and other resolution logic so callers get a consistent API.

----

### 📌 Example with subtype selection (like InetAddress: IPv4 vs IPv6)

↳ This given below code demonstrates a **static factory** that hides concrete subclasses and returns the appropriate implementation (IPv4 or IPv6) based on input. The caller asks for an IpAddress via a single static method and never uses new Ipv4(...) or new Ipv6(...) directly.

```java
// File: IpAddressDemo.java
abstract class IpAddress {
  public abstract String asString(); //Declares an abstract method that every subclass must implement; it returns a human‑readable representation of the address.

  // Static factory decides which concrete class to create
  public static IpAddress fromBytes(byte[] bytes) {
    if (bytes == null) throw new IllegalArgumentException("bytes null");
    if (bytes.length == 4) return new Ipv4(bytes); // If the input length is 4, the factory creates and returns a new Ipv4 instance. The caller sees an IpAddress but receives an Ipv4 object 
    if (bytes.length == 16) return new Ipv6(bytes); // If the input length is 16, the factory creates and returns a new Ipv6 instance
    throw new IllegalArgumentException("invalid length");
  } //It returns an IpAddress reference but can actually return any concrete subclass
}

final class Ipv4 extends IpAddress // The class is final, so it cannot be subclassed further.
{
  private final byte[] addr; // hold the raw 4‑byte IPv4 address

  Ipv4(byte[] addr) //package‑private constructor because it has no access modifier.
  { this.addr = addr.clone(); } //Cloning is a defensive copy to prevent external code from mutating the internal state.

  @Override
  public String asString() {
    return (addr[0] & 0xFF) + "." + (addr[1] & 0xFF) + "." + (addr[2] & 0xFF) + "." + (addr[3] & 0xFF);
  }
}

final class Ipv6 extends IpAddress {
  private final byte[] addr;
  Ipv6(byte[] addr) { this.addr = addr.clone(); }

  @Override
  public String asString() //Implements the abstract asString method from IpAddress for IPv6.
   {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 16; i += 2) {
      int part = ((addr[i] & 0xFF) << 8) | (addr[i+1] & 0xFF);
      sb.append(Integer.toHexString(part));
      if (i < 14) sb.append(":");
    }
    return sb.toString();
  }
}

// Demo main
public class IpAddressDemo {
  public static void main(String[] args) {
    IpAddress v4 = IpAddress.fromBytes(new byte[]{127,0,0,1});
    IpAddress v6 = IpAddress.fromBytes(new byte[]{
      0x20,0x01, 0x0d, (byte)0xb8, 0,0,0,0, 0,0,0,0, 0,0,0,1
    });

    System.out.println("v4 class: " + v4.getClass().getSimpleName() + " -> " + v4.asString());
    System.out.println("v6 class: " + v6.getClass().getSimpleName() + " -> " + v6.asString());
    /* v4 class: Ipv4 -> 127.0.0.1
     * v6 class: Ipv6 -> 2001:d:b8:0:0:0:0:1 */
  }
}
```

#### 💡 What this shows:
- `abstract class IpAddress`: It cannot be instantiated directly and is intended to define a common API for concrete address types.
- `Ipv6(byte[] addr)`: This is a package‑private constructor because it has no access modifier. It can be called from other classes in the same package but not from outside the package.
- `addr.clone();`: 
    - creates a `new byte[]` whose elements are copied from the original array passed in. Because byte is a primitive type, clone copies the bytes themselves (not references), producing an independent array.
    - It prevents the caller from later changing the internal state by modifying the array they passed in.
    - Without cloning, external code could do:
        * byte[] a = {127,0,0,1};
        * IpAddress ip = IpAddress.fromBytes(a); // if constructor did this.addr = addr;
        * a[0] = 1; // would also change ip's stored bytes if no clone was made
    - **Cloning makes the Ipv4 object immutable with respect to external mutations of the original array**.

### Short concert demonstration for why clone matters:
```java
byte[] data = {127,0,0,1};
Ipv4 ip = new Ipv4(data);      // constructor clones inside
data[0] = 1;                   // original array changed
System.out.println(ip.asString()); // still prints 127.0.0.1 because ip kept a clone
```

#### 👉 This is the reason the constructor uses `addr.clone()`: to protect the internal state from outside modification and to help make the object behave immutably and safely.

----

### 🏗️ Concrete examples and behaviors

- Example usage:
  - InetAddress a = InetAddress.getByName("example.com");
  - InetAddress b = InetAddress.getByAddress(new byte[]{127,0,0,1});

- What happens inside the factory:
  - Parse input to determine address form.
  - If input is a name, perform DNS lookup to obtain one or more IPs.
  - Choose a concrete subclass (IPv4 or IPv6) and instantiate it.
  - Optionally return a cached instance or newly constructed object.
  - Throw or translate resolution errors consistently.

### 🖊️ Practical implications for API users

- You cannot subclass or instantiate `InetAddress` directly outside its package because constructors are protected.
- You rely on the factory names and signatures to create instances, so read the static methods to understand creation semantics.
- Factories may return the same instance for repeated requests; do not assume object identity equals address equality.
- Factories can do blocking I/O; prefer asynchronous or explicit control where latency matters.

---

### ☑️ Quick checklist to recognize a static factory design
- The class offers public static methods that return its type.
- Constructors are private or non-public.
- The static methods may return different concrete classes that implement or extend the declared return type.
- The static methods encapsulate creation logic such as parsing, caching, or resource pooling.