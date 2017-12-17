package src.colliders;

import src.PFigure;

import java.awt.*;

/**
 This class represents PFigures which can move, or collide with other PFigures. The instances track whether they should
 be removed from the stage internally. This class mostly exists as to not override PFigure.
 @author andrewkaiser */
public abstract class Collider extends PFigure
{
   protected int xVel = 0, yVel = 0;
   private boolean shouldDie = false;

   /**
    Constructor to match super, nearly exactly.
    @param startX  the starting x coordinate
    @param startY  the starting y coordinate
    @param _width  the width of the collider
    @param _height the height of the collider
    @param pr      the priority of the collider
    @param p       the panel the collider is on
    */
   public Collider( int startX, int startY, int _width, int _height, int pr, Panel p )
   {
      super(startX, startY, _width, _height, pr, p);
   }

   /**
    Checks if the collider is still in-bounds.
    @return true if the collider is OUT of bounds, false if it is IN bounds.
    */
   public boolean isOutOfBounds()
   {
      return x < 0 || y < 0 || x > panel.getWidth() || y > panel.getHeight();
   }

   /**
    A getter method for shouldDie, which is used to track if the object should be removed from the stage.
    @return shouldDie
    */
   public boolean getShouldDie()
   {
      return shouldDie;
   }

   /**
    A setter method for shouldDie. This method doesn't take a parameter, since colliders always start with shouldDie
    as false and should never have shouldDie change from true to false, only false to true.
    */
   public void tellToDie()
   {
      shouldDie = true;
   }
}
