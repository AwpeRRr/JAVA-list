import java.io.File;

public class RenameFile {
    private static void fileData(File f){
        System.out.println("File name: " + f.getName());
        System.out.println("Absolute path: " + f.getAbsolutePath());
        System.out.println("Can read: " + f.canRead());
        System.out.println("Can write: " + f.canWrite());
        System.out.println("getName: " + f.getName());
        System.out.println("getPath: " + f.getPath());
        System.out.println("getParent: " + f.getParent());
        System.out.println("length: " + f.length());
        System.out.println("lastModified: " + f.lastModified());
        if (f.isFile()) System.out.println("f is a file!");
        else System.out.println("f is a directory!");
    }
    
    public static void main(String[] args) {
    	File f1 = new File(args[0]);
    	File f2 = new File(args[1]);
    	System.out.println("The original file's information: ");
    	fileData(f1);
    	f1.renameTo(f2);
    	System.out.println("The file information after rename: ");
    	fileData(f2);
    	if(!f1.exists()) System.out.println("The original file never exists!");
    	
    }
}
