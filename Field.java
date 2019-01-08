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

public class Field{
  public static void main(String[] args) {
    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();
    boolean running = true;
    Player bob = new Player(100, 10, 10, 2);
    while (running){
      terminal.moveCursor(bob.getX(),bob.getY());
      terminal.putCharacter(bob.getCharacter());
      Key key = terminal.readInput();
      terminal.setCursorVisible(false);
      if (key != null){
        if (key.getKind() == Key.Kind.Escape){
          terminal.exitPrivateMode();
          running = false;
        }
        if (key.getKind() == Key.Kind.ArrowUp){
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(' ');
          bob.move("up");
          terminal.moveCursor(bob.getX(),bob.getY());
          terminal.putCharacter(bob.getCharacter());
        }
      }
    }
  }
}
