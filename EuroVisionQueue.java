package eurovision;

/**
 * A eurovision queue which holds potential customer in two parllel lines.
 * One according to priority and one according to order of apperance.
 * At any given time, a customer standing at the front of either line may be removed from the data structure.
 */
public class EuroVisionQueue {

    /*
     * The heap and queue which compose the data structure.
     * The most important thing to observe is:
     * 	A customer element exists in the queue if and only if it also exists in the heap.
     */
    CustomerHeap heap;
    CustomerQueue q;

    /**
     * Creates an empty eurovision queue
     */
    public EuroVisionQueue() {
        heap = new CustomerHeap();
        q = new CustomerQueue();
    }


    /**
     * Adds a new customer to the data structure.
     * The customer is entered (wrapped by a customer element) to the back of the queue
     * and into the heap, according to its priority.
     *
     * @param c
     */
    public void addCustomer(Customer c) {
        CustomerElement newCustomer = new CustomerElement(c);
        heap.insert(newCustomer);
        q.enqueue(newCustomer);
    }

    /**
     * Removes the customer with the highest priority from the data structure.
     * The customer must be removed both from the heap and the queue.
     *
     * @return the customer with the highest priority.
     */
    public Customer servePriorityCustomer() {
        if (heap.size == 0) {
            return null;
        } else {
            CustomerElement maxPriority = heap.extractMax();

            if(this.q.peek() == maxPriority){
                this.q.dequeue();
            }
            else{
                CustomerElement node = maxPriority.prev;
                node.next = maxPriority.next;
                if(q.last == maxPriority){
                    q.last = node;
                }
            }

            return maxPriority.c;
        }
    }

    /**
     * Removes the customer which arrived first to the data structure.
     * The customer must be removed both from the heap and the queue.
     *
     * @return the customer which arrived first to the data structure
     */
    public Customer serveRegularCustomer() {
        if (heap.size == 0) {
            return null;
        } else {
            Customer firstCustomer = q.peek().c;
            heap.remove(q.dequeue().heapIndex);
            return firstCustomer;
        }
    }

    public static void main(String[] args) {

        /*
         * A basic test to check your class.
         * Expected outcome:
         * 	customer: Liran GreyShirt, priority: 20
         *	customer: Netta Barzilay, priority: 10
         *	customer: Izhar Ashdot, priority: 2
         *	customer: Alan Turing, priority: 100
         *  customer: Dana International, priority: 3
         */
        EuroVisionQueue q = new EuroVisionQueue();
        Customer a = new Customer(10, "Netta Barzilay");
        Customer b = new Customer(2, "Izhar Ashdot");
        Customer c = new Customer(3, "Dana International");
        Customer d = new Customer(20, "Liran GreyShirt");
        Customer e = new Customer(100, "Alan Turing");


        q.addCustomer(a);
        q.addCustomer(b);
        q.addCustomer(c);
        q.addCustomer(d);
        System.out.println(q.servePriorityCustomer());
        System.out.println(q.servePriorityCustomer());
        q.addCustomer(e);
        System.out.println(q.serveRegularCustomer());
        System.out.println(q.servePriorityCustomer());
        System.out.println(q.serveRegularCustomer());
    }
}
