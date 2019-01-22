import java.util.ArrayList;
public class Player implements Damageable{
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
    logo = (char)health;
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
  /**
   * Deducts health from the player
   * @param damage The amount of health to Deduct
   */
  public void takeDamage(int damage){
    health = health - damage;
    logo = (char) health;
  }
  /**
   * Checks if a wall is present one tile in the direction the player is attempting to move in
   * @param direction the direction in which Player is attempting to move in
   * @param floors All the floors currently in Field
   * @param currentFloor the current floor the Player is on
   * @return true if such wall exists false otherwise.
   */
  public boolean validMove(String direction, ArrayList<Floor> floors, Floor currentFloor){
    int index = floors.indexOf(currentFloor);
    Floor current = floors.get(index);
    if (direction.equals("up")){
      for (int wall = 0; wall < current.getBorder().size(); wall++){
        if ((this.xCoord == current.getBorder().get(wall).getX()) && (this.yCoord - 1 == current.getBorder().get(wall).getY())){
          return false;
        }
      }
    }
    if (direction.equals("down")){
      for (int wall = 0; wall < current.getBorder().size(); wall++){
        if ((this.xCoord == current.getBorder().get(wall).getX()) && (this.yCoord + 1 == current.getBorder().get(wall).getY())){
          return false;
        }
      }
    }
    if (direction.equals("left")){
      for (int wall = 0; wall < current.getBorder().size(); wall++){
        if ((this.xCoord - 1 == current.getBorder().get(wall).getX()) && (this.yCoord == current.getBorder().get(wall).getY())){
          return false;
        }
      }
    }
    if (direction.equals("right")){
      for (int wall = 0; wall < current.getBorder().size(); wall++){
        if ((this.xCoord + 1 == current.getBorder().get(wall).getX()) && (this.yCoord == current.getBorder().get(wall).getY())){
          return false;
        }
      }
    }
    return true;
  }
  /**
   * Getter method for damage
   * @return damage of player
   */
  public int getDamage(){
    return damage;
  }
  /**
   * sets xCoord to newX
   * @param newX new x cord
   */
  public void setX(int x){
    xCoord = x;
  }
  /**
   * sets yCoord to newY
   * @param newY new r cord
   */
  public void setY(int y){
    yCoord = y;
  }
}
