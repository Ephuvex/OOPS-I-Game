package src.colliders.railcolliders.bricks;

import src.PFigure;
import src.colliders.nonrailcolliders.Ball;

import java.awt.*;

/**
 This class is a kind of brick which, when destroyed, creates a new ball at the brick's old position. Is otherwise
 identical to Brick.
 @author andrewkaiser */
public class DuplicateBrick extends Brick
{
    /**
     Constructor to match super, but with different colors.
     @param startX  the starting x coordinate
     @param startY  the starting y coordinate
     @param _width  the width of the brick
     @param _height the height of the brick
     @param p       the panel the brick is on
     */
    public DuplicateBrick( int startX, int startY, int _width, int _height, Panel p )
    {
        super(startX, startY, _width, _height, p);
        colorLight = new Color(255, 0, 255);
        colorDark = new Color(150, 0, 150);
    }

    /**
     When destroyed, this class creates a new ball in the brick's old location, and returns it.
     @return the new ball
     */
    @Override
    public PFigure didGetDestroyed()
    {
        return new Ball(x + 25, y + 10, -2, 2, panel);
    }
}
