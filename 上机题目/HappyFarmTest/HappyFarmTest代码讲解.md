# HappyFarmTest 项目代码讲解稿

老师您好，我来讲解一下我写的 `HappyFarmTest` 项目。这个项目是一个简单的控制台版“开心农场管理系统”，主要练习了 Java 面向对象中的抽象类、继承、方法重写、多态、数组存储以及 `instanceof` 类型判断。

## 一、项目整体功能

这个系统可以在控制台中管理农场里的对象，比如小麦、玉米、鸡和牛。用户运行程序后，会看到一个菜单，可以完成下面这些操作：

1. 创建农场对象；
2. 根据名称查找对象；
3. 根据类型输出对象；
4. 输出某个编号之前的对象；
5. 照料指定对象；
6. 判断指定对象的真实类型；
7. 收获或移除对象；
8. 输出所有对象；
9. 退出系统。

从结构上看，项目中一共有 6 个 Java 文件：

| 文件名 | 作用 |
| --- | --- |
| `FarmObject.java` | 抽象父类，定义所有农场对象共有的属性和行为 |
| `Wheat.java` | 小麦类，继承 `FarmObject` |
| `Corn.java` | 玉米类，继承 `FarmObject` |
| `Chicken.java` | 鸡类，继承 `FarmObject` |
| `Cow.java` | 牛类，继承 `FarmObject` |
| `FarmTest.java` | 主程序类，负责菜单交互和对象管理 |

## 二、父类 FarmObject 的设计

老师，这个项目的核心父类是 `FarmObject`：

```java
public abstract class FarmObject
```

我把它设计成抽象类，是因为“农场对象”本身是一个比较泛化的概念，它不应该直接被创建。真正能创建出来的是小麦、玉米、鸡、牛这些具体对象。

这个类里有三个普通属性：

```java
private int id;
private String name;
private String type;
```

其中：

- `id` 表示编号；
- `name` 表示用户输入的名称；
- `type` 表示对象类型，比如“农作物-小麦”或者“动物-牛”。

另外还有一个静态变量：

```java
private static int nextId = 1;
```

这个变量是所有对象共享的，用来实现编号自增。每创建一个新的农场对象，构造方法里都会执行：

```java
this.id = nextId++;
```

这样第一个对象编号是 1，第二个对象编号是 2，后面依次递增。即使删除对象，新的对象编号也不会重复。

`FarmObject` 还定义了一个抽象方法：

```java
public abstract void care();
```

这个方法表示“照料农场对象”。但是不同对象的照料方式不一样，比如小麦要浇水除草，牛要喂草挤奶，所以父类只规定必须有这个方法，具体内容交给子类去重写。

最后，`toString()` 方法用于统一输出对象信息：

```java
return "编号: " + id + "\t名称: " + name + "\t类型: " + type;
```

这样在主程序里输出对象时，就不需要重复拼接编号、名称和类型了。

## 三、四个子类的实现

项目里的四个具体子类分别是 `Wheat`、`Corn`、`Chicken` 和 `Cow`。它们都继承自 `FarmObject`。

### 1. Wheat 小麦类

`Wheat` 的构造方法中调用了父类构造方法：

```java
super(name, "农作物-小麦");
```

这里把用户输入的名称传给父类，同时把类型固定为“农作物-小麦”。

它重写的 `care()` 方法是：

```java
System.out.println("正在为小麦 [" + getName() + "] 浇水和除草...");
```

也就是说，小麦的照料行为是浇水和除草。

### 2. Corn 玉米类

`Corn` 类和 `Wheat` 类类似，只是类型变成了“农作物-玉米”：

```java
super(name, "农作物-玉米");
```

它的照料方式是施肥和松土。

### 3. Chicken 鸡类

`Chicken` 的类型是：

```java
super(name, "动物-鸡");
```

它的 `care()` 方法表示给鸡喂饲料和打扫鸡舍。

### 4. Cow 牛类

`Cow` 的类型是：

```java
super(name, "动物-牛");
```

它的照料方式是喂牧草和挤牛奶。

这四个子类的共同点是：都复用了父类的编号、名称、类型属性，又分别实现了自己的照料行为。这正好体现了继承和方法重写。

## 四、主程序 FarmTest 的整体流程

`FarmTest` 是整个系统的入口类，里面有 `main()` 方法。

程序一开始会创建一个 `Scanner` 对象，用来读取用户输入：

```java
Scanner scanner = new Scanner(System.in);
```

然后通过 `while (running)` 循环不断显示菜单：

```java
while (running) {
    showMenu();
    System.out.print("请选择操作：");
    String choice = scanner.nextLine();
    ...
}
```

用户输入不同的数字，程序就通过 `switch` 调用对应的方法。比如输入 `1` 就创建对象，输入 `8` 就输出所有对象，输入 `0` 就退出系统。

## 五、数组存储对象的方式

在 `FarmTest` 中，我使用了数组来保存农场对象：

```java
private static final int MAX_CAPACITY = 10;
private static FarmObject[] farm = new FarmObject[MAX_CAPACITY];
private static int count = 0;
```

这里：

- `MAX_CAPACITY` 表示最多保存 10 个对象；
- `farm` 是一个 `FarmObject` 类型的数组；
- `count` 表示当前已经存入的对象数量。

虽然数组类型是 `FarmObject[]`，但是里面实际可以保存 `Wheat`、`Corn`、`Chicken` 和 `Cow` 对象，因为它们都是 `FarmObject` 的子类。这也是多态的一种体现。

## 六、创建农场对象

创建对象的方法是 `createFarmObject()`。

它首先判断农场是否已经满了：

```java
if (count >= MAX_CAPACITY) {
    System.out.println("农场已满，无法继续添加对象！");
    return;
}
```

如果没满，就让用户选择要创建的类型：

```java
1.小麦 2.玉米 3.鸡 4.牛
```

然后根据用户输入，通过 `switch` 创建不同的子类对象：

```java
case "1": newObj = new Wheat(name); break;
case "2": newObj = new Corn(name); break;
case "3": newObj = new Chicken(name); break;
case "4": newObj = new Cow(name); break;
```

这里变量 `newObj` 的声明类型是 `FarmObject`，但实际指向的可能是任何一个子类对象。这也是多态写法：

```java
FarmObject newObj = new Wheat(name);
```

最后把对象放入数组，并让 `count` 加 1：

```java
farm[count] = newObj;
count++;
```

## 七、查询和输出功能

### 1. 根据名称查找

`searchByName()` 会遍历数组中已有的对象：

```java
for (int i = 0; i < count; i++)
```

然后用 `equals()` 比较对象名称：

```java
if (farm[i].getName().equals(name))
```

如果名称相同，就输出这个对象。

这里使用 `equals()` 而不是 `==`，是因为字符串内容比较应该使用 `equals()`。

### 2. 根据类型输出

`outputByType()` 让用户输入一个类型关键字，例如“农作物”“动物”“小麦”等。

判断时使用：

```java
farm[i].getType().contains(keyword)
```

这样只要类型字符串里包含用户输入的关键字，就会输出对应对象。这个设计比完全相等匹配更灵活。

### 3. 输出指定编号之前的对象

`outputBeforeId()` 会先把用户输入的字符串转换为整数：

```java
int id = Integer.parseInt(scanner.nextLine());
```

然后输出所有 `getId() < id` 的对象。

为了防止用户输入的不是数字，这里使用了 `try-catch` 捕获 `NumberFormatException`。

## 八、照料对象体现多态

项目中最能体现多态的是 `careForObject()` 方法。

它先根据编号找到对象：

```java
FarmObject obj = findById(id);
```

找到之后直接调用：

```java
obj.care();
```

虽然 `obj` 的编译类型是 `FarmObject`，但是程序运行时会根据它真正指向的对象类型，调用不同子类的 `care()` 方法。

比如：

- 如果 `obj` 实际是 `Wheat`，就执行小麦的浇水除草；
- 如果 `obj` 实际是 `Corn`，就执行玉米的施肥松土；
- 如果 `obj` 实际是 `Chicken`，就执行鸡的喂饲料和打扫鸡舍；
- 如果 `obj` 实际是 `Cow`，就执行牛的喂草和挤奶。

所以老师，这一部分体现的是“父类引用指向子类对象，调用被重写的方法时执行子类版本”。

## 九、判断对象类型

`judgeObjectType()` 会调用静态方法 `checkInstanceType()` 来判断对象真实类型。

判断方式是使用 `instanceof`：

```java
if (obj instanceof Wheat) {
    System.out.println("Wheat (小麦)");
} else if (obj instanceof Corn) {
    System.out.println("Corn (玉米)");
}
```

`instanceof` 的作用是判断某个对象是不是某个类的实例。这里可以帮助用户看到编号对应的对象到底是小麦、玉米、鸡还是牛。

## 十、收获或移除对象

`harvestOrRemove()` 用来从数组中删除一个对象。

它的流程是：

1. 让用户输入要删除的编号；
2. 遍历数组，找到该编号对应的下标；
3. 如果找到了，就把后面的元素整体向前移动一位；
4. 把最后一个有效位置设置为 `null`；
5. `count--`，表示对象数量减少。

核心移动代码是：

```java
for (int i = indexToRemove; i < count - 1; i++) {
    farm[i] = farm[i + 1];
}
farm[count - 1] = null;
count--;
```

因为数组长度是固定的，不能像集合一样直接删除元素，所以这里需要手动移动后面的元素。

## 十一、辅助方法 findById

`findById()` 是一个根据编号查找对象的辅助方法：

```java
private static FarmObject findById(int id)
```

它会遍历当前已有对象，如果找到编号相同的对象，就返回这个对象；如果没找到，就返回 `null`。

这个方法被 `careForObject()` 和 `judgeObjectType()` 复用，避免重复写查找代码。

## 十二、我认为这个项目体现的知识点

老师，我觉得这个项目主要体现了下面几个 Java 知识点：

1. 抽象类：`FarmObject` 是抽象类，不能直接实例化；
2. 继承：`Wheat`、`Corn`、`Chicken`、`Cow` 都继承 `FarmObject`；
3. 方法重写：四个子类都重写了 `care()` 方法；
4. 多态：`FarmObject` 类型的数组可以保存不同子类对象，调用 `care()` 时执行子类自己的方法；
5. 封装：属性使用 `private` 修饰，并通过 `getId()`、`getName()`、`getType()` 访问；
6. 静态变量：`nextId` 用来实现统一编号自增；
7. 静态方法：`checkInstanceType()` 不依赖具体对象，可以直接通过类来调用；
8. 异常处理：输入编号时使用 `try-catch` 防止非数字输入导致程序崩溃；
9. 数组操作：使用数组保存对象，并通过移动元素实现删除。

## 十三、可以改进的地方

如果后续继续完善这个项目，我觉得可以从下面几个方面改进：

1. 可以把数组改成 `ArrayList<FarmObject>`，删除和添加会更方便；
2. 可以增加修改对象名称的功能；
3. 可以把菜单输入的数字判断封装成单独方法；
4. 可以对空名称做校验，避免创建名称为空的对象；
5. 可以给农作物和动物再分别设计中间父类，比如 `Crop` 和 `Animal`；
6. 可以增加保存到文件的功能，让程序关闭后数据还能保留。

## 十四、总结

总的来说，这个项目虽然功能比较简单，但是结构比较清楚。`FarmObject` 作为抽象父类定义统一属性和抽象行为，四个子类分别实现具体照料方式，`FarmTest` 通过菜单和数组管理这些对象。

我认为这个项目的重点不是菜单本身，而是通过一个农场管理场景，把继承、抽象类、方法重写和多态这些面向对象知识点串起来。尤其是 `obj.care()` 这一句，表面上调用的是父类引用的方法，实际运行时会根据对象真实类型执行不同的子类方法，这就是本项目最核心的多态体现。
