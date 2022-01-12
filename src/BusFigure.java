import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusFigure implements ActionListener {

    private Color color;
    private int x;
    private int y;
    private int width;
    private int hight;

    public BusFigure(int x1, int y1, int x2, int y2){
        this.x = x1;
        this.y = y1;
        this.width = 7;
        this.hight = 3;
        this.color = Color.GREEN;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHight() {
        return hight;
    }

    public void drawBus(Graphics bus){
        bus.setColor(color);
        bus.fillRect(getX(),getY(),getWidth(),getHight());
        bus.setColor(color);
        bus.drawRect(getX(),getY(),getWidth(),getHight());
    }

    public static void moveBus(BusFigure bus){
        bus.setX(bus.getX()+2);
        bus.setY(bus.getY()+2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
