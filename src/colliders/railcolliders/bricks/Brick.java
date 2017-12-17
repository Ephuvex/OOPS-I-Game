package src.colliders.railcolliders.bricks;

import src.PFigure;
import src.colliders.railcolliders.RailCollider;

import java.awt.*;


/**
 This class represents the bricks at the top of the stage. Bricks are RailColliders, since they use Rails to check for
 collision side-wise. Is also the parent class of DuplicateBrick and MissileBrick.
 @author andrewkaiser */
public class Brick extends RailCollider
{
   //These are package-private, so that child classes can access them
   Color colorLight = new Color(255, 255, 255, 255);
   Color colorDark = new Color(0, 0, 0, 255);

   /**
    Constructor, to match parent. Priority is constant at 4 for all bricks
    @param startX  the starting x coordinate
    @param startY  the starting y coordinate
    @param _width  the width of the brick
    @param _height the height of the brick
    @param p       the panel the brick is on
    */
   public Brick( int startX, int startY, int _width, int _height, Panel p )
   {
        super(startX, startY, _width, _height, 4, p);
    }

   /**
    Draws the brick on the panel passed in the constructor. Creates a light rectangle as a border, followed by an
    additional inset border in a dark rectangle, and lastly fills in the center with the light color.
    */
   @Override
   public void draw()
   {
      Graphics g = panel.getGraphics();

      g.setColor(colorLight);
      g.drawRect(x, y, width - 1, height - 1);

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
}
