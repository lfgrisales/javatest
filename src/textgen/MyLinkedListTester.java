/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
		//System.out.println("executed before");
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		System.out.println(list1.toString());
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		
		// TODO: Add more tests here
		
		int b = list1.remove(1);
		assertEquals("Remove: check a is correct ", 42, b);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 1, list1.size());
		assertEquals("Remove: correctly updated nextnode.prev ", (Integer)21, list1.tail.prev.data);
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		int size=list1.size();
		list1.add(100);
		assertEquals("add at end 100 ", (Integer)100, list1.get(list1.size-1));
		list1.add(101);
		assertEquals("add at end 101", (Integer)101, list1.get(list1.size-1));
		assertEquals("size updated", (Integer)size+2, list1.size());
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
	
		
		
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		int size=list1.size();
		int index=1;
		int indexNodeData = list1.get(index);
		int beforeNodeData = list1.get(index-1);
		//int nextNodeData = list1.get(index+1);
		
		list1.add(index, 77);
		
		assertEquals("added at position index", (Integer)77, list1.get(index));
		assertEquals("added at position index", (Integer)65, list1.get(index-1));
		assertEquals("added at position index", (Integer)21, list1.get(index+1));
		assertEquals("size updated", (Integer)size+1, list1.size());
		System.out.println(list1.toString());
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
	    
		list1.add(88);
		
		System.out.println(list1.toString());
		
		int firstUpdated = list1.set(0, 77);
		int secondUpdated = list1.set(2, 70);
		int actualSize = list1.size();
		
		assertEquals("set: old first updated", (Integer)firstUpdated, (Integer)65);
		assertEquals("set: old second updated", (Integer)secondUpdated, (Integer)42);
		
		assertEquals("set: new first value", (Integer)77, list1.get(0));
		assertEquals("set: new third value", (Integer)70, list1.get(2));
		
		assertTrue(list1.size()==(Integer)actualSize);
		
		System.out.println(list1.toString());
		
	}
	
	
	// TODO: Optionally add more test methods.
	
}
