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

public int attack(){
return damage;
}
public void takeDamage(int damage){ // might make it return int
health = health - damage;
}

public void move(String direction){
  if (direction.equals("up")){
    y++
  }
  if (direction.equals("down")){
    y--
  }
  if (direction.equals("left")){
    x--
  }
  if (direction.equals("right")){
    x++
  }
}

public int getDamage(){
return damage;
}

public int getHealth(){
  return health;
}

}
