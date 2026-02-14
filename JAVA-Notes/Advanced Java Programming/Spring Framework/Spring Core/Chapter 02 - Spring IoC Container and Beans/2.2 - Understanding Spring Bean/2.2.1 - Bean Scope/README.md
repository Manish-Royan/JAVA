# 🔭 Spring Bean Scope

## 1. What Is Bean Scope?
- **Scope** determines the **lifecycle and visibility** of a bean within the Spring Container.
- It answers: 
> *When is a bean created? How many instances exist? Who can access it?*

> “When I ask the container for this bean, do I get the same instance or a new one—and how long does it live?”

💭 Think of it like this: 
* When you define a bean, you're giving the Spring Container a blueprint. 
* The **scope** is the instruction that tells the container how to use that blueprint. ***Should it build just one object for everyone to share***, or ***should it build a fresh new object for everyone who asks***❓

### ⁉️Bean scope answers three fundamental questions:

- How many instances of this bean should exist?
- When is a new instance created?
- When (if ever) is the bean destroyed?

---

### \# Spring currently supports **six built-in scopes** (as of 2025/2026 releases — no major changes since ~Spring 4/5 era regarding the standard set).

| Scope          | # Instances                          | Created when?                          | Destroyed when?                        | Available in                  | Most common use case                              | Thread-safe concern? |
|----------------|--------------------------------------|----------------------------------------|----------------------------------------|-------------------------------|---------------------------------------------------|----------------------|
| **singleton**  | 1 per IoC container                  | First request (lazy) or startup (eager)| When container closes                  | All contexts                  | Stateless services, repositories, utilities       | Must be thread-safe  |
| **prototype**  | New instance **every time** requested| Every time `getBean()` or injection    | **Never** managed by container         | All contexts                  | Stateful beans, heavy objects you want fresh      | Usually safe         |
| **request**    | 1 per HTTP request                   | Start of each HTTP request             | End of HTTP request                    | Web-aware only                | Request-scoped data (e.g. current user context)   | Safe per request     |
| **session**    | 1 per HTTP session                   | First request in session               | HTTP session timeout / invalidation    | Web-aware only                | Shopping cart, user preferences during login     | Safe per session     |
| **application**| 1 per ServletContext                 | First access                           | ServletContext destroyed               | Web-aware only                | Global application-level caches, config           | Must be thread-safe  |
| **websocket**  | 1 per WebSocket session              | WebSocket connection established       | WebSocket connection closed            | WebSocket-aware only          | Per-client WebSocket state                        | Safe per connection  |

### 📝Note:
➡️ In pure Spring Core (no Spring Boot), the most important scopes are:
- `singleton` (default)
- `prototype`

➡️ (Other scopes like `request`, `session`, etc. exist, but they require a web-aware context, so we’ll keep them aside.)