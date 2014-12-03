package spacetrader.ships;

/**
 * The ship players gets to start the game
 * 
 * @author Menghang Li
 *
 */
public class Flea extends Ship {

    public Flea() {
        setName("Flea");
        setCargoBay(10);
        setWeaponSlots(0);
        setShieldSlots(0);
        setGadgetSlots(0);
        setCrew(1);
        setFuel(20);
        setMinTechLevel(4);
        setFuelCost(1);
        setPrice(2000);
        setBounty(5);
        setOccurrence(2);
        setHullStrength(25);
        setPolice(-1);
        setPirate(-1);
        setTrader(0);
        setRepairCost(1);
        setSize(0);
        setAttack(40);
        setUrl("http://icons.iconarchive.com/icons/everaldo/crystal-clear/64/App-kspaceduel-spaceship-icon.png");

    }

}
