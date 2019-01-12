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
   * Constructs and empty floor.
   * @param Level The level of the floor.
   */
  public Floor(int Level){
    border = new ArrayList<Wall>();
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
   * Getter method for the ArrayList of walls
   */
  public ArrayList<Wall> getBorder(){
    return border;
  }
  /**
   * Getter method for level
   */
  public int getLevel(){
    return level;
  }
}
