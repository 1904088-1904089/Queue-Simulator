import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QueueSimulator {
    private int totalCustomersArrived;
    private int totalCustomersServed;
    private int totalCustomersLeft;
    private long totalServiceTime;

    private BankQueue bankQueue;

    public QueueSimulator(int numTellers, int maxBankQueueLength) {
        this.bankQueue = new BankQueue(numTellers, maxBankQueueLength);
    }

    public void simulate(int durationMinutes) {
        int currentTime = 0;
        int endTime = durationMinutes * 60;

        while (currentTime < endTime) {
            int nextArrival = ThreadLocalRandom.current().nextInt(20, 61); // 20 to 60 seconds
            currentTime += nextArrival;

            Customer customer = new Customer(currentTime);
            totalCustomersArrived++;

            boolean addedToBankQueue = bankQueue.addCustomer(customer);
           

            if (!addedToBankQueue ) {
                totalCustomersLeft++;
                customer.setWasServed(false);
            }

            // Serve customers at bank tellers and grocery cashiers
            for (int i = 0; i < bankQueue.tellers; i++) {
                Customer servedCustomer = bankQueue.serveCustomer();
                if (servedCustomer != null) {
                    totalCustomersServed++;
                    totalServiceTime += servedCustomer.getServiceTime();
                }
            }

           
            currentTime++;
        }
    }

    public void printStatistics() {
        double averageServiceTime = (double) totalServiceTime / totalCustomersServed;
        System.out.println("Total customers arrived: " + totalCustomersArrived);
        System.out.println("Total customers served: " + totalCustomersServed);
        System.out.println("Total customers left without service: " + totalCustomersLeft);
        System.out.println("Average service time: " + averageServiceTime + " seconds");
    }
}
