/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication6;

/**
 *
 * @author zixiangzhu
 */
public class CharacterShip {
    private String userName = "";
    private int pilotP, fighterP, traderP, engineerP;
    
    public CharacterShip() {
        pilotP = 0;
        fighterP = 0;
        traderP = 0;
        engineerP = 0;
    }
    
    public void record(String name, int pilotP, int fighterP, int traderP, int engineerP) {
        userName = name;
        this.fighterP = fighterP;
        this.traderP = traderP;
        this.engineerP = engineerP;
    }
    
    public String getName() {
        return userName;
    }
    
    public int getPilotP() {
        return pilotP;
    }
    
    public int getFighterP() {
        return fighterP;
    }
    
    public int getTraderP() {
        return traderP;
    }
    
    public int getEngineerP() {
        return engineerP;
    }
    
}
