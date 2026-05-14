package maputils.interfaces;

/**
 *  Interface for map tiles that are passable (not walls or obstacles).
 *  Empty tiles allow the player to move through them. They may have effects on
 *  player health (positive or negative) depending on the specific tile type. */
public interface EmptyMapTile extends MapTile {
    /**
     * Retrieves the health effect this tile has on the player.
     * Each tile can impact player health: positive values heal,
     * negative values damage, zero has no effect.
     * @return the health impact value     */
    public int getPlayerHealthImpact();
}