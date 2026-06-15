# Java 期末读程序题模拟 10 题

生成时间：2026-06-15  
用途：模拟期末可能出现的高频读程序题。每题包含题目、参考答案和解析。重点覆盖初始化顺序、继承与多态、`static`、`private`、异常流程、IO 流和线程。

## 使用方式

- 先遮住参考答案，自己写输出。
- 如果输出不唯一，要写出“不唯一”的原因和哪些顺序是确定的。
- 考试中读程序题最容易丢分的点：静态初始化默认值、构造方法中的动态绑定、`static` 方法没有多态、`private` 方法不是重写、`finally` 一定执行、线程调度不保证固定顺序。

## 1. 静态初始化与后缀自增

题目：写出程序输出。

```java
class StaticDemo {
    static int i;

    static {
        i = 5;
        System.out.println("Static code:i=" + i++);
    }
}

public class TestStaticInit {
    public static void main(String[] args) {
        System.out.println("Main code:i=" + StaticDemo.i);
    }
}
```

参考答案：

```text
Static code:i=5
Main code:i=6
```

解析：

访问 `StaticDemo.i` 触发 `StaticDemo` 类初始化。静态块中先执行 `i = 5`，再输出 `i++` 的旧值 5，输出后 `i` 变成 6。因此主方法中再输出 `StaticDemo.i` 时结果为 6。

## 2. 父子类静态初始化顺序

题目：写出程序输出。

```java
class T1 {
    static int s1 = 1;

    static {
        System.out.println("static block of T1:" + T2.s2);
    }

    T1() {
        System.out.println("T1():" + s1);
    }
}

class T2 extends T1 {
    static int s2 = 2;

    static {
        System.out.println("static block of T2:" + s2);
    }

    T2() {
        System.out.println("T2():" + s2);
    }
}

public class InheritStaticInit {
    public static void main(String[] args) {
        new T2();
    }
}
```

参考答案：

```text
static block of T1:0
static block of T2:2
T1():1
T2():2
```

解析：

创建子类对象时先初始化父类。父类 `T1` 的静态块访问 `T2.s2` 时，`T2` 的静态字段还没有完成显式初始化，只是默认值 0，所以第一行输出 0。然后子类 `T2.s2 = 2`，执行子类静态块。静态初始化结束后再执行构造方法，构造顺序是父类构造方法先于子类构造方法。

## 3. 构造方法中调用被重写方法

题目：写出程序输出。

```java
class Glyph {
    void draw() {
        System.out.println("Glyph.draw()");
    }

    Glyph() {
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 169;

    RoundGlyph(int r) {
        radius = r;
        System.out.println("RoundGlyph(), radius = " + radius);
    }

    void draw() {
        System.out.println("RoundGlyph.draw(), radius = " + radius);
    }
}

public class PolyConstruct {
    public static void main(String[] args) {
        new RoundGlyph(5);
    }
}
```

参考答案：

```text
Glyph() before draw()
RoundGlyph.draw(), radius = 0
Glyph() after draw()
RoundGlyph(), radius = 5
```

解析：

创建 `RoundGlyph` 时先执行父类 `Glyph` 构造方法。父类构造方法调用 `draw()` 时发生动态绑定，实际调用子类 `RoundGlyph.draw()`。但是此时子类字段 `radius` 还没有执行显式初始化 `= 169`，更没有执行子类构造方法中的 `radius = r`，所以还是默认值 0。

## 4. `static` 方法没有运行时多态

题目：写出程序输出。

```java
class StaticSuper {
    public static String staticGet() {
        return "Base staticGet()";
    }

    public String dynamicGet() {
        return "Base dynamicGet()";
    }
}

class StaticSub extends StaticSuper {
    public static String staticGet() {
        return "Derived staticGet()";
    }

    public String dynamicGet() {
        return "Derived dynamicGet()";
    }
}

public class StaticPoly {
    public static void main(String[] args) {
        StaticSuper sup = new StaticSub();
        System.out.println(sup.staticGet());
        System.out.println(sup.dynamicGet());
    }
}
```

参考答案：

```text
Base staticGet()
Derived dynamicGet()
```

解析：

静态方法属于类，不属于对象，不参与运行时多态。`sup.staticGet()` 按引用变量的编译时类型 `StaticSuper` 绑定，所以输出父类版本。普通实例方法 `dynamicGet()` 参与动态绑定，按实际对象 `StaticSub` 调用，所以输出子类版本。

## 5. `private` 方法不是重写

题目：写出程序输出。

```java
public class PrivateOverrideTest {
    private void f() {
        System.out.println("private f()");
    }

    public static void main(String[] args) {
        PrivateOverrideTest obj = new Derived();
        obj.f();
    }
}

class Derived extends PrivateOverrideTest {
    public void f() {
        System.out.println("public f()");
    }
}
```

参考答案：

```text
private f()
```

解析：

父类中的 `private void f()` 对子类不可见，因此子类的 `public void f()` 不是重写，只是定义了一个新方法。`obj.f()` 这句代码位于父类内部，可以访问父类私有方法，编译期就绑定到父类的 `private f()`，所以输出 `private f()`。

## 6. 字段不具有多态性

题目：写出程序输出。

```java
class Base {
    private int i = 100;

    public void increase() {
        this.i++;
    }

    public int getI() {
        return this.i;
    }
}

public class FieldPolyTest extends Base {
    private int i = 2;

    public static void main(String[] args) {
        FieldPolyTest a = new FieldPolyTest();
        a.increase();
        System.out.println(a.i);
        System.out.println(a.getI());

        Base b = new FieldPolyTest();
        b.increase();
        System.out.println(b.getI());
    }
}
```

参考答案：

```text
2
101
101
```

解析：

子类的 `private int i = 2` 和父类的 `private int i = 100` 是两个不同字段。子类没有重写 `increase()` 和 `getI()`，所以调用的是父类方法，操作的是父类字段 `Base.i`。第一次 `a.increase()` 后父类字段从 100 变 101，但 `a.i` 仍是子类字段 2。第二个对象 `b` 是新的 `FieldPolyTest`，其父类字段也从 100 变为 101。

## 7. `try-catch-finally` 与循环

题目：下面程序会正常结束吗？如果不会，写出第一次从开始运行到数组越界后重新回到开头之前的输出。

```java
public class TryFinallyLoop {
    public static void main(String[] args) {
        int i = 0;
        String[] greetings = {"Hello World!", "Hello!", "HELLO!"};

        while (i < 4) {
            try {
                System.out.println(greetings[i]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Re-setting Index Value");
                i = -1;
            } finally {
                System.out.println("This is always printed");
            }
            i++;
        }
    }
}
```

参考答案：

程序不会正常结束，会无限循环。第一次循环到数组越界后重新回到开头之前的输出是：

```text
Hello World!
This is always printed
Hello!
This is always printed
HELLO!
This is always printed
Re-setting Index Value
This is always printed
```

解析：

当 `i` 为 0、1、2 时正常输出数组元素，并执行 `finally`。当 `i` 为 3 时，`greetings[3]` 数组越界，进入 `catch`，输出重置信息并把 `i` 设为 `-1`。随后 `finally` 仍然执行，循环末尾 `i++` 把 `i` 变回 0，因此循环重新开始，永远不会结束。

## 8. 数据流读写顺序

题目：写出程序输出。

```java
import java.io.*;

public class DataIOQuestion {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(buffer);

        out.writeBoolean(false);
        out.writeChar('c');
        out.writeByte(1);
        out.writeShort(2);
        out.writeInt(3);
        out.writeUTF("Java");
        out.close();

        DataInputStream in = new DataInputStream(
                new ByteArrayInputStream(buffer.toByteArray()));

        System.out.println(in.readBoolean());
        System.out.println(in.readChar());
        System.out.println(in.readByte());
        System.out.println(in.readShort());
        System.out.println(in.readInt());
        System.out.println(in.readUTF());
    }
}
```

参考答案：

```text
false
c
1
2
3
Java
```

解析：

`DataOutputStream` 按基本数据类型写入二进制数据，`DataInputStream` 必须按相同类型和相同顺序读取。这里写入顺序是 `boolean -> char -> byte -> short -> int -> UTF`，读取顺序完全一致，所以能正确恢复原值。

## 9. `join()` 保证线程先后关系

题目：写出程序输出。

```java
class ToJoin extends Thread {
    public ToJoin(String name) {
        super(name);
    }

    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("ToJoin!!");
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
}

class Joiner implements Runnable {
    private ToJoin toJoin;

    public Joiner(ToJoin t) {
        this.toJoin = t;
    }

    public void run() {
        try {
            this.toJoin.join();
            System.out.println("Joiner!!");
        } catch (InterruptedException e) {
            System.out.println("join interrupted");
        }
    }
}

public class TestJoinQuestion {
    public static void main(String[] args) {
        ToJoin t1 = new ToJoin("t1");
        t1.start();
        new Thread(new Joiner(t1)).start();
    }
}
```

参考答案：

```text
ToJoin!!
Joiner!!
```

解析：

`Joiner` 线程中调用 `toJoin.join()`，表示当前线程必须等待 `t1` 线程结束后才能继续执行。`t1` 的 `run()` 先睡眠 1 秒，然后输出 `ToJoin!!` 并结束。之后 `Joiner` 才能继续输出 `Joiner!!`，所以这两行的先后顺序是确定的。

## 10. 多线程输出是否唯一

题目：下面程序的完整输出顺序是否唯一？写出可以确定的输出规律。

```java
public class CountDownQuestion implements Runnable {
    private static int idCount = 1;
    private final int threadId = idCount++;
    private int counter = 3;

    public void run() {
        while (counter >= 0) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("#" + threadId
                    + (counter > 0 ? "->" + counter : "->run"));
            counter--;
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new CountDownQuestion());
        Thread t2 = new Thread(new CountDownQuestion());
        t1.start();
        t2.start();
        System.out.println("waiting for running...");
    }
}
```

参考答案：

完整输出顺序不唯一。一次可能的输出是：

```text
waiting for running...
#1->3
#2->3
#1->2
#2->2
#1->1
#2->1
#1->run
#2->run
```

也可能出现 `#2` 的某些行先于 `#1` 的对应行。可以确定的是：

- 主线程调用 `start()` 后会继续向下执行，因此 `waiting for running...` 通常会先输出，但严格说线程调度不保证它一定早于所有子线程输出。
- 每个线程内部的顺序一定是 `3 -> 2 -> 1 -> run`。
- 两个线程之间的输出交错顺序不确定，因为线程调度由 JVM 和操作系统决定。

解析：

`start()` 只是让线程进入可运行状态，并不保证马上运行。两个子线程各有自己的 `counter`，所以每个线程内部递减顺序固定；但两个线程谁先获得 CPU 不确定，因此跨线程输出顺序不能唯一确定。考试中遇到类似题，不能强行写死一种完整输出，应说明“不唯一”以及哪些局部顺序是确定的。

