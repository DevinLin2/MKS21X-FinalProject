import java.util.ArrayList;
public class Projectile {
  private int x;
  private int y;
  private char logo;
  private int damage;
  private String direction;
  private int count;
  public Projectile(int xCoord, int yCoord, int dam, String dir){
    x = xCoord;
    y = yCoord;
    damage = dam;
    logo = '\u25E6';
    direction = dir;
    count = 0;
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
  public String getDirection(){
    return direction;
  }
  public int getCount(){
    return count;
  }
  public void addToCount(){
    count++;
  }
  public void resetCount(){
    count = 0;
  }
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
