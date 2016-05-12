import java.util.HashSet;
/*
 * Solve the N-Queen Problem using a Stack. 
 */
public class NQueens 
{

	//Initialize a stack to keep track of locations. 
	private Stack<Integer[]> queenPositions;
	private int filled;
	// Keep track of the forbidden spaces.
	private HashSet<Integer> forbiddenRows;
	private HashSet<Integer> forbiddenColumns;
	private HashSet<Integer> forbiddenDiagonals; 
	
	/*
	 * Constructor 
	 */
	public NQueens() 
	{
		// Create a list of forbidden spaces 
		forbiddenRows = new HashSet<Integer>();
		forbiddenColumns = new HashSet<Integer>();
		forbiddenDiagonals = new HashSet<Integer>();
		queenPositions = new Stack<Integer[]>();
		filled = 0;
		fillBoard();
		printInfo();
	}
	
	/*
	 * Fill the board with all of the queens. 
	 * We do this by attempting to place a queen in a given spot. If there is no conflict, 
	 * we keep going. However, if one of the queens conflicts, we try the next available space
	 * until finding a spot. 
	 * If it turns out that there comes a row in which no spot is available, we backtrack
	 * and try putting the previous queen in the next available space. 
	 */
	private void fillBoard() 
	{
		// keep going until the entire board is filled with queens
		int columnPos = 0; 
		int rowPos = 0; 
		while(filled < 8) 
		{
			for (int row = rowPos; row < 8; row++) 
			{
				// keep track of whether the queen made it 
				boolean currentQueenPlaced = false;  
				// Iterate through each element, if it's available put a queen there and block off its
				// corresponding row/column/diagnols and increment filled. 
				for (int column = columnPos; column < 8; column++) 
				{
					if(available(row, column)) 
					{
						// Add the forbidden spaces into the list 
						forbiddenRows.add(row);
						forbiddenColumns.add(column);
						forbiddenDiagonals.add(row-column);
						forbiddenDiagonals.add(row+column);
						
						// Add the queen's position to a stack
						Integer[] location = {row, column, (row-column), (row+column)}; 
						queenPositions.push(location);
						currentQueenPlaced = true;
						
						filled++;
						columnPos = 0; 
						rowPos++;
						break;
					}
				}
				// If we have reached the end of a row and a queen has not been placed
				// we have to backtrack and try again to put the previous queen down
				// We do this by popping the stack and removing the associated elements 
				// from any forbidden lists. 
				if (!currentQueenPlaced) 
				{
					filled--;
					Integer[] prevQueenData = queenPositions.pop().getData();
					
					//Remove the forbidden spaces from the list
					forbiddenRows.remove(prevQueenData[0]);
					forbiddenColumns.remove(prevQueenData[1]);
					forbiddenDiagonals.remove(prevQueenData[2]);
					forbiddenDiagonals.remove(prevQueenData[3]); 
					
					// Remove the queen's position from the stack and try replacing
					// the previous queen.
					columnPos = prevQueenData[1]+1;
					rowPos = prevQueenData[0]; 
					break;
				}
			}
		}
	}
	/*
	 * Check whether a spot is available by seeing if its row, column or diagonal conflict with 
	 * any other.
	 * @param row, the row to check
	 * @param column, the column to check
	 * @return whether the spot is safe
	 */
	private boolean available(int row, int column) 
	{
		if (forbiddenRows.contains(row) || forbiddenColumns.contains(column) || forbiddenDiagonals.contains(row-column) || forbiddenDiagonals.contains(row+column)) 
		{
			return false;
		}
		
		return true;
	}
	/*
	 * Print the value of each queen (row, column)
	 */
	private void printInfo()
	{
		int size = queenPositions.size();
		for(int i = 0; i < size; i++) 
		{
			Integer[] tempQueen = queenPositions.pop().getData();
			for (int j = 0; j < 2; j++) 
			{
			    System.out.print(tempQueen[j] + " ");
			 }
			 System.out.println(); 
		}
	}
	
}
