# Chapter 1 - Introduction to Java

Java is high-level class based, object-oriented programming language that is designed to have as few implementation dependencies as possible.

It was designed to be platform-independent, meaning it is intended to let application developers **Write Once and Run Anywhere** (**WORA**), meaning that compiled Java code can run on all platforms that support Java without the need for recompilation. Java was developed by *James Gosling* at *Sun Microsystems Inc*. in **May 1995** and later acquired by *Oracle Corporation* and is widely used for developing applications for desktop, web, and mobile devices.

Java is known for its *simplicity*, *robustness*, and *security* *features*, making it a popular choice for **enterprise-level applications**. Java applications are compiled to ***byte code*** (**.class** file), that can run on any **Java Virtual Machine**. Java makes writing, compiling, and debugging programming easy. It helps to create reusable code and modular programs.


## Q. What is high-level programming language?
→ A high level programming language is a programming language that is designed to be easy to read, write and understand by humans. These languages are typically more abstract and closer to natural human languages than low-level language which are closer to machine code and hardware architecture.

## Q. What is high-level programming language?
→ Object-oriented programming (OOP) is a programming paradigm based on the concept of "**object**", which are self-contained units that contains both ***data*** (***attributes*** or ***properties***) and ***methods*** (***function*** or ***procedures***) to operate on that data.


##  ▸ Key principle of OOP:
The key principles of Object-Oriented Programming (OOP) are **encapsulation**, **inheritance**, **polymorphism**, and **abstraction**; these are often referred to as the "***four pillars***" of OOP, allowing objects to interact with each other by managing data and behaviors within a structured way. 

![Key principle of OOP](https://github.com/user-attachments/assets/5a8395c0-1f80-4832-a355-787388efad85)


### » Explanation of each principle:

i. **Encapsulation**: Hides internal details of an object, only exposing necessary information through methods, promoting data protection and modularity. 

ii.**Inheritance**: Enables new classes to inherit properties and behaviors from existing classes, promoting code reuse and creating hierarchical relationships. 

iii.**Polymorphism**: Allows objects of different classes to respond to the same method call in different ways, enhancing code flexibility. 

iv.**Abstraction**: Focuses on essential features of an object, hiding unnecessary complexities, providing a high-level view of functionality. 


## Why Use Java?
* Java works on different platforms (Windows, Mac, Linux, Raspberry Pi, etc.)
* It is one of the most popular programming languages in the world
* It has a large demand in the current job market
* It is easy to learn and simple to use
* It is open-source and free
* It is secure, fast and powerful
* It has huge community support (tens of millions of developers)
* Java is an object oriented language which gives a clear structure to programs and allows code to be reused, lowering development costs
* As Java is close to *C++* and *C#*, it makes it easy for programmers to switch to Java or vice versa


## Java Quickstart
In Java, every application begins with a **class** name, and that ***class* must match the filename**.

Let's create our first Java file, called **Main.java**, which can be done in any text editor (like Notepad).

The file should contain a "*Hello World*" message, which is written with the following code:

```Java
public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World");
  }
}
```

```
Output: 
Hello
```


##  ▸ Application
According to Sun Microsystems, 3 billion devices run Java. There are various devices where Java is currently used. Some of them are as follows:

* Desktop Applications such as acrobat reader, media player, antivirus, etc.
* Web Applications such as irctc.co.in, javatpoint.com, etc.
* Enterprise Applications such as banking applications.
* Mobile
* Embedded System
* Smart Card
* Robotics
* Games, etc.


## ▸ Types of Java Applications
» There are the following 4-types of applications that can be created using Java programming:

1) **Standalone Application**: Standalone applications are also known as desktop applications or window-based applications. These are traditional software that we need to install on every machine. Examples of standalone application are Media player, antivirus, etc. AWT and Swing are used in Java for creating standalone applications.

2) **Web Application**: An application that runs on the server side and creates a dynamic page is called a web application. Currently, Servlet, JSP, Struts, Spring, Hibernate, JSF, etc. technologies are used for creating web applications in Java.

3) **Enterprise Application**:An application that is distributed in nature, such as banking applications, etc. is called an enterprise application. It has advantages like high-level security, load balancing, and clustering. In Java, EJB is used for creating enterprise applications.

4) **Mobile Application**: An application which is created for mobile devices is called a mobile application. Currently, Android and Java ME are used for creating mobile applications.


## ▸ Java Platforms / Editions
» There are 4 platforms or editions of Java:

1) **Java SE (Java Standard Edition)**: It is a Java programming platform. It includes Java programming APIs such as java.lang, java.io, java.net, java.util, java.sql, java.math etc. It includes core topics like OOPs, String, Regex, Exception, Inner classes, Multithreading, I/O Stream, Networking, AWT, Swing, Reflection, Collection, etc.

2) **Java EE (Java Enterprise Edition)**: It is an enterprise platform that is mainly used to develop web and enterprise applications. It is built on top of the Java SE platform. It includes topics like Servlet, JSP, Web Services, EJB, JPA, etc.

3) **Java ME (Java Micro Edition)**: It is a micro platform that is dedicated to mobile applications.

4) **JavaFX**: It is used to develop rich internet applications. It uses a lightweight user interface API.
