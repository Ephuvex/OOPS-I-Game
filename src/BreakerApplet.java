/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import src.colliders.nonrailcolliders.Ball;
import src.colliders.nonrailcolliders.Missile;
import src.colliders.nonrailcolliders.NonRailCollider;
import src.colliders.nonrailcolliders.NonRailColliderList;
import src.colliders.railcolliders.Paddle;
import src.colliders.railcolliders.RailColliderList;
import src.colliders.railcolliders.bricks.Brick;
import src.colliders.railcolliders.bricks.DuplicateBrick;
import src.colliders.railcolliders.bricks.MissileBrick;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * @author Quinn, Andrew
 */
public class BreakerApplet extends java.applet.Applet implements java.awt.event.ActionListener
{

    private static final int HEIGHT = 700;
    private static final int WIDTH = 500;
    private javax.swing.Timer moveTimer = new javax.swing.Timer(16, this);
    private RailColliderList railColliderList;
    private NonRailColliderList nonRailColliderList;
    private Paddle paddle;
    // Variables declaration - do not modify                     
    private java.awt.Panel panel;

    @Override
    public void init()
    {
        Window window = (Window) this.getParent().getParent();
        window.setSize(WIDTH, HEIGHT + 100);
        window.setLocationRelativeTo(null);

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

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        panel.paint(g);
    }

    private void setStage()
    {
        panel.setSize(WIDTH, HEIGHT);
        railColliderList = new RailColliderList();
        nonRailColliderList = new NonRailColliderList();

        Random rand = new Random();
        startGame();
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        paddle.hide();
        paddle.move();
        paddle.draw();
        gameOver();


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
    }

    private void gameOver()
    {
        if (railColliderList.bricksLeft())
        {
            PopUp.infoBox("Congratulations", "Game Over!");
            startGame();
        }
        for (int i = 0; i < nonRailColliderList.getSize(); i++)
        {
            PFigure pFigure = nonRailColliderList.get(i);
            if (new Rail(0, 700, 500, 1).collidedWith(pFigure))
            {
                if (nonRailColliderList.getSize() > 1) //Since paddle is in nonRailColliders i think
                {
                    pFigure.hide();
                    nonRailColliderList.delete(pFigure);
                    return;
                }
                PopUp.infoBox("Git Gud!", "Game Over");
                startGame();
            }
        }
    }

    private void startGame()
    {
        paddle.hide();
        railColliderList.hide();
        nonRailColliderList.hide();
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
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
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
               break;
           default:
               break;
       }
   }//GEN-LAST:event_panelKeyPressed

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
