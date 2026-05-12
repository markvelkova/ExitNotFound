package maputils.interfaces;

import toolkit.Player;

public interface FoundableObject
{
    String getEndingOfFindingMessage();
    void find(Player p);
}

