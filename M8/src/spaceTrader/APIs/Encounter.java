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
    private String[] options;
    private String type;
    
    @SuppressWarnings("empty-statement")
    public Encounter() {
        load();
        
        // generate different encounter situation by probabilty
        int encounterRand = rand.nextInt(100);
        if (encounterRand < 34) {
            Trader opponent  = new Trader();
            options = new String[] {"Attack", "Ignore", "Trade"};
            type = "Trader";
        } else if (encounterRand < 67) {
            Police opponent = new Police();
            options = new String[] {"Attack", "Flee", "Submit", "Bribe"};
            type = "Police";
        } else {
            Pirate opponent = new Pirate();
            options = new String[] {"Attack", "Flee", "Surrender"};
            type = "Pirate";
        }        
    }
    
    public String[] getOptions() {
        return options;
    }
    
    public String getOpponentType() {
        return type;
    }
    
    // attack option, will print whether one's ship is destroyed
    public void Attack() {
        if (ship.pullStrength() == 0) {
            System.out.println("Your Ship is destroyed");
        }
        if (opponent.getStrength() == 0) {
            System.out.println("Your opponent's ship is destroyed");
        }
    }
    
    // return if successfully flee when choosing flee option
    public boolean Flee() {
        int fleeRand = rand.nextInt(100);
        return fleeRand > 50; 
    }
    
    public void Submit() {}
    
    public void Bribe() {}
    
    public void Ignore() {
        // end the encounter scenario
    }
    
    public void Trade() {
    
    }
    
    public void Surrender() {
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

    // inner class for opponent trader
    private static class Trader {
        private int hull;
        private int shield;
        private String type = "Trader";
        
        Trader() {
            hull = 100;
            shield = 100;
        }
        
        private int getHull() {return hull;}
        
        private int getShield() {return shield;}
    }
        
    private static class Police {
        private int hull;
        private int shield;
        private String type = "Police";
        
        Police() {
            hull = 100;
            shield = 100;
        }
    
        private int getHull() {return hull;}
        
        private int getShield() {return shield;}
    }
    
    private static class Pirate {
        private int hull;
        private int shield;
        private String type = "Pirate";
    
        Pirate() {
            hull = 100;
            shield = 100;
        }
  
        private int getHull() {return hull;}
        
        private int getShield() {return shield;}    
    }
}
