package SpaceTrader.Model;

/*
* This enumerator represents polices in the universe.
*/
public enum Polices {
	ASKSOMEMONEY(0), ASKALOTMONEY(1), LEAVEYOURSHIP(2), KILLONSIGHT(3);
	
	public static final int TYPES_OF_POLICE = 4;
	
	private int num;
	
	Polices (int num) {
		this.num = num;
	}
}
