package src.colliders.railcolliders;

import src.PFigure;
import src.colliders.Collider;
import src.colliders.nonrailcolliders.NonRailCollider;

import java.awt.*;

public abstract class RailCollider extends Collider {
    public RailCollider(int startX, int startY, int _width, int _height, int pr, Panel p) {
        super(startX, startY, _width, _height, pr, p);
    }

    public boolean didHitSide(NonRailCollider nonRailCollider) {
        Rail topRail = new Rail(x + 2, y, width - 4, 1);
        Rail bottomRail = new Rail(x + 2, y + height - 1, width - 4, 1);

        return nonRailCollider.collidedWith(topRail) || nonRailCollider.collidedWith(bottomRail);
    }

    public boolean didHitTopBottom(NonRailCollider nonRailCollider) {
        Rail leftRail = new Rail(x, y + 2, 1, height - 4);
        Rail rightRail = new Rail(x + width - 1, y + 2, 1, height - 4);

        return nonRailCollider.collidedWith(leftRail) || nonRailCollider.collidedWith(rightRail);
    }

    public abstract PFigure didGetDestroyed();
}
