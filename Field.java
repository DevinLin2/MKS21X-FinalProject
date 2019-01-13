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

public class Field{
  /**
   * ArrayList of different floors each a different level
   */
  private ArrayList<Floor> floor;
  /**
   * Constructs a Field which initiates the Arraylist of floors.
   * The floors will each hold an unique ArrayList of walls.
   */
  public Field(){
    floor = new ArrayList<Floor>();
    Floor levelOne = new Floor(1);
    levelOne.addWall(0,0);
    levelOne.addWall(1,0);
    levelOne.addWall(2,0);
    levelOne.addWall(3,0);
    levelOne.addWall(4,0);
    levelOne.addWall(5,0);
    levelOne.addWall(6,0);
    levelOne.addWall(20,20);
    levelOne.addWall(20,21);
    levelOne.addWall(20,22);
    levelOne.addWall(20,23);
    levelOne.addWall(20,24);
    levelOne.addWall(20,25);
    levelOne.addWall(20,26);
    levelOne.addWall(20,27);
    floor.add(levelOne);
  }
  public static void main(String[] args) {
    Player bob = new Player(100, 10, 10, 2);
    Terminal terminal = TerminalFacade.createTextTerminal();
    Screen screen = new Screen(terminal);
    Field playingField = new Field();
    screen.startScreen();
    screen.putString(1,3,"Health: " + bob.getHealth(), Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
    for (int floorLevel = 0; floorLevel < playingField.floor.size(); floorLevel++){
      Floor current = playingField.floor.get(floorLevel);
      for (int currentWall = 0; currentWall < current.getBorder().size(); currentWall++){
        terminal.moveCursor(current.getBorder().get(currentWall).getX(),current.getBorder().get(currentWall).getY());
        terminal.putCharacter(current.getBorder().get(currentWall).getLogo());
      }
    }
    screen.refresh();
    boolean running = true;
    while (running){
      terminal.moveCursor(bob.getX(),bob.getY());
      terminal.putCharacter(bob.getCharacter());
      Key key = terminal.readInput();
      terminal.setCursorVisible(false);
      if (key != null){
        if (key.getKind() == Key.Kind.Escape){
          screen.stopScreen();
          running = false;
        }
        if (key.getKind() == Key.Kind.ArrowUp){ // also check if player will be in boundary of walls.
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(' ');
          bob.move("up");
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(bob.getCharacter());
        }
        if (key.getKind() == Key.Kind.ArrowDown){
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(' ');
          bob.move("down");
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(bob.getCharacter());
        }
        if (key.getKind() == Key.Kind.ArrowLeft){
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(' ');
          bob.move("left");
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(bob.getCharacter());
        }
        if (key.getKind() == Key.Kind.ArrowRight){
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(' ');
          bob.move("right");
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(bob.getCharacter());
        }
      }
    }
  }
}
