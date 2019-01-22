import java.util.ArrayList;
public class Projectile {
  /**
   * x coord of projectile
   */
  private int x;
  /**
   * y coord of projectile
   */
  private int y;
  /**
   * logo of projectile unicode escape character
   */
  private char logo;
  /**
   * damage of projectile
   */
  private int damage;
  /**
   * direction of projectile
   */
  private String direction;
  /**
   * count of projectile used for movement
   */
  private int count;
  /**
   * Makes a projectile at x,y with damage dam, and direction dir
   * @param xCoord x position of projectile
   * @param yCoord y position of projectile
   * @param dam damage of projectile
   * @param dir direction of projectile
   */
  public Projectile(int xCoord, int yCoord, int dam, String dir){
    x = xCoord;
    y = yCoord;
    damage = dam;
    logo = '\u25E6';
    direction = dir;
    count = 0;
  }
  /**
   * changes the projectile's x or y position depending on projectile's direction
   */
  public void move(){
    if (direction.equals("up")){
      y--;
    }
    if (direction.equals("down")){
      y++;
    }
    if (direction.equals("left")){
      x--;
    }
    if (direction.equals("right")){
      x++;
    }
  }
  /**
   * @return x position of projectile
   */
  public int getX(){
    return x;
  }
  /**
   * @return y position of projectile
   */
  public int getY(){
    return y;
  }
  /**
   * @return damage of projectile
   */
  public int getDamage(){
    return damage;
  }
  /**
   * @return logo of projectile
   */
  public char getLogo(){
    return logo;
  }
  /**
   * sets xCoord to newX
   * @param newX new x cord
   */
  public void setX(int newX){
    x = newX;
  }
  /**
   * sets yCoord to newY
   * @param newY new r cord
   */
  public void setY(int newY){
    y = newY;
  }
  /**
   * @return direction of projectile
   */
  public String getDirection(){
    return direction;
  }
  /**
   * @return count of projectile
   */
  public int getCount(){
    return count;
  }
  /**
   * adds one to count
   */
  public void addToCount(){
    count++;
  }
  /**
   * resets count to zero
   */
  public void resetCount(){
    count = 0;
  }
  /**
   * Checks if a wall is present one tile in the direction the projectile is attempting to move in
   * @param direction the direction in which projectile is attempting to move in
   * @param floors All the floors currently in Field
   * @param currentFloor the current floor the projectile is on
   * @return true if such wall exists false otherwise.
   */
  public boolean validMove(String direction, ArrayList<Floor> floors, Floor currentFloor){
    int index = floors.indexOf(currentFloor);
    Floor current = floors.get(index);
    if (direction.equals("up")){
      for (int wall = 0; wall < current.getBorder().size(); wall++){
        if ((this.x == current.getBorder().get(wall).getX()) && (this.y - 1 == current.getBorder().get(wall).getY())){
          return false;
        }
      }
    }
    if (direction.equals("down")){
      for (int wall = 0; wall < current.getBorder().size(); wall++){
        if ((this.x == current.getBorder().get(wall).getX()) && (this.y + 1 == current.getBorder().get(wall).getY())){
          return false;
        }
      }
    }
    if (direction.equals("left")){
      for (int wall = 0; wall < current.getBorder().size(); wall++){
        if ((this.x - 1 == current.getBorder().get(wall).getX()) && (this.y == current.getBorder().get(wall).getY())){
          return false;
        }
      }
    }
    if (direction.equals("right")){
      for (int wall = 0; wall < current.getBorder().size(); wall++){
        if ((this.x + 1 == current.getBorder().get(wall).getX()) && (this.y == current.getBorder().get(wall).getY())){
          return false;
        }
      }
    }
    return true;
  }
}
