package Thread;

public class Reentrant {
	public synchronized void a() {
		b();
		System.out.println("here am i, im a()!");
	}
	
	public synchronized void b() {
		System.out.println("here am i, im b()!");
	}
	
	
}
