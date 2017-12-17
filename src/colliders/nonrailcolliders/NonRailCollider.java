package src.colliders.nonrailcolliders;

import src.colliders.Collider;
import src.colliders.railcolliders.RailCollider;

import java.awt.*;

/**
 This class is an abstract class for any collider which do not use rails to check individual sides for collisions with
 PFigures.
 @author andrewkaiser */
public abstract class NonRailCollider extends Collider
{
    /**
     @param startX  the starting x coordinate
     @param startY  the starting y coordinate
     @param _width  the width of the collider
     @param _height the height of the collider
     @param pr      the priority of the collider
     @param p       the panel the collider is on
     */
    @SuppressWarnings("WeakerAccess")
    public NonRailCollider( int startX, int startY, int _width, int _height, int pr, Panel p )
    {
        super(startX, startY, _width, _height, pr, p);
    }

    /**
     Called when the collider collides with a RailCollider.
     @param collider the collider this instance collided with
     */
    public abstract void didCollideWith( RailCollider collider );

    /**
     Called to tell a collider that it needs to die, so any closed operations can be performed.
     */
    public abstract void kill();
}
