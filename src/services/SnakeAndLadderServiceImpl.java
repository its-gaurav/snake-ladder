package services;

import models.Ladder;
import models.Player;
import models.Snake;
import models.SnakeAndLadderArena;

import java.util.*;

public class SnakeAndLadderServiceImpl implements SnakeAndLadderService{

    private SnakeAndLadderArena snakeAndLadderArena;
    private Queue<Player> players;

    private Integer INITIAL_PLAYER_COUNT;

    private final Integer DEFAULT_ARENA_SIZE = 100;

    public SnakeAndLadderServiceImpl(int boardSize) {
        snakeAndLadderArena = new SnakeAndLadderArena(boardSize);
        this.players = new LinkedList<>();
    }

    private Integer rollDice() {
        return new Random().nextInt(6)+1;
    }

    public Queue<Player> getPlayers() {
        return players;
    }

    private Integer getNewPositionAfterGoingThroughSnakesAndLaddersForGivenDiceValue(Player player, Integer rolledDiceValue) {
        Integer currentPosition = snakeAndLadderArena.getPlayerPieces().get(player.getId());
        Integer newPosition = currentPosition + rolledDiceValue;
        if(newPosition.compareTo(snakeAndLadderArena.getArenaSize())>0){ // if newPosition crosses arena size, then player won't move
            return currentPosition;
        }
        Integer pastPosition;
        do{
            pastPosition = newPosition;
            List<Snake> snakes = snakeAndLadderArena.getSnakes();
            for(Snake snake: snakes){
                if(snake.getHead().equals(newPosition)){
                    newPosition = snake.getTail();
                }
            }
            List<Ladder> ladders = snakeAndLadderArena.getLadders();
            for(Ladder ladder: ladders){
                if(ladder.getStart().equals(newPosition)){
                    newPosition = ladder.getEnd();
                }
            }
        }while(!pastPosition.equals(newPosition));

        return newPosition;
    }

    private Integer moveToNextPosition(Player player, Integer rolledDiceValue) {
        Integer nextPosition = this.getNewPositionAfterGoingThroughSnakesAndLaddersForGivenDiceValue(player, rolledDiceValue);
        Integer oldPosition = snakeAndLadderArena.getPlayerPieces().get(player.getId());
        snakeAndLadderArena.getPlayerPieces().put(player.getId(), nextPosition);
        System.out.println("Player "+ player.getName() +" has rolled a "+rolledDiceValue+" and moved from "+oldPosition+" to "+nextPosition);
        return nextPosition;
    }

    @Override
    public void startGame() {
        Map<String, Integer> playerPieces = snakeAndLadderArena.getPlayerPieces();
        boolean hasWon = false;
        while (!isGameCompleted()){
            Player currentPlayer = players.remove();
            Integer rolledDiceValue = this.rollDice();
            Integer nextPosition = this.moveToNextPosition(currentPlayer, rolledDiceValue);
            if(hasPlayerWon(currentPlayer)){
                System.out.println("Player " + currentPlayer.getName() + " has won");
                snakeAndLadderArena.getPlayerPieces().remove(currentPlayer.getId());
            }else{
                players.add(currentPlayer);
            }
        }
    }
    // if any player has left the arena, consider it to be completed
    private boolean isGameCompleted() {
        return INITIAL_PLAYER_COUNT.compareTo(this.getPlayers().size())>0;
    }

    private boolean hasPlayerWon(Player currentPlayer) {
        Integer winningPosition = snakeAndLadderArena.getArenaSize();
        Integer playerPosition = snakeAndLadderArena.getPlayerPieces().get(currentPlayer.getId());
        return winningPosition.equals(playerPosition);
    }

    @Override
    public void setPlayers(List<Player> players) {
        INITIAL_PLAYER_COUNT = players.size();
        this.players = new LinkedList<Player>();
        Map<String, Integer> playerPieces = new HashMap<>();
        for(Player player: players){
            this.players.add(player);
            playerPieces.put(player.getId(), 0); // Each player would be at 0 at start of the game
        }
        snakeAndLadderArena.setPlayerPieces(playerPieces);

    }

    @Override
    public void setSnakes(List<Snake> snakes) {
        snakeAndLadderArena.setSnakes(snakes);
    }

    @Override
    public void setLadders(List<Ladder> ladders) {
        snakeAndLadderArena.setLadders(ladders);
    }
}
