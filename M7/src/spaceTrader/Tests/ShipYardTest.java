package spaceTrader.Tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import spaceTrader.APIs.ShipYard;
import spaceTrader.APIs.SqliteAPI;
import spaceTrader.Equipment.EquipmentFactory;
import spaceTrader.Equipment.Shield;
import spaceTrader.Equipment.Weapon;
import spaceTrader.Planets.GameCharacter;
import spaceTrader.Ships.PlayerShip;
import spaceTrader.Ships.Ship;
import spaceTrader.Ships.ShipFactory;

public class ShipYardTest {

    private ShipYard sY;
    private SqliteAPI db;
    
    
    @Before
    public void setUp() throws Exception {
        sY = new ShipYard();
        db = new SqliteAPI();
    }

    @Test
    public void testPlayerBuyShip() {
        PlayerShip playerShip = db.getShip();
        List<String> shipNames = sY.getShipNames();
        String ship = shipNames.get(0);
        sY.playerBuyShip(ship);
        assertNotEquals(playerShip, db.getShip());
        
    }

    @Test
    public void testPlayerBuyWeapon() {
        PlayerShip playerShip = db.getShip();
        GameCharacter player = db.getPlayer();
        int money = player.getMoney();
        int attack = playerShip.getAttack();
        int weaponSlots = playerShip.getWeaponSlots();
        
        List<String> names = sY.getWeaponNames();
        String weapon = names.get(0);
        sY.playerBuyWeapon(weapon);
        assertNotEquals(attack, db.getShip().getAttack());
        assertNotEquals(weaponSlots, db.getShip().getWeaponSlots());
        assertNotEquals(money, db.getPlayer().getMoney());
        db.update();

    }

    @Test
    public void testPlayerBuyShield() {
        PlayerShip playerShip = db.getShip();
        GameCharacter player = db.getPlayer();
        int money = player.getMoney();
        int shield = playerShip.getShield();
        int shieldSlots = playerShip.getShieldSlots();
        List<String> names = sY.getShieldNames();
        String name = names.get(0);
        sY.playerBuyShield(name);
        assertNotEquals(shield, db.getShip().getShield());
        assertNotEquals(shieldSlots, db.getShip().getShieldSlots());
        assertNotEquals(money, db.getPlayer().getMoney());
    }
    
    @Test
    public void testPlayerBuyCargoExpansion() {
        PlayerShip playerShip = db.getShip();
        GameCharacter player = db.getPlayer();
        int money = player.getMoney();
        int cargoBay = playerShip.getBase().getCargoBay();
        List<String> name = sY.getGadgetNames();
        if (!name.isEmpty()) {
            sY.playerBuyCargoExpansion();
            assertNotEquals(cargoBay, db.getShip().getCargoSpace());
            assertNotEquals(money, db.getPlayer().getMoney());
        }
    }

}
