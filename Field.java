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
    while (running){
      terminal.moveCursor(0,0);
      terminal.putCharacter('\u262d');
      Key key = terminal.readInput();

      if (key != null){
        if (key.getKind() == Key.Kind.Escape){
          terminal.exitPrivateMode();
          running = false;
        }
      }
    }
  }
}
