package HappyFarmTest_ArrayList_4th;

public class Chicken extends FarmObject {
    public Chicken(int id, String name) {
        super(id, name, "动物-鸡");
    }

    @Override
    public void care() {
        System.out.println("正在给鸡 [" + getName() + "] 喂饲料和打扫鸡舍...");
    }
}
