package src;

import src.bricks.Brick;
import src.bricks.BrickList;

import java.applet.Applet;
import java.awt.*;

public class BrickBreakerApplet extends Applet
{
   private Panel p;
   private BrickList brickList;

   public void init()
   {
      setLayout(new BorderLayout());
      brickList = new BrickList();
      p = new Panel();
      add(p);
      p.setBackground(Color.CYAN);

      for ( int i = 1; i <= 10; i++ )
      {
         for ( int j = 1; j <= 10; j++ )
         {
            brickList.add(new Brick(i * 30, j * 30, 20, 20, 0, p));
         }
      }
   }

   public void paint( Graphics g )
   {
      // FIXME: 12/11/17 [AndrewKaiser] The paint method is drawing overtop
      // of the bricks in brickList.
      brickList.draw();
   }
}
