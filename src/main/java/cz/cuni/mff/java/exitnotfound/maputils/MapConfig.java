package cz.cuni.mff.java.exitnotfound.maputils;

import java.io.Serializable;

/**
 * Configuration parameters for map generation and game difficulty.
 * This class holds immutable configuration values that control how maps are generated and
 * how the game difficulty is adjusted. Values can be modified by creating a new MapConfig
 * with different parameters.
 */
public class MapConfig implements Serializable {
    /**
     * Maximum probability value for good tiles to heal the player.
     * Lower values mean higher chance of healing on good tiles.
     */
    public final int goodTileMaxProb;
    /**
     * Maximum damage amount that bad tiles can inflict on the player.
     * Actual damage is randomized up to this value.
     */
    public final int badTileMaxImpact;
    /**
     * Health bonus amount provided when the player finds and collects an apple.
     */
    public final int appleBonus;

    /**
     * Constructs a MapConfig with the specified parameters.
     *
     * @param goodTileMaxProb the maximum probability for good tile healing
     * @param badTileMaxImpact the maximum damage for bad tiles
     * @param appleBonus the health bonus provided by apples
     */
    public MapConfig(int goodTileMaxProb, int badTileMaxImpact, int appleBonus) {
        this.goodTileMaxProb = goodTileMaxProb;
        this.badTileMaxImpact = badTileMaxImpact;
        this.appleBonus = appleBonus;
    }
}