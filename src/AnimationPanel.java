import javax.swing.*;
import java.awt.*;

public class AnimationPanel extends JPanel {

    public Animation animation = new Animation();

    private int leftSideX = -350;
    private int rightSideX = 343;
    private int bothSidesY = 980;
    private int motion = 2;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (animation == null) return;
        //animation.draw(g);
    }

}
