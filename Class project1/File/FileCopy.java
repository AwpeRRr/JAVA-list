package File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileCopy {
    public static void main(String[] args) {
        try(
            FileInputStream in = new FileInputStream("Class project1/File/FileCopy.java");
            FileOutputStream out = new FileOutputStream("Class project1/File/FileCopy.txt");

        ){
            int c;
            while((c = in.read()) != -1){
                out.write(c);
            }

            System.out.println("File copy successfully!");
        }
        catch(FileNotFoundException e){
            System.out.println("File not found!");
        }
        catch(IOException e){
            System.out.println("Copy failed!");
        }        
    }
}

