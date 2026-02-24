package maputils;

public class MapConfig {
    public final int goodTileMaxProb;
    public final int badTileMaxImpact;
    public final int appleBonus;
    public MapConfig(int goodTileMaxProb, int badTileMaxImpact, int appleBonus) {
        this.goodTileMaxProb = goodTileMaxProb;
        this.badTileMaxImpact = badTileMaxImpact;
        this.appleBonus = appleBonus;
    }
}
