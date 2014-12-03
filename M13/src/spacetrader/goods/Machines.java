package spacetrader.goods;

/**
 *
 * @author zixiangzhu
 */
public class Machines extends Good {
     /**
     * Constructor thats sets all properties for Machines
     */
    public Machines() {
        setName("Machines");
        setMtlp(4);
        setMtlu(3);
        setTtp(5);
        setBasePrice(900);
        setIpl(-30);
        setVar(5);
        setPriceIncrease(PriceIncrease.LACKOFWORKERS.ordinal());
        setPriceLowCase(-1);
        setPriceHighCase(-1);
        setMinPriceWithTrader(30);
        setMaxPriceWithTrader(50);
        
    }
    
}
