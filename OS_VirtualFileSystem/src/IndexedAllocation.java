import java.util.Arrays;
import java.util.Vector;

public class IndexedAllocation extends Allocation {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4560370455305991317L;

	public int availableSpace = 100;
	
	public Vector<indexedCell> mem = new  Vector<indexedCell>();
	
	
	public IndexedAllocation()
	{
		int S = availableSpace;
		for(int i = 0 ; i < S ; i++) {
			mem.add(new indexedCell());
		}
	}
	
	@Override
	public Vector<Integer> allocateMemory(int size)
	{
		// TODO Auto-generated method stub
		///0000Pt000tt0
		boolean pointerFound = false;
		int indexOFpointer = 0;
		int counter = 0;
		
		
		for(int i = 0 ; i < mem.size() ; i++)
		{
			if(mem.get(i).free == true && !pointerFound)
			{
					pointerFound= true;
					indexOFpointer = i;
					mem.get(indexOFpointer).free = false;
			}else if(mem.get(i).free == true && pointerFound){
					mem.get(indexOFpointer).pointers.add(i);
					mem.get(i).free = false;
					counter++;
					if(counter == size)
					{
						break;
					}
			}
			
		}
		availableSpace -= size + 1;
		Vector<Integer> indecies = new Vector<Integer>();
		indecies.add(indexOFpointer);
		return indecies;
	}

	@Override
	public boolean checkFreeSpace(int size)
	{
		// TODO Auto-generated method stub
		if(availableSpace >= size + 1)
			return true;
		
		return false;
	}

	@Override
	public void deleteFileInfo(Vector<Integer> vec) {
		// TODO Auto-generated method stub 
		for(int i = 0 ; i < mem.get(vec.get(0)).pointers.size();i++)
		{
			mem.get(mem.get(vec.get(0)).pointers.get(i)).free = true;
		}
		availableSpace += mem.get(vec.get(0)).pointers.size() + 1;
		mem.get(vec.get(0)).free = true;
		mem.get(vec.get(0)).pointers.clear();
	}

	@Override
	public void displaySpace() {
		// TODO Auto-generated method stub
		int space = 0;
		for(int i = 0 ; i < mem.size() ; i++) {
			System.out.print(mem.get(i).free+ " ");
			space++;
			if(space == 10) {
				System.out.println();
				space = 0;
			}
		}
		System.out.println( "the available space is : "+ availableSpace);
		System.out.println( "the allocated space is : "+ ((mem.size()) - availableSpace));
		
	}

}
