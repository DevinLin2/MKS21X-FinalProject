public class Wall{
  private int xCoord;
  private int yCoord;
  private char logo;
  public Wall(int x, int y){
    xCoord = x;
    yCoord = y;
    logo = "\u25fb";
  }
  public void setX(int x){
    xCoord = x;
  }
  public void setY(int y){
    yCoord = y;
  }
  public int getX(){
    return xCoord;
  }
  public int getY(){
    return yCoord;
  }
}
