public class Player{
  private int health;
  private int xCoord;
  private int yCoord;
  private char logo;
  private int damage;
  public Player(int h, int x, int y, int d){
    health = h;
    xCoord = x;
    yCoord = y;
    logo = '\u0b87';
    damage = d;
  }
  public void move(String direction){
    if (direction.equals("up")){
      yCoord--;
    }
    if (direction.equals("down")){
      yCoord++;
    }
    if (direction.equals("left")){
      xCoord++;
    }
    if (direction.equals("right")){
      xCoord--;
    }
  }
  public char getCharacter(){
    return logo;
  }
  public int getX(){
    return xCoord;
  }
  public int getY(){
    return yCoord;
  }
}
