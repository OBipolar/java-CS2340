package spacetrader.planets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The universe
 * 
 * @author Menghang Li
 *
 */
public class Universe {

    /**
     * All the system names in the game
     */
    public static final String[] SYSTEMSNAMES = {
            "Acamar",
            "Adahn", // The alternate personality for The Nameless One in
                     // "Planescape: Torment"
            "Aldea", "Andevian", "Antedi", "Balosnee", "Baratas",
            "Brax", // One of the heroes in Master of Magic
            "Bretel", // This is a Dutch device for keeping your pants up.
            "Calondia", "Campor",
            "Capelle", // The city I lived in while programming this game
            "Carzon",
            "Castor", // A Greek demi-god
            "Cestus", "Cheron",
            "Courteney", // After Courteney Cox…
            "Daled", "Damast", "Davlos", "Deneb", "Deneva", "Devidia",
            "Draylon", "Drema", "Endor", "Esmee", // One of the witches in
                                                  // Pratchett's Discworld
            "Exo", "Ferris", // Iron
            "Festen", // A great Scandinavian movie
            "Fourmi", // An ant, in French
            "Frolix", // A solar system in one of Philip K. Dick's novels
            "Gemulon", "Guinifer", // One way of writing the name of king
                                   // Arthur's wife
            "Hades", // The underworld
            "Hamlet", // From Shakespeare
            "Helena", // Of Troy
            "Hulst", // A Dutch plant
            "Iodine", // An element
            "Iralius", "Janus", // A seldom encountered Dutch boy's name
            "Japori", "Jarada", "Jason", // A Greek hero
            "Kaylon", "Khefka", "Kira", // My dog's name
            "Klaatu", // From a classic SF movie
            "Klaestron", "Korma", // An Indian sauce
            "Kravat", // Interesting spelling of the French word for "tie"
            "Krios", "Laertes", // A king in a Greek tragedy
            "Largo", "Lave", // The starting system in Elite
            "Ligon", "Lowry", // The name of the "hero" in Terry Gilliam's
                              // "Brazil"
            "Magrat", // The second of the witches in Pratchett's Discworld
            "Malcoria", "Melina", "Mentar", // The Psilon home system in Master
                                            // of Orion
            "Merik", "Mintaka", "Montor", // A city in Ultima III and Ultima VII
                                          // part 2
            "Mordan", "Myrthe", // The name of my daughter
            "Nelvana", "Nix", // An interesting spelling of a word meaning
                              // "nothing" in Dutch
            "Nyle", // An interesting spelling of the great river
            "Odet", "Og", // The last of the witches in Pratchett's Discworld
            "Omega", // The end of it all
            "Omphalos", // Greek for navel
            "Orias", "Othello", // From Shakespeare
            "Parade", // This word means the same in Dutch and in English
            "Penthara", "Picard", // The enigmatic captain from ST:TNG
            "Pollux", // Brother of Castor
            "Quator", "Rakhar", "Ran", // A film by Akira Kurosawa
            "Regulas", "Relva", "Rhymus", "Rochani", "Rubicum", // The river
                                                                // Ceasar
                                                                // crossed to
                                                                // get into Rome
            "Rutia", "Sarpeidon", "Sefalla", "Seltrice", "Sigma", "Sol", // That's
                                                                         // our
                                                                         // own
                                                                         // solar
                                                                         // system
            "Somari", "Stakoron", "Styris", "Talani", "Tamus", "Tantalos", // A
                                                                           // king
                                                                           // from
                                                                           // a
                                                                           // Greek
                                                                           // tragedy
            "Tanuga", "Tarchannen", "Terosa", "Thera", // A seldom encountered
                                                       // Dutch girl's name
            "Titan", // The largest moon of Jupiter
            "Torin", // A hero from Master of Magic
            "Triacus", "Turkana", "Tyrus", "Umberlee", // A god from AD&D, which
                                                       // has a prominent role
                                                       // in Baldur's Gate
            "Utopia", // The ultimate goal
            "Vadera", "Vagra", "Vandor", "Ventax", "Xenon", "Xerxes", // A Greek
                                                                      // hero
            "Yew", // A city which is in almost all of the Ultima games
            "Yojimbo", // A film by Akira Kurosawa
            "Zalkon", "Zuul" // From the first Ghostbusters movie
    };

    /**
     * the max x coordinate a solar system can have
     */
    public static final int XPOSMAX = 200;
    /**
     * the max y coordinate a solar system can have
     */
    public static final int YPOSMAX = 200;

    /**
     * number of solar systems in the game
     */
    private int numOfSystems;
    /**
     * A list of all solar systems in the game
     */
    private List<SolarSystem> universe;

    /**
     * Constructor that fills the universe
     */
    public Universe() {
        numOfSystems = SYSTEMSNAMES.length;
        universe = new ArrayList<>();
        initialize();
    }

    /**
     * Constuctor that takes in systems
     * 
     * @param systems
     */
    public Universe(List<SolarSystem> systems) {
        universe = systems;
        numOfSystems = SYSTEMSNAMES.length;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (SolarSystem s : universe) {
            buffer.append(" ");
            buffer.append(s.toString());
        }
        return buffer.toString();
    }

    /**
     * initializes the universe by filling in SolarSystems
     * 
     */
    private void initialize() {

        int[] xpos = getRandomNumArr(XPOSMAX, numOfSystems);
        int[] ypos = getRandomNumArr(YPOSMAX, numOfSystems);
        int pirate;
        int politicalSystem;
        int resourcesLevel;
        int techLevel; 
        int police;

        Capital cap;
        String systemName;
        SolarSystem system;

        for (int i = 0; i < numOfSystems; i++) {
            systemName = SYSTEMSNAMES[i];
            politicalSystem = getRandomNum(PoliticalSystem.NUM_OF_POLITICAL_SYSTEM);
            resourcesLevel = getRandomNum(ResourcesLevel.NUM_OF_RESOURCES_LEVEL);
            if (i == 0) {
            	techLevel = 6;
            } else {
            	techLevel = getRandomNum(TechLevels.NUM_OF_TECHLEVELS);
            }
            
            pirate = getRandomNum(Pirates.KINDS_OF_PIRATES);
            police = getRandomNum(Polices.TYPES_OF_POLICE);
            cap = new Capital(politicalSystem, resourcesLevel, techLevel,
                    pirate, police, systemName);
            system = new SolarSystem(xpos[i], ypos[i], systemName, cap);
            universe.add(system);
        }
//        systemName = SYSTEMSNAMES[0];
//        politicalSystem = getRandomNum(PoliticalSystem.NUM_OF_POLITICAL_SYSTEM);
//        resourcesLevel = getRandomNum(ResourcesLevel.NUM_OF_RESOURCES_LEVEL);
//        techLevel = 6;
//        pirate = getRandomNum(Pirates.KINDS_OF_PIRATES);
//        police = getRandomNum(Polices.TYPES_OF_POLICE);
//        cap = new Capital(politicalSystem, resourcesLevel, techLevel,
//                pirate, police, systemName);
//        system = new SolarSystem(xpos[0], ypos[0], systemName, cap);
//        
//        universe.set(0, system);
        

    }

    /**
     * Returns an array of integers generated randomly thats ranges from 0 to
     * limit(inclusive)
     * 
     * @param limit
     *            an integer
     * @param size
     *            the size of the return array
     * @return an array of integers generated randomly
     */
    private int[] getRandomNumArr(int limit, int size) {
        Random random = new Random();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(limit + 1);
        }

        return arr;

    }

    /**
     * Returns an random integer between 0 and limit (exclusive)
     * 
     * @param limit
     *            an integer
     * @return Returns an random integer between 0 and limit (exclusive)
     */
    private int getRandomNum(int limit) {
        return new Random().nextInt(limit);
    }

    /**
     * Return all solar systems in a list
     * 
     * @return all solar systems in a list
     */
    public List<SolarSystem> getUniverse() {
        return universe;
    }

}
