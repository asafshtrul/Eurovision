package eurovision;

public class Customer {
	int priority;
	String name;
	
	/**
	 * A standard constructor for the customer class
	 * 
	 * @param priority
	 * @param name
	 */
	
	public Customer(int priority, String name){
		this.name = name;
		this.priority = priority;
	}

	/**
	 * Compares this customer to another customer.
	 * This customer is considered smaller than the other customer if and only if
	 * The priority of this customer is smaller than the other customer or the priorities are equal 
	 * and the name of this customer is smaller in the lexicoraphgic ordering than the name of the other customer.
	 * 
	 * If this customer is smaller returns a negative number. If this customer is bigger return a positive number.
	 * If the customers are equal return 0.
	 * 
	 * 
	 * @param other
	 * @return a negative/positive or zero number of this customer is smaller/greater or equal to other
	 */
	public int compareTo(Customer other) {
		if(other.priority < this.priority){
			return 1;
		} else if (other.priority > this.priority){
			return -1;
		}else{
			// Lexicographic comparision:
			for(int i = 0; i < Math.min(this.name.length(),other.name.length()); i++) {
				if (this.name.charAt(i) < other.name.charAt(i)) {
					return 1;
				}
				if (this.name.charAt(i) > other.name.charAt(i)) {
					return -1;
				}
			}
			if(this.name.length() < other.name.length()){
				return 1;
			}
			else if(this.name.length() > other.name.length()){
				return -1;
			}
		}
		return 0;
	}
	
	
	public String toString(){
		return "customer: " + this.name + ", priority: " + this.priority;
	}
}

