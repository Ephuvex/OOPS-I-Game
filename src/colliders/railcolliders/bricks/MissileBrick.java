package src.colliders.railcolliders.bricks;

import src.PFigure;
import src.colliders.nonrailcolliders.Missile;

import java.awt.*;

/**
 This class is a type of brick which, when destroyed, increments the missile counter by one.
 @author andrewkaiser */
public class MissileBrick extends Brick
{
    /**
     Constructor to match super.
     @param startX  the starting x coordinate
     @param startY  the starting y coordinate
     @param _width  the width of the brick
     @param _height the height of the brick
     @param p       the panel the brick is on
     */
    public MissileBrick( int startX, int startY, int _width, int _height, Panel p )
    {
        super(startX, startY, _width, _height, p);
        colorLight = new Color(0, 255, 255);
        colorDark = new Color(0, 150, 150);
    }

    /**
     Called when the brick is destroyed. Increments the missile counter, and returns null, since no new objects should
     be created when this brick is destroyed.
     @return null, always
     */
    @Override
    public PFigure didGetDestroyed()
    {
        Missile.counter++;
        return null;
    }
}
