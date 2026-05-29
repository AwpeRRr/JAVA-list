package HappyFarmTest;

public class Wheat extends FarmObject {
    public Wheat(String name) {
        super(name, "农作物-小麦");
    }

    @Override
    public void care() {
        System.out.println("正在为小麦 [" + getName() + "] 浇水和除草...");
    }
}