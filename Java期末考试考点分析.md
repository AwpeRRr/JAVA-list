# Java 期末考试考点分析

生成时间：2026-06-15  
分析范围：上传的 8 份课件、教师给出的考试范围、当前项目目录中的 Java 代码文件。

## 0. 总结结论

本次考试最值得投入的内容不是“大而全背诵”，而是按题型准备：

- 简答题 4-5 道：必有 1 道多线程、1 道 GUI；还要重点准备 Infinity/NaN、枚举、初始化顺序、异常/IO 概念。
- 读程题约 7 道：高概率来自初始化顺序、继承/多态、static/final、异常执行流程、IO 流链、线程启动与 join/sleep、值传递。
- 编程题 2 道：1 道类设计，1 道 IO + 异常处理；多线程和 GUI 只按简答准备，不按编程准备。

当前项目代码非常适合用来复习两类编程题：

- 类设计题：`上机题目/HappyFarmTest_ArrayList_4th`、`HappyFarmTest_ArrayList`、`HappyFarmTest`。
- IO + 异常题：`Class project1/File` 和 `HappyFarmTest_ArrayList_4th/Farm.java` 的存档读写。

## 1. 分析依据

### 1.1 上传课件

已抽取并检查 8 份 PDF，合计 536 页，所有页面均有可读文本：

| 文件 | 页数 | 主要内容 |
| --- | ---: | --- |
| `1_JAVA程序设计 (1).pdf` | 60 | Java 概述、平台无关、JVM、语言特征、多线程特征 |
| `2_面向对象程序设计概念.pdf` | 38 | 对象、类、抽象、封装、继承、多态等 OOP 基础 |
| `3_JAVA语言基础 (1).pdf` | 90 | 基本类型、表达式、流程控制、数组、特殊浮点值 |
| `4_Java面向对象特性.pdf` | 88 | 类、对象、构造方法、继承、super、对象初始化 |
| `5_Java高级语言特征.pdf` | 100 | static/final、静态初始化、重写规则、枚举、Wrapper |
| `6_异常处理.pdf` | 46 | 异常体系、try-catch-finally、throws/throw、自定义异常、断言 |
| `7_输入输出.pdf` | 56 | File、字节流、字符流、缓冲流、数据流、标准 IO、随机文件、序列化 |
| `9_线程.pdf` | 58 | 进程/线程、Thread/Runnable、调度、同步、锁、wait/notify、管道流 |

注意：已上传 PDF 中没有独立 GUI 课件正文，GUI 只在课程目录中出现；GUI 考点以下按教师考试范围补充。

### 1.2 项目代码

当前目录共检查到 87 个 Java 文件，其中与考试最相关的文件如下：

| 方向 | 重点文件 |
| --- | --- |
| 类设计、继承、多态 | `上机题目/HappyFarmTest_ArrayList_4th/*.java`、`Class project1/Construct.java`、`Class project1/Inheritance.java`、`Class project1/OOverriding.java` |
| 初始化顺序、static | `Class project1/TestStaticInit.java`、`Class project1/InheritStaticInit.java`、`Class project1/PolyConstruct.java`、`Class project1/StaticPoly.java` |
| IO + 异常 | `Class project1/File/FileCopy.java`、`DataIO.java`、`BufferedIO.java`、`StandardIO.java`、`SerializeDate.java`、`RandomAccessTest.java`、`上机题目/HappyFarmTest_ArrayList_4th/Farm.java` |
| 多线程 | `Class project1/Thread/CountDown.java`、`CountDown2.java`、`CountDown3.java`、`TestJoin.java`、`Reentrant.java`、`PipedStreamDemo.java` |
| 值传递、private 方法、重写陷阱 | `Class project1/Passtest1.java`、`Passtest2.java`、`PrivOverride.java`、`OOverriding.java` |

## 2. 简答题考点

### 2.1 多线程必考简答

必须能用自己的话说明以下内容。

1. 进程和线程的区别

- 进程是资源分配单位，拥有代码、数据、堆、文件、PCB 等资源。
- 线程是进程内的单个顺序执行流，是 CPU 调度执行的基本单位。
- 同一进程中的多个线程共享进程资源，如堆和代码；每个线程有自己的程序计数器、栈、寄存器状态。

2. Java 多线程程序的特点和优点

- Java 在语言和类库层面支持多线程，核心类是 `java.lang.Thread`，也可通过 `Runnable` 提供线程体。
- 优点：提高系统响应性和资源利用率；让等待 IO、等待用户输入、后台计算等任务并发执行；让程序结构更贴近真实并发任务。

3. 创建线程的两种方式

- 继承 `Thread`，重写 `run()`，创建子类对象后调用 `start()`。
- 实现 `Runnable`，把实现对象交给 `new Thread(runnable)`，再调用 `start()`。
- 考试答题要强调：直接调用 `run()` 只是普通方法调用，不会启动新线程；调用 `start()` 才进入可运行状态。

4. 多线程会带来的问题

- 执行顺序不确定：同一次程序多次运行，输出交错顺序可能不同。
- 共享数据竞争：多个线程同时读写共享变量，可能导致脏读、丢失更新、不一致状态。
- 死锁：两个或多个线程互相等待对方持有的锁。
- 可见性问题：一个线程修改共享变量后，另一个线程不一定立即看到。

5. 解决方法

- 对共享数据的访问统一放入临界区，用 `synchronized` 修饰方法或代码块。
- 把共享数据设为 `private`，不让外部绕过同步方法直接访问。
- 多资源加锁时使用固定的全局加锁顺序，释放锁按反序，降低死锁风险。
- 线程协作使用 `wait()`、`notify()`、`notifyAll()`，必须在同步块或同步方法中调用。

6. 线程锁和状态

- 对象锁：每个对象都有与 `synchronized` 相关的排他锁。
- 方法锁：`public synchronized void m()` 等价于对当前对象 `this` 加锁。
- 代码块锁：`synchronized(obj) { ... }` 对指定对象加锁，范围更精确。
- 类锁：`static synchronized` 或 `synchronized(类名.class)`，锁住类对象，影响该类静态同步方法。
- 可重入锁：同一线程已经持有某对象锁时，可以再次进入该对象的其他同步方法，例如 `Reentrant.java`。
- `wait pool`：调用 `wait()` 后释放对象锁并进入等待池。
- `lock pool`：被 `notify()` 唤醒后进入锁池，等待重新获得对象锁。

常见答题模板：

> 多线程的核心问题是多个执行流共享资源时顺序不确定。Java 通过 `Thread/Runnable` 创建线程，通过 `start()` 进入可运行状态。共享数据需要用 `synchronized` 建立临界区，保证同一时刻只有一个线程操作共享对象。线程协作可用 `wait/notify/notifyAll`，其中 `wait` 会释放对象锁，`notify` 只唤醒等待池中的线程但不立即交出锁。死锁可通过统一加锁顺序、减少锁嵌套和及时释放资源避免。

### 2.2 GUI 必考简答

已上传课件没有 GUI 正文，但考试范围明确要求 GUI 简答，按以下准备。

1. AWT 架构

- AWT 位于 `java.awt` 包，组件通常是重量级组件，依赖本地平台的 peer 实现。
- 典型层次：`Component` 是所有可显示组件的父类；`Container` 可以包含组件；`Window`、`Frame`、`Dialog` 是顶层窗口；`Panel` 常用于分组。
- AWT 程序由组件、容器、布局管理器、事件模型组成。

2. Swing 架构

- Swing 位于 `javax.swing` 包，多数是轻量级组件，建立在 AWT 事件模型之上。
- 常见组件：`JFrame`、`JPanel`、`JButton`、`JLabel`、`JTextField`、`JMenuBar`。
- Swing 顶层容器通常有 Root Pane 结构：`JRootPane` 包含 `glassPane`、`layeredPane`、`contentPane` 和可选菜单栏。
- Swing 常见设计思想是模型-视图-控制相关分离，很多组件有 Model，例如 `ButtonModel`、`ListModel`、`TableModel`。
- Swing 更新界面应在事件分派线程 EDT 中进行。

3. GUI 事件处理模型

- 事件源：发生事件的组件，如按钮、文本框、窗口。
- 事件对象：保存事件信息，如 `ActionEvent`、`MouseEvent`、`KeyEvent`。
- 监听器：处理事件的对象，如 `ActionListener`、`MouseListener`、`WindowListener`。
- 基本步骤：创建组件 -> 定义监听器 -> 注册监听器 `addXxxListener(listener)` -> 在回调方法中处理事件。

4. 监听器定义方式

- 单独定义类实现监听器接口。
- 当前类实现监听器接口。
- 内部类或匿名内部类。
- Java 8 以后可对函数式监听器使用 lambda，例如 `button.addActionListener(e -> doSomething())`。
- 多方法监听器可用 Adapter 类简化，例如 `WindowAdapter`、`MouseAdapter`。

5. Layout 布局

- Layout 是容器对子组件位置和大小的管理策略。
- `FlowLayout`：按加入顺序从左到右排列，适合简单面板。
- `BorderLayout`：分 East/West/North/South/Center，适合主窗口。
- `GridLayout`：规则网格，每格同等大小。
- `CardLayout`：多页面切换。
- `GridBagLayout`：最灵活但复杂，适合复杂表单。
- 实际题目中可答：复杂界面通常用多个 `JPanel` 嵌套，每个面板使用不同布局。

### 2.3 Infinity、NaN、IEEE 754 和 Golden Rule

课件第 3 章明确出现特殊浮点值，考试范围又单独点名，需重点准备。

1. IEEE 754 中的基本定义

- `+Infinity/-Infinity`：指数位全为 1，尾数/有效数字部分为 0，符号位决定正负。
- `NaN`：指数位全为 1，尾数/有效数字部分非 0，表示 Not a Number。
- Java 中 `double` 和 `float` 遵循 IEEE 754，常量包括 `Double.POSITIVE_INFINITY`、`Double.NEGATIVE_INFINITY`、`Double.NaN`、`Float.NaN` 等。

2. Java 中的典型结果

```java
0.0 == -0.0        // true
1.0 / 0.0          // Infinity
1.0 / -0.0         // -Infinity
0.0 / 0.0          // NaN
Double.NaN == Double.NaN  // false
Double.NaN != Double.NaN  // true
```

注意：整数除以 0 会抛出 `ArithmeticException`，浮点数除以 0 才产生 Infinity 或 NaN。

3. Golden Rule

可按下面这句话背：

> NaN 是无序的。任何包含 NaN 的普通大小比较都为 false，包括 `==`；唯一特殊的是 `!=` 为 true。判断 NaN 应使用 `Double.isNaN(x)` 或 `Float.isNaN(x)`。

这正好解释课件中的题：

- `1.0 < Double.NaN` -> `false`
- `1.0 > Double.NaN` -> `false`
- `1.0 == Double.NaN` -> `false`
- `1.0 != Double.NaN` -> `true`
- `Double.NaN == Double.NaN` -> `false`

### 2.4 枚举类型

课件第 5 章有完整枚举内容，属于自学必考点。

1. 定义方式

```java
public enum Week {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}
```

2. 带属性和构造方法的枚举

```java
enum Coin {
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

    private final int value;

    Coin(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
```

3. 必背规则

- 枚举用 `enum` 定义，本质上也是一种类。
- 枚举常量是该枚举类型的 `static final` 实例。
- 枚举隐式继承 `java.lang.Enum`，不能再继承其他类，但可以实现接口。
- 构造方法只能是 `private` 或省略访问修饰符，不能由外部 `new`。
- 常用方法：`values()`、`name()`、`ordinal()`。
- `switch` 可以直接使用枚举常量作为分支。

### 2.5 程序初始化顺序

这是读程题和简答题都很容易考的内容。

1. 类加载和静态初始化

- 类第一次主动使用时加载。
- 静态成员变量按出现顺序初始化。
- 静态初始化块 `static { ... }` 按出现顺序执行。
- 每个类的静态初始化只执行一次。

2. 对象创建顺序

普通对象创建时：

1. 分配对象空间，所有实例字段先取默认值。
2. 执行父类构造链，从 `Object` 到直接父类。
3. 执行本类实例字段显式初始化和实例初始化块。
4. 执行本类构造方法体。
5. 返回对象引用。

3. 子类对象初始化

- 子类构造方法第一行必须是 `super(...)` 或 `this(...)`；没有写时编译器默认插入 `super()`。
- 如果父类没有无参构造方法，子类必须显式调用父类有参构造。
- 父类构造期间调用可被重写的方法时，会动态绑定到子类方法，但此时子类字段还没有完成显式初始化，这是读程题陷阱。

## 3. 读程题重点

### 3.1 静态初始化

文件：`Class project1/TestStaticInit.java`

关键代码：

```java
class StaticDemo {
    static int i;
    static {
        i = 5;
        System.out.println("Static code:i=" + i++);
    }
}
```

已编译运行验证输出：

```text
Static code:i=5
Main code:i=6
```

考点：访问 `StaticDemo.i` 触发类加载；静态块先把 `i` 设为 5，再后缀自增，所以主方法看到 6。

### 3.2 继承静态初始化

文件：`Class project1/InheritStaticInit.java`

已编译运行验证输出：

```text
static block of T1:0
static block of T2:2
T1():1
T2():2
```

考点：

- 创建 `T2` 先初始化父类 `T1`。
- 父类静态块中访问 `T2.s2` 时，`T2` 还处于准备阶段，静态字段默认值为 0。
- 然后执行 `T2` 静态字段显式初始化，`s2 = 2`。
- 最后执行父类构造，再执行子类构造。

### 3.3 构造方法中的动态绑定陷阱

文件：`Class project1/PolyConstruct.java`

已编译运行验证输出：

```text
Glyph() before draw()
RG.draw().radius = 0
Glyph() after draw()
RG.RoundGlyph() , radius = 5
```

考点：

- 父类构造方法调用 `draw()` 时发生动态绑定，实际调用子类 `RoundGlyph.draw()`。
- 但此时子类字段 `radius` 还没执行 `private int radius = 169`，仍是默认值 0。
- 所以先输出 `radius = 0`，不是 169，也不是构造参数 5。

### 3.4 static 方法没有多态

文件：`Class project1/StaticPoly.java`

已编译运行验证输出：

```text
Base staticGet()
Derived dynamicGet()
```

考点：

- 静态方法按引用类型绑定，`StaticSuper sup = new StaticSub()` 调用 `sup.staticGet()` 看左边类型，输出父类版本。
- 普通实例方法动态绑定，`sup.dynamicGet()` 看实际对象，输出子类版本。

### 3.5 private 方法不是重写

文件：`Class project1/PrivOverride.java`

已编译运行验证输出：

```text
private f()
```

考点：

- 父类 `private void f()` 对子类不可见，子类的 `public void f()` 不是重写，只是新方法。
- 在父类内部 `po.f()` 编译期绑定到父类 private 方法。

### 3.6 字段不会多态

文件：`Class project1/OOverriding.java`

已编译运行验证输出：

```text
2
101
101
```

考点：

- 子类 `private int i = 2` 和父类 `private int i = 100` 是两个不同字段。
- 子类没有重写 `increase()` 和 `getI()`，调用的是父类方法，操作父类的 `i`。
- 字段访问不参与多态，方法才参与动态绑定。

### 3.7 继承构造顺序

文件：`Class project1/Construct.java`

已编译运行验证输出：

```text
Person
Student 666
Undergraduate
```

考点：构造顺序从父到子，`Undergraduate` 显式 `super(i)` 调用 `Student(int)`，而 `Student` 构造前默认调用 `Person()`。

### 3.8 线程启动和 join

文件：`Class project1/Thread/TestJoin.java`

已编译运行验证输出：

```text
Tojoin!!
Joiner!!
```

考点：

- `t1.start()` 启动 `ToJoin` 线程。
- `Joiner.run()` 中调用 `tojoin.join()`，当前 Joiner 线程等待 `t1` 执行结束。
- 所以 `Tojoin!!` 一定先于 `Joiner!!`。

### 3.9 线程输出交错不稳定

文件：`Class project1/Thread/CountDown.java`

一次运行输出：

```text
waitinig for running...
#2->3
#1->3
#1->2
#2->2
#1->1
#2->1
#1->run
#2->run
```

考点：

- 主线程启动两个子线程后继续执行，所以 `waitinig for running...` 往往先输出。
- 两个子线程之间的输出顺序不保证，每次运行可能交错不同。
- 能保证的是每个线程内部 `3 -> 2 -> 1 -> run` 的相对顺序。

### 3.10 管道流和线程间数据传输

文件：`Class project1/Thread/PipedStreamDemo.java`

考点：

- `PipedInputStream` 和 `PipedOutputStream` 通过 `connect()` 连接。
- 一个线程用 `DataOutputStream.writeInt/writeUTF` 写入，另一个线程用 `DataInputStream.readInt/readUTF` 读取。
- `try-with-resources` 会自动关闭流。
- 输出顺序受线程调度影响，但读线程必须先读到写线程写入的数据才能继续。

### 3.11 IO 读写和异常处理

重点文件：

- `Class project1/File/FileCopy.java`
- `Class project1/File/BufferedIO.java`
- `Class project1/File/DataIO.java`
- `Class project1/File/StandardIO.java`

读程题常问：

- `read()` 返回 `-1` 表示文件结束。
- `FileInputStream/FileOutputStream` 是字节流，适合复制字节。
- `FileReader/FileWriter` 是字符流，适合文本。
- `BufferedReader.readLine()` 一次读一行，不包含换行符。
- `BufferedWriter` 或 `BufferedOutputStream` 需要关闭或 `flush()` 才能确保缓冲区写出。
- `DataOutputStream` 写入的类型和顺序，必须用 `DataInputStream` 按同样顺序读取。
- `try-with-resources` 中声明的流会自动关闭。
- 多个 `catch` 时，子类异常要写在父类异常前面。

## 4. 编程题 1：类设计题

最可能形式：给一个小型业务场景，要求定义父类/子类、字段、构造方法、方法重写、集合管理和简单菜单。

可参考项目：`上机题目/HappyFarmTest_ArrayList_4th`

### 4.1 必须掌握的设计点

1. 父类或抽象类

`FarmObject.java` 是标准模板：

```java
public abstract class FarmObject {
    private int id;
    private String name;
    private String type;

    public FarmObject(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public abstract void care();

    @Override
    public String toString() {
        return "编号: " + id + "\t类型: " + type + "\t名称: " + name;
    }
}
```

考试迁移写法：

- 如果题目要求“共同属性 + 不同行为”，优先定义抽象父类。
- 字段用 `private`，通过构造方法初始化，通过 getter 读取。
- 子类继承父类并重写抽象方法。
- 输出对象信息时重写 `toString()`。

2. 子类设计

类似 `Wheat`、`Corn`、`Chicken`、`Cow`：

```java
public class Wheat extends FarmObject {
    public Wheat(int id, String name) {
        super(id, name, "小麦");
    }

    @Override
    public void care() {
        System.out.println("给小麦浇水。");
    }
}
```

3. 管理类设计

`Farm.java` 使用 `ArrayList<ArrayList<FarmObject>>` 管理多行对象。

应掌握：

- `addObject`
- `removeObject`
- `getObject`
- `searchByName`
- `printAllObjects`
- `clearFarm`
- `hasId`
- 下标合法性判断

4. 输入合法性

`FarmTest.java` 中 `readInt()` 是典型写法：

```java
private static int readInt(Scanner scanner, String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            System.out.println("请输入有效的整数！");
        }
    }
}
```

### 4.2 类设计题答题骨架

遇到类设计题可以按这个顺序写：

1. 定义父类：字段、构造方法、getter、`toString()`。
2. 如果有共同接口但行为不同，定义抽象方法。
3. 定义 2-4 个子类，构造方法中 `super(...)`，重写方法。
4. 定义管理类：用数组或 `ArrayList` 保存对象。
5. 写增删查改方法，注意下标越界、重复编号、空名称。
6. 写 `main` 或测试类，用 `Scanner` 做菜单。

## 5. 编程题 2：IO + 异常处理题

最可能形式：读写一个文本文件或数据文件，要求处理文件不存在、格式错误、数字转换错误，并保证流关闭。

可参考：

- `Class project1/File/FileCopy.java`
- `Class project1/File/BufferedIO.java`
- `Class project1/File/DataIO.java`
- `上机题目/HappyFarmTest_ArrayList_4th/Farm.java`

### 5.1 必须掌握的 IO 选择

| 需求 | 推荐类 |
| --- | --- |
| 复制任意文件字节 | `FileInputStream` + `FileOutputStream` |
| 逐行读写文本 | `BufferedReader` + `BufferedWriter` 或 `PrintWriter` |
| 读写基本类型 | `DataInputStream` + `DataOutputStream` |
| 控制台输入 | `Scanner` 或 `BufferedReader(new InputStreamReader(System.in))` |
| 检查文件是否存在 | `File.exists()` 或 `Files.exists(Path)` |
| 自动关闭资源 | `try-with-resources` |

### 5.2 IO + 异常题高分模板

```java
public static void copyText(String src, String dest) {
    try (
        BufferedReader reader = Files.newBufferedReader(Paths.get(src), StandardCharsets.UTF_8);
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(dest), StandardCharsets.UTF_8)
    ) {
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("文件读写失败: " + e.getMessage());
    }
}
```

如果题目要求“遇到格式错误要报告第几行”，参考 `Farm.java`：

```java
private int parseInteger(String value, int lineNumber, String fieldName) throws IOException {
    try {
        return Integer.parseInt(value);
    } catch (NumberFormatException e) {
        throw new IOException("第 " + lineNumber + " 行的" + fieldName + "不是有效整数。");
    }
}
```

### 5.3 IO + 异常题评分点

- 是否正确选择字节流/字符流/数据流。
- 是否关闭流，最好使用 `try-with-resources`。
- 是否区分 `FileNotFoundException`、`IOException`、`NumberFormatException`。
- 多个 `catch` 是否从子类到父类排列。
- 是否处理文件不存在、空文件、格式错误、重复数据、越界数据。
- 是否用 `throws` 把异常交给调用者，或用 `try-catch` 在本方法内处理。
- 是否避免吞异常：不能空 `catch`，至少输出或转换为更明确的异常。

## 6. 章节级具体考点清单

### 6.1 Java 概述

- Java 平台无关：源代码编译为字节码，由 JVM 执行。
- JVM、JRE、JDK 区别。
- Java 语言特征：面向对象、健壮性、安全性、自动垃圾回收、多线程、平台无关。
- `main` 方法格式：`public static void main(String[] args)`。

### 6.2 面向对象程序设计概念

- 类和对象的关系：类是模板，对象是实例。
- 封装：字段私有化，方法提供受控访问。
- 继承：`extends`，is-a 关系，复用父类成员。
- 多态：父类引用指向子类对象，运行时根据实际对象调用重写方法。
- 抽象：提取共同属性和行为。
- 消息：对象之间通过方法调用交互。

### 6.3 Java 语言基础

- 基本类型：`boolean`、`char`、整数、浮点。
- 引用类型：类、接口、数组、枚举。
- 字符和 Unicode，常见转义字符。
- 整型常量进制：十进制、八进制、十六进制。
- 浮点常量默认 `double`，`float` 后缀 `F/f`。
- `String` 不可变，`StringBuilder` 可变，`StringBuffer` 线程安全。
- 运算符优先级、类型转换、短路与/或。
- 数组创建、初始化、遍历，下标从 0 开始。

### 6.4 Java 面向对象特性

- 成员变量默认值和局部变量必须显式初始化的区别。
- 构造方法无返回类型，方法名与类名相同。
- 默认构造方法只有在没有定义任何构造方法时才自动生成。
- `this` 表示当前对象，`super` 表示父类部分。
- `super(...)` 必须在子类构造方法第一行。
- 方法重载：同名不同参数列表，编译期确定。
- 方法重写：子类重新定义父类实例方法，运行期动态绑定。
- 访问控制：`private`、default、`protected`、`public`。
- 包和 import。

### 6.5 Java 高级语言特征

- `static` 字段属于类，所有对象共享。
- `static` 方法不能直接访问实例成员。
- 静态方法不具有多态性。
- 静态初始化块类加载时执行一次。
- `final` 修饰变量、方法、类的含义。
- blank final 必须在构造方法中完成初始化。
- `abstract` 类不能实例化，抽象方法必须由非抽象子类实现。
- 接口定义常量和抽象行为，类用 `implements` 实现接口。
- 枚举类型定义、构造方法、`values/name/ordinal`。
- Wrapper 类和自动装箱/拆箱。

### 6.6 异常处理

- `Throwable` 是异常体系根类。
- `Error` 通常不可恢复，不要求捕获。
- `Exception` 是程序可处理异常。
- RuntimeException 及其子类是 unchecked exception。
- 非 RuntimeException 的异常是 checked exception，编译器强制处理。
- `try-catch-finally` 执行流程。
- `finally` 常用于释放文件、网络等资源。
- `throw` 是抛出一个异常对象。
- `throws` 是方法声明可能抛出的异常类型。
- 自定义异常通常继承 `Exception`，而不是 `RuntimeException`。
- 重写方法抛出的 checked exception 不能比父类方法更宽。
- `assert` 用于调试期检查不应发生的状态。

### 6.7 输入输出

- `File` 只表示文件/目录路径和元信息，不负责读写文件内容。
- 流是有方向的有序字节序列。
- 输入流读，输出流写。
- 节点流直接连接数据源；过滤流包装已有流增强功能。
- 字节流父类：`InputStream`、`OutputStream`。
- 字符流父类：`Reader`、`Writer`。
- 缓冲流减少实际 IO 次数，提高效率。
- `flush()` 强制写出缓冲区。
- `DataInputStream/DataOutputStream` 读写基本类型，读写顺序必须一致。
- `ObjectInputStream/ObjectOutputStream` 可序列化对象。
- `RandomAccessFile` 可随机定位读写。

### 6.8 线程

- `Thread.currentThread()` 获取当前线程。
- `isAlive()` 判断线程是否已启动且未结束。
- `sleep()` 使当前线程睡眠，不释放对象锁。
- `join()` 让当前线程等待目标线程结束。
- `yield()` 让出 CPU，但不保证一定切换。
- `stop/suspend/resume` 已废弃，可能导致死锁或状态不一致。
- `synchronized` 建立临界区。
- `wait()` 释放锁并进入等待池。
- `notify()` 唤醒一个等待线程，`notifyAll()` 唤醒全部等待线程。
- 线程状态：new、Runnable、Running、Blocked、Dead。
- 管道流可在线程间直接传输数据。

## 7. 最后复习优先级

### S 级：必须会

- 多线程简答：进程/线程区别、优点、问题、锁、wait/notify、死锁。
- GUI 简答：AWT/Swing 架构、事件模型、监听器、Layout。
- IO + 异常编程：`try-with-resources`、`BufferedReader/Writer`、`IOException`、格式校验。
- 类设计编程：抽象父类、继承、重写、集合管理、`Scanner` 输入校验。
- 初始化顺序读程：静态块、父子构造、构造中动态绑定。

### A 级：高概率读程

- static 方法没有多态。
- private 方法不是重写。
- 字段不多态。
- `join()` 保证先后顺序，线程间普通输出不保证顺序。
- `read()` 的 `-1`、`readLine()` 的 `null`。
- 多 catch 顺序和 finally 执行。
- `NaN` 比较规则。

### B 级：补充记忆

- 枚举 `values/name/ordinal`。
- Wrapper 和装箱拆箱。
- `final` 变量、方法、类。
- 断言 `assert`。
- 序列化、随机访问文件。

## 8. 外部补充来源

以下只用于补充教师点名的 Infinity/NaN 自学内容：

- Oracle Java `Double` 文档：`https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html`
- IEEE 754 概览：`https://en.wikipedia.org/wiki/IEEE_754`
- NaN 编码概览：`https://en.wikipedia.org/wiki/NaN`

