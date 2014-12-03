package spacetrader.apis;

import java.util.Random;

import spacetrader.planets.SolarSystem;
import spacetrader.ships.BumbleBee;
import spacetrader.ships.Firefly;
import spacetrader.ships.Flea;
import spacetrader.ships.Gnat;
import spacetrader.ships.Mosquito;
import spacetrader.ships.PlayerShip;
import spacetrader.ships.Ship;

public class Encounter {
	private Ship encounteredShip;
	private Random rand;
	private PlayerShip ps;
	private boolean playerKilled;
	private boolean oppKilled;
	
	public Encounter(PlayerShip ps) {
		this.ps = ps;
		playerKilled = false;
		oppKilled = false;
		rand = new Random();
		int num = rand.nextInt(5);
		switch (num) {
        case 0:
            encounteredShip = new Flea();
            break;
        case 1:
            encounteredShip = new Gnat();
            break;
        case 2:
            encounteredShip = new Firefly();
            break;
        case 3:
            encounteredShip = new Mosquito();
            break;
        case 4:
            encounteredShip = new BumbleBee();
            break;
        default :
            encounteredShip = new Flea();
            break;
		}
	}
	
	public int getOppType() {
		int num = rand.nextInt(3);
		if (num == 0) {
			return 0;
		} else {
			return 1;
		}
	}
	
	public String getOppAction(int oppType) {
		//System.out.println("playerHull: " + playerHull);
		if (oppType == 0) {
			return "It ignores you?.";
		} else {
			int num = rand.nextInt(4);
			if (num == 0) {
				return "It ignores you?.";
			} else {
				int life = ps.getBase().getHullStrength() - 10;
				System.out.println("life: " + life);
				if (life <= 0) {
					playerKilled = true;
					return "Killed";
				} else {
					ps.getBase().setHullStrength(life);
					System.out.println("player hull: " + ps.getBase().getHullStrength());
					return "It attacks you?.";
				}
				
			}
		}
	}
	
	public void playerAttack() {
		int life = encounteredShip.getHullStrength() - ps.getBase().getAttack();
		if (life <= 0) {
			life = 0;
		} else {
			encounteredShip.setHullStrength(life);
		}
	}
	
	public int getPlayerHull() {
		return ps.getBase().getHullStrength();
	}
}
