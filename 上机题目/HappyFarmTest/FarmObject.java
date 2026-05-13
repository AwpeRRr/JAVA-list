package HappyFarmTest;

public abstract class FarmObject {
    // 静态变量，用于实现自增的种植编号
    private static int nextId = 1; 
    
    private int id;
    private String name;
    private String type;

    public FarmObject(String name, String type) {
        this.id = nextId++; // 每次创建对象时分配编号并自增
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    // 抽象的照料方法，供子类重写以实现多态
    public abstract void care();

    @Override
    public String toString() {
        return "编号: " + id + "\t名称: " + name + "\t类型: " + type;
    }
}