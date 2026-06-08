package HappyFarm_Set;

public class Wheat extends FarmObject {
    public Wheat(int id, String name) {
        super(id, name, "农作物-小麦");
    }

    @Override
    public void care() {
        System.out.println("正在为小麦 [" + getName() + "] 浇水和除草...");
    }
}
