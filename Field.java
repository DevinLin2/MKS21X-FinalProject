import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;
import com.googlecode.lanterna.screen.Screen;
import java.util.ArrayList;
import java.util.Random;

public class Field{
  /**
   * ArrayList of different floors each a different level
   */
  private ArrayList<Floor> floor;
  /**
   * current floor of player
   */
  private Floor currentFloor;
  /**
   * Constructs a Field which initiates the Arraylist of floors.
   * The floors will each hold an unique ArrayList of walls.
   */
  public Field(){
    floor = new ArrayList<Floor>();
    Floor levelOne = new Floor(1);
    levelOne.addWall(3,8);
    levelOne.addWall(3,9);
    levelOne.addWall(3,10);
    levelOne.addWall(3,11);
    levelOne.addWall(3,12);
    levelOne.addWall(3,13);
    levelOne.addWall(3,14);
    levelOne.addWall(3,15);
    levelOne.addWall(3,16);
    levelOne.addWall(3,17);
    levelOne.addWall(3,18);
    levelOne.addWall(3,19);

    levelOne.addWall(3,8);
    levelOne.addWall(4,8);
    levelOne.addWall(5,8);
    levelOne.addWall(6,8);
    levelOne.addWall(7,8);
    levelOne.addWall(8,8);
    levelOne.addWall(9,8);
    levelOne.addWall(10,8);
    levelOne.addWall(11,8);
    levelOne.addWall(12,8);

    levelOne.addWall(12,8);
    levelOne.addWall(12,9);
    levelOne.addWall(12,10);
    levelOne.addWall(12,11);

    levelOne.addWall(12,17);
    levelOne.addWall(12,18);
    levelOne.addWall(12,19);

    levelOne.addWall(3,19);
    levelOne.addWall(4,19);
    levelOne.addWall(5,19);
    levelOne.addWall(6,19);
    levelOne.addWall(7,19);
    levelOne.addWall(8,19);
    levelOne.addWall(9,19);
    levelOne.addWall(10,19);
    levelOne.addWall(11,19);
    levelOne.addWall(12,19);

    levelOne.addWall(12,11);
    levelOne.addWall(13,11);
    levelOne.addWall(14,11);
    levelOne.addWall(15,11);
    levelOne.addWall(16,11);
    levelOne.addWall(17,11);
    levelOne.addWall(18,11);
    levelOne.addWall(19,11);
    levelOne.addWall(20,11);
    levelOne.addWall(21,11);
    levelOne.addWall(22,11);
    levelOne.addWall(23,11);
    levelOne.addWall(24,11);
    levelOne.addWall(25,11);

    levelOne.addWall(12,17);
    levelOne.addWall(13,17);
    levelOne.addWall(14,17);
    levelOne.addWall(15,17);
    levelOne.addWall(16,17);
    levelOne.addWall(17,17);
    levelOne.addWall(18,17);
    levelOne.addWall(19,17);
    levelOne.addWall(20,17);

    levelOne.addWall(20,17);
    levelOne.addWall(20,18);
    levelOne.addWall(20,19);
    levelOne.addWall(20,20);
    levelOne.addWall(20,21);
    levelOne.addWall(20,22);
    levelOne.addWall(20,23);
    levelOne.addWall(20,24);
    levelOne.addWall(21,24);
    levelOne.addWall(22,24);
    levelOne.addWall(23,24);
    levelOne.addWall(24,24);
    levelOne.addWall(25,24);
    levelOne.addWall(26,24);
    levelOne.addWall(27,24);
    levelOne.addWall(28,24);
    levelOne.addWall(29,24);
    levelOne.addWall(30,24);
    levelOne.addWall(31,24);
    levelOne.addWall(32,24);
    levelOne.addWall(33,24);
    levelOne.addWall(34,24);
    levelOne.addWall(35,24);
    levelOne.addWall(36,24);
    levelOne.addWall(37,24);
    levelOne.addWall(38,24);
    levelOne.addWall(39,24);
    levelOne.addWall(40,24);
    levelOne.addWall(41,24);
    levelOne.addWall(42,24);
    levelOne.addWall(43,24);
    levelOne.addWall(44,24);

    levelOne.addWall(23,17);
    levelOne.addWall(24,17);
    levelOne.addWall(25,17);
    levelOne.addWall(26,17);
    levelOne.addWall(27,17);
    levelOne.addWall(28,17);

    levelOne.addWall(23,17);
    levelOne.addWall(23,18);
    levelOne.addWall(23,19);
    levelOne.addWall(23,20);
    levelOne.addWall(23,21);

    levelOne.addWall(24,21);
    levelOne.addWall(25,21);
    levelOne.addWall(26,21);
    levelOne.addWall(27,21);
    levelOne.addWall(28,21);
    levelOne.addWall(29,21);
    levelOne.addWall(30,21);
    levelOne.addWall(31,21);

    levelOne.addWall(31,18);
    levelOne.addWall(31,19);
    levelOne.addWall(31,20);

    levelOne.addWall(28,7);
    levelOne.addWall(28,8);
    levelOne.addWall(28,9);
    levelOne.addWall(28,10);
    levelOne.addWall(28,11);
    levelOne.addWall(28,12);
    levelOne.addWall(28,13);
    levelOne.addWall(28,14);
    levelOne.addWall(28,15);
    levelOne.addWall(28,16);
    levelOne.addWall(28,17);

    levelOne.addWall(25,4);
    levelOne.addWall(25,5);
    levelOne.addWall(25,6);
    levelOne.addWall(25,7);
    levelOne.addWall(25,8);
    levelOne.addWall(25,9);
    levelOne.addWall(25,10);
    levelOne.addWall(25,11);

    levelOne.addWall(26,4);
    levelOne.addWall(27,4);
    levelOne.addWall(28,4);
    levelOne.addWall(29,4);
    levelOne.addWall(30,4);
    levelOne.addWall(31,4);
    levelOne.addWall(32,4);

    levelOne.addWall(28,7);
    levelOne.addWall(29,7);
    levelOne.addWall(30,7);
    levelOne.addWall(31,7);
    levelOne.addWall(32,7);

    levelOne.addWall(32,2);
    levelOne.addWall(32,3);
    levelOne.addWall(32,4);

    levelOne.addWall(33,2);
    levelOne.addWall(34,2);
    levelOne.addWall(35,2);
    levelOne.addWall(36,2);
    levelOne.addWall(37,2);
    levelOne.addWall(38,2);
    levelOne.addWall(39,2);
    levelOne.addWall(40,2);
    levelOne.addWall(41,2);
    levelOne.addWall(42,2);
    levelOne.addWall(43,2);
    levelOne.addWall(44,2);
    levelOne.addWall(45,2);
    levelOne.addWall(46,2);
    levelOne.addWall(47,2);
    levelOne.addWall(48,2);
    levelOne.addWall(49,2);
    levelOne.addWall(50,2);
    levelOne.addWall(51,2);
    levelOne.addWall(52,2);
    levelOne.addWall(53,2);
    levelOne.addWall(54,2);

    levelOne.addWall(32,18);
    levelOne.addWall(33,18);
    levelOne.addWall(34,18);
    levelOne.addWall(35,18);
    levelOne.addWall(36,18);
    levelOne.addWall(37,18);
    levelOne.addWall(38,18);
    levelOne.addWall(39,18);
    levelOne.addWall(40,18);
    levelOne.addWall(41,18);
    levelOne.addWall(42,18);
    levelOne.addWall(43,18);
    levelOne.addWall(44,18);
    levelOne.addWall(45,18);
    levelOne.addWall(46,18);
    levelOne.addWall(47,18);
    levelOne.addWall(48,18);
    levelOne.addWall(49,18);
    levelOne.addWall(50,18);
    levelOne.addWall(51,18);
    levelOne.addWall(52,18);
    levelOne.addWall(53,18);
    levelOne.addWall(54,18);
    levelOne.addWall(55,18);
    levelOne.addWall(56,18);

    levelOne.addWall(44,21);
    levelOne.addWall(44,22);
    levelOne.addWall(44,23);
    levelOne.addWall(44,24);

    levelOne.addWall(45,21);
    levelOne.addWall(46,21);
    levelOne.addWall(47,21);
    levelOne.addWall(48,21);
    levelOne.addWall(49,21);
    levelOne.addWall(50,21);
    levelOne.addWall(51,21);
    levelOne.addWall(52,21);

    levelOne.addWall(52,22);
    levelOne.addWall(52,23);

    levelOne.addWall(53,23);
    levelOne.addWall(54,23);
    levelOne.addWall(55,23);
    levelOne.addWall(56,23);
    levelOne.addWall(57,23);
    levelOne.addWall(58,23);
    levelOne.addWall(59,23);
    levelOne.addWall(60,23);
    levelOne.addWall(61,23);
    levelOne.addWall(62,23);
    levelOne.addWall(63,23);
    levelOne.addWall(64,23);
    levelOne.addWall(65,23);
    levelOne.addWall(66,23);
    levelOne.addWall(67,23);
    levelOne.addWall(68,23);
    levelOne.addWall(69,23);
    levelOne.addWall(70,23);
    levelOne.addWall(71,23);
    levelOne.addWall(72,23);
    levelOne.addWall(73,23);

    levelOne.addWall(32,8);
    levelOne.addWall(32,9);
    levelOne.addWall(32,10);
    levelOne.addWall(32,11);
    levelOne.addWall(32,12);
    levelOne.addWall(32,13);
    levelOne.addWall(32,14);

    levelOne.addWall(33,14);
    levelOne.addWall(34,14);
    levelOne.addWall(35,14);
    levelOne.addWall(36,14);
    levelOne.addWall(37,14);
    levelOne.addWall(38,14);
    levelOne.addWall(39,14);
    levelOne.addWall(40,14);
    levelOne.addWall(41,14);
    levelOne.addWall(42,14);
    levelOne.addWall(43,14);
    levelOne.addWall(44,14);
    levelOne.addWall(45,14);
    levelOne.addWall(46,14);
    levelOne.addWall(47,14);
    levelOne.addWall(48,14);
    levelOne.addWall(49,14);
    levelOne.addWall(50,14);
    levelOne.addWall(51,14);
    levelOne.addWall(52,14);
    levelOne.addWall(53,14);
    levelOne.addWall(54,14);

    levelOne.addWall(54,2);
    levelOne.addWall(54,3);
    levelOne.addWall(54,4);
    levelOne.addWall(54,5);
    levelOne.addWall(54,6);
    levelOne.addWall(54,7);
    levelOne.addWall(54,8);

    levelOne.addWall(54,12);
    levelOne.addWall(54,13);
    levelOne.addWall(54,14);

    levelOne.addWall(55,8);
    levelOne.addWall(56,8);
    levelOne.addWall(57,8);
    levelOne.addWall(58,8);
    levelOne.addWall(59,8);
    levelOne.addWall(60,8);

    levelOne.addWall(55,12);
    levelOne.addWall(56,12);

    levelOne.addWall(56,13);
    levelOne.addWall(56,14);
    levelOne.addWall(56,15);
    levelOne.addWall(56,16);
    levelOne.addWall(56,17);
    levelOne.addWall(56,18);

    levelOne.addWall(60,9);
    levelOne.addWall(60,10);
    levelOne.addWall(60,11);
    levelOne.addWall(60,12);
    levelOne.addWall(60,13);
    levelOne.addWall(60,14);
    levelOne.addWall(60,15);
    levelOne.addWall(60,16);
    levelOne.addWall(60,17);
    levelOne.addWall(60,18);

    levelOne.addWall(61,18);
    levelOne.addWall(62,18);
    levelOne.addWall(63,18);
    levelOne.addWall(64,18);
    levelOne.addWall(65,18);
    levelOne.addWall(66,18);
    levelOne.addWall(67,18);

    levelOne.addWall(67,8);
    levelOne.addWall(67,9);
    levelOne.addWall(67,10);
    levelOne.addWall(67,11);
    levelOne.addWall(67,12);
    levelOne.addWall(67,13);
    levelOne.addWall(67,14);
    levelOne.addWall(67,15);
    levelOne.addWall(67,16);
    levelOne.addWall(67,17);

    levelOne.addWall(65,8);
    levelOne.addWall(66,8);

    levelOne.addWall(65,3);
    levelOne.addWall(65,4);
    levelOne.addWall(65,5);
    levelOne.addWall(65,6);
    levelOne.addWall(65,7);

    levelOne.addWall(66,3);
    levelOne.addWall(67,3);
    levelOne.addWall(68,3);
    levelOne.addWall(69,3);
    levelOne.addWall(70,3);
    levelOne.addWall(71,3);
    levelOne.addWall(72,3);
    levelOne.addWall(73,3);

    levelOne.addWall(73,4);
    levelOne.addWall(73,5);
    levelOne.addWall(73,6);
    levelOne.addWall(73,7);
    levelOne.addWall(73,8);

    levelOne.addWall(71,8);
    levelOne.addWall(72,8);

    levelOne.addWall(71,9);
    levelOne.addWall(71,10);
    levelOne.addWall(71,11);
    levelOne.addWall(71,12);
    levelOne.addWall(71,13);
    levelOne.addWall(71,14);
    levelOne.addWall(71,15);
    levelOne.addWall(71,16);
    levelOne.addWall(71,17);
    levelOne.addWall(71,18);

    levelOne.addWall(72,18);
    levelOne.addWall(73,18);

    levelOne.addWall(73,19);
    levelOne.addWall(73,20);
    levelOne.addWall(73,21);
    levelOne.addWall(73,22);
    levelOne.addWall(73,23);
    levelOne.addMonster(3,30,6,5,1);
    levelOne.addMonster(2,50,10,5,3);
    levelOne.addMonster(5,34,21,5,6);
    currentFloor = levelOne;
    floor.add(levelOne);
  }
  public static void main(String[] args) {
    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.setCursorVisible(false);
    boolean running = true;
    Player bob = new Player(100, 10, 10, 2);
    Screen screen = new Screen(terminal);
    Field playingField = new Field();
    String[] directionArray = new String[]{"up", "down", "left", "right"};
    Random randgen = new Random();
    screen.startScreen();
    // puts down the walls in the terminal
    for (int floorLevel = 0; floorLevel < playingField.floor.size(); floorLevel++){ // put this into a function that is able to switch detween floors and call here
      Floor current = playingField.floor.get(floorLevel);// fix this to make sense with currentFloor variable
      for (int currentWall = 0; currentWall < current.getBorder().size(); currentWall++){
        terminal.moveCursor(current.getBorder().get(currentWall).getX(),current.getBorder().get(currentWall).getY());
        terminal.putCharacter(current.getBorder().get(currentWall).getLogo());
      }
    }
    while (running){
      terminal.moveCursor(bob.getX(),bob.getY());
      terminal.putCharacter(bob.getCharacter());
      Key key = terminal.readInput();
      String lastKey = "";
      screen.putString(1,3,"Health: " + bob.getHealth(), Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
      // following code responsible for monster movement,shooting, and damaging Player
      for (int monster = 0; monster < playingField.currentFloor.getEnemies().size(); monster++){
        Monster currentMonster = playingField.currentFloor.getEnemies().get(monster);
        int randIndex = Math.abs(randgen.nextInt(4));
        currentMonster.addToCount();
        if (currentMonster.getCount() % 5000 == 0){
          for (int bullet = 0; bullet < currentMonster.getBullets().size(); bullet++) {
            Projectile currentBullet = currentMonster.getBullets().get(bullet);
            if (currentBullet.getX() != currentMonster.getX() || currentBullet.getY() != currentMonster.getY()){
              terminal.moveCursor(currentBullet.getX(), currentBullet.getY());
              terminal.putCharacter(' ');
            }
            currentBullet.move();
            terminal.moveCursor(currentBullet.getX(), currentBullet.getY());
            terminal.putCharacter(currentBullet.getLogo());
            if (currentBullet.getX() == bob.getX() && currentBullet.getY() == bob.getY()){
              bob.takeDamage(currentMonster.getDamage());
            }
          }
          currentMonster.addToBulletMoveCount();
          // makes bullets at the end of their range disappear
          if (currentMonster.getBulletMoveCount() == currentMonster.getRange()){
            for (int i = 0; i < currentMonster.getBullets().size(); i++) {
              Projectile current = currentMonster.getBullets().get(i);
              terminal.moveCursor(current.getX(), current.getY());
              terminal.putCharacter(' ');
            }
            currentMonster.resetBullets();
            currentMonster.resetBulletMoveCount();
          }
        }
        if ((currentMonster.validMove(directionArray[randIndex], playingField.floor, playingField.currentFloor)) && (currentMonster.getCount() == 0 || (currentMonster.getCount() % 25000 == 0))) {
          terminal.moveCursor(currentMonster.getX(), currentMonster.getY());
          terminal.putCharacter(' ');
          currentMonster.move(directionArray[randIndex]);
          terminal.moveCursor(currentMonster.getX(), currentMonster.getY());
          terminal.putCharacter(currentMonster.getCharacter());
          currentMonster.resetCount();
          currentMonster.resetBullets();
        }
      }
      if (key != null){
        if (key.getKind() == Key.Kind.Escape){
          screen.stopScreen();
          running = false;
        }
        if (key.getKind() == Key.Kind.ArrowUp && bob.validMove("up", playingField.floor, playingField.currentFloor)){
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(' ');
          bob.move("up");
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(bob.getCharacter());
          lastKey = "up";
        }
        if (key.getKind() == Key.Kind.ArrowDown && bob.validMove("down", playingField.floor, playingField.currentFloor)){
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(' ');
          bob.move("down");
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(bob.getCharacter());
          lastKey = "down";
        }
        if (key.getKind() == Key.Kind.ArrowLeft && bob.validMove("left", playingField.floor, playingField.currentFloor)){
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(' ');
          bob.move("left");
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(bob.getCharacter());
          lastKey = "left";
        }
        if (key.getKind() == Key.Kind.ArrowRight && bob.validMove("right", playingField.floor, playingField.currentFloor)){
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(' ');
          bob.move("right");
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(bob.getCharacter());
          lastKey = "right";
        }
        screen.putString(0, 0, "Last Key: " + lastKey, Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
        if (key.getCharacter() == ' '){
          for (int monster = 0; monster < playingField.currentFloor.getEnemies().size(); monster++){
            Monster currentMonster = playingField.currentFloor.getEnemies().get(monster);
            if (((currentMonster.getX() <= bob.getX()+2) && (currentMonster.getX() >= bob.getX()-2)) && ((currentMonster.getY() <= bob.getY()+2) && (currentMonster.getY() >= bob.getY()-2))) {
              currentMonster.takeDamage(bob.getDamage());
            }
            if(currentMonster.getHealth() <= 0){
              terminal.moveCursor(currentMonster.getX(), currentMonster.getY());
              terminal.putCharacter(' ');
              playingField.currentFloor.removeMonster(currentMonster);
            }
          }
        }
      }
      screen.refresh();
    }
  }
}
