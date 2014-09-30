package SpaceTrader.Model;

/*
* This enumerator represents the resource level of a solar system.
*/
public enum ResourcesLevel {
	NOSPECIAL(0), MINERALPOOR(1), DESERT(2), LOTSOFWAR(3), RICHSOIL(4),
	POORSOIL(5), RICHFAUNA(6), LIFELESS(7), WEIRDMUSHROOMS(8),
	LOTSOFHERBS(9), ARTISTIC(10), WARLIKE(11);
	
	public static final int NUM_OF_RESOURCES_LEVEL = 12;
	
	private int num;
	
	ResourcesLevel (int num) {
		this.num = num;
	}
	
}
