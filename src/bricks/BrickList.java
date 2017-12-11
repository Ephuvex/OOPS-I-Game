package src.bricks;

import src.PFigure;

public class BrickList
{
   private static final int SIZE = 800;
   private int size;
   private Brick[] bricks = new Brick[SIZE];

   public boolean add( Brick additionBrick )
   {
      bricks[size++] = additionBrick;
      return true;
   }

   public boolean remove( Brick brick )
   {
      for ( int i = 0; i < size; i++ )
         if ( bricks[i].equals(brick) )
         {
            System.arraycopy(bricks, i + 1, bricks, i, size-- - i);
            return true;
         }
      return false;
   }

   public boolean isEmpty()
   {
      return size == 0;
   }

   /**
    Will run through all bricks, and if any collided with the passed PFigure,
    will return any new objects created by the collision (such as a new ball).
    If no collision took place or if nothing was created from the collision,
    will return null.
    @param pFigure the figure to check for collision with
    @return a new PFigure if applicable, null otherwise
    */
   public PFigure collisionCheck( PFigure pFigure )
   {
      for ( Brick brick : bricks )
      {
         if ( brick.collidedWith(pFigure) )
         {
            remove(brick);
            return brick.didGetDestroyed();
         }
      }

      return null;
   }

   public void draw()
   {
      for ( int i = 0; i < size; i++ )
         bricks[i].draw();
   }
}
