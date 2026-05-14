package maputils.topobjects;

import enums.PlayerState;
import maputils.interfaces.FoundableObject;
import maputils.interfaces.TopObject;
import toolkit.Player;

import java.io.Serializable;

public class Door implements FoundableObject, TopObject, Serializable
{
    @Override
    public String getEndingOfFindingMessage() {
        return "a door";
    }
    @Override
    public void find(Player p)
    {
        p.state = PlayerState.won;
    }
}
