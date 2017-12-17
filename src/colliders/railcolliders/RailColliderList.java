package src.colliders.railcolliders;

import src.PFigure;
import src.colliders.nonrailcolliders.NonRailCollider;

public class RailColliderList {
    private static final int SIZE = 800;
    private int size;
    private RailCollider[] colliders = new RailCollider[SIZE];

    public boolean add(RailCollider collider) {
        colliders[size++] = collider;
        return true;
    }

    public boolean remove(RailCollider collider) {
        for (int i = 0; i < size; i++)
            if (colliders[i].equals(collider)) {
                System.arraycopy(colliders, i + 1, colliders, i, size-- - i);
                return true;
            }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Will run through all colliders, and if any collided with the passed PFigure,
     * will return any new objects created by the collision (such as a new ball).
     * If no collision took place or if nothing was created from the collision,
     * will return null.
     *
     * @param nonRailCollider the figure to check for collision with
     * @return a new PFigure if applicable, null otherwise
     */
    public PFigure collisionCheck(NonRailCollider nonRailCollider) {
        for (int i = 0; i < size; i++) {
            RailCollider railCollider = colliders[i];
            if (nonRailCollider.collidedWith(railCollider)) {
                remove(railCollider);
                railCollider.hide();

                nonRailCollider.didCollideWith(railCollider);

                return railCollider.didGetDestroyed();
            }
        }

        return null;
    }

    public void draw() {
        for (int i = 0; i < size; i++)
            colliders[i].draw();
    }
}
