package Assignment_10022025;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;
import static java.time.temporal.ChronoUnit.MILLIS;

class Biker implements Runnable {
    private static final CountDownLatch startLatch = new CountDownLatch(1);
    private static final ConcurrentSkipListSet<Biker> winners = new ConcurrentSkipListSet<>(
            (b1, b2) -> Long.compare(b1.timeTaken, b2.timeTaken));

    private final String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private long timeTaken;
    private int speed = 0;
    private static final int RACE_DISTANCE = 50;
    private final Random random = new Random();

    Biker(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println("Biker " + name + " is ready to start the race");

        try {
            startLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        startTime = LocalTime.now();
        System.out.println(name + " started at: " + startTime);

        int distanceCovered = 0;
        while (distanceCovered < RACE_DISTANCE) {
            updateSpeed();
            distanceCovered += speed > 0 ? speed : 1;

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            if (distanceCovered % 10 == 0) {
                System.out.println(name + " has reached " + distanceCovered + " meters");
            }
        }

        endTime = LocalTime.now();
        timeTaken = MILLIS.between(startTime, endTime);
        System.out.println("Biker " + name + " finished in " + timeTaken + " ms");

        winners.add(this);
    }

    private void updateSpeed() {
        int choice = random.nextInt(3);
        if (choice == 0) {
            speed = Math.min(speed + 2, 5);
        } else if (choice == 1 && speed > 0) {
            speed--;
        }
    }

    public String getResults() {
        return String.format("%-8s | Start: %-12s | End: %-12s | Time: %4d ms",
                name, startTime, endTime, timeTaken);
    }

    public static ConcurrentSkipListSet<Biker> getWinners() {
        return winners;
    }

    public static void startRace() {
        startLatch.countDown();
    }
}

public class BikeRace {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Biker[] bikers = {
                new Biker("Shoyab"), new Biker("Sanjana"), new Biker("Kaif"),
                new Biker("Sanchit"), new Biker("Sarvesh"), new Biker("Omkar"),
                new Biker("Aditi"), new Biker("Atharva"), new Biker("Nidhi"),
                new Biker("Hina")
        };

        System.out.println("Bike Racing Game\n-----------------");
        System.out.println("Starting countdown:");

        for (int i = 10; i > 0; i--) {
            System.out.println(i + "...");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("GO!\n");

        // Submit all bikers to the executor
        Arrays.stream(bikers).forEach(executor::execute);

        // Start the race by releasing the latch
        Biker.startRace();

        executor.shutdown();

        if (!executor.awaitTermination(2, TimeUnit.MINUTES)) {
            executor.shutdownNow();
        }

        System.out.println("\nRace Results:");
        System.out.println("---------------------------------------------------");
        System.out.println("Rank | Biker    | Time Taken (ms)");
        System.out.println("---------------------------------------------------");

        int rank = 1;
        for (Biker biker : Biker.getWinners()) {
            System.out.printf("%2d  | %s\n", rank++, biker.getResults());
        }
        System.out.println("---------------------------------------------------");
    }
}