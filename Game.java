import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Game class represents a running instance of the PortalSnake game. It
 * keeps track of the Snake object, lists of Apple, Rock, and PortalPair
 * objects, the current score, and whether the player has won.
 * 
 * The game engine (which we've written for you) will create a new instance of
 * this class when the player chooses a level to play. 
 * 
 * At each iteration of the game loop, the game engine calls the update() method
 * in the Game class. The update() method tells each of the objects in the game
 * to update itself based on the rules of the game. It then checks if the game
 * is over or not.
 * 
 * <p>Bugs: (No known bugs)
 */
public class Game 
{	
	// Store the instances of the game objects that you create for your game
	// in these member variables: 
	private Snake snake;
	private ArrayList<Apple> appleList;		
	private ArrayList<Rock> rockList;
	private ArrayList<PortalPair> portals;	
	private int control;
	private int score=0;
	private int a = 0;
		
	// Create member variables to track the controlType, score (ie number
	// of apples eaten by the snake), and whether the game has been won
	// or lost here.
	
	/**
	 * 
	 * When this constructor is called it creates an instance of Game.
	 * It initializes the ArrayLists that hold Apple, Rock, and PortalPair
	 * objects. Depending on the passed in parameter level, it also calls 
	 * createRandomLevel or loadLevel methods. Sets the control type to 
	 * what has been passed in.
	 *   
	 * 
	 * @param level - "RANDOM" or descriptions of the object to load
	 * @param controlType - 1: classic, 2: analog, 3: slither
	 */
	public Game(String level, int controlType) {
		// Initialize all member variables
		appleList = new ArrayList<Apple>();
		 rockList = new ArrayList<Rock>();
		 portals = new ArrayList<PortalPair>();
		
		 // Create a random level when level contains: RANDOM
		
		if (level == "RANDOM"){
			createRandomLevel();
		} else {
			loadLevel(level);
		}
		control = controlType;
		// Otherwise load the objects described in the string level
		
	}

	/**
	 * 
	 * creates a new level with randomly positioned:
	 * Snake(1), Rocks(20), Apples(8), and PortalPairs(3)
	 */
	public void createRandomLevel()
	{	 
		// create a snake: positioned as specified in the write-up
		snake = new Snake(Engine.getWidth()/2,Engine.getHeight()/2);
		
		//Random number generator to randomly position objects on screen
		Random rn = new Random();
		
		// create 20 randomly positioned rocks
		for( int i= 1; i<= 20; i++){
			float x = rn.nextInt(Engine.getWidth());
			float y = rn.nextInt(Engine.getHeight());
			Rock rockObject = new Rock(x,y);	
			//Adds the newly created object to the ArrayList of Rock objects
			rockList.add(rockObject);
		}
		// create 8 randomly positioned apples
		for( int i= 1; i<= 8; i++){
			float x = rn.nextInt(Engine.getWidth());
			float y = rn.nextInt(Engine.getHeight());
			Apple appleObject = new Apple(x,y);	
			//Adds the newly created object to the ArrayList of Apple objects
			appleList.add(appleObject);
		}
		// create 3 randomly positioned portal pairs with individual names and
		// X,Y coordinates
		for( int i= 1; i<= 3; i++){
			float x1 = rn.nextInt(Engine.getWidth());
			float y1 = rn.nextInt(Engine.getHeight());
			float x2 = rn.nextInt(Engine.getHeight());
			float y2 = rn.nextInt(Engine.getHeight());
			String nameOfPortalPair = "PortalPair"+i;
			PortalPair PP1 = new PortalPair(nameOfPortalPair,x1,y1,x2,y2);
			//Adds the newly created object to the ArrayList of PortalPair 
			//objects
			portals.add(PP1);
		}
		
	}

	/**
	 * Loads a level from a String description.
	 * 
	 * Initializes all of the class private fields which hold the Snake object
	 * and the lists of Apple, Rock, and PortalPair objects from the provided
	 * String which contains  
	 * 
	 * @param level - a string containing the names and locations of objects
	 */
	public void loadLevel(String level)
	{
		 // Creates a File object
		 File file = new File(level);
		 // Creates a new scanner to read the level description	
		 Scanner scnr = new Scanner(file +".pslevel");	 
		// Loop through lines in the level description
		while(scnr.hasNextLine()){
			// Get the next line
			String objectInfo = scnr.nextLine();
			// If it's a portal pair, create a new PortalPair with the
			// name equal to the second token, with the first portal at the
			// x and y coordinates specified by the third and fourth
			// tokens, and the second portal at the x and y coordinates
			// specified by the fifth and sixth tokens
			if(objectInfo.contains("PortalPair")){
				//Splits the read in line into an array of the individual pieces
				//of information
				String [] portalArray = objectInfo.split(",", 7);
				//Loop to trim any unneeded whitespace 
				for(int i = 0; i < portalArray.length; i++){
				portalArray[i] = portalArray[i].trim();
				}
				//Create a portalPair object with the given information
				PortalPair portalPair = new PortalPair(portalArray[1], 
						Integer.parseInt(portalArray[2]), 
						Integer.parseInt(portalArray[3]), 
						Integer.parseInt(portalArray[4]), 
						Integer.parseInt(portalArray[5]));
				//Add the newly created portalPair object to the portalPair 
				//arrayList
				portals.add(portalPair);
				//If the line read in is not for a portalPair object
			}else{
				//Splits the read in line into an array of the individual pieces
				//of information
				String [] tokens = objectInfo.split(",",4);
				//Loop to trim any unneeded whitespace and to remove commas 
				for(int i = 0; i < tokens.length; i++){
					if(tokens[i].contains(",")){
						tokens[i]=tokens[i].replaceAll(",", "");
					} 
					tokens[i] = tokens[i].trim();
				}
				// Determine the type of object to add to the level	
				// If it's a rock, create a new rock at the x and y coordinates
				// specified by the second and third tokens and add it to the
				// list of rocks
				if (tokens[0].equals("Rock")){
					Rock rock = new Rock(Integer.parseInt(tokens[1]), 
							Integer.parseInt(tokens[2]));
					rockList.add(rock);
				}
				// If it's an apple, create a new apple at the x and y
				// coordinates specified by the second and third tokens, and add
				// it to the list of apples
				else if (tokens[0].equals("Apple")){
					Apple apple = new Apple(Integer.parseInt(tokens[1]), 
							Integer.parseInt(tokens[2]));
					appleList.add(apple);
				}
				// If it's a snake, create a new snake at the x and y
				// coordinates specified by the second and third tokens
				else if (tokens[0].equals("Snake")){
					 this.snake = new Snake(Integer.parseInt(tokens[1]), 
							 Integer.parseInt(tokens[2]));
				}
			}
		}
		// Close the scanner	
		scnr.close();
	}

	/**
	 * Updates the game objects.
	 * 
	 * Goes through each of the objects--the snake, rocks, apples, and portals--
	 * and tells them to behave according to the rules of the game. This method
	 * returns true if the game should continue, or false if the game is over.
	 * 
	 * @return - false if the game is over, otherwise true
	 */
	
	public boolean update()
	{
		
		// Tell the snake to update itself
		snake.updateMoveDirection(control);
		// Tell the snake to die if it's colliding with itself
		snake.dieIfCollidingWithOwnBody();
		// For each rock, tell the rock to kill the snake if the two are
		// colliding. If the snake is dead, call playerHasWon 
		// so the game is lost and ends. 
		for(int i=0; i< rockList.size();i++){
			rockList.get(i).killSnakeIfColliding(snake);
			if (snake.isDead()) {
				playerHasWon();
				return false;
			}
			}
		// For each apple, tell the apple to be eaten by the snake if the two
		// are colliding, and if so update the score
		for(int i=0; i< appleList.size();i++){
			if(appleList.get(i).getEatenBySnakeIfColliding(snake)){
				score++;	
			}
		}
		// For each portal pair, tell the pair to teleport the snake if the two
		// are colliding
		for(int i=0; i< portals.size();i++){
			portals.get(i).teleportSnakeIfColliding(snake);
			}
		// Check for win/loss
			// If the score is equal to the number of apples, make sure 
			// playerHasWon() will return true and then return false 
		if(playerHasWon()){
			return false;
		}
			// Else, if the snake is dead, make sure playerHasWon() will 
			//return false and then return false
		else if(snake.isDead()){
			playerHasWon();
		}
			// If the game isn't over, return true
		return true;
	}

	/**
	 *  Returns true if the player has won either wise return false
	 * 
	 * @return true when the player has won, and false when they have lost or
	 * the game is not over
	 */
	public boolean playerHasWon()
	{
		//If all the apples are eaten, player has won
		if (score == appleList.size()){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the player's score.
	 * 
	 * @return the current score (number of apples eaten)
	 */
	public int getScore() {
		return score;
	}	

	/**
	 * There is nothing left to implement in this method, it simply calls
	 * Engine.startEngineAndGame(), which in turn starts the Engine and creates
	 * an instance of this Game class.  The engine will then repeatedly call
	 * the update() method on this Game until it returns false.
	 * 
	 * If you want to turn off the logging you can change the parameter being
	 * passed to startEngineAndGame to false.  
	 *  
	 * @param args - command line arguments
	 */
	public static void main(String[] args)
	{
		Application.startEngineAndGame(false);
	}
//End of Game class
}