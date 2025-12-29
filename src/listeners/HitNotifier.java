// 211767561 Ofri Keidar
package listeners;

/**
 * The HitNotifier interface indicate that objects that implement it send notifications when they are being hit.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl the listener we want to add to the HitListener list.
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the listener we want to remove from the HitListener list.
     */
    void removeHitListener(HitListener hl);

}
