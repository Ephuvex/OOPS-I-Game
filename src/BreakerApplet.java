/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import src.bricks.Brick;
import src.bricks.BrickList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Quinn, Andrew
 */
public class BreakerApplet extends java.applet.Applet implements java.awt.event.ActionListener, KeyListener {
    private static final int PAUSE_TIME = 20;
    private static final int HEIGHT = 800;
    private static final int WIDTH = 500;
    private javax.swing.Timer moveTimer = new javax.swing.Timer(500, this);
    private BrickList brickList;
    private PFigureList figList;
    private Ball ball;
    private Paddle paddle;
    // Variables declaration - do not modify
    private java.awt.Panel jPanel1;

    @Override
    public void init() {
        Window window = (Window) this.getParent().getParent();
        window.setSize(WIDTH, HEIGHT + 100);
        window.setLocationRelativeTo(null);

        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    moveTimer.start();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean gameIsOver() {
        //Out of bounds?

        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        jPanel1.paint(g);
        brickList.draw();
        ball.draw();
        paddle.draw();
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        setLayout(null);

        addKeyListener(this);

        jPanel1 = new Panel();
        jPanel1.setBackground(new java.awt.Color(100, 100, 100));
        jPanel1.setMaximumSize(new java.awt.Dimension(WIDTH, HEIGHT));
        jPanel1.setMinimumSize(new java.awt.Dimension(WIDTH, HEIGHT));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));
        add(jPanel1);
        jPanel1.setBounds(0, 0, WIDTH, HEIGHT);

        brickList = new BrickList();

        for (int i = 0; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                brickList.add(new Brick(i * 50, j * 40, 50, 20, 0, jPanel1));
            }
        }

        ball = new Ball((WIDTH - 10) / 2, HEIGHT - 75, 10, 10, Color.RED, 10, jPanel1);
        paddle = new Paddle((WIDTH - 100) / 2, HEIGHT - 50, 100, 20, 0, jPanel1);
    }// </editor-fold>

    @Override
    public void actionPerformed(ActionEvent ae) {
        ball.move();
        brickList.collisionCheck(ball);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 83:
                ball.draw();
                figList.add(ball);
                break;
            case 39:
                paddle.move(15, 0);
                break;
            case 37:
                paddle.move(-15, 0);
                break;
            default:
                break;
        }
        // End of variables declaration
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
