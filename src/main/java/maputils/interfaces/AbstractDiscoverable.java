package maputils.interfaces;

public abstract class AbstractDiscoverable implements Discoverable {

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