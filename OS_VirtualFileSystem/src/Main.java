import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import java.util.Scanner;

public class Main 
{
	
	public static Scanner input = new Scanner(System.in);
	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
		// TODO Auto-generated method stub
		Directory root = new Directory();
		root.name = "root";
		Allocation allocation = null;
		Parser peter = new Parser();
		System.out.println("Press 1 for contigouos allocation, 2 for indexed allocation");
		File myObj;
		String filename;

		int option = input.nextInt();
		if(option == 1) 
		{
			filename = "ContFile.ser";
			myObj = new File(filename);
			if (myObj.createNewFile()) {
				allocation = new ContigousAllocation();
		        System.out.println("File created: " + myObj.getName());
		      } else {
		    	    FileInputStream fi = new FileInputStream(new File(filename));
		            ObjectInputStream oi = new ObjectInputStream(fi);
		            // Read objects
		            allocation = (ContigousAllocation) oi.readObject();
		            root = (Directory) oi.readObject();
		            oi.close();
		            fi.close();
		      }
		}
		else 
		{
			
			filename = "IndFile.ser";
			myObj = new File(filename);
			if (myObj.createNewFile()) {
				allocation = new IndexedAllocation();
		        System.out.println("File created: " + myObj.getName());
		      } else {
		    	  FileInputStream fi = new FileInputStream(new File(filename));
		            ObjectInputStream oi = new ObjectInputStream(fi);

		            // Read objects
		            allocation = (IndexedAllocation) oi.readObject();
		            root = (Directory) oi.readObject();
		            oi.close();
		            fi.close();
		      }	
		}
		System.out.println();
		while(true)
		{
			String command = input.nextLine();
			
			if(!peter.parse(command, root, allocation)) {
				break;
			}
		}
			FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(allocation);
            objectOut.writeObject(root);
            objectOut.close();
            fileOut.close();
            System.exit(0);
            
         
			
		

	}
	

}
