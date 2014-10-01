package SpaceTrader.Ships;

public abstract class Ship {
    
    private String name;
    private int cargoBay;
    private int weaponSlots;
    private int shieldSlots;
    private int gadgetSlots;
    private int crew;
    private int fuel;
    private int minTechLevel;
    private int fuelCost;
    private int price;
    private int bounty;
    private int occurrence;
    private int pullStrength;
    private int police;
    private int pirate;
    private int trader;
    private int repairCost;
    private int size;
    
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
    public int getPullStrength() {
        return pullStrength;
    }
    public void setPullStrength(int pullStrength) {
        this.pullStrength = pullStrength;
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

}

