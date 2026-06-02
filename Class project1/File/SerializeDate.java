package File;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.io.FileOutputStream;

public class SerializeDate {
	SerializeDate(){
		Date d = new Date();
		try {
			ObjectOutputStream s = new ObjectOutputStream(new FileOutputStream("Class project1/File/date.ser"));
			s.writeObject(d);
			s.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			
		}
		
		
	}
	public static void main(String[] args) {
		SerializeDate b = new SerializeDate();
	}
}
