public class Projectile {
  private int x;
  private int y;
  private char logo;
  private int damage;
  public Projectile(int xCoord, int yCoord, int dam){
    x = xCoord;
    y = yCoord;
    damage = dam;
    logo = '\u25E6';
  }
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
