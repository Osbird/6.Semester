import java.util.Timer;
import java.util.Random;
import java.util.TimerTask;
/**
 * This class implements the Door component of the sushi bar assignment
 * The Door corresponds to the Producer in the producer/consumer problem
 */
public class Door implements Runnable  {
    private WaitingArea waitingArea;
    private static Timer timer= new Timer();
    private int doorWait;
    private int customerWait;

    /**
     * Creates a new Door. Make sure to save the
     * @param waitingArea   The customer queue waiting for a seat
     * @param doorWait
     * @param customerWait
     */
    public Door(WaitingArea waitingArea, int customerWait, int doorWait) {
        // TODO Implement required functionality
        // Todo: check if an OS tries to place a new customer and gets an exception or if it checks first
        this.waitingArea=waitingArea;
        this.doorWait = doorWait;
        this.customerWait = customerWait;

        /*
        Random generator = new Random();
        int interval;

        this.run();
        //Open for specific amount of time, when customers can enter.
        */
    }

    /**
     * This method will run when the door thread is created (and started)
     * The method should create customers at random intervals and try to put them in the customerQueue
     */
/*
    private class Task extends TimerTask{
        @Override
        public void run() {
            int delay = (new Random().nextInt(doorWait)+1);
            timer.schedule(new Task(), delay);
            if (!waitingArea.isFull()) {
                //waitingArea.
            }
        }
    }
  */

    @Override
    public void run() {
        Random rNum = new Random();
        int idCounter = 0;

        while (SushiBar.isOpen) {
            try {
                Thread.sleep(rNum.nextInt(SushiBar.doorWait));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Customer customer = new Customer(idCounter);
            idCounter++;
            try {
                waitingArea.enter(customer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    // Add more methods as you see fit
}
