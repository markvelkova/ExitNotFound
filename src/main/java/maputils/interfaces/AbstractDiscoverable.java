package maputils.interfaces;

import java.io.Serializable;

public abstract class AbstractDiscoverable implements Discoverable, Serializable {

    private boolean discovered = false;

    @Override
    public void discover() {
        discovered = true;
    }

    @Override
    public boolean wasDiscovered() {
        return discovered;
    }
}