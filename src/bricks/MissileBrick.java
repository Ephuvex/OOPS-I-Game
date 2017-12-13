package src.bricks;

import src.Missile;
import src.PFigure;

import java.awt.*;

public class MissileBrick extends Brick
{
   private Missile ghostMissile;

   public MissileBrick( int startX, int startY, int _width, int _height, int pr, Panel p )
   {
      super(startX, startY, _width, _height, pr, p);
   }

   @Override
   public void draw()
   {
      super.draw();
      ghostMissile.draw();
   }

   @Override
   public PFigure didGetDestroyed()
   {
      Missile.counter++;
      return null;
   }
}
