package spaceTrader.Ships;

/**
 * Child of Ship, Gnat
 * 
 * @author mli
 *
 */
public class Gnat extends Ship {

    public Gnat() {
        setName("Gnat");
        setCargoBay(15);
        setWeaponSlots(1);
        setShieldSlots(0);
        setGadgetSlots(1);
        setCrew(1);
        setFuel(14);
        setMinTechLevel(5);
        setFuelCost(2);
        setPrice(10000);
        setBounty(50);
        setOccurrence(28);
        setPullStrength(100);
        setPolice(0);
        setPirate(0);
        setTrader(0);
        setRepairCost(1);
        setSize(1);

    }
}
