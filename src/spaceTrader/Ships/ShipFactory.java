package spaceTrader.Ships;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory that produces ships
 * 
 * @author mli
 *
 */
public class ShipFactory {

	/**
	 * Returns a ship object given the ship name
	 * 
	 * 
	 * @param name
	 * @return
	 */
	public Ship getShip(String name) {
		Ship ship;
		switch (name) {
	        case "Flea":
	            ship = new Flea();
	            break;
	        case "Gnat":
	            ship = new Gnat();
	            break;
	        case "Firefly":
	            ship = new Firefly();
	            break;
	        case "Mosquito":
	            ship = new Mosquito();
	            break;
	        case "BumbleBee":
	            ship = new BumbleBee();
	            break;
            default :
            	ship = new Flea();
            	break;
        }
		return ship;
	}
	
	/**
	 * Return a list of ships available on a planet given its tech level
	 * 
	 * @param techLevel
	 * @return
	 */
	public List<Ship> getShip (int techLevel) {
		List<Ship> all = new ArrayList<>();
		all.add(new Flea());
		all.add(new Firefly());
		all.add(new Gnat());
		all.add(new Mosquito());
		all.add(new BumbleBee());
		
		List<Ship> available = new ArrayList<>();
		
		for (Ship s : all) {
			if (s.getMinTechLevel() <= techLevel) {
				available.add(s);
			}
		}
		return available;
	}
	
	
}
