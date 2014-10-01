package SpaceTrader.Goods;

public enum PriceIncrease {
    DROUGHT(0), COLD(1), CROPFAIL(2), WAR(3), BOREDOM(4), PLAGUE(5),
    LACKOFWORKERS(6);
    
    public static final int EVENTS = 7;
    
    private int num;
    
    PriceIncrease (int num) {
        this.num = num;
    }
     
}
