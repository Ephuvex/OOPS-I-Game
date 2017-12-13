package src.bricks;

import src.Ball;
import src.PFigure;

import java.awt.*;

public class DuplicateBrick extends Brick
{
   private Ball ghostBall;

   public DuplicateBrick( int startX, int startY, int _width, int _height, int pr, Panel p )
   {
      super(startX, startY, _width, _height, pr, p);
   }

   @Override
   public void draw()
   {
      super.draw();
      ghostBall.draw();
   }

   @Override
   public PFigure didGetDestroyed()
   {
      return new Ball(x + 40, x + 20, 20, -20, colorDark, 10, panel);
   }
}
