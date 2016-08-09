

/**
 * The Rock class represents a rock in the game.
 * 
 * The Game class instantiates this class once for each rock present when a new
 * level is loaded.
 * 
 *<p>Bugs: (No known bugs)
 
 */
public class Rock {
	// Create private field to hold the GraphicObject associated with this rock
	private GraphicObject rock;
	/**
	 * Constructor initializes a new Rock object.
	 * 
	 * @param x		the x position of the rock
	 * @param y		the y position of the rock
	 */
	
	public Rock(float x, float y){
		// Initialize this rock's associated GraphicObject with type "ROCK" at 
		rock = new GraphicObject("ROCK",x,y);
	}
	
	/**
	 * Checks if this rock is colliding with the specified snake.
	 * 
	 * If the GraphicObject associated with this rock is colliding with the head 
	 * of the GraphicObject associated with the head of the snake, kills the 
	 * snake.
	 * 
	 * @param snake		snake to check for collisions with
	 * 
	 */
	public void killSnakeIfColliding(Snake snake){
		// If this rock is colliding with the snake's head's GraphicObject, kill
		// the snake
		if(snake.getGraphicObject().isCollidingWith(rock)){
			snake.die();
		}
	}
//End of Rock class
}