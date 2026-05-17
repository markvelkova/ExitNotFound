package cz.cuni.mff.java.exitnotfound.maputils.interfaces;

import java.io.Serializable;

/**
 * Abstract base class implementing the Discoverable interface.
 *
 * This class provides a common implementation of the discovery mechanism used by
 * most map tiles. It maintains a discovered flag and provides concrete implementations
 * of the discover() and wasDiscovered() methods.
 */
public abstract class AbstractDiscoverable implements Discoverable, Serializable {

    /**
     * Flag tracking whether this object has been discovered by the player.
     */
    private boolean discovered = false;

    /**
     * Initializes a new AbstractDiscoverable with the discovery flag set to false.
     */
    public AbstractDiscoverable() {
    }

    /**
     * Marks this object as discovered by setting its internal flag.
     */
    @Override
    public void discover() {
        discovered = true;
    }

    /**
     * Checks whether this object has been discovered.
     * @return the current discovery status
     */
    @Override
    public boolean wasDiscovered() {
        return discovered;
    }
}