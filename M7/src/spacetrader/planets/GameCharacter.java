/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader.planets;

/**
 * Model for character
 * 
 * @author ZixiangZhu, Menghang Li
 */
public class GameCharacter {

    /**
     * Player name
     */
    private String name = "";
    /**
     * pilot skill of player 
     */
    private int pilotP; 
    /**
     * fighter skill of player 
     */
    private int fighterP; 
    /**
     * trader skill of player 
     */
    private int traderP; 
    /**
     * engineer skill of player 
     */
    private int engineerP;   
    /**
     * Money player has
     */
    private int money;
    /**
     * x coordinate of player position
     */
    private int xpos;
    
    /**
     * y coordinate of player position
     */
    private int ypos;

    private static final int START_MONEY = 100000;

    /**
     * Constructor that sets all properties for a character
     * 
     * @param name
     *            player name
     * @param pilotP
     *            piloting skills
     * @param fighterP
     *            fighting skills
     * @param traderP
     *            trading skills
     * @param engineerP
     *            engineering skills
     * 
     */

    public GameCharacter(String name, int pilotP, int fighterP, int traderP,
            int engineerP) {
        setName(name);
        this.pilotP = pilotP;
        this.fighterP = fighterP;
        setTraderP(traderP);
        this.engineerP = engineerP;
        setMoney(START_MONEY);

    }


    public GameCharacter() {
        
    }


    @Override
    public String toString() {
        return String.format("Player name: %s, money: %d, pilotP: %d, f"
                + "fighterP: %d, engineerP: %d, position(%d, %d)/n", name,
                money, pilotP, fighterP, engineerP, xpos, ypos);
    }

    /**
     * Makes the player travel to location with x, y as coordinates
     * 
     * @param x
     * @param y
     */
    public void travel(int xpos, int ypos) {
        setXpos(xpos);
        setYpos(ypos);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public int getTraderP() {
        return traderP;
    }

    public void setTraderP(int traderP) {
        this.traderP = traderP;
    }

    public int getPilotP() {
        return pilotP;
    }

    public void setPilotP(int pilotP) {
        this.pilotP = pilotP;
    }

    public int getEngineerP() {
        return engineerP;
    }

    public void setEngineerP(int engineerP) {
        this.engineerP = engineerP;
    }

    public int getFighterP() {
        return fighterP;
    }

    public void setFighterP(int fighterP) {
        this.fighterP = fighterP;
    }

}
