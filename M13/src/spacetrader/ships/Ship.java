package spacetrader.ships;


/**
 * Parent class of all ships
 * 
 * @author Menghang Li
 *
 */
public class Ship {

    /**
     * name of the ship
     */
    private String name;
    /**
     * cargo capacity
     */
    private int cargoBay;
    /**
     * weapon slots
     */
    private int weaponSlots;
    /**
     * shield slots
     */
    private int shieldSlots;
    /**
     * gadget slots
     */
    private int gadgetSlots;
    /**
     * number of crews on the ship
     */
    private int crew;
    /**
     * fuel amount
     */
    private int fuel;
    /**
     * minimum tech level to use the ship
     */
    private int minTechLevel;
    /**
     * cost to refill the fule per unit
     */
    private int fuelCost;
    /**
     * price of the ship
     */
    private int price;
    /**
     * tbd
     */
    private int bounty;
    /**
     * tbd
     */
    private int occurrence;
    /**
     * hull strength
     */
    private int hullStrength;
    /**
     * the ability to attract police
     */
    private int police;
    /**
     * the ability to attract pirate
     */
    private int pirate;
    /**
     * the ability to attract trader
     */
    private int trader;
    /**
     * cost to repair per hull strength
     */
    private int repairCost;
    /**
     * size of the ship
     */
    private int size;
    /**
     * attack attribute
     */
    private int attack;
    /**
     * shield attribute
     */
    private int shield;
    
    private String url;
    
  

    public String toString() {
        return String.format("ship type: %s\n", name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCargoBay() {
        return cargoBay;
    }

    public void setCargoBay(int cargoBay) {
        this.cargoBay = cargoBay;
    }

    public int getWeaponSlots() {
        return weaponSlots;
    }

    public void setWeaponSlots(int weaponSlots) {
        this.weaponSlots = weaponSlots;
    }

    public int getShieldSlots() {
        return shieldSlots;
    }

    public void setShieldSlots(int shieldSlots) {
        this.shieldSlots = shieldSlots;
    }

    public int getGadgetSlots() {
        return gadgetSlots;
    }

    public void setGadgetSlots(int gadgetSlots) {
        this.gadgetSlots = gadgetSlots;
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getMinTechLevel() {
        return minTechLevel;
    }

    public void setMinTechLevel(int minTechLevel) {
        this.minTechLevel = minTechLevel;
    }

    public int getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(int fuelCost) {
        this.fuelCost = fuelCost;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBounty() {
        return bounty;
    }

    public void setBounty(int bounty) {
        this.bounty = bounty;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public int getPolice() {
        return police;
    }

    public void setPolice(int police) {
        this.police = police;
    }

    public int getPirate() {
        return pirate;
    }

    public void setPirate(int pirate) {
        this.pirate = pirate;
    }

    public int getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(int repairCost) {
        this.repairCost = repairCost;
    }

    public int getTrader() {
        return trader;
    }

    public void setTrader(int trader) {
        this.trader = trader;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHullStrength() {
        return hullStrength;
    }

    public void setHullStrength(int hullStrength) {
        this.hullStrength = hullStrength;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }
    
    public String getUrl() {
    	return url;
    }
    
    public void setUrl(String s) {
    	url = s;
    }

}
