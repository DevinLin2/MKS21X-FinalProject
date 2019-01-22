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
    // everytime a monster is hit it takes 1000 damage. keep that in mind when setting its health
    // level one monsters
    levelOne.addMonster(2500,30,6,1,2);
    levelOne.addMonster(2500,50,10,1,2);
    levelOne.addMonster(2500,34,21,1,2);
    levelOne.addMonster(2500,34,6,1,3);
    levelOne.addMonster(2500,50,11,1,3);
    levelOne.addMonster(2500,34,20,1,3);
    try{ // adding first floor walls
      File f = new File("LevelOne.txt");
      Scanner in = new Scanner(f);
      while(in.hasNext()){
        String line = in.nextLine();
        String[] lvlOneWallCord = line.split(",");
        levelOne.addWall(Integer.parseInt(lvlOneWallCord[0]), Integer.parseInt(lvlOneWallCord[1]));
      }
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }
    floor.add(levelOne);
    currentFloor = levelOne; //used to set walls of the correct floor
  }
  public void changeLevel(int lvlNum){ // used to set current floor
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
    Player bob = new Player(85, 10, 10, 1); // health at 85 which translates to the char 'U'
    Screen screen = new Screen(terminal);
    Field playingField = new Field();
    String[] directionArray = new String[]{"up", "down", "left", "right"};
    Random randgen = new Random();
    String lastKey = "";
    String mode = "playing";
    screen.startScreen();
    // puts down the walls in the terminal
    for (int floorLevel = 0; floorLevel < playingField.floor.size(); floorLevel++){ // put this into a function that is able to switch detween floors and call here
      Floor current = playingField.floor.get(floorLevel);// fix this to make sense with currentFloor variable
      for (int currentWall = 0; currentWall < current.getBorder().size(); currentWall++){
        terminal.moveCursor(current.getBorder().get(currentWall).getX(),current.getBorder().get(currentWall).getY());
        terminal.putCharacter(current.getBorder().get(currentWall).getLogo());
      }
    }
    portal exit = new portal(69,6);
    while (running) {
      if (mode.equals("playing")) { // code runs when player is alive therefore "playing"
        if (exit.getX() == bob.getX() && exit.getY() == bob.getY()){ // player standing on portal wont glitch
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(bob.getCharacter());
        }
        else{ // puts player and portal on screen
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(bob.getCharacter());
          terminal.moveCursor(exit.getX(),exit.getY());
          terminal.putCharacter(exit.getLogo());
        }
        Key key = terminal.readInput();
        // following code responsible for monster movement,shooting, and damaging Player
        for (int monster = 0; monster < playingField.currentFloor.getEnemies().size(); monster++){
          Monster currentMonster = playingField.currentFloor.getEnemies().get(monster);
          int randIndex = Math.abs(randgen.nextInt(4));
          if ((currentMonster.validMove(directionArray[randIndex], playingField.floor, playingField.currentFloor)) && (currentMonster.getCount() % 10000 == 0)) { // 10000 is the buffer time increase it and monster will move slowerdecrease it and monster will move faster
            terminal.moveCursor(currentMonster.getX(), currentMonster.getY());
            currentMonster.move(directionArray[randIndex]);
            terminal.moveCursor(currentMonster.getX(), currentMonster.getY());
            terminal.putCharacter(currentMonster.getCharacter());
            // player Damage
            if (((bob.getX() <= currentMonster.getX()+currentMonster.getRange()) && (bob.getX() >= currentMonster.getX()-currentMonster.getRange())) && ((bob.getY() <= currentMonster.getY()+currentMonster.getRange()) && (bob.getY() >= currentMonster.getY()-currentMonster.getRange()))) {
              bob.takeDamage(currentMonster.getDamage());
              if (bob.getHealth() <= 64){ // 64 is set because the char 'A' int value is 65. our rule is that if player goes below the letter A they die
                mode = "lose";
              }
            }
            currentMonster.resetCount(); // monster moved; reset count to not make it go over int cap of 2^31-1 if program is left on too long
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
                playingField.currentFloor.removeMonster(currentMonster); // used to check if all monsters are killed
              }
            }
          }
          currentMonster.addToCount(); //adds to count as a way to buffer monster movement
        }
        // player bullet travel
        for (int bullet = 0; bullet < playingField.playerBullets.size(); bullet++){
          Projectile currentBullet = playingField.playerBullets.get(bullet);
          currentBullet.addToCount();
          if (currentBullet.getCount() % 1000 == 0){ // 1000 is the buffer for bullet movement. the higher the buffer, the slower the bullet and vice versa.
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
          // player portal to new floor
          if(key.getCharacter() == 'p' && bob.getX() == exit.getX() && bob.getY() == exit.getY()) { // makes sure player is standing on the portal
            if(playingField.currentFloor.getEnemies().size() == 0){ // player killed all enemies
              if (playingField.currentFloor.getLevel() == 1){
                Floor levelTwo = new Floor(2);
                try{ // reads in text file to make a floor
                  File f = new File("LevelTwo.txt");
                  Scanner in = new Scanner(f);
                  while(in.hasNext()){
                    String line = in.nextLine();
                    String[] lvlTwoWallCord = line.split(",");
                    levelTwo.addWall(Integer.parseInt(lvlTwoWallCord[0]), Integer.parseInt(lvlTwoWallCord[1]));
                  }
                }
                catch(FileNotFoundException e){
                  e.printStackTrace();
                }
                playingField.floor.add(levelTwo);
                lastKey = "p";
                playingField.changeLevel(2);
                for(int c = 0; c < 81; c ++){ // clears screen for new walls
                  for(int r = 0; r < 25; r ++){
                    terminal.moveCursor(c,r);
                    terminal.putCharacter(' ');
                  }
                }
                // adds floor two walls
                for (int currentwall = 0; currentwall < playingField.floor.get(1).getBorder().size(); currentwall ++){
                  terminal.moveCursor(playingField.floor.get(1).getBorder().get(currentwall).getX(), playingField.floor.get(1).getBorder().get(currentwall).getY());
                  terminal.putCharacter('\u25fb');
                }
                terminal.moveCursor(bob.getX(),bob.getY());
                terminal.putCharacter(' ');
                bob.setX(4);
                bob.setY(20); // repositions player to valid location
                terminal.moveCursor(bob.getX(),bob.getY());
                terminal.putCharacter('\u0040');
                terminal.moveCursor(exit.getX(), exit.getY());
                terminal.putCharacter(' ');
                exit.setX(78);
                exit.setY(7); // repositions portal to valid location
                terminal.moveCursor(exit.getX(),exit.getY());
                terminal.putCharacter('\u06DE');
                // adds level two monsters to ArrayList of enemies
                levelTwo.addMonster(2500,18,7,1,2);
                levelTwo.addMonster(2500,14,4,1,2);
                levelTwo.addMonster(2500,40,4,1,3);
                levelTwo.addMonster(2500,29,15,1,2);
                levelTwo.addMonster(2500,47,22,1,3);
                levelTwo.addMonster(2500,67,18,1,2);
                levelTwo.addMonster(2500,29,6,1,2);
                levelTwo.addMonster(2500,41,3,1,3);
                levelTwo.addMonster(2500,47,18,1,2);
                levelTwo.addMonster(2500,53,21,1,2);
                levelTwo.addMonster(2500,40,4,1,2);
                levelTwo.addMonster(2500,49,14,1,3);
                levelTwo.addMonster(2500,70,5,1,2);
                levelTwo.addMonster(2500,65,13,1,3);
              }
              else{
                Floor levelThree = new Floor(3);
                try{ // reads text file for level 3
                  File ff = new File("LevelThree.txt");
                  Scanner in = new Scanner(ff);
                  while(in.hasNext()){
                    String line = in.nextLine();
                    String[] lvlThreeWallCord = line.split(",");
                    levelThree.addWall(Integer.parseInt(lvlThreeWallCord[0]), Integer.parseInt(lvlThreeWallCord[1]));
                  }
                }
                catch(FileNotFoundException e){
                  e.printStackTrace();
                }
                playingField.floor.add(levelThree);
                playingField.changeLevel(3);
                for(int c = 0; c < 81; c ++){ // clears screen
                  for(int r = 0; r < 25; r ++){
                    terminal.moveCursor(c,r);
                    terminal.putCharacter(' ');
                  }
                }
                // adds lv 3 walls
                for (int currentwall = 0; currentwall < playingField.floor.get(2).getBorder().size(); currentwall ++){
                  terminal.moveCursor(playingField.floor.get(2).getBorder().get(currentwall).getX(), playingField.floor.get(2).getBorder().get(currentwall).getY());
                  terminal.putCharacter('\u25fb');
                }
                terminal.moveCursor(bob.getX(),bob.getY());
                terminal.putCharacter(' ');
                bob.setX(4);
                bob.setY(20); // repositions player
                terminal.moveCursor(bob.getX(),bob.getY());
                terminal.putCharacter('\u0040');
                terminal.moveCursor(exit.getX(), exit.getY());
                terminal.putCharacter(' ');
                exit.setX(58);
                exit.setY(13); // repositions portal
                terminal.moveCursor(exit.getX(),exit.getY());
                terminal.putCharacter('\u06DE');
                // adds level 3 monsters to ArrayList of enemies
                levelThree.addMonster(2500,18,5,1,3);
                levelThree.addMonster(2500,24,8,1,2);
                levelThree.addMonster(2500,53,3,1,2);
                levelThree.addMonster(2500,61,4,1,3);
                levelThree.addMonster(2500,63,13,1,2);
                levelThree.addMonster(2500,72,10,1,3);
                levelThree.addMonster(2500,67,15,1,2);
                levelThree.addMonster(2500,37,21,1,2);
                levelThree.addMonster(2500,40,23,1,3);
                levelThree.addMonster(2500,34,9,1,2);
                levelThree.addMonster(2500,44,16,1,2);
                levelThree.addMonster(2500,40,10,1,3);
                levelThree.addMonster(2500,50,8,1,2);
                levelThree.addMonster(2500,30,14,1,2);
                levelThree.addMonster(2500,39,13,1,3);
                /*
                this is the boss
                it needs to be hit 30 times by bullets to die
                it has an extra long range
                */
                levelThree.addMonster(30000,38,12,1,6);
              }
            }
            if (playingField.currentFloor.getLevel() == 3 && playingField.currentFloor.getEnemies().size() == 0) {
              // if player presses p on final portal and they killed all monsters, they will have won
              mode = "win";
            }
          }
          // player attack
          if (key.getCharacter() == 'w'){ // shoots projectile up
            Projectile bullet = new Projectile(bob.getX(), bob.getY(), bob.getDamage(), "up");
            playingField.playerBullets.add(bullet);
          }
          if (key.getCharacter() == 'a'){// shoots projectile left
            Projectile bullet = new Projectile(bob.getX(), bob.getY(), bob.getDamage(), "left");
            playingField.playerBullets.add(bullet);
          }
          if (key.getCharacter() == 's'){// shoots projectile down
            Projectile bullet = new Projectile(bob.getX(), bob.getY(), bob.getDamage(), "down");
            playingField.playerBullets.add(bullet);
          }
          if (key.getCharacter() == 'd'){// shoots projectile right
            Projectile bullet = new Projectile(bob.getX(), bob.getY(), bob.getDamage(), "right");
            playingField.playerBullets.add(bullet);
          }
          screen.refresh();
        }
      }
      if (mode.equals("win")) { // player has won the game! Congrats. the game exits on its own.
        screen.stopScreen();
        running = false;
        System.out.println("YOU WIN!!!!!!!!!!!!!!!!!!!");
      }
      if (mode.equals("lose")) { // the player has lost! try again! the game exits on its own.
        screen.stopScreen();
        running = false;
        System.out.println("YOU LOSE :(");
      }
    }
  }
}
/*
if you are reading this I hope u have a beautiful day :)))))))))))))))
*/
