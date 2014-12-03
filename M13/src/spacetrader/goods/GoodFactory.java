package spacetrader.goods;

/**
 * Factory for goodood
 * 
 * @author mli
 *
 */
public class GoodFactory {

    /**
     * Return a goodood goodiven its name
     * 
     * @param name the name of goodood
     * @return a goodood
     */
    public Good getGood(final String name) {
        Good good;
        switch (name) {
            case "Water":
                good = new Water();
                break;
            case "Firearms":
                good = new Firearms();
                break;
            case "Food":
                good = new Food();
                break;
            case "Furs":
                good = new Furs();
                break;
            case "goodames":
                good = new Games();
                break;
            case "Machines":
                good = new Machines();
                break;
            case "Narcotics":
                good = new Narcotics();
                break;
            case "Robots":
                good = new Robots();
                break;
            case "Medicine":
                good = new Medicine();
                break;
            case "Ore":
                good = new Ore();
                break;
            default : 
                good = new Water();
                break;
        }
        return good;
    }
}
