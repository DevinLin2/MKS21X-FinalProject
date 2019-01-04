public class Monster implements Damageable{
private int health;
private int damage;
private int x;
private int y;

public Monster(int h, int d, int xcord, int ycord){
  health = h;
  damage = d;
  x = xcord;
  y = ycord;

}

public void attack(){

}
public void takeDamage(int damage){ // might make it return int
health = health - damage;
}

public void move(){

}

public int getDamage(){
return damage;
}

public int getHealth(){
  return health;
}

}
