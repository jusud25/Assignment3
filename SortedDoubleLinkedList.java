import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T>extends BasicDoubleLinkedList<T> {
	private Comparator<T>comparator;
	public SortedDoubleLinkedList(Comparator<T>comparator){
		super();
		this.comparator=comparator;
	}
	/** 
	 * @param add To insert the specified element at the correct 
	 * position in the sorted list
	 */
	public void add(T add) {
		if (head == null || tail == null) {
			// Case when list is empty
			head = new Node(add);
			tail = head;

			return;
		}

		Node stars = new Node(add);
		Node current = head;

		while (current != null) {
			if (comparator.compare(current.data, add) > 0) {
				if (current == head) {
					stars.next = current;
					current.prev = stars;

					head = stars;
				} else {
					current.prev.next = stars;
					stars.prev = current.prev;

					stars.next = current;
					current.prev = stars;
				}

				return;
			}

			current = current.next;
		}

		tail.next = stars;
		stars.prev = tail;
		tail = stars;

	}
	/**
	 * @param data this is not supported
	 * @throws UnsupportedOperationException will be thrown to
	 * to indicate that the operation is supported
	 */
	public void addToEnd​(T data)throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	/**
	 * @param data this is not supported
	 * @throws UnsupportedOperationException will be thrown to
	 * to indicate that the operation is supported
	 */
	public void addToFront​(T data)throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	public ListIterator<T>iterator(){
		return super.iterator();	
	}

	public  Node remove(T data,Comparator<T> comparator){
		return super.remove(data, comparator);

	}
}

