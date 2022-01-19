/*
 * Program: Symulacja busów przejeżdżających przez most
 *    Plik: Bus.java
 *
 *   Autor: Zofia Zub
 *  indeks: 259114
 *    Data: 14.01.2022 r.
 */

import java.util.concurrent.ThreadLocalRandom;

class Bus implements Runnable {

    public static final int MIN_BOARDING_TIME = 1000;
    public static final int MAX_BOARDING_TIME = 10000;

    public static final int GETTING_TO_BRIDGE_TIME = 500;

    public static final int CROSSING_BRIDGE_TIME = 3000;

    public static final int GETTING_PARKING_TIME = 500;

    public static final int UNLOADING_TIME = 500;

    public static int limitation;

    public static BusDirection directionLimitation = null;

    private static int numberOfBuses = 0;

    public static int securityCounter = 0;

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }

    public static void sleep(int min_millis, int max_milis) {
        sleep(ThreadLocalRandom.current().nextInt(min_millis, max_milis));
    }

    NarrowBridge bridge;

    int id;

    BusDirection dir;

    Bus(NarrowBridge bridge) {
        this.bridge = bridge;
        this.id = ++numberOfBuses;
        if (ThreadLocalRandom.current().nextInt(0, 2) == 0)
            this.dir = BusDirection.EAST;
        else this.dir = BusDirection.WEST;
        securityCounter += 1;
        if (securityCounter == 20) {
            directionLimitation = null;
        }
    }

    void boarding() {
        if (dir != directionLimitation) {
            bridge.printBusInfo("Czeka na nowych pasażerów", id, dir);
            sleep(MIN_BOARDING_TIME, MAX_BOARDING_TIME);
        }
    }

    void goToTheBridge() {
        if (dir != directionLimitation) {
            System.out.println(directionLimitation);
            bridge.printBusInfo("Jazda w stronę mostu", id, dir);
            sleep(GETTING_TO_BRIDGE_TIME);
        }
    }

    void rideTheBridge() {
        if (dir != directionLimitation) {
            bridge.printBusInfo("Przejazd przez most", id, dir);
            sleep(CROSSING_BRIDGE_TIME);
        }
    }

    void goToTheParking() {
        if (dir != directionLimitation) {
            bridge.printBusInfo("Jazda w stronę końcowego parkingu", id, dir);
            sleep(GETTING_PARKING_TIME);
        }
    }

    void unloading() {
        if (dir != directionLimitation) {
            bridge.printBusInfo("Rozładunek pasażerów", id, dir);
            sleep(UNLOADING_TIME);
        }
    }

    public static int getLimitation() {
        return limitation;
    }

    public static BusDirection getDirectionLimitation() {
        return directionLimitation;
    }

    public void run() {
        bridge.allBuses.add(this);
        boarding();
        goToTheBridge();
        bridge.getOnTheBridge(this);
        rideTheBridge();
        bridge.getOffTheBridge(this);
        goToTheParking();
        unloading();
        bridge.allBuses.remove(this);
        bridge.allBuses.remove(this);
    }
}
