package src.colliders.nonrailcolliders;

import src.colliders.railcolliders.RailCollider;

import java.awt.*;

/**
 This class creates the ball BrickBreaker Game. This class also handles the  
 the move, draw and collides function for the ball. 
 @author dericksonb 
*/
public class Ball extends NonRailCollider
{
    private final static int RADIUS = 5;

    /**
     THis is the constructor of the ball. It takes in the starting position
     based on where the paddle is or where a duplicate brick is. 
     @param initX The Starting X position of the ball
     @param initY The Starting Y position of the ball
     @param initXVel The Starting X velocity of the ball
     @param initYVel The Starting Y velocity of the ball
     @param p the panel of the BreakerApplet
     */
    
    public Ball( int initX, int initY, int initXVel, int initYVel, 
            Color initColor, Panel p )
    {
        super(initX, initY, RADIUS * 2, RADIUS * 2, 1, p);
        xVel = initXVel;
        yVel = initYVel;
        
    }

    /**
    This method is used if the ball bounces off the paddle of the game. If does
    it then reverses the X velocity. 
    */
    @SuppressWarnings("WeakerAccess")
    public void bounceX()
    {
        yVel = -yVel;
    }

    /**
    This method is used if the ball bounces off the paddle of the game. If does
    * it then reverses the Y velocity. 
    */
    @SuppressWarnings("WeakerAccess")
    public void bounceY()
    {
        xVel = -xVel;
    }

    
    /**
    This method overwrites the move function of the parent class to move 
    the missile. If the ball hits the wall of the applet then it reverses the 
    x velocity and if it hits the ceiling of the Applet then it reverses the 
    y velocity. 
     */
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

    /**
    This method overwrites the draw function of the parent class to draw 
    the ball on the panel. 
     */
    @Override
    public void draw()
    {
        Graphics g = panel.getGraphics();
        g.fillOval(x, y, 10, 10);
    }

    /**
    This method overwrites the isOutOfBounds of the parent class. Checks of the
    ball is hitting the wall or ceiling of the panel. 
    */
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

    /**
    This method overwrites the didCollideWith function of the parent class to
    see if the ball collided with anything and if it was a brick then it 
    destroys the brick. 
     */
    @Override
    public void didCollideWith( RailCollider railCollider )
    {
        if ( railCollider.didHitSide(this) )
            this.bounceX();

        if ( railCollider.didHitTopBottom(this) )
            this.bounceY();
    }
    /**
    This method overwrites the kill function of the parent class to kill 
    the ball on the panel if its required. 
     */
    @Override
    public void kill()
    {
        return;
    }
}
