package spacetrader.equipment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spaceTrader.Planets.GameCharacter;
import spacetrader.apis.SqliteAPI;

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
        List<Weapon> w = new ArrayList<>();
        Collection<Weapon> c = weapons.values();
        for (Weapon weapon : c) {
            if (weapon.getMinTechLevel() <= techLevel) {
                w.add(weapon);
            }
        }
        return w;
    }
    
    /**
     * Returns all the ship that requires no higher tech level
     * than the given tech level
     * 
     * @param techLevel
     * @return
     */
    public List<Shield> getShield(int techLevel) {
        List<Shield> s = new ArrayList<>();
        Collection<Shield> c = shields.values();
        for (Shield shield : c) {
            if (shield.getMinTechLevel() <= techLevel) {
                s.add(shield);
            }
        }
        return s;
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
