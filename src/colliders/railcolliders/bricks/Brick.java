package src.colliders.railcolliders.bricks;

import src.PFigure;
import src.colliders.railcolliders.RailCollider;

import java.awt.*;


public class Brick extends RailCollider {
    protected Color colorLight = new Color(255, 255, 255, 255);
    protected Color colorDark = new Color(0, 0, 0, 255);

    public Brick(int startX, int startY, int _width, int _height, Panel p) {
        super(startX, startY, _width, _height, 4, p);
    }

    @Override
    public void draw() {
        Graphics g = panel.getGraphics();

        g.setColor(colorLight);
        g.drawRect(x, y, width - 1, height - 1);

        g.setColor(colorDark);
        g.drawRect(x + 1, y + 1, width - 3, height - 3);

        g.setColor(colorLight);
        g.fillRect(x + 2, y + 2, width - 5, height - 5);
    }

    /**
     * To be called when an active ball/missile collided with the brick. Will
     * cause perform any operations necessary for the destruction of the brick,
     * and will return any new object that the brick may have created (such as a
     * new ball).
     *
     * @return any new object created, such as a ball
     */
    public PFigure didGetDestroyed() {
        return null;
    }
}
