/**
* Link establishes a Link object that can link to next
* @author Ricardo Carrasco
* @version 3/7/2021
*/
class Link{
	public State state;
	public Link next;
	/**
	* Constructor for Link object
	* @param state
	*/
	public Link(State state) {
		this.state = state;
	}
	/**
	* Prints state object in the link object
	*/
	public void displayLink() {
		this.state.print();
	}
}
/**
* LinkList establishes a double ended linked list data structure by implementing the link class
* @author Ricardo Carrasco
* @version 3/7/2021
*/
class LinkList{
	public Link first;
	public Link last;
	/**
	* Constructor method for LinkList object
	*/
	public LinkList() {
		first = null;
		last = null;
	}
	/**
	* Checks if LinkList is empty
	* @return true or false
	*/
	public boolean isEmpty() {
		return (first == null);
	}
	/**
	* inserts a Link(state) into the front of the list
	*/
	public void insertFirst(State state) {
		if (isEmpty()) {
			Link newLink = new Link(state);
			first = newLink;
			last = newLink;
		}
		else {
		Link newLink = new Link(state);
		newLink.next = first;
		first = newLink;
		}
	}
	/**
	* Deletes and returns the state object of the first link
	* @return State object
	*/
	public State deleteFirst(){
		if(isEmpty()) {
			return null;
		}
		Link temp = first;
		first = first.next;
		if(isEmpty()) {
			last = null;
		}
		return temp.state;
	}
	/**
	* Recursively prints the state object for all links (current -> last)
	* @param current link that should be set to first
	*/
	public void displayList(Link current) {
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
* Stack implements LinkList to create a Stack data structure 
* @author Ricardo Carrasco
* @version 3/7/2021
*/
public class Stack {
	LinkList stateList;
	/**
	* Constructor method for Stack object
	*/
	public Stack() {
		stateList = new LinkList();
	}
	/**
	* pushes a state into the top of the stack (front of the Linked list) using insertFirst from LinkList
	*/
	public void push(State state) {
		stateList.insertFirst(state);
	}
	/**
	* removes and returns the state at the top of the Stack
	* @return State object
	*/
	public State pop() {
		return stateList.deleteFirst();
	}
	/**
	* prints the Stack of state objects using the recursive method from LinkList
	*/
	public void printStack() {
		if(stateList.isEmpty()) {
			System.out.println("\nStack is empty");
		}
		else {
			System.out.println("\nName\t\tMHI\t\tVCR\t\tCFR\t\tCase Rate\tDeath Rate");
			System.out.println("------------------------------------------------------------------------------------------");
			Link current  = stateList.first;
			stateList.displayList(current);
		}
	}
	/**
	* Checks if the Stack is empty using isEmpty from LinkList
	*/
	public boolean isEmpty() {
		return(stateList.isEmpty());
	}
}
