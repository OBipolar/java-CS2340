package spacetrader.equipment;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Factory for equipments
 * 
 * @author mli
 *
 */
public class EquipmentFactory {
    
    private Map<String, Weapon> weapons;
    private Map<String, Shield> shields;
    
    public EquipmentFactory() {
          
        weapons = new HashMap<String, Weapon>();
        shields = new HashMap<String, Shield>();
        weapons.put(BeamLaser.NAME, new BeamLaser());
        weapons.put(MilitaryLaser.NAME, new MilitaryLaser());
        weapons.put(PulseLaser.NAME, new PulseLaser());
        shields.put(EnergyShield.NAME, new EnergyShield());
        shields.put(ReflectiveShield.NAME, new ReflectiveShield());    
    }
   

    /**
     * Returns all the ship that requires no higher tech level
     * than the given tech level
     * 
     * @param techLevel
     * @return
     */
    public List<Weapon> getWeapon(int techLevel) {
        List<Weapon> wlist = new ArrayList<>();
        Collection<Weapon> col = weapons.values();
        for (Weapon weapon : col) {
            if (weapon.getMinTechLevel() <= techLevel) {
                wlist.add(weapon);
            }
        }
        return wlist;
    }
    
    /**
     * Return a Weapon given its name
     * 
     * @param name the name of the weapon
     * @return a Weapon
     */
    public Weapon getWeapon(String name) {
        return weapons.get(name);
    }
    
    /**
     * Returns all the ship that requires no higher tech level
     * than the given tech level
     * 
     * @param techLevel
     * @return
     */
    public List<Shield> getShield(int techLevel) {
        List<Shield> slist = new ArrayList<>();
        Collection<Shield> col = shields.values();
        for (Shield shield : col) {
            if (shield.getMinTechLevel() <= techLevel) {
                slist.add(shield);
            }
        }
        return slist;
    }
    
    /**
     * Return a Shield given its name
     * 
     * @param name the name of the shield
     * @return a Shield
     */
    public Shield getShield(String name) {
        return shields.get(name);
    }
    
    /**
     * Return a Gadget given its name
     * 
     * @param name the name of the Gadget
     * @return a Gadget
     */
    public CargoExpansion getCargoExpansion() {
        return new CargoExpansion();
    }
    
    

}
