package spacetrader.goods;

/**
 * Parent class of all goods
 * 
 * @author mli
 *
 */
public class Good {

    public static final int NUM_OF_GOODS = 10;

    /**
     * name of the good 
     */
    private String name;
    /**
     * Minimum Tech Level to Produce this resource
     */
    private int mtlp;
    /**
     * Minimum Tech Level to Use this resource
     */
    @SuppressWarnings("unused")
    private int mtlu;

    /**
     * Tech Level which produces the most of this item
     */
    private int ttp;
    /**
     * the base price
     */
    private int basePrice;
    /**
     * Price increase per tech level
     */
    private int ipl;
    /**
     * the maximum percentage that the price can vary above or below the base 
     */
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
    public boolean equals(final Object object) {
        if (!(object instanceof Good)) {
            return false;
        } 
        Good good = (Good) object;
        if (good.basePrice == this.basePrice && good.ipl == this.ipl
                && good.maxPriceWithTrader == this.maxPriceWithTrader
                && good.minPriceWithTrader == good.minPriceWithTrader
                && good.mtlp == this.mtlp) {
            return true;
        }
        return false;

    }

    /**
     * Returns the name of Good
     * 
     * @return the name of Good
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of Good
     * 
     * @param name a name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the mtlp
     * 
     * @return the mtlp
     */
    public int getMtlp() {
        return mtlp;
    }

    /**
     * Set the mtlp
     * 
     * @param mtlp a mtlp
     */
    public void setMtlp(int mtlp) {
        this.mtlp = mtlp;
    }

    /**
     * Return the mtlu
     * 
     * @return the mtlu
     */
    public int getMtlu() {
        return mtlp;
    }

    /**
     * Set the mtlu
     * 
     * @param mtlu
     */
    public void setMtlu(int mtlu) {
        this.mtlu = mtlu;
    }

    /**
     * Return the ttp
     * 
     * @return the ttp
     */
    public int getTtp() {
        return ttp;
    }

    /**
     * Set the ttp
     * 
     * @param ttp a ttp
     */
    public void setTtp(int ttp) {
        this.ttp = ttp;
    }

    /**
     * Return the base price
     * 
     * @return the base price
     */
    public int getBasePrice() {
        return basePrice;
    }

    /**
     * Set the base price
     * 
     * @param basePrice a base price
     */
    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Return ipl
     * 
     * @return ipl
     */
    public int getIpl() {
        return ipl;
    }

    /**
     * Set ipl
     * 
     * @param ipl a ipl
     */
    public void setIpl(int ipl) {
        this.ipl = ipl;
    }

    /**
     * Return the var
     * 
     * @return var
     */
    public int getVar() {
        return var;
    }

    /**
     * set the var
     *
     * @param var a var
     */
    public void setVar(int var) {
        this.var = var;
    }

    /**
     * Returns the priceIncrease
     * 
     * @return priceIncrease
     */
    public int getPriceIncrease() {
        return priceIncrease;
    }

    /**
     * Set the priceIncrease
     * 
     * @param priceIncrease a priceIncrease
     */
    public void setPriceIncrease(int priceIncrease) {
        this.priceIncrease = priceIncrease;
    }

    /**
     * Return the priceLowCase
     * 
     * @return priceLowCase
     */
    public int getPriceLowCase() {
        return priceLowCase;
    }

    /**
     * Set the priceLowCase
     * 
     * @param priceLowCase a priceLowCase
     */
    public void setPriceLowCase(int priceLowCase) {
        this.priceLowCase = priceLowCase;
    }

    /**
     * Return the priceHighCase
     * 
     * @return the priceHighCase
     */
    public int getPriceHighCase() {
        return priceHighCase;
    }

    /**
     * Set the priceHighCase
     * 
     * @param priceHighCase a priceHighCase
     */
    public void setPriceHighCase(int priceHighCase) {
        this.priceHighCase = priceHighCase;
    }

    /**
     * Return minPriceWithTrader
     * 
     * @return minPriceWithTrader
     */
    public int getMinPriceWithTrader() {
        return minPriceWithTrader;
    }

    /**
     * Set the minPriceWithTrader
     * 
     * @param minPriceWithTrader a minPriceWithTrader
     */
    public void setMinPriceWithTrader(int minPriceWithTrader) {
        this.minPriceWithTrader = minPriceWithTrader;
    }

    /**
     * Return the max price with trader
     * 
     * @return the max price with trader
     */
    public int getMaxPriceWithTrader() {
        return maxPriceWithTrader;
    }

    /**
     * Set the maxPriceWithTrader
     * 
     * @param maxPriceWithTrader a maxPriceWithTrader
     */
    public void setMaxPriceWithTrader(int maxPriceWithTrader) {
        this.maxPriceWithTrader = maxPriceWithTrader;
    }

}
