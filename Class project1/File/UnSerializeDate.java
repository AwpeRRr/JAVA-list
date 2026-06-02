package File;

import java.util.Date;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class UnSerializeDate {
	Date d = new Date();
	UnSerializeDate(){
		try {
			ObjectInputStream s = new ObjectInputStream(new FileInputStream("Class project1/File/date.ser"));
			Object o = s.readObject();
			if(d instanceof Date) d = (Date) o;
			s.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		UnSerializeDate un = new UnSerializeDate();
		System.out.println(un.d.toString());
	}
	
}
