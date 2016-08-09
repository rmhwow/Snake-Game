

/**
 * The PortalPair class represents a pair of portals.
 * 
 * The Game class instantiates this class once for each pair of portals present
 * when a new level is loaded.
 * 
 *<p>Bugs: (No known bugs)
 */
public class PortalPair{
	// Create private field to hold the GraphicObject associated with the blue portal
	private GraphicObject bluePortal;
	// Create private field to hold the GraphicObject associated with the orange portal
	private GraphicObject orangePortal;
	private String portalName;
	/**
	 * Initializes a new PortalPair object at the specified (x,y) positions.
	 * 
	 * @param name		name displayed on each end of the portal pair
	 * @param blueX		the x position of the blue portal
	 * @param blueY		the y position of the blue portal
	 * @param orangeX	the x position of the orange portal
	 * @param orangeY	the y position of the orange portal
	 */
	public PortalPair(String name, float blueX, float blueY, 
			                       float orangeX, float orangeY){
		// Initialize the GraphicObjects associated with the blue and orange
		// portals, setting the type to "BLUE_PORTAL_name" or
		// "ORANGE_PORTAL_name", respectively, and setting their x and y
		// coordinates appropriately
		portalName=name;
		bluePortal = new GraphicObject("BLUE_PORTAL_"+portalName,blueX, blueY);
		orangePortal = new GraphicObject("ORANGE_PORTAL_"+portalName,orangeX, orangeY);
	}
		
	/**
	 * Checks if either end of this portal pair is colliding with the specified
	 * snake.
	 * 
	 * If either end of this portal pair is colliding with the snake, moves the
	 * snake past the other end of the portal.
	 * 
	 * @param snake		the snake to check for collisions with
	 */
	public void teleportSnakeIfColliding(Snake snake){
		// If the blue portal is colliding with the snake's head's GraphicObject,
		// move the snake's head's GraphicObject past the orange portal
		if(bluePortal.isCollidingWith(snake.getGraphicObject())){
			snake.getGraphicObject().movePast(orangePortal);
		}
		// If the orange portal is colliding with the snake's head's 
		// GraphicObject, move the snake's head's GraphicObject past the 
		// blue portal
		if(orangePortal.isCollidingWith(snake.getGraphicObject())){
			snake.getGraphicObject().movePast(bluePortal);
		}
	}
//End of PortalPair class
}