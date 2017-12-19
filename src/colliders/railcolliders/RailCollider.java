package src.colliders.railcolliders;

import src.PFigure;
import src.colliders.Collider;
import src.colliders.nonrailcolliders.NonRailCollider;

import java.awt.*;

/**
 This class is an abstract class for any collider which use rails to check individual sides for collisions with
 PFigures. Such classes include Paddle and Brick.
 @author andrewkaiser 
 */
public abstract class RailCollider extends Collider
{
    /**
     Constructor to match super.
     @param startX  the starting x coordinate
     @param startY  the starting y coordinate
     @param _width  the width of the collider
     @param _height the height of the collider
     @param pr      the priority of the collider
     @param p       the panel the collider is on
     */
    public RailCollider( int startX, int startY, int _width, int _height, int pr, Panel p )
    {
        super(startX, startY, _width, _height, pr, p);
    }

    /**
     Returns whether or not the passed object collided with the side rails.
     @param nonRailCollider the collider to be checked
     @return true if a collision took place, false otherwise
     */
    public boolean didHitSide( NonRailCollider nonRailCollider )
    {
        Rail topRail = new Rail(x + 2, y, width - 4, 1);
        Rail bottomRail = new Rail(x + 2, y + height - 1, width - 4, 1);

        return nonRailCollider.collidedWith(topRail) || nonRailCollider.collidedWith(bottomRail);
    }

    /**
     * Returns whether or not the passed object collided with the top or bottom rails.
     @param nonRailCollider the collider to be checked
     @return true if a collision took place, false otherwise
     */
    public boolean didHitTopBottom( NonRailCollider nonRailCollider )
    {
        Rail leftRail = new Rail(x, y + 2, 1, height - 4);
        Rail rightRail = new Rail(x + width - 1, y + 2, 1, height - 4);

        return nonRailCollider.collidedWith(leftRail) || nonRailCollider.collidedWith(rightRail);
    }

    /**
     Called when the collider is destroyed, so that any closing operations can take place. Returns any new objects created
     by the destruction of the collider.
     @return any new objects created by destroying the collider.
     */
    public abstract PFigure didGetDestroyed();
}
