package space.maxus.skyblockd.objects;

public class SeaCreatureContainer {
    public double chance;
    public int level;
    public int exp;

    public SeaCreatureContainer(int xp, int lvl, double drop) {
        chance = drop;
        level = lvl;
        exp = xp;
    }

    @Override
    public String toString() {
        return "SeaCreatureContainer{" +
                "chance=" + chance +
                ", level=" + level +
                ", exp=" + exp +
                '}';
    }
}
