import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

public class Animation implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<BusFigure> busFigureList;
    public List<BusFigure> getBusFigureList() {
        return busFigureList;
    }

    public Animation() {
        this.busFigureList = new LinkedList<BusFigure>();
    }

    public void addBusFigure(BusFigure busFigure) {

        busFigureList.add(busFigure);
    }

    public void removeBusFigure(BusFigure busFigure) {

        busFigureList.remove(busFigure);

    }

    public void createBus (BusFigure busFigure){

    }
}
