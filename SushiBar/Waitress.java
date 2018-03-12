import java.util.Random;

/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {
    private WaitingArea waitingArea;
    private int customerWait;
    private int waitressWait;
    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */
    Waitress(WaitingArea waitingArea, int customerWait, int waitressWait) {
        // TODO Implement required functionality
        this.waitingArea=waitingArea;
        this.customerWait=customerWait;
        this.waitressWait=waitressWait;


        //this.run();

        //Customers orders sushi when fetched by waitress
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
        // TODO Implement required functionality
        Random rNum = new Random();
        //System.out.println("Waitress run, wArea isempty "+waitingArea.isEmpty());
        //System.out.println("Waitress run sBar isOpen "+SushiBar.isOpen);
        while(SushiBar.isOpen || !waitingArea.isEmpty()) {
            //System.out.println("Waitress run inside while");
            try {
                Customer customer = waitingArea.next();
                customer.order();
                //System.out.println("Waitress run, try");
                try {
                    Thread.sleep(SushiBar.waitressWait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
