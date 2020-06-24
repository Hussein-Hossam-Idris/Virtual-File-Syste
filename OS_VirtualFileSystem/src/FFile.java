
import java.io.Serializable;
import java.util.Vector;

public class FFile extends AbsDirectory
{
	public Vector<Integer> allocatedBlocks;
	public int size;
	
	public FFile(String name) 
	{
		this.name = name;
		this.allocatedBlocks = new Vector<Integer>();
	}
	public FFile() 
	{
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean CreateFile(Allocation allocation, String input,int s) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean CreateDirectory(Allocation allocation, String input) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean DeleteFile(Allocation allocation, String input) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean DeleteDirectory(Allocation allocation, String input) {
		// TODO Auto-generated method stub
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

	}
	
	
	
}
