public class Wall{
  /**
   * X coordinate of wall
   */
  private int xCoord;
  /**
   * Y coordinate of wall
   */
  private int yCoord;
  /**
   * Unicode of wall which will appear on terminal as a square.
   */
  private char logo;
  /**
   * Makes a wall given x and y coords.
   * @param x X coordinate of wall
   * @param y Y coordinate of wall
   */
  public Wall(int x, int y){
    xCoord = x;
    yCoord = y;
    logo = '\u25fb';
  }
  /**
   * Setter method to change xCoord
   * @param x new x value to replace xCoord
   */
  public void setX(int x){
    xCoord = x;
  }
  /**
   * Setter method to change yCoord
   * @param y new y value to replace yCoord
   */
  public void setY(int y){
    yCoord = y;
  }
  /**
   * Getter method to get xCoord
   */
  public int getX(){
    return xCoord;
  }
  /**
   * Getter method to get yCoord
   */
  public int getY(){
    return yCoord;
  }
}
