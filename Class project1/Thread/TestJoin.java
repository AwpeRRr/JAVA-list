package Thread;

public class TestJoin {
	public static void main(String[] args) {
		ToJoin t1 = new ToJoin("t1");
		t1.start();
		new Thread (new Joiner(t1)).start();
	}
}

class ToJoin extends Thread{
	public ToJoin(String nm) {
		super(nm);
	}
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("Tojoin!!");
		}
		catch(InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " awake!!");
		}
	}
	
}

class Joiner implements Runnable{
	private ToJoin tojoin;
	public Joiner(ToJoin t) {
		this.tojoin = t;
	}
	public void run() {
		try {
			this.tojoin.join();
			System.out.println("Joiner!!");
		}
		catch(InterruptedException e) {
			System.out.println(this.tojoin.getName() + " join finished!");
		}
	}
	
}