
package src.colliders.railcolliders;

import src.PFigure;

/**
 This class is simply a PFigure which should never be drawn, and is used for collision detection. Rails whose location
 and dimension match the border pixels of RailColliders are created, and used for checking collisions with objects on
 individual sides. RAILS SHOULD NOT BE DRAWN!
 @author andrewkaiser 
 */
public class Rail extends PFigure
{
   @SuppressWarnings("WeakerAccess")
   public Rail( int startX, int startY, int _width, int _height )
   {
      super(startX, startY, _width, _height, 0, null);
   }

   /**
    Throws an UnsupportedOperationException, since this method should never be called.
    @throws UnsupportedOperationException when used
    */
   @Override
   public void draw()
   {
      throw new UnsupportedOperationException("Not supported yet.");
   }

}
