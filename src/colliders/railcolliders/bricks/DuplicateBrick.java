package src.colliders.railcolliders.bricks;

import src.colliders.nonrailcolliders.Ball;
import src.PFigure;

import java.awt.*;

public class DuplicateBrick extends Brick
{
    public DuplicateBrick(int startX, int startY, int _width, int _height, Panel p)
   {
       super(startX, startY, _width, _height, p);
      colorLight = new Color(255, 0, 255);
      colorDark = new Color(150, 0, 150);
   }

   @Override
   public PFigure didGetDestroyed()
   {
       return new Ball(x + 25, y + 10, -2, 2, panel);
   }
}
