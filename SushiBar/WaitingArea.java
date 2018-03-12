import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {

    private Queue<Customer> waitingAreaList = new LinkedList<Customer>();
    private int size;
    /**
     * Creates a new waiting area.
     * areaSize decides how many people can be waiting at the same time (how large the shared buffer is)
     *
     * @param size The maximum number of Customers that can be waiting.
     */
    public WaitingArea(int size) {
        // TODO Implement required functionality
        this.size=size;
/*
        for (int i=0; i<size; i++){
            waitingAreaList.add(null);
        }*/
        //Check for string type vs int type later
        //System.out.println("size of w area "+waitingAreaList.get(19));
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) throws InterruptedException {
        // TODO Implement required functionality
        while (this.isFull()) {
            wait();
        }
        if (SushiBar.isOpen) {
            waitingAreaList.add(customer);
            SushiBar.write("Customer "+customer.getCustomerID()+" is now waiting.");
            SushiBar.customerCounter.increment();

            //Testing
            System.out.println(this.waitingAreaList);

        }
        notifyAll();
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() throws InterruptedException {
        // TODO Implement required functionality
        //Todo: implement check for free room in sushibar
        while (waitingAreaList.isEmpty()){
            wait();
        }
        Customer customer = waitingAreaList.remove();
        SushiBar.write("Customer "+customer.getCustomerID()+" is now fetched.");
        notifyAll();
        return customer;
    }

    // Add more methods as you see fit

    public boolean isFull(){
        return waitingAreaList.size()>=size;
    }

    public boolean isEmpty(){
        return waitingAreaList.size()==0;
    }


}
