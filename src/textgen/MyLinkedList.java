package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.head=new LLNode<E>(null);
		this.tail=new LLNode<E>(null);
		this.size=0;
		
		this.head.next=this.tail;
		this.tail.prev=this.head;
		
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method

		if(element == null)
			throw new NullPointerException("null values are not allowed");
		
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> tempNode = tail.prev;
		tail.prev = newNode;
		tempNode.next = newNode;
		newNode.prev = tempNode;
		newNode.next = tail;
		
		this.size++;
				
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		
		if(index < 0 || index > (size-1) || size == 0)
			throw new IndexOutOfBoundsException("index is out of bounds.");
		
		if(index==0)
			return this.head.next.data;
		else if (index==size-1) {
			return this.tail.prev.data;
		}
		
		LLNode<E> foundNode=head.next;
		for(int i=1; i <= index; i++) {
			foundNode=foundNode.next;
		}

			
		
		return foundNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		
		if(element == null)
			throw new NullPointerException("null values are not allowed");
		
		// TODO: Implement this method
		if(index < 0 || index > (size-1))
			throw new IndexOutOfBoundsException("index is out of bounds.");
		//index in the first position
		if(index==0){
			LLNode<E> newNode = new LLNode<E>(element);
			LLNode<E> tempNode = head.next;
			
			head.next = newNode;
			tempNode.prev = newNode;
			newNode.prev = head;
			newNode.next = tempNode;
			this.size++;
			return;
		}
		//index the last position
		if(index==(size-1)){
			add(element);
			return;
		}
		
		LLNode<E> indexNode=head.next;
		for(int i=1; i <= index; i++) {
			indexNode=indexNode.next;
		}
		
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> beforeIndexNode=indexNode.prev;
		
		 indexNode.prev = newNode;
		 beforeIndexNode.next = newNode;
		
		 newNode.prev = beforeIndexNode;
		 newNode.next = indexNode;
		 this.size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index < 0 || index > (size-1) || size == 0)
			throw new IndexOutOfBoundsException("index is out of bounds.");
		
		E data=null;
		
		LLNode<E> foundNode=head;
		for(int i=0; i <= index; i++) {
			foundNode=foundNode.next;
		}
		
		data=foundNode.data;
		
		LLNode<E> nextNode = foundNode.next;
		LLNode<E> prevNode = foundNode.prev;
		
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		
		foundNode = null;
		
		this.size--;
		
		return data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		
		if(element == null)
			throw new NullPointerException("null values are not allowed");
		
		// TODO: Implement this method
		if(index < 0 || index > (size-1))
			throw new IndexOutOfBoundsException("index is out of bounds.");
		
		E data=null;
		
		LLNode<E> foundNode=head;
		for(int i=0; i <= index; i++) {
			foundNode=foundNode.next;
		}
		
		data = foundNode.data;
		foundNode.data = element;
		
		return data;
	}   
	
	
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();	
		LLNode<E> indexNode=head.next;
		for(int i=0; i < this.size(); i++) {
			sb.append(indexNode.data);
			sb.append(" ");
			indexNode=indexNode.next;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		MyLinkedList<String> shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		
		String test = shortList.get(1); 
		
		
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	

}

