/*
 * Program: Symulacja busów przejeżdżających przez most
 *    Plik: BusDirection.java
 *
 *   Autor: Zofia Zub
 *  indeks: 259114
 *    Data: 14.01.2022 r.
 */

enum BusDirection {
    EAST,
    WEST;

    @Override
    public String toString() {
        switch (this) {
            case EAST:
                return "W";
            case WEST:
                return "Z";
        }
        return "";
    }
}
