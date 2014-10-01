package spaceTrader.Goods;

/**
 * Parent class of all goods
 * 
 * @author mli
 *
 */
public class Good {
    
    public static final int NUM_OF_GOODS = 10;
    
    private String name;
    private int MTLP;  
    //MTLP = Minimum Tech Level to Produce this resource 
    //(You can't buy on planets below this level)
    private int MTLU;
    //MTLU = Minimum Tech Level to Use this resource 
    //(You can't sell on planets below this level)
    private int TTP;
    //TTP = Tech Level which produces the most of this item
    private int basePrice;   
    private int IPL;
    //Price increase per tech level
    private int var;
    private int PriceIncrease;
    private int PriceLowCase;
    private int PriceHighCase;
    private int minPriceWithTrader;
    private int maxPriceWithTrader;
    
    @Override
    public String toString() {
        return getName();
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMTLP() {
        return MTLP;
    }
    public void setMTLP(int mTLP) {
        MTLP = mTLP;
    }
    public int getMTLU() {
        return MTLU;
    }
    public void setMTLU(int mTLU) {
        MTLU = mTLU;
    }
    public int getTTP() {
        return TTP;
    }
    public void setTTP(int tTP) {
        TTP = tTP;
    }
    public int getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }
    public int getIPL() {
        return IPL;
    }
    public void setIPL(int iPL) {
        IPL = iPL;
    }
    public int getVar() {
        return var;
    }
    public void setVar(int var) {
        this.var = var;
    }
    public int getPriceIncrease() {
        return PriceIncrease;
    }
    public void setPriceIncrease(int priceIncrease) {
        PriceIncrease = priceIncrease;
    }
    public int getPriceLowCase() {
        return PriceLowCase;
    }
    public void setPriceLowCase(int priceLowCase) {
        PriceLowCase = priceLowCase;
    }
    public int getPriceHighCase() {
        return PriceHighCase;
    }
    public void setPriceHighCase(int priceHighCase) {
        PriceHighCase = priceHighCase;
    }
    public int getMinPriceWithTrader() {
        return minPriceWithTrader;
    }
    public void setMinPriceWithTrader(int minPriceWithTrader) {
        this.minPriceWithTrader = minPriceWithTrader;
    }
    public int getMaxPriceWithTrader() {
        return maxPriceWithTrader;
    }
    public void setMaxPriceWithTrader(int maxPriceWithTrader) {
        this.maxPriceWithTrader = maxPriceWithTrader;
    }
    
    
}
