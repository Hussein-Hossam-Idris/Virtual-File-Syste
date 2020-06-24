import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

public class Directory extends AbsDirectory
{
	public Vector<FFile> files = new Vector<FFile>();
	public Vector<Directory> subDirectories = new Vector<Directory>();
	public static Scanner in = new Scanner(System.in);
	String Path;
	
	@Override///         root/file.txt 100
	public boolean CreateFile(Allocation allocation,String input,int s) 
	{
		///Creating File
		FFile newFile = new FFile();
		String[] splitInput = input.split("/");
		if(allocation.checkFreeSpace(s))
		{
			if(checkFileInput(splitInput,newFile,allocation))
			{
				System.out.println("File Created Succesful!!!");
				
			}else
			{
				System.out.println("Wrong Path.");
			}
		}else {
			System.out.println("No free space available");
		}
		
		return false;
	}
	///CreateFile root/dir1/file.txt 100
	private boolean checkFileInput(String[] input,FFile file,Allocation allocation)
	{
		boolean found = false;
		Directory prev = this;
		for(int i = 1 ; i < input.length ; i++) 
		{
			if(i == input.length-1)
			{
				String[] fName = input[i].split(" ");
				String value1 = fName[0];
				int value2 = Integer.parseInt(fName[1]);
				file.name = value1;
				file.size = value2;
			    file.allocatedBlocks = allocation.allocateMemory(file.size);
				prev.files.add(file);
				return true;
			}
		    for (Directory obj : prev.subDirectories)
		    {
		        if(obj.name.equals(input[i]))
		        {
		        	prev = obj;
		        	found = true;
		        	break;
		        }
		    }
		    if(!found) {
		    	return false;
		    }else {
		    	found = false;
		    }
		    
		}
		return true;
	}
	
	
	///Creating Directory root/dir/dir2
	@Override
	public boolean CreateDirectory(Allocation allocation,String input) {
		Directory newDir = new Directory();
		String[] splitInput = input.split("/");
		if(checkDirInput(splitInput,newDir)) {
			System.out.println("Directory Created Succesful!!!");
			newDir.Path = input;
		}else {
			System.out.println("Wrong Path.");
		}
		
		return false;
	}
	
	public boolean checkDirInput(String[] input,Directory dir){
		boolean found = false;
		Directory prev = this;
		for(int i = 1 ; i < input.length ; i++)
		{
			if(i == input.length - 1)
			{
				dir.name = input[i];
				prev.subDirectories.add(dir);
				return true;
			}
			for (Directory obj : prev.subDirectories)
		    {
		        if(obj.name.equals(input[i]))
		        {
		        	prev = obj;
		        	found = true;
		        	break;
		        }
		    }
		    if(!found) {
		    	return false;
		    }else {
		    	found = false;
		    }
		}
		return true;
	}
	
	
	///deletefile root/dir/dir2/dir3 / file.txt
	@Override
	public boolean DeleteFile(Allocation allocation, String input2) {
		// TODO Auto-generated method stub
		String[] input = input2.split("/");
		
		boolean found = false;
		Directory prev = this;
		int Final = input.length - 1;
		for(int i = 1 ; i < input.length ; i++) 
		{
			if(i == input.length-1)
			{
				FFile f = prev.files.stream().filter(t -> t.name.equals(input[Final])).findFirst().get();
				Vector<Integer> toBeDeleted = f.allocatedBlocks;
				allocation.deleteFileInfo(toBeDeleted);
				int ind = prev.files.indexOf(f);
				prev.files.remove(ind);
				System.out.println("File Deleted");
				return true;
			}
		    for (Directory obj : prev.subDirectories)
		    {
		        if(obj.name.equals(input[i]))
		        {
		        	prev = obj;
		        	found = true;
		        	break;
		        }
		    }
		    if(!found) {
		    	return false;
		    }else {
		    	found = false;
		    }
		    
		}
		
		return false;
	}
	@Override
	public boolean DeleteDirectory(Allocation allocation, String input2) {
		// TODO Auto-generated method stub
		String[] input = input2.split("/");
		if(input[input.length- 1]=="root") {
			System.out.println("Cant delete root Directory");
			return false;
		}
		boolean found = false;
		Directory prev = this;
		int Final = input.length - 1;
		for(int i = 1 ; i < input.length ; i++) 
		{	///root/dir1  /dir2/ dir3 
			///     file1   file3
			///		file2   file4	
			if(i == input.length-1)
			{
				Directory temp = prev.subDirectories.stream().filter(t -> t.name.equals(input[Final])).findFirst().get();
				temp.RecursiveFolderDelete(allocation);
				prev.subDirectories.remove(temp);
				System.out.println("Directory deleted!");
				return true;
			}
		    for (Directory obj : prev.subDirectories)
		    {
		        if(obj.name.equals(input[i]))
		        {
		        	prev = obj;
		        	found = true;
		        	break;
		        }
		    }
		    if(!found) {
		    	return false;
		    }else {
		    	found = false;
		    }
		    
		}
		return false;
	}
	@Override
	public boolean DisplayDiskStatus(Allocation allocation) {
		// TODO Auto-generated method stub
	    
		return false;
	}
	@Override
	public void DisplayDiskStructure(String tab) {
		// TODO Auto-generated method stub
		System.out.println(tab+"*"+ this.name);
		tab = tab + "    ";
		for(int i = 0 ; i < this.files.size() ; i++)
		{
			if(this.files.get(i).allocatedBlocks.size()==2)
			{
				System.out.println(tab+"-"+ this.files.get(i).name + " "+
						this.files.get(i).allocatedBlocks.get(0)
						+ " " + this.files.get(i).allocatedBlocks.get(1));
			}else
			{
				System.out.println(tab+"-"+ this.files.get(i).name + " "+
						this.files.get(i).allocatedBlocks.get(0));
			}
			
		}
		for(int i = 0 ; i < this.subDirectories.size() ; i++) {
			this.subDirectories.get(i).DisplayDiskStructure(tab);
		}
	}
	
	///root/dir1/   dir2/ dir3 
	///     ----   file3
	public void RecursiveFolderDelete(Allocation allocation)
	{
		for(int i = 0 ; i < this.files.size() ; i++)
		{
			allocation.deleteFileInfo(this.files.get(i).allocatedBlocks);
		}
		for(int i = 0 ; i < this.subDirectories.size() ; i++) {
			this.subDirectories.get(i).RecursiveFolderDelete(allocation);
		}
	}
	
	
}
