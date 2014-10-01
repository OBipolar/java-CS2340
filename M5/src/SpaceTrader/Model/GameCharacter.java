/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SpaceTrader.Model;

/**
 * Model for character
 * @author ZixiangZhu, Menghang Li
 */
public class GameCharacter {
    
    private String name = "";
    private int pilotP, fighterP, traderP, engineerP;
    private int money;
    
    private static final int START_MONEY = 1000;

    public GameCharacter(String name, int pilotP, int fighterP, int traderP,
            int engineerP) {
        setName(name);
        this.pilotP = pilotP;
        this.fighterP = fighterP;
        this.traderP = traderP;
        this.engineerP = engineerP;
        setMoney(START_MONEY);
    }

    public void setName(String name) {       
        this.name = name;       
    }
    
    public String getName() {
        return name;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
