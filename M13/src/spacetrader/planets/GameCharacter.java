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

    /**
     * Return the name of the player
     * 
     * @param name a String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the name of the player
     * 
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Return the money the player have
     * 
     * @return the money the player have
     */
    public int getMoney() {
        return money;
    }

    /**
     * Set the money of the player
     * 
     * @param money an int
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Return the x coordinate of player location
     * 
     * @return the x coordinate of player location
     */
    public int getXpos() {
        return xpos;
    }

    /**
     * Set the x coordinate of player location
     * 
     * @param xpos an int
     */
    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    /**
     * Return the y coordinate of the player
     * 
     * @return the y coordinate of the player
     */
    public int getYpos() {
        return ypos;
    }

    /**
     * Set the y coordinate of the player
     * 
     * @param ypos an int
     */
    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    /**
     * Return the trader skill of the player
     * 
     * @return the trader skill of the player
     */
    public int getTraderP() {
        return traderP;
    }

    /**
     * Set the trader skill of the player
     * 
     * @param traderP an int
     */
    public void setTraderP(int traderP) {
        this.traderP = traderP;
    }

    /**
     * Return the pilot skill of the player
     * 
     * @return the pilot skill of the player
     */
    public int getPilotP() {
        return pilotP;
    }

    /**
     * Set the pilot skill of the player
     * 
     * @param pilotP an int
     */
    public void setPilotP(int pilotP) {
        this.pilotP = pilotP;
    }

    /**
     * Return the engineer skill of player
     * 
     * @return the engineer skill of player
     */
    public int getEngineerP() {
        return engineerP;
    }

    /**
     * Set the engineer skill of player
     * 
     * @param engineerP the engineer skill of player
     */
    public void setEngineerP(int engineerP) {
        this.engineerP = engineerP;
    }

    /**
     * Return the figher skill of player
     * 
     * @return the figher skill of player
     */
    public int getFighterP() {
        return fighterP;
    }

    /**
     * Set the fighter skill of the player
     * 
     * @param fighterP an int
     */
    public void setFighterP(int fighterP) {
        this.fighterP = fighterP;
    }

}
