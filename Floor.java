import java.util.ArrayList;
public class Floor{
  /**
   * ArrayList of Walls which maps the playing field.
   */
  private ArrayList<Wall> border;
  /**
   * The level the player is currently on. This is the identifier for the floor.
   */
  private int level;
  /**
   * ArrayList of all the monsters on the floor
   */
  private ArrayList<Monster> enemies;
  /**
   * Constructs and empty floor.
   * @param Level The level of the floor.
   */
  public Floor(int Level){
    border = new ArrayList<Wall>();
    enemies = new ArrayList<Monster>();
    level = Level;
  }
  /**
   * Constructs a new wall and adds it to the ArrayList of walls.
   * @param x X coordinate for wall.
   * @param y Y coordinate for wall.
   */
  public void addWall(int x, int y){
    Wall toAdd = new Wall(x,y);
    border.add(toAdd);
  }
  /**
   * Constructs a new Monster and adds it to the ArrayList of monsters.
   */
  public void addMonster(int health, int x, int y, int damage, int range){
    Monster toAdd = new Monster(health, x, y, damage, range);
    enemies.add(toAdd);
  }
  /**
   * Getter method for the ArrayList of walls
   */
  public ArrayList<Wall> getBorder(){
    return border;
  }
  /**
   * Getter method for ArrayList of monsters
   */
  public ArrayList<Monster> getEnemies(){
    return enemies;
  }
  /**
   * Getter method for level
   */
  public int getLevel(){
    return level;
  }
  /**
   * removes a Monster from ArrayList of enemies
   * @param toRemove the monster to remove from enemies
   */
  public void removeMonster(Monster toRemove){
    enemies.remove(toRemove);
  }
}
