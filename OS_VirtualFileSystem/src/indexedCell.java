import java.io.Serializable;
import java.util.Vector;

public class indexedCell implements Serializable {
	public  Vector<Integer> pointers = new Vector<Integer>();
	boolean free;
	
	public indexedCell()
	{
		free = true;
	}
}
