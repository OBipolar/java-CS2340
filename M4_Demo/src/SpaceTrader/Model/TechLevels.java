package spaceTrader.Model;
/*
* This enumerator represents technology levels of solar systems in the universe.
*/
public enum TechLevels {
	PREARGICULCURE(0), MEDIEVAL(1), RENAISSANCE(2), EARLYINDUSTRIAL(3), 
	INDUSTRIAL(4), POSTINDUSTRIAL(5), HITECH(6);
	
	public static final int NUM_OF_TECHLEVELS = 7;  
	
	private int num;
	
	TechLevels(int num) {
		this.num = num;
	}
	
}
