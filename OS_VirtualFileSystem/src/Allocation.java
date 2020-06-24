import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;

public abstract class Allocation implements Serializable {
	
	public abstract Vector<Integer> allocateMemory(int size);
	
	public abstract boolean checkFreeSpace(int size);
	
	public abstract void deleteFileInfo(Vector<Integer> vec);
	
	public abstract void displaySpace();
}
