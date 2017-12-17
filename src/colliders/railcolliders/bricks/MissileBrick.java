package src.colliders.railcolliders.bricks;

import src.colliders.nonrailcolliders.Missile;
import src.PFigure;

import java.awt.*;

public class MissileBrick extends Brick
{
   public MissileBrick(int startX, int startY, int _width, int _height, Panel p)
   {
      super(startX, startY, _width, _height, p);
      colorLight = new Color(0, 255, 255);
      colorDark = new Color(0, 150, 150);
   }

   @Override
   public PFigure didGetDestroyed()
   {
      Missile.counter++;
      return null;
   }
}
