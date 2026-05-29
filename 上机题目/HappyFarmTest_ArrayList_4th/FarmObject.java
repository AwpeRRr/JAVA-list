package HappyFarmTest_ArrayList_4th;

public abstract class FarmObject {
    private int id;
    private String name;
    private String type;

    public FarmObject(int id, String name, String type) {
        this.id = id;
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

    public abstract void care();

    @Override
    public String toString() {
        return "编号: " + id + "\t类型: " + type + "\t名称: " + name;
    }
}
