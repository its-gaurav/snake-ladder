package services;

import models.Ladder;
import models.Player;
import models.Snake;

import java.util.List;

public interface SnakeAndLadderService {

    void startGame();
    void setPlayers(List<Player> players);
    void setSnakes(List<Snake> snakes);
    void setLadders(List<Ladder> ladders);
}
