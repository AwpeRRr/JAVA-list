我在实现“初始化农场行数”功能时，主要是把农场整体看成一个二维集合结构。在 Farm 类中，我的程序使用 ArrayList<ArrayList<FarmObject>> farmRows 来保存农场数据。外层 ArrayList 表示农场有多少行，内层 ArrayList<FarmObject> 表示某一行中具体存放了哪些农场对象。这样就不再像原来的数组版本那样只能线性保存对象，而是可以通过 rowIndex 和 positionIndex 两个下标来定位对象。对应代码出处：Farm.java:6 中的 farmRows 成员变量。

       在 initFarm 方法中，我选择先调用 farmRows.clear() 清空原来的农场结构，然后再根据用户输入的 rowCount 循环创建新的空行。每一行都是一个新的 ArrayList<FarmObject>。我认为这样写的好处是逻辑比较清晰，重新初始化时不会残留旧数据，而且能够准确创建题目要求的指定行数农场。对应代码出处：Farm.java:8-12 中的 initFarm(int rowCount) 方法。

       创建农场对象的时候，无论用户选择创建哪种子类（Wheat、Corn、Chicken、Cow），我想代码都应该在实例化后统一赋值给父类引用 FarmObject newObj。这样做利用了向上转型的思想，虽然实际创建出来的是不同子类对象，但它们都可以作为 FarmObject 被统一管理，并存入二维 ArrayList 的某一行中。对应代码出处：FarmTest.java:111 中的 FarmObject newObj，FarmTest.java:123-138 中的 createObjectByType 方法。

       在添加对象功能中，我没有直接把对象随便加入集合，而是先让用户输入对象编号、名称和目标行号。程序会先通过 farm.hasId(id) 判断编号是否已经存在，再通过 farm.isValidRowIndex(rowIndex) 判断用户输入的行号是否合法。这样可以防止编号重复，也可以避免向不存在的农场行中添加对象。对应代码出处：FarmTest.java:92-106 中的编号、名称和行号输入校验，Farm.java:36-44 中的 hasId(int id) 方法，Farm.java:26-28 中的 isValidRowIndex(int rowIndex) 方法。

       关于二维 ArrayList 的添加过程，我选择在 Farm 类中封装 addObject 方法。只要传入的对象不为空，并且 rowIndex 是合法的，就调用 farmRows.get(rowIndex).add(obj) 把对象添加到指定行的末尾。这样 FarmTest 不需要直接操作底层集合，只负责读取用户输入和调用方法，我认为这种写法让菜单交互和数据管理的职责更加分明。对应代码出处：Farm.java:47-53 中的 addObject(FarmObject obj, int rowIndex) 方法，FarmTest.java:117-119 中对 farm.addObject 和 getRowSize 的调用。

       我在实现“输出全部农场对象”功能时，使用了双层 for 循环。外层循环遍历每一行，内层循环遍历这一行里的所有对象，并输出每个对象当前所在的行号和位置编号。因为本项目使用的是二维 ArrayList，所以不能只像一维数组那样用一个下标遍历，而是必须同时处理 rowIndex 和 positionIndex。对应代码出处：Farm.java:83-104 中的 printAllObjects() 方法，FarmTest.java:26 中菜单选项对 farm.printAllObjects() 的调用。

       对象总数统计功能的实现上，我没有继续使用原数组版本中的 count 变量，而是通过遍历 farmRows 中的每一行，把每一行的 size() 累加起来。这样对象总数始终来源于集合当前真实保存的数据，即使用户添加、删除或清空对象，也不容易出现计数器和实际数据不一致的问题。对应代码出处：Farm.java:75-81 中的 getTotalCount() 方法，Farm.java:102 中输出对象总数的位置。

       名称查找功能的实现上，我用 String.equals() 方法进行字符串的值匹配。程序会遍历整个二维 ArrayList，将用户输入的名称与每个对象的 getName() 返回值进行完全比对。如果匹配成功，就输出该对象的编号、类型、名称，以及它所在的 rowIndex 和 positionIndex。对应代码出处：Farm.java:106-126 中的 searchByName(String name) 方法，Farm.java:113 中的 obj.getName().equals(name)，FarmTest.java:140-145 中的 searchByName 菜单处理。

       我在实现“照料指定农场对象”功能时，充分利用了多态特性。在 careForObject 方法中，程序先根据用户输入的行号和位置编号，通过 farm.getObject(rowIndex, positionIndex) 获取父类类型的 FarmObject obj，然后直接调用 obj.care()。这样 Java 会在运行时进行动态绑定，自动执行对应子类内部重写的 care 逻辑。对应代码出处：FarmTest.java:148-160 中的 careForObject 方法，Farm.java:55-60 中的 getObject 方法。

       我认为 obj.care() 是本项目中最能体现多态的位置。FarmTest 并不需要判断这个对象到底是小麦、玉米、鸡还是牛，只需要把它当成 FarmObject 来调用 care 方法即可。如果以后新增新的农场对象类型，只要新的类继承 FarmObject 并重写 care 方法，照料功能这里就不需要大改。对应代码出处：FarmTest.java:159 中的 obj.care()，FarmObject.java:26 中的抽象 care 方法，Wheat.java:8、Corn.java:8、Chicken.java:9、Cow.java:8 中各子类重写的 care 方法。

       关于对象类型的显示，本项目没有单独使用 instanceof 再做类型判断，而是通过 FarmObject 中的 type 属性保存对象类型。每个子类在构造方法中调用 super(id, name, type) 时传入自己的类型信息，例如 Wheat 传入小麦类型，Cow 传入牛的类型。这样在输出和查找结果中，可以直接通过 getType() 显示对象所属类型。对应代码出处：FarmObject.java:3-12 中的 id、name、type 属性和构造方法，FarmObject.java:22-24 中的 getType() 方法，Wheat.java:4、Corn.java:4、Chicken.java:5、Cow.java:4 中各子类传入 type 的 super 调用。

       创建和删除对象的时候我选择使用 ArrayList 作为容器，这样在定位到要删除的对象位置后，只需直接调用内层 ArrayList 的 remove(int index) 方法即可。这不仅免去了手动编写 for 循环覆盖数据的步骤，也不需要像数组版本那样手动维护 count--，同时还降低了手动移位时出现索引越界的风险。对应代码出处：Farm.java:62-67 中的 removeObject(int rowIndex, int positionIndex) 方法，尤其是 Farm.java:66 中的 remove(positionIndex) 调用。

       在 removeObject 方法中，我的程序会先调用 isValidPosition(rowIndex, positionIndex) 判断位置是否合法。如果位置不存在，就返回 null；如果位置存在，就执行 farmRows.get(rowIndex).remove(positionIndex) 并返回被删除的对象。FarmTest 再根据返回值判断删除是否成功，并给用户输出对应提示。对应代码出处：Farm.java:30-34 中的 isValidPosition 方法，Farm.java:62-67 中的 removeObject 方法，FarmTest.java:162-173 中的 removeByPosition 方法。

       我在实现“清空农场对象”功能时，并没有直接清空外层 farmRows，而是遍历每一行并调用 row.clear()。这样做的原因是清空对象不等于删除农场行。用户清空后，原来的农场行结构仍然存在，之后还能继续向这些行中添加新的对象。对应代码出处：Farm.java:69-73 中的 clearFarm() 方法，FarmTest.java:175-183 中的 clearFarm(Scanner scanner) 菜单处理。

       在处理用户输入行号和位置编号的场景中，我通过 isValidRowIndex 和 isValidPosition 两个方法集中进行合法性判断。rowIndex 必须大于等于 0 并且小于农场总行数，positionIndex 必须大于等于 0 并且小于该行对象数量。这样可以避免直接访问二维 ArrayList 时产生下标越界异常。对应代码出处：Farm.java:26-34 中的 isValidRowIndex 和 isValidPosition 方法，FarmTest.java:187-190 中的 printPositionNotFound 方法。

       在处理用户需要输入数字编号的场景中，我运用了 try-catch 异常处理结构。readInt 方法会先用 scanner.nextLine() 读取一整行内容，再通过 Integer.parseInt(input.trim()) 转成整数。如果用户输入的不是合法整数，就捕获 NumberFormatException 并提示用户重新输入。我想这样提高了界面的交互能力。对应代码出处：FarmTest.java:205-214 中的 readInt(Scanner scanner, String prompt) 方法。

       对于对象编号这种必须为正数的输入，我又在 readInt 的基础上封装了 readPositiveInt 方法。它会反复判断用户输入的整数是否大于 0，只有满足条件才返回。这样对象编号的校验逻辑就不会散落在多个菜单功能里，代码也更容易维护。对应代码出处：FarmTest.java:194-203 中的 readPositiveInt 方法，FarmTest.java:92 中创建对象时对 readPositiveInt 的调用。

       界面交互我选择和普通控制台菜单程序一样，使用 boolean running = true 控制外层 while 大循环，然后配合 switch-case 菜单进行选择。只有当用户明确输入 "0" 退出时，才将 running 修改为 false，并在循环结束后调用 scanner.close() 释放资源。对应代码出处：FarmTest.java:12-49 中的 main 方法，FarmTest.java:18-44 中的 switch-case 菜单选择，FarmTest.java:52-61 中的 showMenu 方法。

       总体来看，HappyFarmTest_ArrayList 的关键改进并不是简单替换容器，而是围绕二维 ArrayList 重新组织了农场对象的管理方式。FarmTest 负责菜单和输入，Farm 负责集合数据的添加、查询、删除、清空和统计，FarmObject 及其子类负责对象属性和多态照料行为。我认为这样的实现更符合本次作业对 Java 集合框架、二维 ArrayList 和多态使用的要求。对应代码出处：FarmTest.java 负责交互入口，Farm.java 负责二维集合管理，FarmObject.java、Wheat.java、Corn.java、Chicken.java、Cow.java 负责对象继承结构和多态行为。
