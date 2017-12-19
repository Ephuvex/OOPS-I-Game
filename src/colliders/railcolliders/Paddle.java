package src.colliders.railcolliders;


import src.PFigure;
import src.colliders.nonrailcolliders.Ball;
import src.colliders.nonrailcolliders.Missile;
import src.colliders.nonrailcolliders.NonRailCollider;

import java.awt.*;

/**
 This class handles the construction, movement and collision of the paddle in 
 the game. It also handles the firing of the missile, creates a shooting arrow 
 in the direction the player wants to fire the missile. 
 @author dericksonb 
 */
public class Paddle extends RailCollider
{

    private Color colorD = new Color(255, 0, 0, 255);
    private Color colorLight = new Color(255, 140, 140, 255);
    private boolean firingMissile = false;
    private int xAccel = 0;
    private boolean travellingLeft = false;
    private boolean travellingRight = false;

    /**
     THis is the constructor of the paddle. It takes in the starting position
     of the paddle, height and width and the panel itself. 
     @param startX The Starting X position of the paddle
     @param startY The Starting Y position of the paddle
     @param _width The width of the paddle
     @param _height The height of the paddle
     @param p the panel of the BreakerApplet
     */
    public Paddle( int startX, int startY, int _width, int _height, Panel p )
    {
        super(startX, startY, _width, _height, 0, p);
    }

    @Override
    public PFigure didGetDestroyed()
    {
        throw new UnsupportedOperationException();
    }

    /**
    This method overrides the draw function of the parent class. Its grabs the
    graphics and then sets the color, size and position of the paddle. If the
    player has hit the space button, to indicate that the player wants to fire a
    missile then the paddle will also draw a line that is pointed in the 
    direction that the ball will fire. 
     */
    @Override
    public void draw()
    {

        Graphics g = panel.getGraphics();

        g.setColor(colorD);
        g.drawRect(x, y, width - 1, height - 1);

        g.setColor(colorLight);
        g.fillRect(x + 1, y + 1, width - 2, height - 2);
        if ( firingMissile )
        {
            int x2 = (int) -(50 * Math.sin(Math.toRadians(Missile.angle + 90)));
            int y2 = (int) (50 * Math.cos(Math.toRadians(Missile.angle + 90)));
            g.drawLine(x + 50, y, x2 + x + 50, y2 + y);
        }

    }

    /**
    This method checks if the paddle is traveling left and if it is then it sets
    the boolean to what ever the bool that was brought in. 
    @param bool the true or false brought in.
     */
    public void setTravellingLeft( boolean bool )
    {
        travellingLeft = bool;
    }

    /**
    This method checks if the paddle is traveling right and if it is then it 
    sets the boolean to what ever the bool that was brought in.
    @param bool the true or false brought in.
     */
    public void setTravellingRight( boolean bool )
    {
        travellingRight = bool;
    }

    /**
    This method overrides the move function of the parent class. This method 
    checks the movement of the paddle and if it is just a single button press
    then it will only move a single increment. If the button was held down then
    then the paddle will move at an increasingly rate. 
     */
    @Override
    public void move()
    {
        if ( travellingLeft )
        {
            xAccel = -2;
        }
        else if ( travellingRight )
        {
            xAccel = 2;
        }
        xVel += xAccel;
        if ( xVel > 0 )
        {
            xVel = Math.min(15, xVel);
        }
        else if ( xVel < 0 )
        {
            xVel = Math.max(-15, xVel);
        }
        x += xVel;
        if ( x < -width / 2 )
        {
            x = panel.getSize().width - width / 2;
        }
        else if ( (x + width / 2) > panel.getSize().width )
        {
            x = -width / 2;
        }
        if ( xVel > 0 )
        {
            xVel--;
        }
        else if ( xVel < 0 )
        {
            xVel++;
        }
        if ( xAccel > 0 )
        {
            xAccel--;
        }
        else if ( xAccel < 0 )
        {
            xAccel++;
        }
    }

    /**
    This method causes the paddle to accelerate if the button was held down
     @param acceleration the rate at which the acceleration was determined. 
     */
    public void accel( int acceleration )
    {
        xAccel += acceleration;
    }

    /**
    This method checks if the ball bounces with the top of the paddle. If it 
    did it then return where it hit the paddle. 
     @param ball the ball that bounces off the paddle
     @return the position where the ball bounces off of
     */
    public boolean bounceWasX( Ball ball )
    {
        Rail topRail = new Rail(x + 2, y, width - 4, 1);
        Rail bottomRail = new Rail(x + 2, y + height - 1, width - 4, 1);

        return ball.collidedWith(topRail) || ball.collidedWith(bottomRail);
    }

    /**
    This method checks if the ball bounces with the sides of the paddle. If it 
    did it then return where it hit the paddle. 
    @param ball the ball that bounces off the paddle
    @return the position where the ball bounces off of
     */
    public boolean bounceWasY( Ball ball )
    {
        Rail leftRail = new Rail(x, y + 2, 1, height - 4);
        Rail rightRail = new Rail(x + width - 1, y + 2, 1, height - 4);

        return ball.collidedWith(leftRail) || ball.collidedWith(rightRail);
    }

    /**
     *
     @param collider
     */
    public void collisionCheck( NonRailCollider collider )
    {
        collider.didCollideWith(this);
    }

    /**
     This method literally just reverses the the state of firing a missile. 
     */
    public void toggleFireMissile()
    {
        firingMissile = !firingMissile;
    }

    /**
    This method get if firing a missile was called. 
    @return the state of firingMissile
     */
    public boolean getFiringmissile()
    {
        return firingMissile;
    }

    /**
    THis method overrides the hide function of the parent class. This hides the
    paddle so the form can move it for rendering and new game creation. 
     */
    @Override
    public void hide()
    {
        super.hide();

        Graphics g = panel.getGraphics();
        g.setColor(panel.getBackground());
        int x2 = (int) -(50 * Math.sin(Math.toRadians(Missile.angle + 90)));
        int y2 = (int) (50 * Math.cos(Math.toRadians(Missile.angle + 90)));
        g.drawLine(x + 50, y, x2 + x + 50, y2 + y);

    }

    /**
    This method tells where and what angle to fire the missile at. It brings in 
    the angle in which the player has chosen to fire the missile and sets the 
    path of the missile to that. It then creates a new missile with a path and a 
    velocity. 
     @param angle which the player has chosen to fire the missile at. 
     @return the new missile created. 
     */
    public Missile shootMissile( int angle )
    {
        int xVel = (int) -(5 * Math.sin(Math.toRadians(angle + 90)));
        int yVel = (int) (5 * Math.cos(Math.toRadians(angle + 90)));
        return new Missile(x + 50, y - 15, xVel, yVel, panel);
    }
}
