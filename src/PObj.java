package src;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
/**
 *
 * @author dericksonb
 */
public abstract class PObj {
   private int x, y;        // Location of center of ball
   private int xVel, yVel;  // Current velocity in X and Y directions
   private int radius;      // src.bricks.Ball radius
   private Panel panel;     // Panel on which the ball will be drawn.
   private Color color;     // Color of the ball

   // Used to "hide" the figure.  True if draw is suppose to
   // draw the figure in the background color.
   private boolean erase_draw;

   public PObj( int initX, int initY, int initXVel, int initYVel,
                Color initColor, int initRadius, Panel p )
   {
      x      = initX;
      y      = initY;
      xVel   = initXVel;
      yVel   = initYVel;
      color = initColor;
      radius = initRadius;
      panel = p;
      erase_draw = false;
   }

   // Erase the ball
   public void hide()
   {
      erase_draw = true;
      show();
   }

   public void show()
   {
      Graphics g = panel.getGraphics();  // Get a graphics to draw on

      if (erase_draw)
      {
         g.setColor(Color.white);
         erase_draw = false;
      }
      else
        g.setColor(color);                 // Set the pen color


      g.fillOval(x - radius ,y - radius, radius*2, radius*2);

   }


   // Move the ball ahead using the current velocity.
   // Check for wall collisions!
   public void move()
   {
      // Does it hit the right or left wall?  If so, change the xVel.
      // It has to bounce off the side wall.  Just flip the velocity.
      if ( xVel < 0 && x - radius <= 0 ||
           xVel > 0 && x + radius >= panel.getSize().width )
         xVel = - xVel;

      // DO_02
      // Does it hit the floor or ceiling? If so, change the yVel.
      // It has to bounce off the wall or ceiling.  Just flip the velocity.
      if ( yVel < 0 && y - radius <= 0 ||
           yVel > 0 && y + radius >= panel.getSize().height )
         yVel = - yVel;




      // Update x & y
      x = x + xVel;
      y = y + yVel;
   }

   // Returns true if (panel_x,panel_y) is in the square circumscribed
   // around the ball, false otherwise.
   public boolean inBall ( int panel_x, int panel_y )
   {
      return panel_x >= x - radius && panel_x <= x + radius &&
             panel_y >= y - radius && panel_y <= y + radius;
   }
}
