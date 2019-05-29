/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author archimede
 */
public class Forza4 {
    private int[][] grid;
    private String lastPlayer = "O"; // X O
    private int coins = 0;
    
    public Forza4(){
        this.grid = new int[6][7];
    }

    @Override
    public String toString() {
        String finalString = "";
        for(int i=grid.length -1; i>=0; i--){
            for(int j=0; j<7; j++){
                finalString += grid[i][j] + "|";
            }
            finalString += "\n";
        }
        return finalString;
    }
    
    public boolean addCoin(int column, String player) throws Exception{
        // if(column > 6 || column < 0) return false;
        if (this.grid[5][column] != 0) return false;
        if (player.equals(this.lastPlayer)) return false;
        if (!"X".equals(player) && !"O".equals(player)) throw new Exception("Valore player errato");
        if (coins == 21) return false;
        for(int i=0; i<6; i++){
            if (this.grid[i][column] != 0) continue;
            if (player.equals("X")) {
                    this.grid[i][column] = 1;
            } else {
                this.grid[i][column] = 5;
            }
            break;
        }
        this.lastPlayer = player;
        this.coins++;
        return this.rowWin() || this.columnWin() || this.diagonalWin(); 
    }
    
    
    private boolean rowWin() {
        for(int x=0; x<6; x++){
            for(int y=0; y<4; y++){
                int sum = grid[x][y] + grid[x][y+1] + grid[x][y+2] + grid[x][y+3];
                if(sum == 20 || sum == 4) return true;
            }
        }
        return false;
    }
    
    private boolean columnWin() {
        for(int y=0; y<7; y++){
            for(int x=0; x<3; x++){
                int sum = grid[x][y] + grid[x+1][y] + grid[x+2][y] + grid[x+3][y];
                if(sum == 20 || sum == 4) return true;
            }
        }
        return false;
    }
    
    private boolean diagonalWin() {
        for(int x=0; x<3; x++){
            for(int y=0; y<4; y++){
                int sum1 = grid[3 + x][y] + grid[2 + x][1 + y] + grid[1 + x][2 + y] + grid[x][3 + y];
                int sum2 = grid[x][3 + y] + grid[1 + x][2 + y] + grid[2 + x][1 + y] + grid[3 + x][y];
                if(sum1 == 20 || sum1 == 4 || sum2 == 20 || sum2 == 4) return true;
            }
        }
        return false;
    }
    
    public String nextPlayer(){
        if ("X".equals(this.lastPlayer)) return "O";
        return "X";
    }
    
}
