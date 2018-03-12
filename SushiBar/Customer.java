import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class implements a customer, which is used for holding data and update the statistics
 *
 */
public class Customer {
    private UUID uniqueID = UUID.randomUUID();
    /**
     *  Creates a new Customer.
     *  Each customer should be given a unique ID
     */
    public Customer() {
        // TODO Implement required functionality

        //Eats some sushi at bar, takes rest with them
    }


    /**
     * Here you should implement the functionality for ordering food as described in the assignment.
     */
    public synchronized void order(){
        // TODO Implement required functionality
        //Todo :  some logic called from sushibar, or waitress
    }

    /**
     *
     * @return Should return the customerID
     */
    public UUID getCustomerID() {
        // TODO Implement required functionality
        return uniqueID;
    }

    // Add more methods as you see fit
}
