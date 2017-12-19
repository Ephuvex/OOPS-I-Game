package src.colliders.nonrailcolliders;


import src.colliders.railcolliders.RailCollider;

import java.awt.*;

/**
 This Class creates the Missile for BrickBreaker game. This uses the parent 
 class NonRailCollider and fires a missile when the player hits the space 
 button. 
 @author dericksonb 
 */
public class Missile extends NonRailCollider
{
    public static int angle = 90;
    public static int counter = 10;
    
    public static boolean isMissileInPlay = false;

    /**
     THis is the constructor of the missile. It takes in the starting position
     based on where the directional arrow of the paddle is. Also Sets the 
     missile off at a predetermined speed of the missile and sends it on its
     way in the direction the player chooses. 
     @param initX The Starting X position of the missile
     @param initY The Starting Y position of the missile
     @param initXVel The Starting X velocity of the missile
     @param initYVel The Starting Y velocity of the missile
     @param p the panel of the BreakerApplet
     */
    public Missile( int initX, int initY, int initXVel, int initYVel, Panel p )
    {
        super(initX, initY, 10, 10, 0, p);
        xVel = initXVel;
        yVel = initYVel;
    }

    /**
    This method overwrites the move function of the parent class to move 
    the missile. 
     */
    @Override
    public void move()
    {
        x += xVel;
        y += yVel;
    }

    /**
    This method overwrites the draw function of the parent class to draw 
    the missile on the panel. 
     */
    @Override
    public void draw()
    {
        Graphics g = panel.getGraphics();
        g.setColor(Color.blue);
        g.fillOval(x, y, 10, 10);
    }

    
    /**
    This method overwrites the didCollideWith function of the parent class to
    see if the missile collided with anything and if it does it kills itself. 
     */
    @Override
    public void didCollideWith( RailCollider collider )
    {
        if ( collider.compareTo(this) != 0 )
        {
            kill();
        }
    }

    
    /**
    This method overwrites the kill function of the parent class to destroy the
    missile if it collided with a brick. 
     */
    @Override
    public void kill()
    {
        tellToDie();
        isMissileInPlay = false;
        hide();
    }
}
