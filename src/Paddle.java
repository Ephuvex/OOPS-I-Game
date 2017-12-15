package src;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import src.PFigure;

import java.awt.*;

import java.awt.Panel;
import src.bricks.Rail;

/**
 *
 * @author dericksonb
 */
public class Paddle extends PFigure {

    boolean firinmissile = false;
    private boolean travellingLeft = false;
    private boolean travellingRight = false;
    int xVel = 0;
    int xAccel = 0;

    protected Color colorD = new Color(255, 0, 0, 255);
    protected Color colorLight = new Color(255, 140, 140, 255);

    public Paddle(int startX, int startY, int _width, int _height, int pr, Panel p) {
        super(startX, startY, _width, _height, pr, p);
    }

    @Override
    public void draw() {

        Graphics g = panel.getGraphics();

        g.setColor(colorD);
        g.drawRect(x, y, width - 1, height - 1);

        g.setColor(colorLight);
        g.fillRect(x + 1, y + 1, width - 2, height - 2);
        if (firinmissile) {
            int x2 = (int) -(50 * Math.sin(Math.toRadians(Missile.angle + 90)));
            int y2 = (int) (50 * Math.cos(Math.toRadians(Missile.angle + 90)));
            g.drawLine(x + 50, y, x2 + x + 50, y2 + y);
        }

    }

    public void setTravellingLeft(boolean bool) {
        travellingLeft = bool;
    }

    public void setTravellingRight(boolean bool) {
        travellingRight = bool;
    }

    @Override
    public void move() {
        if (travellingLeft) {
            xAccel = -2;
        } else if (travellingRight) {
            xAccel = 2;
        }

        xVel += xAccel;

        if (xVel > 0) {
            xVel = Math.min(15, xVel);
        } else if (xVel < 0) {
            xVel = Math.max(-15, xVel);
        }

        x += xVel;

        if (x < -width / 2) {
            x = panel.getSize().width - width / 2;
        } else if ((x + width / 2) > panel.getSize().width) {
            x = -width / 2;
        }

        if (xVel > 0) {
            xVel--;
        } else if (xVel < 0) {
            xVel++;
        }

        if (xAccel > 0) {
            xAccel--;
        } else if (xAccel < 0) {
            xAccel++;
        }
    }

    public void accel(int acceleration) {
        xAccel += acceleration;
    }

    public boolean bounceWasX(Ball ball) {
        Rail topRail = new Rail(x + 2, y, width - 4, 1);
        Rail bottomRail = new Rail(x + 2, y + height - 1, width - 4, 1);

        return ball.collidedWith(topRail) || ball.collidedWith(bottomRail);
    }

    public boolean bounceWasY(Ball ball) {
        Rail leftRail = new Rail(x, y + 2, 1, height - 4);
        Rail rightRail = new Rail(x + width - 1, y + 2, 1, height - 4);

        return ball.collidedWith(leftRail) || ball.collidedWith(rightRail);
    }

    public void collisionCheck(PFigure pFigure) {
        if (pFigure instanceof Ball) {
            Ball ball = (Ball) pFigure;
            if (bounceWasX(ball)) {
                ball.bounceX();
            }

            if (bounceWasY(ball)) {
                ball.bounceY();
            }
        }
    }

    public void toggleFIREMISSILE() {
        firinmissile = !firinmissile;
    }

    public boolean getFiringmissile() {
        return firinmissile;
    }

    @Override
    public void hide() {
        super.hide();

        Graphics g = panel.getGraphics();
        g.setColor(panel.getBackground());
        int x2 = (int) -(50 * Math.sin(Math.toRadians(Missile.angle + 90)));
        int y2 = (int) (50 * Math.cos(Math.toRadians(Missile.angle + 90)));
        g.drawLine(x + 50, y, x2 + x + 50, y2 + y);

    }

    public Missile shootMissile(int angle) {
        int xVel = (int) -(5 * Math.sin(Math.toRadians(angle + 90)));
        int yVel = (int) (5 * Math.cos(Math.toRadians(angle + 90)));
        return new Missile(x + 50, y + 5, xVel, yVel, null, 1, panel);
    }
}
