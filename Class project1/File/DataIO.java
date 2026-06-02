package File;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DataIO {
	public static void main(String[] args) throws IOException {
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream (new FileOutputStream("Class project1/File/DataIO.txt")));
		
		out.writeBoolean(false);
		out.writeChar('c');
		out.writeByte(1);
		out.writeShort(2);
		out.writeInt(3);
		out.writeLong(44444444);
		out.writeFloat(6.657f);
		out.writeDouble(66.57);
		out.writeUTF("hello world!!!");
		
		out.close();

		DataInputStream in = new DataInputStream(
			new BufferedInputStream(new FileInputStream("Class project1/File/DataIO.txt"))
		);

		System.out.println(in.readBoolean());
		System.out.println(in.readChar());
		System.out.println(in.readByte());
		System.out.println(in.readShort());
		System.out.println(in.readInt());
		System.out.println(in.readLong());
		System.out.println(in.readFloat());
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		
		
	}
}
