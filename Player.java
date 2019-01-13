import java.util.ArrayList;
public class Player{
  /**
   * Health of Player
   */
  private int health;
  /**
   * X position of Player
   */
  private int xCoord;
  /**
   * Y position of Player
   */
  private int yCoord;
  /**
   * Appearance of Player
   */
  private char logo;
  /**
   * Base damage of Player
   */
  private int damage;
  /**
   * Constructor for Player
   * @param h Health for Player
   * @param x Initial X position of Player
   * @param y Initial Y position of Player
   * @param d damage of Player
   */
  public Player(int h, int x, int y, int d){
    health = h;
    xCoord = x;
    yCoord = y;
    logo = '\u0040';
    damage = d;
  }
  /**
   * Changes player's x or y positions given a direction
   * @param direction the direction in which the player is moving
   */
  public void move(String direction){
    if (direction.equals("up")){
      yCoord--;
    }
    if (direction.equals("down")){
      yCoord++;
    }
    if (direction.equals("left")){
      xCoord--;
    }
    if (direction.equals("right")){
      xCoord++;
    }
  }
  /**
   * Getter for logo of Player
   */
  public char getCharacter(){
    return logo;
  }
  /**
   * Getter for X coordinate of Player
   */
  public int getX(){
    return xCoord;
  }
  /**
   * Getter for Y coordinate of Player
   */
  public int getY(){
    return yCoord;
  }
  /**
   * Getter for health of Player
   */
  public int getHealth(){
    return health;
  }
  // /**
  //  * Checks if a wall is present one tile in the direction the player is attempting to move in
  //  * @param direction the direction in which Player is attempting to move in
  //  * @param floors All the floors currently in Field
  //  * @param currentFloor the current floor the Player is on
  //  * @return true if such wall exists false otherwise.
  //  */
  // public boolean validMove(String direction, ArrayList<Floor> floors, Floor currentFloor){
  //   Floor
  //   if (direction.equals("up")){
  //
  //   }
  // }
}
