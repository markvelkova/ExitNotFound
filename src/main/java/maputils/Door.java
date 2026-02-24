package maputils;

import enums.PlayerState;
import maputils.interfaces.FoundableObject;
import maputils.interfaces.TopObject;
import toolkit.Player;

public class Door implements FoundableObject, TopObject
{
    public void find(Player p)
    {
        p.state = PlayerState.won;
    }
}
