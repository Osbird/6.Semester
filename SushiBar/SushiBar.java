import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SushiBar {

    //SushiBar settings
    private static int waitingAreaCapacity = 20;
    private static int waitressCount = 10;
    private static int duration = 3;
    public static int maxOrder = 10;
    public static int waitressWait = 50; // Used to calculate the time the waitress spends before taking the order
    public static int customerWait = 3000; // Used to calculate the time the customer uses eating
    public static int doorWait = 100; // Used to calculate the interval at which the door tries to create a customer
    public static boolean isOpen = true;

    //Creating log file
    private static File log;
    private static String path = "./";

    //Variables related to statistics
    public static SynchronizedInteger customerCounter;
    public static SynchronizedInteger servedOrders;
    public static SynchronizedInteger takeawayOrders;
    public static SynchronizedInteger totalOrders;


    public static void main(String[] args) {
        log = new File(path + "log.txt");

        //Initializing shared variables for counting number of orders
        customerCounter = new SynchronizedInteger(0);
        totalOrders = new SynchronizedInteger(0);
        servedOrders = new SynchronizedInteger(0);
        takeawayOrders = new SynchronizedInteger(0);

        WaitingArea waitingAreaObj = new WaitingArea(waitingAreaCapacity);

        // Create waitress and door & pass the waitingArea to their constructor
        Door door = new Door(waitingAreaObj,customerWait,doorWait);
        Thread doorThread = new Thread(door);

        System.out.println(waitingAreaObj.isFull());

        List<Thread> waitressList = new ArrayList<Thread>();

        //make correct amount of waitresses
        for(int i=0;i<waitressCount;i++){
            Waitress waitress = new Waitress(waitingAreaObj, customerWait, waitressWait);
            Thread waitressThread = new Thread(waitress);
            waitressThread.start();
            waitressList.add(waitressThread);
        }
        doorThread.start();
        Clock timer = new Clock(duration);
/*
        for (Thread waitressThread: waitressList) {
                waitressThread.start();
        }
*/
        try{
            doorThread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        for (Thread waitressThread: waitressList) {
            try {
                waitressThread.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        write("1. Total number of orders: " + totalOrders.get());
        write("2. Total number of takeaway orders: " + takeawayOrders.get());
        write("3. Total number of orders that customers have eaten at the bar: " + servedOrders.get());

        // TODO initialize the bar and start the different threads
    }

    //Writes actions in the log file and console
    public static void write(String str) {
        try {
            FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Clock.getTime() + ", " + str + "\n");
            bw.close();
            System.out.println(Clock.getTime() + ", " + str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
