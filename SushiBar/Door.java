/**
 * This class implements the Door component of the sushi bar assignment
 * The Door corresponds to the Producer in the producer/consumer problem
 */
public class Door implements Runnable {
    WaitingArea waitingArea;

    /**
     * Creates a new Door. Make sure to save the
     * @param waitingArea   The customer queue waiting for a seat
     */
    public Door(WaitingArea waitingArea) {
        // TODO Implement required functionality
        // Todo: check if an OS tries to place a new customer and gets an exception or if it checks first
        this.waitingArea=waitingArea;
        this.run();
        //Open for specific amount of time, when customers can enter.
    }

    /**
     * This method will run when the door thread is created (and started)
     * The method should create customers at random intervals and try to put them in the customerQueue
     */
    @Override
    public void run() {



        if (!waitingArea.isFull()) {
            //waitingArea.
        }


    }

    // Add more methods as you see fit
}
