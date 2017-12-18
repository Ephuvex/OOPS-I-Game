import src.colliders.nonrailcolliders.Ball;
import src.colliders.nonrailcolliders.Missile;
import src.colliders.nonrailcolliders.NonRailCollider;
import src.colliders.nonrailcolliders.NonRailColliderList;
import src.colliders.railcolliders.Paddle;
import src.colliders.railcolliders.RailColliderList;
import src.colliders.railcolliders.bricks.Brick;
import src.colliders.railcolliders.bricks.DuplicateBrick;
import src.colliders.railcolliders.bricks.MissileBrick;

import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;
import src.colliders.railcolliders.Rail;

/**
 This class is the backbone to the BrickBreaker Game. This class is the big 
 picture which handles everything graphically and manipulating the methods
 within the other files to create a usable GUI.
 @author Quinn, Andrew
 */
public class BreakerApplet extends java.applet.Applet implements
        java.awt.event.ActionListener
{

    private static final int HEIGHT = 700;
    private static final int WIDTH = 500;
    private final javax.swing.Timer moveTimer = new javax.swing.Timer(16, this);
    private RailColliderList railColliderList;
    private NonRailColliderList nonRailColliderList;
    private Paddle paddle;
    // Variables declaration - do not modify                     
    private java.awt.Panel panel;

    /**
     Called to initialize the applet. This is the main method in BreakerApplet
     This method will call methods to set the state, and prep the game for a
     user.
     */
    @Override
    public void init()
    {
        Window window = (Window) this.getParent().getParent();
        window.setSize(WIDTH, HEIGHT + 100);
        window.setLocation(710, 140);
        try
        {
            //Lambda for efficiency
            java.awt.EventQueue.invokeAndWait(()
                    -> 
                    {
                        initComponents();
                        setStage();
                        moveTimer.start();
                        panel.requestFocus();
            });
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        setLayout(new BorderLayout());
    }

    /**
     Called by the applet, paint uses the passed graphics to pain the panel and
     window.

     @param g the graphics which will render the panel and window.
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        panel.paint(g);
    }

    /**
     setStage modifies the panel size to accomodate the bricklist, then creates
     new lists, and finally starts the game.
    */
    private void setStage()
    {
        panel.setSize(WIDTH, HEIGHT);
        railColliderList = new RailColliderList();
        nonRailColliderList = new NonRailColliderList();
        startGame();
    }

    /**
     ActionPerformed hides, moves, and draws: the paddle, nonRailColliderList,
     railColliderList. It creates a new panel and draws the latter on top.

     @param ae Running event while the game is playing.
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        paddle.hide();
        paddle.move();
        paddle.draw();
        for (int i = 0; i < nonRailColliderList.getSize(); i++)
        {
            NonRailCollider nonRailCollider = nonRailColliderList.get(i);
            nonRailCollider.hide();
            nonRailCollider.move();
            nonRailCollider.draw();
            nonRailColliderList.add(railColliderList.collisionCheck(nonRailCollider));
            paddle.collisionCheck(nonRailCollider);
            if (nonRailCollider.isOutOfBounds())
                nonRailCollider.tellToDie();
            if (nonRailCollider.getShouldDie())
            {
                nonRailCollider.kill();
                nonRailColliderList.delete(nonRailCollider);
            }
        }
        railColliderList.draw();
        Graphics g = panel.getGraphics();
        g.setColor(panel.getBackground());
        g.fillRect(0, 0, 40, 20);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.setColor(new Color(0, 0, 0));
        g.drawString(String.valueOf(Missile.counter), 10, 20);
        gameOver();
    }

    /**
     Game Over checks if there is any bricks left and shows a popup if it is
     empty, then handles restarting the game. It also checks if the last ball
     hits the bottom of the screen which causes a user to lose. If it is the
     last ball it shows a popup then restarts.
     */
    private void gameOver()
    {
        Rail death = new Rail(0, 700, 500, 1);
        if (railColliderList.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Congratulations", "Game Over",
                    JOptionPane.INFORMATION_MESSAGE);
            paddle.hide();
            nonRailColliderList.hide();
            startGame();
        }
        for (int i = 0; i < nonRailColliderList.getSize(); i++)
        {
            NonRailCollider collider = nonRailColliderList.get(i);
            if (death.collidedWith(collider))
            {
                if (nonRailColliderList.getSize() > 1) //Since paddle is in nonRailColliders i think
                {
                    collider.hide();
                    nonRailColliderList.delete(collider);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Git Gud", "Game Over",
                        JOptionPane.INFORMATION_MESSAGE);
                paddle.hide();
                nonRailColliderList.hide();
                startGame();
            }
        }
    }

    /**
     StartGame takes care of the generation of bricks, the randomization of
     missile and duplicate bricks, and creates a new ball and paddle. Adding the
     ball in turn starts the movement and allows the user to play.
     */
    private void startGame()
    {
        this.getParent().getParent().setSize(WIDTH, HEIGHT + 100);
        railColliderList = new RailColliderList();
        nonRailColliderList = new NonRailColliderList();
        Random rand = new Random();
        for (int i = 0; i <= 10; i++)
        {
            for (int j = 1; j <= 10; j++)
            {
                Brick brick;
                int num = rand.nextInt();
                switch (num % 15)
                {
                    case 0:
                        brick = new MissileBrick(i * 50, j * 40, 50, 20, panel);
                        break;
                    case 5:
                        brick = new DuplicateBrick(i * 50, j * 40, 50, 20, panel);
                        break;
                    default:
                        brick = new Brick(i * 50, j * 40, 50, 20, panel);
                        break;
                }
                railColliderList.add(brick);
            }
        }
        nonRailColliderList.add(new Ball((WIDTH - 10) / 2, HEIGHT - 75, 2, -2, panel));
        paddle = new Paddle((WIDTH - 100) / 2, HEIGHT - 50, 100, 20, panel);
    }

    /**
     This method is called from within the init() method to initialize the form.
     WARNING: Do NOT modify this code. The content of this method is always
     regenerated by the Form Editor.
     */
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents()
   {

      panel = new java.awt.Panel();

      setLayout(null);

      panel.setBackground(new java.awt.Color(150, 150, 150));
      panel.setPreferredSize(new java.awt.Dimension(800, 500));
      panel.addKeyListener(new java.awt.event.KeyAdapter()
      {
         public void keyPressed( java.awt.event.KeyEvent evt )
         {
            panelKeyPressed(evt);
         }

         public void keyReleased( java.awt.event.KeyEvent evt )
         {
            panelKeyReleased(evt);
         }
      });
      add(panel);
      panel.setBounds(0, 0, 800, 500);
   }// </editor-fold>//GEN-END:initComponents
   /**
     keyPressed handles modifying the playing field. When either up or down are
     pressed the speed is modified. If left or right are pressed the paddle
     moves. When C and V are pressed the missle launch angle is changed and
     drawn. The spacebar launches the missles in the missle angle.

     @param evt the keyEvent which is pressed
     */
    private void panelKeyPressed(java.awt.event.KeyEvent evt)
   {//GEN-FIRST:event_panelKeyPressed
       switch (evt.getKeyCode())
       {
           case 38:
               moveTimer.setDelay(moveTimer.getDelay() - 1);
               break;
           case 40:
               moveTimer.setDelay(moveTimer.getDelay() + 1);
               break;
           case 37:
               paddle.setTravellingLeft(true);
               break;
           case 39:
               paddle.setTravellingRight(true);
               break;
           case 67:
               paddle.hide();
               Missile.angle -= 10;
               if (Missile.angle < 45)
                   Missile.angle = 45;
               paddle.draw();
               break;
           case 86:
               paddle.hide();
               Missile.angle += 10;
               if (Missile.angle > 135)
                   Missile.angle = 135;
               paddle.draw();
               break;
           case 32:
               if (paddle.getFiringmissile() && !Missile.isMissileInPlay && Missile.counter > 0)
               {
                   nonRailColliderList.add(paddle.shootMissile(Missile.angle));
                   Missile.isMissileInPlay = true;
                   Missile.counter--;
               }
               paddle.toggleFireMissile();
           default:
               break;
       }
   }//GEN-LAST:event_panelKeyPressed

    /**
     To handle moving swiftly when the left or right arrow keys are released 
     the paddle will stop.
     @param evt the keyEvent which is released
     */
    private void panelKeyReleased(java.awt.event.KeyEvent evt)
   {//GEN-FIRST:event_panelKeyReleased
       switch (evt.getKeyCode())
       {
           case 37:
               paddle.setTravellingLeft(false);
               break;
           case 39:
               paddle.setTravellingRight(false);
               break;
       }
   }//GEN-LAST:event_panelKeyReleased
    // End of variables declaration                   
}
