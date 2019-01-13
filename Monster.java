public class Monster implements Damageable{
  private int health;
  private int damage;
  private int x;
  private int y;
  private char logo;
  public Monster(int h,int xcord, int ycord, int d){
    health = h;
    damage = d;
    x = xcord;
    y = ycord;
    logo = '\u2639';
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
  public void move(String direction){
    if (direction.equals("up")){
      y--;
    }
    if (direction.equals("down")){
      y++;
    }
    if (direction.equals("left")){
      x++;
    }
    if (direction.equals("right")){
      x--;
    }
  }
}
