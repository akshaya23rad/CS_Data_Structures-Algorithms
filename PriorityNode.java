/**
 * This class represents the nodes of the doubly linked list used to implement the priority queue
 * @author Akshaya Radhakrishnan CS 1027
 *
 * @param <T>
 */
public class PriorityNode<T> {
	//reference to data item stored in the node
	private T dataItem;
	// a reference to the next node of the linked list
	private PriorityNode<T> next;
	//a reference to the previous node in the linked list
	private PriorityNode<T> previous;
	//priority of the data item stored in the node
	private double priority;
	
	/**
	 * creates node, given data and priority
	 * @param data
	 * @param prio
	 */
	public PriorityNode(T data, double prio) {
		priority = prio;
		dataItem = data;	
	}
	/**
	 * creates and empty node with null data and priority zero
	 */
	public PriorityNode() {
		next = null;
		priority = 0;
	}
	
	/**
	 * priority of node
	 * @return priority
	 */
	public double getPriority() {
		return priority;
	}
	
	/**
	 * gets the data item in the node
	 * @return dataItem
	 */
	public T getDataItem() {
		return dataItem;
	}
	
	/**
	 * gets the next cell
	 * @return next
	 */
	public PriorityNode<T> getNext() {
		return next;
	}

	/**
	 * gets the previous node
	 * @return previous
	 */
	public PriorityNode<T> getPrevious(){
		return previous;
	}
	
	/**
	 * sets new value of data in node
	 * @param data
	 */
	public void setDataItem(T data) {
		dataItem = data;
		
	}
	
	/**
	 * sets the next node
	 * @param nextNode
	 */
	public void setNext(PriorityNode<T> nextNode) {
		next = nextNode;
	}
	
	/**
	 * sets previous node to parameter
	 * @param prevNode
	 */
	public void setPrevious(PriorityNode<T> prevNode) {
		previous = prevNode;
		
	}
	/**
	 * sets priority of node using the parameter
	 * @param prio
	 */
	public void setPriority(double prio) {
		priority = prio;
		
	}
}
