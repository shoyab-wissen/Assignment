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

import java.util.Random;

class Biker extends Thread {
    static Biker[] winners = new Biker[10];
    static int won = 0;
    String name;
    int startTime = 0;
    int endTime = 0;
    int ranking;
    int speed = 0;

    Biker(String name) {
        this.name = name;
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
        for (int i = 0; i < 50; i += (speed > 0 ? speed : 1)) {
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
            endTime += 1;
        }
        System.out.println("Biker " + name + " has finished the race");
        winners[won] = this;
        won += 1;
    }
}

public class BikeRace {
    public static void main(String[] args) {
        Biker[] bikers = new Biker[10];
        bikers[0] = new Biker("Biker 1");
        bikers[1] = new Biker("Biker 2");
        bikers[2] = new Biker("Biker 3");
        bikers[3] = new Biker("Biker 4");
        bikers[4] = new Biker("Biker 5");
        bikers[5] = new Biker("Biker 6");
        bikers[6] = new Biker("Biker 7");
        bikers[7] = new Biker("Biker 8");
        bikers[8] = new Biker("Biker 9");
        bikers[9] = new Biker("Biker 10");
        System.out.println("Bike Racing Game");
        System.out.println("-----------------");
        System.out.println("Countdown");
        for (int i = 10; i < -1; i--) {
            System.out.print((i + 1) + "...");
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
        for (int i = 0; i < 10; i++) {
            try {
                bikers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Bikers list in ascending order of their rankings");
        for (Biker b : Biker.winners) {
            System.out.println(b.name + " Start Time: " + b.startTime + " End Time: " + b.endTime + " Time Taken:"
                    + (b.endTime - b.startTime));
        }
    }
}
