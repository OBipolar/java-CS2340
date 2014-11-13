package spacetrader.goods;

/**
 * Parent class of all goods
 * 
 * @author mli
 *
 */
public class Good {

    public static final int NUM_OF_GOODS = 10;

    private String name;
    private int mtlp;
    // MTLP = Minimum Tech Level to Produce this resource
    // (You can't buy on planets below this level)
    @SuppressWarnings("unused")
    private int mtlu;
    // MTLU = Minimum Tech Level to Use this resource
    // (You can't sell on planets below this level)
    private int ttp;
    // TTP = Tech Level which produces the most of this item
    private int basePrice;
    // Price increase per tech level
    private int ipl;

    private int var;
    private int priceIncrease;
    private int priceLowCase;
    private int priceHighCase;
    private int minPriceWithTrader;
    private int maxPriceWithTrader;

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Good)) {
            return false;
        } else {
            Good good = (Good) object;
            if (good.basePrice == this.basePrice && good.ipl == this.ipl
                    && good.maxPriceWithTrader == this.maxPriceWithTrader
                    && good.minPriceWithTrader == good.minPriceWithTrader
                    && good.mtlp == this.mtlp) {
                return true;
            }
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMtlp() {
        return mtlp;
    }

    public void setMtlp(int mtlp) {
        this.mtlp = mtlp;
    }

    public int getMtlu() {
        return mtlp;
    }

    public void setMtlu(int mtlu) {
        this.mtlu = mtlu;
    }

    public int getTtp() {
        return ttp;
    }

    public void setTtp(int ttp) {
        this.ttp = ttp;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getIpl() {
        return ipl;
    }

    public void setIpl(int ipl) {
        this.ipl = ipl;
    }

    public int getVar() {
        return var;
    }

    public void setVar(int var) {
        this.var = var;
    }

    public int getPriceIncrease() {
        return priceIncrease;
    }

    public void setPriceIncrease(int priceIncrease) {
        this.priceIncrease = priceIncrease;
    }

    public int getPriceLowCase() {
        return priceLowCase;
    }

    public void setPriceLowCase(int priceLowCase) {
        this.priceLowCase = priceLowCase;
    }

    public int getPriceHighCase() {
        return priceHighCase;
    }

    public void setPriceHighCase(int priceHighCase) {
        this.priceHighCase = priceHighCase;
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
