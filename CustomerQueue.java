package eurovision;

/**
 * A queue class, implemented as a linked list.
 * The nodes of the linked list are implemnted as the CustomerElement class.
 * 
 * IMPORTANT: you may not use any loops/recursions in this class.
 */
public class CustomerQueue {

	CustomerElement first;
	CustomerElement last;

	/**
	 * Constructs an empty queue
	 */
	public CustomerQueue() {
		first = null;
		last = null;
	}

	/**
	 * Removes and returns the first element in the queue
	 *
	 * @return the first element in the queue
	 */
	public CustomerElement dequeue() {
		CustomerElement c;
		if (first == null) {//Queue is empty
			last = null;
			return null;
		} else {
			c = first;
			first = c.next;
			if (first != null) {
				first.prev = null;
			} else {
				last = null;
			}
		}
		return c;
	}

	/**
	 * Returns and does not remove the first element in the queue
	 *
	 * @return the first element in the queue
	 */
	public CustomerElement peek() {
		return first;
	}

	/**
	 * Adds a new element to the back of the queue
	 *
	 * @param node
	 */
	public void enqueue(CustomerElement node) {
		if(first == null){
			first = node;
			last = node;
		}else {
			last.next = node;
			node.prev = last;
			last = node;
		}
	}
}

