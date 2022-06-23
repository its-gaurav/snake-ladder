package models;

import java.util.*;

public class SnakeAndLadderArena {

    private List<Ladder> ladders;
    private List<Snake> snakes;
    private Map<String, Integer> playerPieces; // tells current position of each player

    private Integer arenaSize;

    public SnakeAndLadderArena(Integer arenaSize, List<Ladder> ladders,
                               List<Snake> snakes, LinkedHashMap<String, Integer> playerPieces) {
        this.arenaSize = arenaSize;
        this.ladders = ladders;
        this.snakes = snakes;
        this.playerPieces = playerPieces;
    }

    public SnakeAndLadderArena(Integer arenaSize) {
        this.arenaSize = arenaSize;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public Map<String, Integer> getPlayerPieces() {
        return playerPieces;
    }

    public void setPlayerPieces(Map<String, Integer> playerPieces) {
        this.playerPieces = playerPieces;
    }

    public Integer getArenaSize() {
        return arenaSize;
    }

    public void setArenaSize(Integer arenaSize) {
        this.arenaSize = arenaSize;
    }
}
