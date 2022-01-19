/*
 * Program: Symulacja busów przejeżdżających przez most
 *    Plik: NarrowBridge.java
 *
 *   Autor: Zofia Zub
 *  indeks: 259114
 *    Data: 14.01.2022 r.
 */

import java.util.LinkedList;
import java.util.List;

class NarrowBridge {
    List<Bus> allBuses = new LinkedList<Bus>();
    List<Bus> busesWaiting = new LinkedList<Bus>();
    List<Bus> busesOnTheBridge = new LinkedList<Bus>();

    public BusDirection notAllowedDirection = null;

    NarrowBridgeWindow window = new NarrowBridgeWindow();

    void printBridgeInfo(Bus bus, String message) {
        StringBuilder busInfo = new StringBuilder();
        busInfo.append("Bus[" + bus.id + "->" + bus.dir + "]  ");
        busInfo.append(message + "\n");
        window.logBusInfo(busInfo);


        StringBuilder bridgeInfo = new StringBuilder();
        for (Bus b : busesOnTheBridge) {
            bridgeInfo.append(b.id + "  ");
            busInfo.append(message + "\n");
            window.logBridgeInfo(bridgeInfo);
        }

        StringBuilder queueInfo = new StringBuilder();
        for (Bus b : busesWaiting) {
            queueInfo.append(b.id + "  ");
            busInfo.append(message + "\n");
            window.logQueueInfo(queueInfo);
        }
    }

    public void printBusInfo(String message, int ID, BusDirection direction) {
        System.out.println("Bus[" + ID + "->" + direction + "]: " + message);
        StringBuilder sb = new StringBuilder();
        sb.append("Bus[" + ID + "->" + direction + "]: " + message + "\n");
        window.logBusInfo(sb);
    }

    synchronized void getOnTheBridge(Bus bus) {
        notAllowedDirection = bus.getDirectionLimitation();
        if (bus.getLimitation() == 1) {
            while (!busesOnTheBridge.isEmpty()) {
                if (bus.dir != notAllowedDirection) {
                    busesWaiting.add(bus);
                }
                printBridgeInfo(bus, "CZEKA NA WJAZD");
                try {
                    wait();
                } catch (InterruptedException e) {
                }
                if (bus.dir != notAllowedDirection) {
                    busesWaiting.remove(bus);
                }
            }
            if (bus.dir != notAllowedDirection) {
                busesOnTheBridge.add(bus);
            }
            printBridgeInfo(bus, "WJEŻDŻA NA MOST");
        } else if (bus.getLimitation() == 3 || bus.getLimitation() == 30) {
            while (busesOnTheBridge.size() >= 3) {
                if (bus.dir != notAllowedDirection) {
                    busesWaiting.add(bus);
                }
                printBridgeInfo(bus, "CZEKA NA WJAZD");
                try {
                    wait();
                } catch (InterruptedException e) {
                }
                if (bus.dir != notAllowedDirection) {
                    busesWaiting.remove(bus);
                }
            }
            if (bus.dir != notAllowedDirection) {
                busesOnTheBridge.add(bus);
            }
            printBridgeInfo(bus, "WJEŻDŻA NA MOST");
        }
        else {
            if (bus.dir != notAllowedDirection) {
                printBridgeInfo(bus, "CZEKA NA WJAZD");
                busesOnTheBridge.add(bus);
                printBridgeInfo(bus, "WJEŻDŻA NA MOST");
                getOffTheBridge(bus);
            }
        }
    }

    synchronized void getOffTheBridge(Bus bus) {
        busesOnTheBridge.remove(bus);
        printBridgeInfo(bus, "OPUSZCZA MOST");
        notify();
    }

}
