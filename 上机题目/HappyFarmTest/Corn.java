package HappyFarmTest;

public class Corn extends FarmObject {
    public Corn(String name) {
        super(name, "农作物-玉米");
    }

    @Override
    public void care() {
        System.out.println("正在为玉米 [" + getName() + "] 施肥和松土...");
    }
}