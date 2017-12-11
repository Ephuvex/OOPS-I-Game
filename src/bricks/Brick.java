package src.bricks;

import src.PFigure;

import java.awt.*;


public class Brick extends PFigure
{
   protected Color colorLight = new Color(255, 140, 140, 255);
   protected Color colorDark = new Color(255, 0, 0, 255);

   public Brick( int startX, int startY, int _width, int _height, int pr, Panel p )
   {
      super(startX, startY, _width, _height, pr, p);
   }

   @Override
   public void draw()
   {
      Graphics g = panel.getGraphics();

      g.setColor(colorLight);
      g.drawRect(x, y, width, height);

      g.setColor(colorDark);
      g.drawRect(x + 1, y + 1, width - 2, height - 2);

      g.setColor(colorLight);
      g.fillRect(x + 2, y + 2, width - 4, height - 4);
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
}
