package maputils;

import maputils.interfaces.FoundableObject;
import maputils.interfaces.TopObject;
import toolkit.Player;

public class Apple implements FoundableObject, TopObject {
    private final int bonus;
    public Apple(int bonus) {
        this.bonus = bonus;
    }
    @Override
    public void find(Player p) {
        p.updateHealth(bonus);
    }
}
