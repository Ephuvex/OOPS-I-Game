package src.colliders.nonrailcolliders;

import src.colliders.railcolliders.RailCollider;

import java.awt.*;

/**
 @author dericksonb */
public class Ball extends NonRailCollider
{
    private final static int RADIUS = 5;

    public Ball( int initX, int initY, int initXVel, int initYVel, Panel p )
    {
        super(initX, initY, RADIUS * 2, RADIUS * 2, 1, p);
        xVel = initXVel;
        yVel = initYVel;
    }

    @SuppressWarnings("WeakerAccess")
    public void bounceX()
    {
        yVel = -yVel;
    }

    @SuppressWarnings("WeakerAccess")
    public void bounceY()
    {
        xVel = -xVel;
    }

    @Override
    public void move()
    {
        if ( xVel < 0 && x - RADIUS <= 0 ||
                xVel > 0 && x + RADIUS >= panel.getSize().width )
            xVel = -xVel;


        if ( yVel < 0 && y - RADIUS <= 0 ||
                yVel > 0 && y + RADIUS >= panel.getSize().height )
            yVel = -yVel;


        x = x + xVel;
        y = y + yVel;
    }

    @Override
    public void draw()
    {
        Graphics g = panel.getGraphics();
        g.fillOval(x, y, 10, 10);
    }

    @Override
    public boolean isOutOfBounds()
    {
        return !(x - RADIUS > 0 && x + RADIUS < panel.getSize().width &&
                y - RADIUS > 0 && y + RADIUS < panel.getSize().height);
    }

    @Override
    public void tellToDie()
    {
    }

    @Override
    public void didCollideWith( RailCollider railCollider )
    {
        if ( railCollider.didHitSide(this) )
            this.bounceX();

        if ( railCollider.didHitTopBottom(this) )
            this.bounceY();
    }

    @Override
    public void kill()
    {
        //noinspection UnnecessaryReturnStatement
        return;
    }
}
