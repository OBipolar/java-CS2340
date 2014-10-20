package spaceTrader.Planets;

/**
 * Enum that defines Pirates types
 * 
 * @author Menghang Li
 *
 */
public enum Pirates {
    ASKFORMONEY(0), FIREONSIGHT(1), RECRUTER(2), KILLONSIGHT(3);

    public static final int KINDS_OF_PIRATES = 4;

    private int num;

    Pirates(int num) {
        this.num = num;
    }

}
