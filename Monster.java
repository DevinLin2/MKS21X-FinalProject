import java.util.ArrayList;
public class Monster implements Damageable{
  /**
   * Health of monster
   */
  private int health;
  /**
   * damage of monster
   */
  private int damage;
  /**
   * x coord of monster
   */
  private int x;
  /**
   * y coord of monster
   */
  private int y;
  /**
   * logo of monster unicode escape character
   */
  private char logo;
  /**
   * count of monster used for movement
   */
  private int count;
  /**
   * range of monster
   */
  private int range;
  /**
   * Makes a monster at x,y with health h, damage d, and range r
   * @param h health of monster
   * @param xcord x position of monster
   * @param ycord y position of monster
   * @param d damage of monster
   * @param r range of monster
   */
  public Monster(int h, int xcord, int ycord, int d, int r){
    health = h;
    damage = d;
    x = xcord;
    y = ycord;
    logo = '\u2639';
    count = 0;
    range = r;
  }
  /**
   * @return logo of monster
   */
  public char getCharacter(){
    return logo;
  }
  /**
   * Deducts health from the Monster
   * @param damage The amount of health to Deduct
   */
  public void takeDamage(int damage){
    health = health - damage;
  }
  /**
   * @return damage of monster
   */
  public int getDamage(){
    return damage;
  }
  /**
   * @return health of monster
   */
  public int getHealth(){
    return health;
  }
  /**
   * @return x position of monster
   */
  public int getX(){
    return x;
  }
  /**
   * @return y position of monster
   */
  public int getY(){
    return y;
  }
  /**
   * @return count of monster
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
   * @return range of monster
   */
  public int getRange(){
    return range;
  }
  /**
   * changes the monster's x or y position depending on the argument
   * @param direction the direction the monster is moving to
   */
  public void move(String direction){
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
   * Checks if a wall is present one tile in the direction the monster is attempting to move in
   * @param direction the direction in which monster is attempting to move in
   * @param floors All the floors currently in Field
   * @param currentFloor the current floor the monster is on
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
