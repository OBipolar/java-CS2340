package spacetrader.planets;

/**
 * Enum that defines Polices types
 * 
 * @author Menghang Li
 *
 */
public enum Polices {
    ASKSOMEMONEY(0), ASKALOTMONEY(1), LEAVEYOURSHIP(2), KILLONSIGHT(3);

    /**
     * types of polices
     */
    public static final int TYPES_OF_POLICE = 4;

    @SuppressWarnings("unused")
    private int num;

    Polices(int num) {
        this.num = num;
    }
}
