package src.bricks;

import src.PFigure;

import java.awt.*;
import src.Ball;


public class Brick extends PFigure
{
   protected Color colorLight = new Color(255, 255, 255, 255);
   protected Color colorDark = new Color(0, 0, 0, 255);

   public Brick( int startX, int startY, int _width, int _height, int pr, Panel p )
   {
      super(startX, startY, _width, _height, pr, p);
   }

   @Override
   public void draw()
   {
      Graphics g = panel.getGraphics();

      g.setColor(colorLight);
      g.drawRect(x, y, width-1, height-1);

      g.setColor(colorDark);
      g.drawRect(x + 1, y + 1, width - 3, height - 3);

      g.setColor(colorLight);
      g.fillRect(x + 2, y + 2, width - 5, height - 5);
   }

   /**
    To be called when an active ball/missile collided with the brick. Will
    cause perform any operations necessary for the destruction of the brick,
    and will return any new object that the brick may have created (such as a
    new ball).
    @return any new object created, such as a ball
    */
   public PFigure didGetDestroyed()
   {
      return null;
   }
   
   public boolean bounceWasX(Ball ball)
   {
       Rail topRail = new Rail(x + 2, y, width - 4, 1);
       Rail bottomRail = new Rail(x + 2, y + height - 1, width - 4, 1);
       
       return ball.collidedWith(topRail) || ball.collidedWith(bottomRail);
   }
   
   public boolean bounceWasY(Ball ball)
   {
       Rail leftRail = new Rail(x, y + 2, 1, height - 4);
       Rail rightRail = new Rail(x + width - 1, y + 2, 1, height - 4);
       
       return ball.collidedWith(leftRail) || ball.collidedWith(rightRail);
   }
}
