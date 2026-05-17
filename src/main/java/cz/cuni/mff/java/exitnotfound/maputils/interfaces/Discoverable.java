package cz.cuni.mff.java.exitnotfound.maputils.interfaces;

/**
 * Interface for objects that can be discovered or revealed through exploration.
 * Discoverable objects track whether they have been discovered by the player,
 * allowing for game mechanics like fog of war and exploration rewards. */
public interface Discoverable {
    /**
     * Marks this object as discovered by the player.
     * */
    void discover();

    /**
     * Queries whether this object has been discovered.
     * @return true if this object has been discovered, false if still hidden     */
    boolean wasDiscovered();
}
