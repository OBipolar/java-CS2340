package spacetrader.planets;

/**
 * Enum that defines all resources level
 * 
 * @author Menghang Li
 *
 */
public enum ResourcesLevel {
    NOSPECIAL(0), MINERALPOOR(1), DESERT(2), LOTSOFWAR(3), RICHSOIL(4), POORSOIL(
            5), RICHFAUNA(6), LIFELESS(7), WEIRDMUSHROOMS(8), LOTSOFHERBS(9), ARTISTIC(
            10), WARLIKE(11), MINERALRICH(12);

    public static final int NUM_OF_RESOURCES_LEVEL = 13;

    @SuppressWarnings("unused")
    private int num;

    ResourcesLevel(int num) {
        this.num = num;
    }

}
