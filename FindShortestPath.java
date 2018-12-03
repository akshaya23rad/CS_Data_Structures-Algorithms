import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * inputs map file as a textfile and finds the right path to customer
 * @author Akshaya Radhakrishnan
 * CS 1027
 */
public class FindShortestPath {

	/**
	 * checks if neighbour is tower and not null
	 * @param cell
	 * @return true or false
	 */
	private static boolean interference(MapCell cell) {
		for(int i=0;i<6;i++) {
			MapCell neighbour = cell.getNeighbour(i);
			if(neighbour != null && neighbour.isTower()) {
					return true;
			}
		}
		return false;
	}
	
	
	/**
	 * map file gets imported and checks the cell type to find the right path
	 * @param args
	 * @throws InvalidMapException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InvalidNeighbourIndexException
	 * @throws NullPointerException
	 * @throws EmptyPriorityQueueException
	 */
	
	public static void main(String[] args) throws InvalidNeighbourIndexException, InvalidMapException, FileNotFoundException, IOException, NullPointerException, EmptyPriorityQueueException{
		String filename = "";
		try {
		filename = (args[0]);
		}catch(Exception e) {
			System.out.println("file not found");
		}
		
		
		try {
		
			Map cityMap = new Map(filename);
			MapCell currentCell= null;
			double cellDistanceNext=0.0;
			DLPriorityQueue <MapCell> priorityQueue = new DLPriorityQueue<MapCell>();
			MapCell start = cityMap.getUWOstore();
			priorityQueue.enqueue(start, 0);
			start.markEnqueued();
			// solved if the customer is found
			boolean solved = false;
			//while the stack is not empty and the destination has not been found, checks cell type and edits queue accordingly
			while(solved==false && !priorityQueue.isEmpty()) {
				MapCell smallestCell = priorityQueue.getSmallest();
				smallestCell.markDequeued();
				if(smallestCell.isCustomer()) {
					solved = true;
				}
				else if(!smallestCell.isTower() && !interference(smallestCell)) {
					//loops through all neighbouring cells
					for (int i=0; i<6; i++) {
						currentCell = smallestCell.getNeighbour(i);
						if (currentCell!=null && !currentCell.isNoFlying() && !currentCell.isMarkedDequeued()) {
							int distanceInit = smallestCell.getDistanceToStart() + 1;
							if (currentCell.getDistanceToStart() > distanceInit) {
								currentCell.setDistanceToStart(distanceInit);
								currentCell.setPredecessor(smallestCell);
								//System.out.println("Shortest Distance Is: " + currentCell.getDistanceToStart());
							}	
							cellDistanceNext = currentCell.getDistanceToStart() + currentCell.euclideanDistToDest(cityMap);
							if (currentCell.isMarkedEnqueued() && cellDistanceNext < priorityQueue.getPriority(currentCell)){
								priorityQueue.changePriority(currentCell, cellDistanceNext);	
							}
							if (!currentCell.isMarkedEnqueued()) {
								priorityQueue.enqueue(currentCell, currentCell.getDistanceToStart() + currentCell.euclideanDistToDest(cityMap));
								currentCell.markEnqueued();
							}
						}
					}				
				}
				//System.out.println("Shortest Distance Is: " + currentCell.getDistanceToStart());
				
			}
			//prints message depending if path to customer was found or not
			if (solved == true && priorityQueue.isEmpty()==false) {
				System.out.println("The customer has been found");
				System.out.println(priorityQueue.toString());
				System.out.println("Shortest Distance Is: " + (cellDistanceNext + 1.0));

				
			}else {
				System.out.println("The customer was not able to be found");
			}
		// catches appropriate errors and prints out right message for each error to fix
		}catch (InvalidNeighbourIndexException e){
			System.out.println("Invalid Neighbour Index Exception!");
		}catch (FileNotFoundException e) {
			System.out.println("Invalid file Exception thrown!");
		}catch (IOException e) {
			System.out.println("Input Output Error!");
		}catch (InvalidMapException e) {
			System.out.println("Invalid Map!");
		}catch (NullPointerException e) {
			System.out.println("No file found!");
		}catch (EmptyPriorityQueueException e) {
			System.out.println("Priority queue is empty!");
		}
		
	}
}


