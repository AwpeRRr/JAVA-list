package Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.IOException;

public class PipedStreamDemo {
	public static void main(String[] args) {
		try {
			PipedInputStream pin = new PipedInputStream();
			PipedOutputStream pout = new PipedOutputStream();
			
			pin.connect(pout);
			
			Thread producerThread = new Thread(new WordProducer(pout));
			Thread processorThread = new Thread(new WordProcessor(pin));
			
			processorThread.start();
			producerThread.start();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}

class WordProducer implements Runnable{
	private PipedOutputStream pout;
	
	public WordProducer(PipedOutputStream pout) {
		this.pout = pout;
	}
	
	@Override
	public void run() {
		String[] words = {
				"Java", "Stream", "Pipeline"
		};
		
		try (DataOutputStream dos = new DataOutputStream(pout)) {
				dos.writeInt(words.length);
				for(String word : words) {
					System.out.println("sending: " + word);
					dos.writeUTF(word);
					dos.flush();
					Thread.sleep(1000);
				}
			}
		catch(IOException | InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("over");
	}
	
}

class WordProcessor implements Runnable{
	private PipedInputStream pin;
	
	public WordProcessor(PipedInputStream pin) {
		this.pin = pin;
	}
	
	@Override
	public void run() {
		try (DataInputStream dis = new DataInputStream(pin)){
			int count = dis.readInt();
			
			for (int i = 0; i<count ; i++) {
				String originalWord = dis.readUTF();
				String reversedWord = new StringBuilder(originalWord).reverse().toString();
				
				System.out.println("Accept: " + originalWord + "-> reverse processing: " + reversedWord );
				
				System.out.println("over");
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}


