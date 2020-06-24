import java.util.Arrays;
import java.util.Vector;

public class ContigousAllocation extends Allocation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4413103241821551642L;
	public int availableSpace = 100;
	public boolean mem[] = new boolean[availableSpace];
	
	public ContigousAllocation() {
		Arrays.fill(mem, true);
	}
	
	@Override
	public Vector<Integer> allocateMemory(int size) {
		// TODO Auto-generated method stub
		int count = 0; //intitialize count 
        int result = 110000000; //initialize max 
        int bestindex = 0;
        Vector<Integer> indecies = new Vector<Integer>();
        ///001110001100
        int i ;
        for (i = 0; i < mem.length; i++) 
        { 
        	//000111111111111111111111111111
            // Reset count when 0 is found 
            if (mem[i] == false) {
                 if(count>=size) {
                     result = Math.min(result, count);
                     bestindex=i-result;
                 }
                 
                 count = 0;

            }
            // If 1 is found, increment count 
            // and update result if count becomes 
            // more. 
            else
            { 
                count++;//increase count 

            } 
		
        }
        if(count == availableSpace) {
        	bestindex = i - count;
        }
        indecies.add(bestindex);
        indecies.add(size);
        //bestindex+(size-1)
        for(i = bestindex ; i <= bestindex+(size-1) ; i++) {
        	mem[i]=false;
        }
        availableSpace -= size;
        return indecies;
	}

	@Override
	public boolean checkFreeSpace(int size) {
		// TODO Auto-generated method stub
		int count = 0; //intitialize count 
        int result = 110000000; //initialize max 
        int bestindex = 0;
        ///001100011100
        for (int i = 0; i < mem.length; i++) 
        { 

            // Reset count when 0 is found 
            if (mem[i] == false || count==size) {
                 if(count>=size) {
                   
                     return true;
                 }
                 count = 0;

            }
            // If 1 is found, increment count 
            // and update result if count becomes 
            // more. 
            else
            { 
                count++;//increase count 

            } 
		
	}if(count == availableSpace) {
    	return true;
    }
        return false;
	}

	///00011111101100
	@Override
	public void deleteFileInfo(Vector<Integer> vec) {
		// TODO Auto-generated method stub
		for(int i = vec.get(0) ; i < vec.get(1) + vec.get(0) ; i++)
		{
			mem[i] = true;
		}
		availableSpace +=vec.get(1);
	}
	
	@Override
	public void displaySpace() {
		int space = 0;
		for(int i = 0 ; i < mem.length ; i++) {
			System.out.print(mem[i]+ " ");
			space++;
			if(space == 10) {
				System.out.println();
				space = 0;
			}
		}
		System.out.println( "the available space is : "+ availableSpace);
		System.out.println( "the allocated space is : "+ ((mem.length) - availableSpace));
	}
	
}
