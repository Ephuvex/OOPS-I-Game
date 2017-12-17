package src.colliders.nonrailcolliders;

import src.colliders.Collider;
import src.colliders.railcolliders.RailCollider;

import java.awt.*;

public abstract class NonRailCollider extends Collider {
    public NonRailCollider(int startX, int startY, int _width, int _height, int pr, Panel p) {
        super(startX, startY, _width, _height, pr, p);
    }

    public abstract void didCollideWith(RailCollider collider);

    public abstract void kill();
}
