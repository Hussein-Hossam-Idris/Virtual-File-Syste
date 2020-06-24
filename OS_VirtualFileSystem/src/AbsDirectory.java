import java.io.Serializable;

public abstract class AbsDirectory implements Serializable {
	public String name;
	
	public abstract boolean CreateFile(Allocation allocation,String input,int s);
	public abstract boolean CreateDirectory(Allocation allocation,String input);
	public abstract boolean DeleteFile(Allocation allocation,String input);
	public abstract boolean DeleteDirectory(Allocation allocation,String input);
	public abstract boolean DisplayDiskStatus(Allocation allocation);
	public abstract void DisplayDiskStructure(String tab);
	
}
