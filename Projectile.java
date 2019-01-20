public class Projectile {
  private int x;
  private int y;
  private String direction;
  private char logo;
  private int damage;
  public Projectile(int xCoord, int yCoord, int dam, String d){
    x = xCoord;
    y = yCoord;
    direction = d;
    damage = dam;
    logo = '\u25E6';
  }
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
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  public int getDamage(){
    return damage;
  }
  public char getLogo(){
    return logo;
  }
  public void setX(int newX){
    x = newX;
  }
  public void setY(int newY){
    y = newY;
  }
}
