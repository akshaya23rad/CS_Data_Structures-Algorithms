
/**
 * creates a priority queue and allows it to be changed using the methods in the class
 * @author Akshaya Radhakrishnan CS 1027
 *
 * @param <T>
 */
public class DLPriorityQueue<T> implements PriorityQueueADT<T> {
	//reference to the first node of the doubly linked list
	private PriorityNode<T> front;
	//reference to the last node of the doubly linked list
	private PriorityNode<T> rear;
	//number of data items in the priority queue
	private int count;
	
	/**
	 * creates empty priority queue
	 */
	public DLPriorityQueue() {
		count = 0;
	    front = rear = null;
	}
	/**
	 * adds to the priority queue the given data item with its associated priority
	 */
	public void enqueue(T dataItem, double priority) {
		PriorityNode<T> node = new PriorityNode<T>(dataItem, priority);
	    if (isEmpty()) {
	    		front = node;
	    }
	    	else {
	    		rear.setNext(node);
	    		node.setPrevious(rear);
	    }
	    	rear = node;
	    	count++;
	}
	
	/**
	 * removes and returns the data item at the front of the priority queue
	 */
	public T dequeue() throws EmptyPriorityQueueException{
		if (isEmpty())
			throw new EmptyPriorityQueueException ("empty queue");
		//T result = front.getDataItem();
		//Added this section
		PriorityNode<T> result = front;
		if (front==null) {
			front = rear = null;
			count--;
		}
		else {
			front = front.getNext();
		    front.setPrevious(null);
		    count--;
		}
	    //if (isEmpty())
	       //rear = null;
	    return result.getDataItem(); //modified this by adding getDataItem
	}
	
	
	/**
	 * returns priority of specified data item 
	 * @param dataItem
	 * @return  priority of dataItem
	 * @throws InvalidDataItemException
	 */
	public double getPriority(T dataItem) throws InvalidDataItemException{
	    PriorityNode<T> current = front;
	    // checks if priority is found
	    boolean found = false;
		while (current!=null) {
			if (dataItem.equals(current.getDataItem())) {
				found = true;
				break;
			}
			else {
				PriorityNode<T> previous = current;
				current = previous.getNext();
			}
			
		}
		if (found=false)
			throw new InvalidDataItemException ("invalid data item");
	
		return current.getPriority();
	}
	
	/**
	 * 
	 * changes priority of given element to new value 
	 */
	public void changePriority(T dataItem, double newPriority) throws InvalidDataItemException{
		PriorityNode<T> current = front;
	    boolean found = false;
	    // search for data item
		while (current!=null) {
			if (dataItem.equals(current.getDataItem())) {
				current.setPriority(newPriority);
				found = true;

			}
			PriorityNode<T> previous = current;
			current = previous.getNext();	
		}
		if (!found)
			throw new InvalidDataItemException ("invalid data item");
	}
	/**
	 * returns and removes the data item with the lowest priority in the priority queue
	 * 
	 */
	public T getSmallest() throws EmptyPriorityQueueException{
		PriorityNode<T> current = front;
		PriorityNode<T> small = front;
		if(this.isEmpty()) {
			throw new EmptyPriorityQueueException ("is empty");
		}
		//linear search scan
		while (current != null) {
			if (current.getPriority() < small.getPriority()) {
				small = current;
			}
			PriorityNode<T> previous = current;
			current = previous.getNext();
		}

		T dataItem = small.getDataItem(); 
		//removes smallest item
		delete(small);
		return dataItem;
	}
	
	/**
	 * method used to remove the data item with the lowest priority
	 * resets links
	 * @param nodeToDelete
	 */
	private void delete (PriorityNode<T> nodeToDelete){
		
		PriorityNode<T> previous = nodeToDelete.getPrevious();
		PriorityNode<T> next = nodeToDelete.getNext();

		if (front==rear) {
			front=rear=null;
		}

		else if (nodeToDelete.equals(front)) {
			front=front.getNext();
			front.setPrevious(null);
		}
		else if (nodeToDelete.equals(rear)) {
			rear = rear.getPrevious();
			rear.setNext(null);
		}
		else {
			previous.setNext(next);
			next.setPrevious(previous);
		}
		count--;

	}

	/**
	 * returns true if empty
	 * returns false otherwise
	 */
	public boolean isEmpty() {
		if (front==null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * returns number of items in priority queue
	 */
	public int numItems() {
		return count;
	}
	

	/**
	 * returns string representation of priority queue
	 */
	public String toString( ){
		 String wordRepOfPriorityQueue = "";
		PriorityNode<T> current = front;
		while(current!=null) {
			wordRepOfPriorityQueue += current.getDataItem().toString();
			PriorityNode<T> previous = current;
			current = previous.getNext();	
		}
		return wordRepOfPriorityQueue;
	}

}