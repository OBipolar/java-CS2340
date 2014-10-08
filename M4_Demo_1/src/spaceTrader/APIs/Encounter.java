/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spaceTrader.APIs;

import java.sql.SQLException;
import java.util.Random;

import spaceTrader.Goods.Good;
import spaceTrader.Goods.Trade;
import spaceTrader.Planets.GameCharacter;
import spaceTrader.Planets.PlayerShip;
import spaceTrader.Planets.SolarSystem;

/**
 *
 * @author Shaohui Xu(Obipolar)
 */
public class Encounter {
    private SqliteAPI db;
    private GameCharacter player;
    private PlayerShip ship;
    private Trade trade;
    private SolarSystem system;
    private Random rand = new Random();
    
    public Encounter() {
        load();
        
        // generate different encounter situation by probabilty
        int encounterRand = rand.nextInt(100);
        if (encounterRand < 34) {
            Trader opponent  = new Trader();
            encounterTrader(opponent);
        } else if (encounterRand < 67) {
            Police opponent = new Police();
            encounterPolice(opponent);
        } else {
            Pirate opponent = new Pirate();
            encounterPirate(opponent);
        }        
    }
    
    
    /**
     * Loads data from database
     * 
     */
    private void load() {
        try {
            db = new SqliteAPI();
            player = db.getPlayer();
            ship = db.getShip();
            system = db.getSolarSystem(player.getXpos(), player.getYpos());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    // situtation when encountering other trader
    private void encounterTrader(Trader opp) {
        int ignoreRand = rand.nextInt(100);
        
        if (ignoreRand < 50) {
        
        } else {
            encounterTrader(opp);
        }
    }
    
    // inner class for opponent trader
    class Trader {
        private int hull;
        private int shield;
        
        Trader() {
            hull = 100;
            shield = 100;
        }
        
        private void trade(GameCharacter subject) {}
        
        private void attack(GameCharacter subject) {}
        
        private int getHull() {
            return hull;
        }
        
        private int getShield() {
            return shield
        }
    }
    
    // situtation when encountering police
    private void encounterPolice(Police opp) {}
    
    private class Police {}
    
    // situtation when encountering pirate
    private void encounterPirate(Pirate opp) {}
    
    private class Pirate {}
}
