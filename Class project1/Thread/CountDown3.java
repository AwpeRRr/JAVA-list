package Thread;

public class CountDown3 {
	static int idcnt = 1;
	
	static class SubThread extends Thread{
		private final int threadid = CountDown3.idcnt ++;
		public void run() {
			try {
				Thread.sleep(1000);
				System.out.println("#SubThread " + threadid);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	static class Parent{
		public void doSth() {
			System.out.println("do something...");
		}
	}
	
	static class Run extends Parent implements Runnable{
		private final int threadid = CountDown3.idcnt++;
		public void run() {
			try {
				Thread.sleep(1000);
				System.out.println("#" + threadid + ":");
				doSth();
				
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}
		}
	}
	
	public static void main(String[] args) {
		new SubThread().start();
		new Thread(new Run()).start();
	}
}
