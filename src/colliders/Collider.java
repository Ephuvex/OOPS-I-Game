package src.colliders;

import src.PFigure;

import java.awt.*;

public abstract class Collider extends PFigure {
    protected int xVel = 0, yVel = 0;
    protected boolean shouldDie = false;

    public Collider(int startX, int startY, int _width, int _height, int pr, Panel p) {
        super(startX, startY, _width, _height, pr, p);
    }

    public boolean isOutOfBounds() {
        return x < 0 || y < 0 || x > panel.getWidth() || y > panel.getHeight();
    }

    public boolean getShouldDie() {
        return shouldDie;
    }

    public void tellToDie() {
        shouldDie = true;
    }
}
