import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class implements a customer, which is used for holding data and update the statistics
 *
 */
public class Customer {
    //private UUID uniqueID;
    private int id;
    /**
     *  Creates a new Customer.
     *  Each customer should be given a unique ID
     */
    public Customer(int id) {
        // TODO Implement required functionality
        //this.uniqueID = UUID.randomUUID();  dette gjorde det mindre lettelest under debugging
        this.id=id;
    }


    /**
     * Here you should implement the functionality for ordering food as described in the assignment.
     */
    public synchronized void order(){
        // TODO Implement required functionality
        //Todo :  some logic called from sushibar, or waitress
        //Eats some sushi at bar, takes rest with them

        Random rNum = new Random();
        Random rNum2 = new Random();
        int numberPiecesTot = rNum.nextInt(SushiBar.maxOrder) +1;
        int numberPiecesTakeAway = rNum2.nextInt(numberPiecesTot) +1;
        SushiBar.totalOrders.add(numberPiecesTot);
        SushiBar.servedOrders.add(numberPiecesTot-numberPiecesTakeAway);
        SushiBar.takeawayOrders.add(numberPiecesTakeAway);

        try {
            SushiBar.write("Customer "+this.id+"is now eating.");
            wait(SushiBar.customerWait);
            SushiBar.write("Customer "+this.id+"is now leaving.");
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

    }

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() {
        // TODO Implement required functionality
        return this.id;
    }

    // Add more methods as you see fit
}
