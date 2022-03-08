
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * the usage of String and Double in full swing in this JUnit Test
 * @author djusu
 *
 */
public class BasicDoubleLinkedList_STUDENT_Test {

    BasicDoubleLinkedList<String> stringchecks;
    BasicDoubleLinkedList<Double> doubleChecks;
    StringComparator forString;
    DoubleComparator forDouble;

    @Before
    public void setUp() throws Exception {
    	//For String
        stringchecks = new BasicDoubleLinkedList<String>();
       stringchecks.addToEnd("Hello");
        forString = new StringComparator();

        //For double
        doubleChecks = new BasicDoubleLinkedList<Double>();
        doubleChecks.addToEnd(15.0);
        doubleChecks.addToEnd(100.0);
        forDouble = new DoubleComparator();

    }

    @After
    public void tearDown() throws Exception {
        stringchecks = null;
        forString = null;
    }

    @Test
    public void getetSizeTest() {
        assertEquals(1, stringchecks.getSize());
        assertEquals(2, doubleChecks.getSize());
    }
    /**
     * @param To add to list and update
     * @return will return but not remove from the list
     */
    @Test
    public void addToEndTest() {
       
        doubleChecks.addToEnd(20.0);
        assertEquals(20, doubleChecks.getLast().intValue());

        doubleChecks.addToEnd(30.0);
        assertEquals(30, doubleChecks.getLast().intValue());
    }
     /**
      * @return add to the front of the list
      */
    @Test
    public void addToFrontTest() {
    	
        assertEquals("Hello", stringchecks.getFirst());//hello
        stringchecks.addToFront("start");
        assertEquals("start", stringchecks.getFirst());
    	
        
    }
    @Test
    public void testGetFirst() {
    	 assertEquals("Hello", stringchecks.getFirst());
         stringchecks.addToFront("new");
         assertEquals("new", stringchecks.getFirst());
    
    }

    @Test
    public void toArrayListTest() {
        ArrayList<String> list;
        stringchecks.addToFront("Begin");
        stringchecks.addToEnd("End");
        list = stringchecks.toArrayList();
        assertEquals("Begin", list.get(0));
        assertEquals("End", list.get(1));
    }

    @Test
    public void testToArray() {
    
        doubleChecks.addToEnd(20.0);
     
        ArrayList<Double> list = doubleChecks.toArrayList();
        assertEquals(15, list.get(0).intValue());
        assertEquals(100, list.get(1).intValue());
        assertEquals(20, list.get(2).intValue());
    }
    @Test
    public void testIteratorSuccessfulPrevious() {
        stringchecks.addToFront("start");
        stringchecks.addToEnd("ends");
        ListIterator<String> iterator = stringchecks.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals("start", iterator.next());
        assertEquals("start", iterator.next());
        assertEquals(true, iterator.next());
        assertEquals("end", iterator.hasPrevious());
        assertEquals("start", iterator.previous());
        assertEquals("Hey", iterator.previous());
    }

    @Test
    public void iteratorSuccessfulTest() {
       
        doubleChecks.addToEnd(20.0);

        ListIterator<Double> iterator = doubleChecks.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals(15, iterator.next().intValue());
        assertEquals(100, iterator.next().intValue());
        assertEquals(20, iterator.next().intValue());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(20, iterator.previous().intValue());
        assertEquals(15, iterator.previous().intValue());
        assertEquals(100, iterator.previous().intValue());
    }

    @Test
    public void testIteratorNoSuchElementExceptionNext() {
        stringchecks.addToFront("Front");
        stringchecks.addToEnd("End");
        ListIterator<String> iterator = stringchecks.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals("Front", iterator.next());
        assertEquals("Good", iterator.next());
        assertEquals("Morning", iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals("End", iterator.next());
        try {
            //no more elements in list
            iterator.next();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }

    }

    @Test
    public void testIteratorNoSuchElementExceptionStudent() {
        //test the iterator for the linkedDouble.  Exception raised
        //when next is called after last element.
        //be throughal, use the preceeding test method as an example
        doubleChecks.addToEnd(20.0);
        
        ListIterator<Double> iterator = doubleChecks.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(15, iterator.next().intValue());
        assertEquals(100, iterator.next().intValue());
        assertEquals(20, iterator.next().intValue());
        assertEquals(false, iterator.hasNext());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(20, iterator.previous().intValue());
        assertEquals(100, iterator.previous().intValue());
        assertEquals(15, iterator.previous().intValue());

        try {
            //if there is no more elements in list
            iterator.previous();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }
    }
    @Test
    public void iteratorUnsupportedOperationExceptionTest() {
        doubleChecks.addToEnd(20.0);
        ListIterator<Double> iterator = doubleChecks.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(15, iterator.next().intValue());
        assertEquals(100, iterator.next().intValue());
        assertEquals(true, iterator.hasNext());
        assertEquals(20, iterator.next().intValue());

        try {
            //remove is not supported for the iterator
            iterator.remove();
            assertTrue("Did not throw a UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw a UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }
    @Test
    public void removeTest() {
        doubleChecks.addToEnd(20.0);
        doubleChecks.addToEnd(30.0);

        // Remove the first
        assertEquals(15, doubleChecks.getFirst().intValue());
        doubleChecks.remove(15.0, forDouble);
        assertEquals(100, doubleChecks.getFirst().intValue());

        // Remove middle
        doubleChecks.remove(20.0, forDouble);
        assertEquals(100, doubleChecks.getFirst().intValue());
        assertEquals(30, doubleChecks.getLast().intValue());

        // Remove last
        doubleChecks.remove(30.0, forDouble);
        assertEquals(100, doubleChecks.getFirst().intValue());
        assertEquals(100, doubleChecks.getLast().intValue());
    }

    @Test
    public void retrieveFirstElementTest() {
        assertEquals("Hello", stringchecks.getFirst());
        stringchecks.addToFront("New");
        assertEquals("New", stringchecks.getFirst());
        assertEquals("New", stringchecks.retrieveFirstElement());
        assertEquals("Hello", stringchecks.getFirst());
        assertEquals("Hello", stringchecks.retrieveFirstElement());
        assertEquals("World", stringchecks.getFirst());

    }
    @Test
    public void retrieveLastElementTest() {
        doubleChecks.addToEnd(20.0);

        assertEquals(20, doubleChecks.getLast().intValue());
        assertEquals(20, doubleChecks.retrieveLastElement().intValue());
        assertEquals(100, doubleChecks.getLast().intValue());
    }

    private class StringComparator implements Comparator<String> {

        @Override
        public int compare(String arg0, String arg1) {
            // TODO Auto-generated method stub
            return arg0.compareTo(arg1);
        }

    }

    private class DoubleComparator implements Comparator<Double> {

        @Override
        public int compare(Double str, Double arg1) {
            // TODO Auto-generated method stub
            return str.compareTo(arg1);
        }

    }
}