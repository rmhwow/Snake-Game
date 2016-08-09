
/**
 * The Apple class represents an apple in the game.
 * 
 * The Game class instantiates this class once for each apple present when a new
 * level is loaded.
 * 
 *<p>Bugs: (No known bugs)
 */
public class Apple{
	// Create private field to hold the GraphicObject associated with this apple
	private GraphicObject apple;
	
	/**
	 * Initializes a new Apple object.
	 * 
	 * @param x		the x position of the apple
	 * @param y		the y position of the apple
	 */
	public Apple(float x, float y){
		// Initialize this apple's associated GraphicObject with type "APPLE" at
		// this apple's x and y coordinates
		apple = new GraphicObject("APPLE",x,y);
	}
			
	/**
	 * Checks if this apple is colliding with the specified snake.
	 * 
	 * If the GraphicObject associated with this apple is colliding with the
	 * GraphicObject associated with the specified snake's head, grows the 
	 * snake, destroys the GraphicObject associated with this apple (causing it
	 * to disappear from the screen), and returns true. Otherwise, returns false
	 * 
	 * @param snake		snake to check for collisions with
	 * @return true after eating an apple, otherwise false
	 */
	public boolean getEatenBySnakeIfColliding(Snake snake){
		// If this apple is colliding with the snake's head's GraphicObject,
		// grow the snake once and destroy this apple's GraphicObject, then
		// return true
		if(snake.getGraphicObject().isCollidingWith(apple)){
			apple.destroy();
			snake.grow();
			return true;
		}
		// Otherwise, return false
		return false;
	}
//End of Apple class
}