// 211767561 Ofri Keidar
package sprite;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * a SpriteCollection will hold a collection of sprites.
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * constructor for the sprite collection field.
     *
     * @param sprites the sprites array list.
     */
    public SpriteCollection(List<Sprite> sprites) {
        this.sprites = new ArrayList<>();
        this.sprites.addAll(sprites);
    }

    /**
     * second constructor that initialize the array list.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * in this method we add the s sprite to the list.
     *
     * @param s the sprite we want to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * in this method we remove the s sprite from the list.
     *
     * @param s the sprite we want to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * in this method we call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // we must create a copy of the sprites list to avoid concurrent modification issues
        List<Sprite> newSpritesList = new ArrayList<>(this.sprites);
        for (Sprite s : newSpritesList) {
            s.timePassed();
        }
    }

    /**
     * in this method we call drawOn(d) on all sprites.
     *
     * @param d the surface we want to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}

