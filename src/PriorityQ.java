/**
* DLink: this class establishes a doubly-linked Link object that can access both next and previous
* @author Ricardo Carrasco
* @version 3/7/2021
*/
class DLink{
	public State state;
	public DLink next;
	public DLink previous;
	/**
	* Constructor for DLink class, creates DLink Object with state as its "Data"
	* @param State object
	*/
	public DLink(State state) {
		this.state = state;
	}
	/**
	* displayLink prints the state object inside DLink using the print() method in State.java
	*/
	public void displayLink() {
		this.state.print();
	}
}
/**
* DoublyLL: establishes a sorted double ended doubly linked list data structure
* Sorts on insert and removes from the front of the list
* @author Ricardo Carrasco
* @version 3/7/2021
*/
class DoublyLL{
	public DLink first;
	public DLink last;
	/**
	* DoublyLL() is the constructor method for the DoublyLL object, takes no parameters.
	*/
	public DoublyLL() {
		first = null;
		last = null;
	}
	/**
	* isEmpty() checks if the linked list is empty using a boolean value
	* @return true or false
	*/
	public boolean isEmpty() {
		return((first == null) && (last == null));
	}
	/**
	* insert() sorts and inserts a link  into the linked list based on the priority of its State object;
	* @param State object
	*/
	public void insert(State state) {
		DLink newLink = new DLink(state);
		if(isEmpty()) {
			first = newLink;
			last = newLink;
		}
		else {
			DLink current = first;
			while((current != null) && (current.state.deathRate() < state.deathRate())) {
				current = current.next;
			}
			if(current == first) {
				first.previous = newLink;
				newLink.next = first;
				first = newLink;
			}
			else if(current == null) {
				last.next = newLink;
				newLink.previous = last;
				last = newLink;
			}
			else {
				newLink.next = current;
				current.previous.next = newLink;
				newLink.previous = current.previous;
				current.previous = newLink;
			}
		}
	}
	/**
	* removeFirst() removes and returns the first link in the linked-list
	* @return State object of the first link
	*/
	public DLink removeFirst(){
		if(isEmpty()) {
			return null;
		}
		DLink temp = first;
		first = first.next;
		if(first == null) {
			last = null;
		}
		else {
			first.previous = null;
		}
		return temp;
	}
	/**
	* dIntervalDelete() removes links whose states death rates are between a & b (inclusive)
	* @param int a,b. a must be bigger
	* @return true if deletions were found, false if not.
	*/
	public boolean dIntervalDelete(int a, int b){
		if(isEmpty()) {
			return false;
		}
		DLink A = first;
		while((A != null) && (A.state.deathRate() < a)) {
			A = A.next;
		}
		DLink B = last;
		while((B != null) && (B.state.deathRate() > b)) {
			B = B.previous;
		}
		if((A == null) || (B == null)) {
			return false;
		}
		else if((A == first) && (B == last)) {
			first = null;
			last = null;
			return true;
		}
		else if((A == first) && (B != last)) {
			first = B.next;
			B.next.previous = null;
			return true;
		}
		else if((B == last) && (A != first)) {
			last = A.previous;
			A.previous.next = null;
			return true;
		}
		else{
			A.previous.next = B.next;
			B.next.previous = A.previous;
			return true;
		}

	}
	/**
	* displayList() is a recursive method that prints the state object from all links (current -> end)
	* @param current: a starter link that should be initialized at first;
	*/
	public void displayList(DLink current) {
		current.displayLink();
		if(current == last) {
			return;
		}
		else {
			current = current.next;
			displayList(current);
		}
	}
}
/**
* PriorityQ class implements DoublyLL to serve as a blueprint for a priority queue and uses its methods to alter the queue
* @author Ricardo Carrasco
* @version 3/7/2021
*/
public class PriorityQ {
	DoublyLL statesPriorityQ;
	/**
	* PriorityQ() is the constructor method for PriorityQ, creates a new DoublyLL object
	*/
	public PriorityQ() {
		statesPriorityQ = new DoublyLL();
	}
	/**
	* insert(state) method takes a state and inserts it into the queue based on its priority from its death rate.
	* @param State object
	*/
	public void insert(State state) {
		statesPriorityQ.insert(state);
	}
	/**
	* remove() uses the removeFirst method from DoublyLL to remove and return the first State in the queue
	* @return State object
	*/
	public DLink remove() {
		return statesPriorityQ.removeFirst();
	}
	/**
	* isEmpty() uses the isEmpty() method from DoublyLL to check if the queue is empty
	* @return true or false
	*/
	public boolean isEmpty() {
		return statesPriorityQ.isEmpty();
	}
	/**
	* printPriorityQ() prints a header and uses displayList to recursively print all state object in the queue
	*/
	public void printPriorityQ(){
		if(statesPriorityQ.isEmpty()) {
			System.out.println("\nPriorityQ is empty");
		}
		else {
			System.out.println("\nName\t\tMHI\t\tVCR\t\tCFR\t\tCase Rate\tDeath Rate");
		System.out.println("------------------------------------------------------------------------------------------");
		DLink current = statesPriorityQ.first;
		statesPriorityQ.displayList(current);
		}	
	}
	/**
	* intervalDelete() uses dIntervalDelete from DoublyLL to delete states whose death rates fall between a -> b (Inclusive)
	* @return true or false if deletions were made
	*/
	public boolean intervalDelete(int a, int b) {
		return statesPriorityQ.dIntervalDelete(a,b);
	}
}
