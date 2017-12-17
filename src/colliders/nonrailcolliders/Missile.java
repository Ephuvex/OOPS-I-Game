package src.colliders.nonrailcolliders;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import src.colliders.railcolliders.RailCollider;

import java.awt.*;

/**
 * @author dericksonb
 */
public class Missile extends NonRailCollider {
    public static int angle = 90;
    public static int counter = 10;
    public static boolean isMissileInPlay = false;

    public Missile(int initX, int initY, int initXVel, int initYVel, Panel p) {
        super(initX, initY, 10, 10, 0, p);
        xVel = initXVel;
        yVel = initYVel;
    }

    @Override
    public void move() {
        x += xVel;
        y += yVel;
    }

    @Override
    public void draw() {
        Graphics g = panel.getGraphics();
        g.setColor(Color.blue);
        g.fillOval(x, y, 10, 10);
    }

    @Override
    public void didCollideWith(RailCollider collider) {
        if (collider.compareTo(this) != 0) {
            kill();
        }
    }

    @Override
    public void kill() {
        tellToDie();
        isMissileInPlay = false;
        hide();
    }
}
