package spacetrader.planets;

/**
 * Enum that defines all tech levels
 * 
 * @author Menghang Li
 *
 */
public enum TechLevels {
    PREARGICULCURE(0), MEDIEVAL(1), RENAISSANCE(2), EARLYINDUSTRIAL(3), INDUSTRIAL(
            4), POSTINDUSTRIAL(5), HITECH(6);

    public static final int NUM_OF_TECHLEVELS = 7;

    @SuppressWarnings("unused")
    private int num;

    TechLevels(int num) {
        this.num = num;
    }

}
