/*
    Bike Racing Game
    -----------------
    * 10 Bikers with names and their start time
    * Countdown 10, 9, 8, ...... GO
    * Every biker when reaching the finish line mark their end time and ranking
    * Display the bikers list in ascending order of their rankings 
    * Display rankings along with start time, end time and time-taken
 */

package Assignment_17012025;

import java.time.LocalTime;
import java.util.Random;
import static java.time.temporal.ChronoUnit.MILLIS;;;

class Biker extends Thread {
    static Biker[] winners = new Biker[10];
    static int won = 0;
    static boolean raceStarted = false;
    private String name;
    static final Object lock = new Object();
    private LocalTime startTime = LocalTime.now();
    private LocalTime endTime = LocalTime.now();
    private int ranking;
    private int speed = 0;
    private static final int RACEDISTANCE = 50;

    Biker(String name) {
        this.name = name;
    }

    LocalTime getStartTime() {
        return startTime;
    }

    LocalTime getEndtime() {
        return endTime;
    }

    String getBikerName() {
        return name;
    }

    public void accelerate() {
        speed += 1;
    }

    public void decelerate() {
        if (speed < 0) {
            speed = 1;
        } else
            speed -= 1;

    }

    public void run() {
        Random random = new Random();
        System.out.println("Biker " + name + " is ready to start the race");
        if (!raceStarted) {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        startTime = LocalTime.now();
        for (int i = 0; i < RACEDISTANCE; i += (speed > 0 ? speed : 1)) {
            int choice = random.nextInt(2);
            if (choice == 0) {
                accelerate();
            } else if (choice == 1) {
                decelerate();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i % 10 == 0) {
                System.out.println(name + " has reached " + i + " meters");
            }
        }
        endTime = LocalTime.now();
        System.out.println("Biker " + name + " has finished the race");
        winners[won] = this;
        won += 1;
    }
}

public class BikeRace {
    public static void main(String[] args) {
        Biker[] bikers = new Biker[10];
        bikers[0] = new Biker("Shoyab");
        bikers[1] = new Biker("Sanjana");
        bikers[2] = new Biker("Kaif");
        bikers[3] = new Biker("Sanchit");
        bikers[4] = new Biker("Sarvesh");
        bikers[5] = new Biker("Omkar");
        bikers[6] = new Biker("Aditi");
        bikers[7] = new Biker("Atharva");
        bikers[8] = new Biker("Nidhi");
        bikers[9] = new Biker("Hina");
        System.out.println("Bike Racing Game");
        System.out.println("-----------------");
        System.out.println("Countdown");
        for (int i = 10; i > -1; i--) {
            System.out.print((i) + "...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("GO");
        for (int i = 0; i < 10; i++) {
            bikers[i].start();
        }

        synchronized (Biker.lock) {
            Biker.raceStarted = true;
            Biker.lock.notifyAll();
        }
        for (int i = 0; i < 10; i++) {
            try {
                bikers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println();
        System.out.println("---------------------------------------------------");
        System.out.println("Bikers list in ascending order of their rankings");
        System.out.println("---------------------------------------------------");
        for (Biker b : Biker.winners) {
            System.out
                    .println(b.getBikerName() + "\t Start Time: " + b.getStartTime() + "\t End Time: " + b.getEndtime()
                            + "\t Time Taken:"
                            + MILLIS.between(b.getStartTime(), b.getEndtime()) + " milliseconds");
        }
        System.out.println("---------------------------------------------------");
    }
}
