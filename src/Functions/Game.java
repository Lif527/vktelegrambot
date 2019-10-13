package Functions;

import java.io.IOException;

public interface Game {
    public boolean isPlaying();
    public void gameIteration(String text);
    public String startedText() throws IOException;
    public void startGame();
}
