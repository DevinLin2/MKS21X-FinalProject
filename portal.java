public class portal{
  /**
   * X coordinate of portal
   */
  private int xCoord;
  /**
   * Y coordinate of portal
   */
  private int yCoord;
  /**
   * Unicode of portal which will appear on terminal as a square.
   */
  private char logo;
  /**
   * Makes a portal given x and y coords.
   * @param x X coordinate of portal
   * @param y Y coordinate of portal
   */
  public portal(int x, int y){
    xCoord = x;
    yCoord = y;
    logo = '\u06DE';
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
  /**
   * Getter method for logo
   */
  public char getLogo(){
    return logo;
  }
}
