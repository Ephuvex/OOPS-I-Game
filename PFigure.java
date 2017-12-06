/**
You need to properly comment and fully understand this file!

You are only allowed to change things if it is NECESSARY to do your project,
in which case you must comment what you changed and why.
Expect to lose significant points otherwise!

You don't have to finish any code in this file - just comment it well!
Be sure to remove and/or rewrite the original comments!

Also, one method and the constructor don't have comments, and the
original comments are not in the correct style. You need to fix all
of them according to our style rules and put your names as authors.

@Author: Joe Clifton
         Qi Yang (Modified class comment)
@Author:          
*/

import java.awt.*;

public abstract class PFigure implements Comparable
{
   protected int x, y;           // Current position of the figure
   protected int width, height;  // Drawn (displayed) this size
   protected int priority;       // Can use to determine "winner"
   protected Panel panel;        // Panel the figure lives on

   public PFigure ( int startX, int startY, int _width, int _height, 
                    int pr, Panel p )
   {
       x = startX;
       y = startY;
       width = _width;
       height = _height;
       priority = pr;
       panel = p;
   }
   
   // Can use this in "battles", which figures is "greater"
   public int compareTo(Object o)
   {
      if( o instanceof PFigure )
         return priority - ((PFigure)o).priority;
      return Integer.MAX_VALUE;
   }
      
   // Has "this" figure collided with p?
   public boolean collidedWith ( PFigure p )
   {
      if (  p == null )
         return false;

      return ( x + width ) >= p.x && ( p.x + p.width ) >= x &&
             ( y + height ) >= p.y && ( p.y + p.height ) >= y;
   }
   
   // Can be used for moving by keyboard or mouse
   public void move ( int deltaX, int deltaY )
   {
      x = x + deltaX;
      y = y + deltaY;
   }
   
   public void hide()
   {
      Graphics g = panel.getGraphics();
      Color oldColor = g.getColor();
      g.setColor(panel.getBackground() );
      g.fillRect(x, y, width, height);
      g.setColor(oldColor);
   }
   
   // Can be automatic move, for example, called based on timer
   public void move()
   {
   }


   // Draw the figure.
   // Each derived class will write their own drawing method.
   // The first line should be:
   //    Graphics g = panel.getGraphics();
   abstract public void draw();
   
}