package spacetrader.goods;

/**
 * Factory for Good
 * 
 * @author mli
 *
 */
public class GoodFactory {

	/**
	 * Return a good given its name
	 * 
	 * @param name the name of good
	 * @return a good
	 */
	public Good getGood(String name) {
		Good g;
        switch (name) {
	        case "Water":
	            g = (new Water());
	            break;
	        case "Firearms":
	            g = (new Firearms());
	            break;
	        case "Food":
	            g = (new Food());
	            break;
	        case "Furs":
	            g = (new Furs());
	            break;
	        case "Games":
	            g = (new Games());
	            break;
	        case "Machines":
	            g = (new Machines());
	            break;
	        case "Narcotics":
	            g = (new Narcotics());
	            break;
	        case "Robots":
	            g = (new Robots());
	            break;
	        case "Medicine":
	            g = (new Medicine());
	            break;
	        case "Ore":
	            g = (new Ore());
	            break;
            default : 
            	g = new Water();
            	break;
        }
        return g;
	}
	
	
}
