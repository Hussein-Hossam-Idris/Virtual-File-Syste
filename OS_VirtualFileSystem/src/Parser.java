
public class Parser {
	public Parser() {
		
	}
	/*
	 * 1- CreateFile root/file.txt 100
2- CreateFolder root/folder1
3- DeleteFile root/folder1/file.txt
4- DeleteFolder root/folder1
5- DisplayDiskStatus
	6- DisplayDiskStructure
	 * */
	
	public boolean parse(String input,Directory root,Allocation allocation) {
		
		String[] splited = input.split(" ");
		if(splited[0].toLowerCase().equals("createfile"))
		{
			String newSplit= splited[1] + " " + splited[2];
			root.CreateFile(allocation,newSplit,Integer.parseInt(splited[2]));
		}else if(splited[0].toLowerCase().equals("createfolder"))
		{
			root.CreateDirectory(allocation, splited[1]);
		}else if(splited[0].toLowerCase().equals("deletefile"))
		{
			root.DeleteFile(allocation, splited[1]);
		}else if(splited[0].toLowerCase().equals("deletefolder"))
		{
			root.DeleteDirectory(allocation, splited[1]);
		}else if(splited[0].toLowerCase().equals("displaydiskstatus"))
		{
			allocation.displaySpace();
		}else if(splited[0].toLowerCase().equals("displaydiskstructure"))
		{
			root.DisplayDiskStructure("");
		}else if(splited[0].toLowerCase().equals("exit"))
		{
			return false;
		}else if(splited[0].toLowerCase().equals("help"))
		{
			System.out.println("createfile root/dir/..../file.txt [size of file]");
			System.out.println("createfolder root/dir/..../dir3");
			System.out.println("deletefile root/dir/..../file.txt");
			System.out.println("deletefolder root/dir/..../dir3");
			System.out.println("displaydiskstatus to display diskstatus");
			System.out.println("displaydiskstructure to display folder hierarchy");
		}else {
			System.out.println("write help to show all commands");
		}
		return true;
	}
	
}
