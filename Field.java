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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
  * ArrayList of the bullets fired by player
  */
  private ArrayList<Projectile> playerBullets;
  /**
  * Constructs a Field which initiates the Arraylist of floors.
  * The floors will each hold an unique ArrayList of walls.
  */
  public Field(){
    floor = new ArrayList<Floor>();
    playerBullets = new ArrayList<Projectile>();
    Floor levelOne = new Floor(1);
    // evverytime a monster is hit it takes 5000 damage. keep that in mind when setting its health
    // level one monsters
    levelOne.addMonster(2500,30,6,5,4);
    levelOne.addMonster(2500,50,10,5,4);
    levelOne.addMonster(2500,34,21,5,4);
    levelOne.addMonster(2500,34,6,5,4);
    levelOne.addMonster(2500,50,11,5,4);
    levelOne.addMonster(2500,34,20,5,4);
    try{
      File f = new File("LevelOne.txt");
      Scanner in = new Scanner(f);
      while(in.hasNext()){
        String line = in.nextLine();
        String[] arguments = line.split(",");
        levelOne.addWall(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]));
      }
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }
    floor.add(levelOne);
    currentFloor = levelOne;
  }
  public void changeLevel(int lvlNum){
    if (lvlNum == 1){
      currentFloor = floor.get(0);
    }
    if (lvlNum == 2){
      currentFloor = floor.get(1);
    }
    else{
      currentFloor = floor.get(2);
    }
  }
  public static void main(String[] args) {
    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.setCursorVisible(false);
    boolean running = true;
    Player bob = new Player(100, 10, 10, 1);
    Screen screen = new Screen(terminal);
    Field playingField = new Field();
    String[] directionArray = new String[]{"up", "down", "left", "right"};
    Random randgen = new Random();
    String lastKey = "";
    screen.startScreen();
    // puts down the walls in the terminal
    for (int floorLevel = 0; floorLevel < playingField.floor.size(); floorLevel++){ // put this into a function that is able to switch detween floors and call here
      Floor current = playingField.floor.get(floorLevel);// fix this to make sense with currentFloor variable
      for (int currentWall = 0; currentWall < current.getBorder().size(); currentWall++){
        terminal.moveCursor(current.getBorder().get(currentWall).getX(),current.getBorder().get(currentWall).getY());
        terminal.putCharacter(current.getBorder().get(currentWall).getLogo());
      }
    }
    portal exit = new portal(69,6); // 69 6
    while (running){
      if (exit.getX() == bob.getX() && exit.getY() == bob.getY()){
        terminal.moveCursor(bob.getX(),bob.getY());
        terminal.putCharacter(bob.getCharacter());
      }
      else{
        terminal.moveCursor(bob.getX(),bob.getY());
        terminal.putCharacter(bob.getCharacter());
        terminal.moveCursor(exit.getX(),exit.getY());
        terminal.putCharacter(exit.getLogo());
      }
      Key key = terminal.readInput();
      //screen.putString(0,0,"Health: " + bob.getHealth(), Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
      // following code responsible for monster movement,shooting, and damaging Player
      for (int monster = 0; monster < playingField.currentFloor.getEnemies().size(); monster++){
        Monster currentMonster = playingField.currentFloor.getEnemies().get(monster);
        int randIndex = Math.abs(randgen.nextInt(4));
        if ((currentMonster.validMove(directionArray[randIndex], playingField.floor, playingField.currentFloor)) && (currentMonster.getCount() % 10000 == 0)) {
          terminal.moveCursor(currentMonster.getX(), currentMonster.getY());
          terminal.putCharacter(' ');
          currentMonster.move(directionArray[randIndex]);
          terminal.moveCursor(currentMonster.getX(), currentMonster.getY());
          terminal.putCharacter(currentMonster.getCharacter());
          // player Damage
          if (((bob.getX() <= currentMonster.getX()+currentMonster.getRange()) && (bob.getX() >= currentMonster.getX()-currentMonster.getRange())) && ((bob.getY() <= currentMonster.getY()+currentMonster.getRange()) && (bob.getY() >= currentMonster.getY()-currentMonster.getRange()))) {
            bob.takeDamage(currentMonster.getDamage());
            if (bob.getHealth() <= 0){
              screen.clear();
              screen.putString(35,15,"YOU LOSE", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              screen.putString(29,16,"PRESS ESC TO EXIT PROGRAM", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
              running = false;
            }
          }
          currentMonster.resetCount();
        }
        // monster take damage
        for (int bullet = 0; bullet < playingField.playerBullets.size(); bullet++){
          Projectile currentBullet = playingField.playerBullets.get(bullet);
          if (currentMonster.getX() == currentBullet.getX() && currentMonster.getY() == currentBullet.getY()){
            currentMonster.takeDamage(bob.getDamage());
            // removes monster if their health drops to 0
            if (currentMonster.getHealth() <= 0){
              terminal.moveCursor(currentMonster.getX(), currentMonster.getY());
              terminal.putCharacter(' ');
              playingField.currentFloor.removeMonster(currentMonster);
            }
          }
        }
        currentMonster.addToCount();
      }
      // player bullet travel
      for (int bullet = 0; bullet < playingField.playerBullets.size(); bullet++){
        Projectile currentBullet = playingField.playerBullets.get(bullet);
        currentBullet.addToCount();
        if (currentBullet.getCount() % 1000 == 0){
          if (currentBullet.validMove(currentBullet.getDirection(), playingField.floor, playingField.currentFloor)) {
            if (currentBullet.getX() != bob.getX() || currentBullet.getY() != bob.getY()){
              terminal.moveCursor(currentBullet.getX(), currentBullet.getY());
              terminal.putCharacter(' ');
            }
            currentBullet.move();
            terminal.moveCursor(currentBullet.getX(), currentBullet.getY());
            terminal.putCharacter(currentBullet.getLogo());
            currentBullet.resetCount();
          } else {
            terminal.moveCursor(currentBullet.getX(), currentBullet.getY());
            terminal.putCharacter(' ');
            playingField.playerBullets.remove(currentBullet);
          }
        }
      }
      // actions when keys are pressed
      if (key != null){
        if (key.getKind() == Key.Kind.Escape){
          screen.stopScreen();
          running = false;
        }
        // player movement up
        if (key.getKind() == Key.Kind.ArrowUp && bob.validMove("up", playingField.floor, playingField.currentFloor)){
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(' ');
          bob.move("up");
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(bob.getCharacter());
          lastKey = "up";
        }
        // player movement down
        if (key.getKind() == Key.Kind.ArrowDown && bob.validMove("down", playingField.floor, playingField.currentFloor)){
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(' ');
          bob.move("down");
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(bob.getCharacter());
          lastKey = "down";
        }
        // player movement left
        if (key.getKind() == Key.Kind.ArrowLeft && bob.validMove("left", playingField.floor, playingField.currentFloor)){
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(' ');
          bob.move("left");
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(bob.getCharacter());
          lastKey = "left";
        }
        // player movement right
        if (key.getKind() == Key.Kind.ArrowRight && bob.validMove("right", playingField.floor, playingField.currentFloor)){
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(' ');
          bob.move("right");
          terminal.moveCursor(bob.getX(), bob.getY());
          terminal.putCharacter(bob.getCharacter());
          lastKey = "right";
        }
        if(key.getCharacter() == 'p' && bob.getX() == exit.getX() && bob.getY() == exit.getY()) {
          if(playingField.currentFloor.getEnemies().size() == 0){
            if (playingField.currentFloor.getLevel() == 1){
              Floor levelTwo = new Floor(2);
              try{
                File f = new File("LevelTwo.txt");
                Scanner in = new Scanner(f);
                while(in.hasNext()){
                  String line = in.nextLine();
                  String[] argss = line.split(",");
                  levelTwo.addWall(Integer.parseInt(argss[0]), Integer.parseInt(argss[1]));
                }
              }
              catch(FileNotFoundException e){
                e.printStackTrace();
              }
              playingField.floor.add(levelTwo);
              lastKey = "p";
              playingField.changeLevel(2);
              for(int c = 0; c < 81; c ++){
                for(int r = 0; r < 25; r ++){
                  terminal.moveCursor(c,r);
                  terminal.putCharacter(' ');
                }
              }
              for (int currentWalll = 0; currentWalll < playingField.floor.get(1).getBorder().size(); currentWalll ++){
                terminal.moveCursor(playingField.floor.get(1).getBorder().get(currentWalll).getX(), playingField.floor.get(1).getBorder().get(currentWalll).getY());
                terminal.putCharacter('\u25fb');
              }
              terminal.moveCursor(bob.getX(),bob.getY());
              terminal.putCharacter(' ');
              bob.setX(4);
              bob.setY(20);
              terminal.moveCursor(bob.getX(),bob.getY());
              terminal.putCharacter('\u0040');
              terminal.moveCursor(exit.getX(), exit.getY());
              terminal.putCharacter(' ');
              exit.setX(78);
              exit.setY(7);
              terminal.moveCursor(exit.getX(),exit.getY());
              terminal.putCharacter('\u06DE');
              levelTwo.addMonster(2500,18,7,5,4);
              levelTwo.addMonster(2500,14,4,5,4);
              levelTwo.addMonster(2500,40,4,5,4);
              levelTwo.addMonster(2500,29,15,5,4);
              levelTwo.addMonster(2500,47,22,5,4);
              levelTwo.addMonster(2500,67,18,5,4);
              levelTwo.addMonster(2500,29,6,5,4);
              levelTwo.addMonster(2500,41,3,5,4);
              levelTwo.addMonster(2500,47,18,5,4);
              levelTwo.addMonster(2500,53,21,5,4);
              levelTwo.addMonster(2500,40,4,5,4);
              levelTwo.addMonster(2500,49,14,5,4);
              levelTwo.addMonster(2500,70,5,5,4);
              levelTwo.addMonster(2500,65,13,5,4);
            }
            else{
              Floor levelThree = new Floor(3);
              try{
                File ff = new File("LevelThree.txt");
                Scanner in = new Scanner(ff);
                while(in.hasNext()){
                  String line = in.nextLine();
                  String[] argsss = line.split(",");
                  levelThree.addWall(Integer.parseInt(argsss[0]), Integer.parseInt(argsss[1]));
                }
              }
              catch(FileNotFoundException e){
                e.printStackTrace();
              }
              playingField.floor.add(levelThree);
              playingField.changeLevel(3);
              for(int c = 0; c < 81; c ++){
                for(int r = 0; r < 25; r ++){
                  terminal.moveCursor(c,r);
                  terminal.putCharacter(' ');
                }
              }
              for (int currentWalll = 0; currentWalll < playingField.floor.get(2).getBorder().size(); currentWalll ++){
                terminal.moveCursor(playingField.floor.get(2).getBorder().get(currentWalll).getX(), playingField.floor.get(2).getBorder().get(currentWalll).getY());
                terminal.putCharacter('\u25fb');
              }
              terminal.moveCursor(bob.getX(),bob.getY());
              terminal.putCharacter(' ');
              bob.setX(4);
              bob.setY(20);
              terminal.moveCursor(bob.getX(),bob.getY());
              terminal.putCharacter('\u0040');
              terminal.moveCursor(exit.getX(), exit.getY());
              terminal.putCharacter(' ');
              exit.setX(58);
              exit.setY(13);
              terminal.moveCursor(exit.getX(),exit.getY());
              terminal.putCharacter('\u06DE');
              levelThree.addMonster(2500,18,5,5,4);
              levelThree.addMonster(2500,24,8,5,4);
              levelThree.addMonster(2500,53,3,5,4);
              levelThree.addMonster(2500,61,4,2,4);
              levelThree.addMonster(2500,63,13,5,4);
              levelThree.addMonster(2500,72,10,5,4);
              levelThree.addMonster(2500,67,15,5,4);
              levelThree.addMonster(2500,37,21,5,4);
              levelThree.addMonster(2500,40,23,5,4);
              levelThree.addMonster(2500,34,9,5,4);
              levelThree.addMonster(2500,44,16,5,4);
              levelThree.addMonster(2500,40,10,5,4);
              levelThree.addMonster(2500,50,8,5,4);
              levelThree.addMonster(2500,30,14,5,4);
              levelThree.addMonster(2500,39,13,5,4);
              levelThree.addMonster(20000,38,12,10,6);
            }
          }
          if (playingField.currentFloor.getLevel() == 3 && playingField.currentFloor.getEnemies().size() == 0) {
            screen.clear();
            screen.putString(29,15,"YOU WIN!!!!!!!!!!!!!!!!!!!", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
            screen.putString(29,16,"PRESS ESC TO EXIT PROGRAM", Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
            running = false;
          }
        }
        // player attack
        if (key.getCharacter() == 'w'){
          Projectile bullet = new Projectile(bob.getX(), bob.getY(), bob.getDamage(), "up");
          playingField.playerBullets.add(bullet);
        }
        if (key.getCharacter() == 'a'){
          Projectile bullet = new Projectile(bob.getX(), bob.getY(), bob.getDamage(), "left");
          playingField.playerBullets.add(bullet);
        }
        if (key.getCharacter() == 's'){
          Projectile bullet = new Projectile(bob.getX(), bob.getY(), bob.getDamage(), "down");
          playingField.playerBullets.add(bullet);
        }
        if (key.getCharacter() == 'd'){
          Projectile bullet = new Projectile(bob.getX(), bob.getY(), bob.getDamage(), "right");
          playingField.playerBullets.add(bullet);
        }
        screen.refresh();
      }
    }
  }
}
