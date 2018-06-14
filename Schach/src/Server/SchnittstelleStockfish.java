/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author Müller_Admin
 */
public class SchnittstelleStockfish {
    Stockfish client = new Stockfish();
    
    public String stockfishEngine(String FEN) {     
        // initialize and connect to engine
        if (client.startEngine()) { 
        } 
        else{ 
        } 

        // send commands manually 
        client.sendCommand("uci"); 

        // receive output dump 
        client.getOutput(0); 
        
        // get the best move for a position with a given think time 
        String bestMove = client.getBestMove(FEN, 500);
        System.out.println(bestMove);
        client.stopEngine();
        
        return bestMove;
    } 
}
