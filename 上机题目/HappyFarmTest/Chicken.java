public class Chicken extends FarmObject {
    public Chicken(String name) {
        super(name, "动物-鸡");
    }

    @Override
    public void care() {
        System.out.println("正在给鸡 [" + getName() + "] 喂饲料和打扫鸡舍...");
    }
}