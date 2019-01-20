import java.util.ArrayList;
public class Monster implements Damageable{
  private int health;
  private int damage;
  private int x;
  private int y;
  private char logo;
  private int count;
  private ArrayList<Projectile> bullets;
  private int range;
  public Monster(int h, int xcord, int ycord, int d, int r){
    health = h;
    damage = d;
    x = xcord;
    y = ycord;
    logo = '\u2639';
    count = 0;
    range = r;
    bullets = new ArrayList<Projectile>();
    for (int i = 1; i < range + 1; i++){
      Projectile up = new Projectile(x,y-i,damage);
      Projectile down = new Projectile(x,y+i,damage);
      Projectile left = new Projectile(x-i,y,damage);
      Projectile right = new Projectile(x+i,y,damage);
      bullets.add(up);
      bullets.add(down);
      bullets.add(left);
      bullets.add(right);
    }
  }
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
  public int getDamage(){
    return damage;
  }
  public int getHealth(){
    return health;
  }
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
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
  public ArrayList<Projectile> getBullets(){
    return bullets;
  }
  public int getRange(){
    return range;
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
