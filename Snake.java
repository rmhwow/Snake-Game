///////////////////////////////////////////////////////////////////////////////
//ALL STUDENTS COMPLETE THESE SECTIONS
//Main Class File: Game.java 

import java.util.ArrayList;
/**
 * The Snake class represents the player-controlled snake. 
 *
 * The Game class instantiates this class exactly once, when a new level is
 * loaded.
 * 
 *<p>Bugs: (No known bugs)
 */
public class Snake 
{
	// Private variables to hold the GraphicObject associated with the snake's head
	// and an ArrayList of GraphicObject associated with the snake's body
	private ArrayList<GraphicObject> wholeSnake;
	private GraphicObject head;
	private float snakeDirection;
	/**
	 * Initializes a new Snake object at the specified (x,y) position.
	 * 
	 * @param x		the initial x position of the snake
	 * @param y		the initial y position of the snake
	 */
	
	public Snake(float x, float y)
	{
		// Initialize new ArrayList to hold body segments
		wholeSnake = new ArrayList<GraphicObject>();
		// Initialize the head of snake
		head = new GraphicObject("HEAD",x,y);
		// Set the speed of the head
		head.setSpeed(2);
		// Set the direction of the head
		snakeDirection = 90;
		head.setDirection(snakeDirection);
		// Add the head to the list of body segments
		wholeSnake.add(head);
		// Add four body segments (grow the snake four times)
		for( int i = 1; i <= 4; i++){
			grow();
		}
	}
	
	/**
	 * Returns the GraphicObject associated with the head of this snake.
	 * 
	 * @return the GraphicObject associated with the head of this snake
	 */
	public GraphicObject getGraphicObject() {
		return head;
	}

	/**
	 * Grows the snake by one body segment.
	 * 
	 */
	public void grow()
	{
		// Create a new GraphicObject with type "BODY"		
		GraphicObject bodyPiece = new GraphicObject("BODY",Engine.getWidth()/2,
				Engine.getHeight()/2);
		// Find the last body segment in the list of body segments
		GraphicObject lastBodyPart = wholeSnake.get(wholeSnake.size()-1);
		// Set the leader of the new body segment to be the last body segment
		bodyPiece.setLeader(lastBodyPart);
		// Add the new body segment to the end of the list of body segments
		wholeSnake.add(bodyPiece);
	}

	/**
	 * Reads keyboard input and changes the snake's direction as necessary.
	 * 
	 * @param controlType - 1: classic, 2: analog, 3: slither
	 */
	public void updateMoveDirection(int controlType)
	{
		switch(controlType){
		// if controlType is one
		case 1: 	
				//Check if left is pressed and change the direction accordingly 
				if (Engine.isKeyPressed("LEFT")){
					if(snakeDirection == 270) {
						snakeDirection = 0;
					}else {
						snakeDirection += 90;
					}
				head.setDirection(snakeDirection);
				}
				//Check if right is pressed and change the direction accordingly 
				if (Engine.isKeyPressed("RIGHT")){
					if(snakeDirection ==0) {
						snakeDirection = 270;
					}else {
						snakeDirection -= 90;
					}
					head.setDirection(snakeDirection);
				}
			break;
		// if controlType is two
		case 2: 	
				//Check if left is being held and change the direction 
				//accordingly 
				if(Engine.isKeyHeld("LEFT")){
					if(snakeDirection >=354) {
						snakeDirection = 0;
					}else {
					snakeDirection += 6;
					}
				}
				if (Engine.isKeyHeld("RIGHT")) {
					if(snakeDirection <= 0){
						snakeDirection = 354;
					} else {
						snakeDirection -= 6;
					}
				}
				head.setDirection(snakeDirection);
			break;
		//If control type is 3
		case 3: 	
				//If space bar is being held turn left
				if(Engine.isKeyHeld("SPACE")){
					if(snakeDirection >= 354){
					snakeDirection = 0;
					}else{
						snakeDirection += 6;
					}
				}
			//Turn right if space bar isn't being held
				else{
					if(snakeDirection <= 0) {
						snakeDirection = 354;
					} else {
						snakeDirection -=6;
					}
				}
			head.setDirection(snakeDirection);
			break;
		}
	}
	/**
	 * Kills the snake if the head is colliding with any of the body segments.
	 * 
	 */
	public void dieIfCollidingWithOwnBody(){
		// For each game object in the body...
		for (int i = 0; i < wholeSnake.size(); i++) {
			// if the head is colliding with this segment...
			if(getGraphicObject().isCollidingWith(wholeSnake.get(i))){
				// tell the snake to die.
				die();
			}	
		}	
	}

	/**
	 * Kills the snake.
	 */
	public void die(){
		// Set the head's type to "DEAD"
		wholeSnake.get(0).setType("DEAD");
		// For each GraphicObject in the snake's body, set its type to "DEAD"
		for(int i = 1; i< wholeSnake.size();i++){
			wholeSnake.get(i).setType("DEAD");
		}
	}

	/**
	 * Returns true if the snake is dead.
	 * 
	 * @return true if the snake is dead, false otherwise
	 */
	public boolean isDead(){
		if(getGraphicObject().getType()=="DEAD"){
			return true;
		}
			return false;
	}
//End of Snake class
}
