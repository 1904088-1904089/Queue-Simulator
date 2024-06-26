public class Main {
    public static void main(String[] args) {


        int durationMinutes = 100;

        // BankQueue simulation
        System.out.println("Bank Queue Simulation Results:");
        QueueSimulator bankQueueSimulator = new QueueSimulator(2,5); // 3 tellers, max queue length 5
        bankQueueSimulator.simulate(durationMinutes);
        bankQueueSimulator.printStatistics();
    }
}
