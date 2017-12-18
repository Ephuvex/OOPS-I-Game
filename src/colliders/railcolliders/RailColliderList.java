package src.colliders.railcolliders;

import src.PFigure;
import src.colliders.nonrailcolliders.NonRailCollider;

/**
 * A list class for rail colliders. Supports adding and removing RailColliders
 * from the list, but more importantly allows for NonRailColliders to be checked
 * against the entire list for collisions, wherein collisions are appropriately
 * handled. This class also allows for the drawing of all contained items at
 * once.
 *
 * @author andrewkaiser
 */
public class RailColliderList
{

    private static final int SIZE = 800;
    private int size;
    private RailCollider[] colliders = new RailCollider[SIZE];

    /**
     * Adds a RailCollider to the list, and increments the size.
     *
     * @param collider the collider to be added to the list
     */
    public void add(RailCollider collider)
    {
        colliders[size++] = collider;
    }

    /**
     * Removes a RailCollider from the list, and decrements the size.
     *
     * @param collider the collider to be removed
     */
    @SuppressWarnings("WeakerAccess")
    public void remove(RailCollider collider)
    {
        for (int i = 0; i < size; i++)
        {
            if (colliders[i].equals(collider))
            {
                System.arraycopy(colliders, i + 1, colliders, i, size-- - i);
                return;
            }
        }
    }

    /**
     * Checks to see if the list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * Will run through all contained colliders, and if any collided with the
     * passed NonRailCollider, will return any new objects created by the
     * collision (such as a new ball). If no collision took place or if nothing
     * was created from the collision, will return null.
     *
     * @param nonRailCollider the figure to check for collision with
     * @return a new PFigure if applicable, null otherwise
     */
    public PFigure collisionCheck(NonRailCollider nonRailCollider)
    {
        for (int i = 0; i < size; i++)
        {
            RailCollider railCollider = colliders[i];
            if ( nonRailCollider.collidedWith(railCollider) )
            {
                remove(railCollider);
                railCollider.hide();
                nonRailCollider.didCollideWith(railCollider);
                return railCollider.didGetDestroyed();
            }
        }
        return null;
    }

    /**
     * Draws all contained colliders sequentially.
     */
    public void draw()
    {
        for (int i = 0; i < size; i++)
            colliders[i].draw();
    }

    public boolean noBricksLeft()
    {
        return size == 0;
    }

    public void hide()
    {
        for (int i = 0; i < size; i++)
            colliders[i].hide();
    }
}
