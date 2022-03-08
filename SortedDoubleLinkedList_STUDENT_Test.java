
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * Using the GregorianCalendar to create the list
 * Using a sorted list to remove, iterate, add, move cursor 
 * position back and add to front and end
 * @author djusu
 *
 */
public class SortedDoubleLinkedList_STUDENT_Test {

    GregorianMonthComparator comparators;
    GregorianCalendar s1, s2, s3, s4, s5;
    SortedDoubleLinkedList<GregorianCalendar> sortedLinked;
/**
 * setup of the sorted list use to for processing 
 * the sorted list in this code
 * @throws Exception
 */
    @Before
    public void setUp() throws Exception {
        s1 = new GregorianCalendar(2022, -1, 1); 
        s2 = new GregorianCalendar(2022, 3, 15); 
        s3 = new GregorianCalendar(2022, 5, 6);
        s4 = new GregorianCalendar(2022, 7, 13); 
        s5 = new GregorianCalendar(2022, 8, 23); 
        comparators = new GregorianMonthComparator();
        sortedLinked = new SortedDoubleLinkedList<GregorianCalendar>(comparators);
    }
    /**
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        comparators = null;
        sortedLinked = null;
    }
    /**
     * @return false if it did not throw an UnsupportedOperationException
     * and threw an a successful UnsupportedOperationException if it is true
     * otherwise throw a different exception
     */
    @Test
    public void addToEndTest() {
        try {
            sortedLinked.addToEnd(s1);
            assertTrue("Did not throw an UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("successfully threw an UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Will throw an exception other than UnsupportedOperationException", false);
        }
    }
    /**
     * @return false if it did not throw an UnsupportedOperationException
     * and threw an a successful UnsupportedOperationException if it is true
     * otherwise throw a different exception
     */
    @Test
    public void testAddToFront() {
        try {
            sortedLinked.addToFront(s1);
            assertTrue("Did not throw an UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw an UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Will throw an exception other than UnsupportedOperationException", false);
        }
    }
    /**
     * @return will return next element in the list
     */
    @Test
    public void iteratorSuccessfulMonth() {
        sortedLinked.add(s2);
        sortedLinked.add(s3);
        sortedLinked.add(s1);
        sortedLinked.add(s4);
        ListIterator<GregorianCalendar> iterator = sortedLinked.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(s1, iterator.next());
        assertEquals(s2, iterator.next());
        assertEquals(s3, iterator.next());
        assertEquals(true, iterator.hasNext());
    }
    /**
     * @return will return true if the list has many elements
     */
    @Test
    public void iteratorNoSuchElementExceptionNext() {
        sortedLinked.add(s2);
        sortedLinked.add(s3);
        sortedLinked.add(s1);
        sortedLinked.add(s4);
        ListIterator<GregorianCalendar> iterator = sortedLinked.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(s1, iterator.next());
        assertEquals(s2, iterator.next());
        assertEquals(s3, iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals(s4, iterator.next());
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
    /**
     * 
     * @return will return true if the list has many elements
     * To return the previous element and move cursor position back
     */
    @Test
    public void iteratorNoSuchElementExceptionTest() {
        sortedLinked.add(s2);
        sortedLinked.add(s3);
        sortedLinked.add(s1);
        sortedLinked.add(s4);
        ListIterator<GregorianCalendar> iterator = sortedLinked.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(s1, iterator.next());
        assertEquals(s2, iterator.next());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(s2, iterator.previous());
        assertEquals(s1, iterator.previous());
        
        try {
            //there is not element in the list
            iterator.previous();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Will throw an exception other than NoSuchElementException", false);
        }
    }
   /**
    * @param Will insert specified element in the sorted list
    */
    @Test
    public void iteratorUnsupportedOperationExceptionStringTest() {
        sortedLinked.add(s2);
        sortedLinked.add(s3);
        sortedLinked.add(s1);
        sortedLinked.add(s4);
        ListIterator<GregorianCalendar> iterator = sortedLinked.iterator();
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
    /**
     * @return To remove an item from the list
     */
    @Test
    public void addTest() {
        sortedLinked.add(s2);
        sortedLinked.add(s5);
        sortedLinked.add(s1);
        assertEquals(s1, sortedLinked.getFirst());
        assertEquals(s5, sortedLinked.getLast());
        sortedLinked.add(s3);
        sortedLinked.add(s4);
        assertEquals(s1, sortedLinked.getFirst());
        assertEquals(s5, sortedLinked.getLast());
       
        assertEquals(s5, sortedLinked.retrieveLastElement());
        assertEquals(s4, sortedLinked.getLast());
    }
    /**
     * @return will remove the first element from the list
     */
    @Test
    public void removeFirstTest() {
        sortedLinked.add(s2);
        sortedLinked.add(s4);
        assertEquals(s2, sortedLinked.getFirst());
        assertEquals(s4, sortedLinked.getLast());
        sortedLinked.add(s1);
        assertEquals(s1, sortedLinked.getFirst());
        // remove the first
        sortedLinked.remove(s1, comparators);
        assertEquals(s2, sortedLinked.getFirst());
    }
   /**
    * @param To insert element in correct position
    * To compare element for deletion 
    * and remove from the end of the list
    */
    @Test
    public void removeTest() {
        sortedLinked.add(s2);
        sortedLinked.add(s4);
        sortedLinked.add(s1);
        assertEquals(s1, sortedLinked.getFirst());
        assertEquals(s4, sortedLinked.getLast());
        //remove from the end of the list
        sortedLinked.remove(s4, comparators);
        assertEquals(s2, sortedLinked.getLast());
    }
    /**
     * @return To inser element in the list
     * @return To return the size of the list
     */
    @Test
    public void removeCenterTest() {
        sortedLinked.add(s2);
        sortedLinked.add(s4);
        sortedLinked.add(s1);
        assertEquals(s1, sortedLinked.getFirst());
        assertEquals(s4, sortedLinked.getLast());
        assertEquals(3, sortedLinked.getSize());
        //remove from middle of list
        sortedLinked.remove(s2, comparators);
        assertEquals(s1, sortedLinked.getFirst());
        assertEquals(s4, sortedLinked.getLast());
        assertEquals(2, sortedLinked.getSize());
    }

    private class GregorianMonthComparator implements Comparator<GregorianCalendar> {

        @Override
        public int compare(GregorianCalendar month1, GregorianCalendar month2) {
            // TODO Auto-generated method stub
            return month1.compareTo(month2);
        }

    }
}