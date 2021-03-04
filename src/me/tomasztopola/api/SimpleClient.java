package me.tomasztopola.api;

import me.tomasztopola.utils.BoardGenerator;
import me.tomasztopola.utils.Location;

import java.util.Random;

public abstract class SimpleClient implements BattleShipClient{

    private final int[][] board;

    public SimpleClient(){
        BoardGenerator boardGenerator = new BoardGenerator();
        boardGenerator.generate();
        board = boardGenerator.getBoard();
    }

    @Override
    public int getFloatingShips() {
        int floatingShips = 0;
        for(int[] row : board){
            for(int ship : row){
                floatingShips += ship;
            }
        }
        return floatingShips;
    }

    @Override
    public void receiveAttack(Location target) {
        board[target.getY()][target.getX()] = 0;
    }

    @Override
    public String getBotName() {
        return this.getClass().getName();
    }

    @Override
    public abstract Location attack(ClientData opponent);
}