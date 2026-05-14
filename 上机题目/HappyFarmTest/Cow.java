package HappyFarmTest;

public class Cow extends FarmObject {
    public Cow(String name) {
        super(name, "动物-牛");
    }

    @Override
    public void care() {
        System.out.println("正在给牛 [" + getName() + "] 喂牧草和挤牛奶...");
    }
}