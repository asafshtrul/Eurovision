package eurovision;

import java.util.NoSuchElementException;

/**
 * A heap, implemented as an array.
 * The elements in the heap are instances of the class CustomerElement,
 * and the heap is ordered according to the Customer instances wrapped by those objects.
 * <p>
 * IMPORTANT: Except in the constructor no single function may loop/recurse through all elements in the heap.
 * You may only use loops which look at a small fraction of the heap.
 */
public class CustomerHeap {

    /*
     * The array in which the elements are kept according to the heap order.
     * The following must always hold true:
     * 			if i < size then heap[i].heapIndex == i
     */
    CustomerElement[] heap;
    int size; // the number of elements in the heap, neccesarilty size <= heap.length

    /**
     * Creates an empty heap which can initally accomodate 10 elements.
     */
    public CustomerHeap() {
        this.heap = new CustomerElement[10];
        this.size = 0;
    }

    /**
     * Returns the size of the heap.
     *
     * @return the size of the heap
     */
    public int size() {
        return size;
    }

    /**
     * Inserts a given element into the heap.
     *
     * @param e - the element to be inserted.
     */
    public void insert(CustomerElement e) {
        //if heap is full - extends it by twice the number of slots;
        if (size + 1 == heap.length) {
            CustomerElement[] extendedHeap = new CustomerElement[size * 2];
            for (int i = 1; i <= size; i++) {
                extendedHeap[i] = heap[i];
            }
            heap = extendedHeap;
        }
        percUp(size + 1, e);
        size++;
    }

    //Help function for deletion of elements from the heap:
    private void percDown(int i, CustomerElement x) {
        int j;

        if (2 * i > size) {                                     //Case 1: A Leaf - inserts x in this position
            heap[i] = x;
            x.heapIndex = i;
        } else if (2 * i == size) {                             //Case 2: A single child - replaces x with child unless child has smaller priority
            if (heap[2 * i].c.priority > x.c.priority) {
                heap[i] = heap[2 * i];
                heap[2 * i] = x;
                x.heapIndex = 2 * i;
                heap[i].heapIndex = i;
            } else {
                heap[i] = x;
                x.heapIndex = i;
            }
        } else if (2 * i < size) {                              //Case 3: two children, if x has smaller priority - calls recursion method for PercDown, if not, switches with higher priority child
            if (heap[2 * i].c.priority > heap[(2 * i) + 1].c.priority) {
                j = 2 * i;
            } else {
                j = (2 * i) + 1;
            }
            if (heap[j].c.priority > x.c.priority) {
                heap[i] = heap[j];
                heap[i].heapIndex = i;
                percDown(j, x);
            } else {
                heap[i] = x;
                x.heapIndex = i;
            }
        }
    }

    //Help function for insertion of elements to the heap:
    public void percUp(int i, CustomerElement x) {
        if (i == 1) {                                           //Case 1: heap is empty - inserts Element to top of heap
            heap[1] = x;
            x.heapIndex = i;
        } else {
            int p = i / 2;
            if (heap[p].c.priority > x.c.priority) {            //Case 2: Checks if "father Node" has greater element than child node. if not - inserts the element.
                heap[i] = x;
                x.heapIndex = i;
            } else {
                heap[i] = heap[p];
                heap[i].heapIndex = i;
                percUp(p, x);
            }
        }
    }


    /**
     * Returns and does not remove the element which wraps the customer with maximal priority.
     *
     * @return the element which wraps the customer with maximal priority.
     */
    public CustomerElement findMax() {
        return heap[1];
    }

    /**
     * Returns and removes the element which wraps the customer with maximal priority.
     *
     * @return the element which wraps the customer with maximal priority.
     */
    public CustomerElement extractMax() {
        if (size == 0) {
            return null;
        } else {
            CustomerElement max = heap[1];
            remove(1); //the remove method will deal with re-organizing the heap
            return max;
        }
    }

    /**
     * Removes the element located at the given index.
     * <p>
     * Note: this function is not part of the standard heap API.
     * Make sure you understand how to implement it why it is required.
     * There are several ways this function could be implemented.
     * No matter how you choose to implement it, you need to consider the different possible edge cases.
     *
     * @param index
     */
    public void remove(int index) {
        if (index > size || index <= 0) {                               //Case 1: User inserted Illegal index, throws exception
            throw new IndexOutOfBoundsException("Illegal index");
        } else if (index == size) {                                            //Case 2: if user tries to remove the last leaf
            heap[index] = null;
            size--;
            return;
        } else if (index == 1) {
            heap[index] = heap[size];
            heap[index].heapIndex = size;
            heap[size] = null;
            size--;
            percDown(index, heap[index]);
            return;
        }

        heap[index] = heap[size];
        heap[index].heapIndex = size;
        heap[size] = null;
        size--;
        if (heap[index].c.priority > heap[index / 2].c.priority) {
            percUp(index, heap[index]);
        } else {
            percDown(index, heap[index]);
        }
    }



    public static void main(String[] args) {
        /*
         * A basic test for the heap.
         * You should be able to run this before implementing the queue.
         *
         * Expected outcome:
         * 	customer: Netta, priority: 10
         *	customer: Liran, priority: 20
         *	customer: Liran, priority: 20
         *	customer: Netta, priority: 10
         *	customer: Dana, priority: 3
         *	customer: Izhar, priority: 2
         *
         */
        CustomerHeap heap = new CustomerHeap();
        Customer a = new Customer(10, "Netta");
        Customer b = new Customer(2, "Izhar");
        Customer c = new Customer(3, "Dana");
        Customer d = new Customer(22, "Liran");
        Customer e = new Customer(20, "Asaf");
        Customer f = new Customer(25, "Shimon");
        Customer g = new Customer(27, "Shoken");
        Customer h = new Customer(34, "Yael");
        Customer i = new Customer(7549, "BiBi");
        Customer j = new Customer(2234, "Benni");
        Customer k = new Customer(5, "Omri");


        heap.insert(new CustomerElement(a));
        System.out.println(heap.findMax());

        heap.insert(new CustomerElement(b));
        heap.insert(new CustomerElement(c));
        heap.insert(new CustomerElement(d));
        heap.insert(new CustomerElement(e));
        heap.insert(new CustomerElement(f));
        heap.insert(new CustomerElement(g));
        heap.insert(new CustomerElement(h));
        heap.insert(new CustomerElement(i));
        heap.insert(new CustomerElement(j));
        heap.insert(new CustomerElement(k));
        System.out.println(heap.findMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
    }
}
