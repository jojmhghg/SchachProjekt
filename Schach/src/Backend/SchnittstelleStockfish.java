/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author Müller_Admin
 */
public class SchnittstelleStockfish {
    
    public void stockfishEngine(Spielbrett spielbrett) {
        Stockfish client = new Stockfish();
        String FEN = spielbrett.gibStringStockfish();
        System.out.println(FEN);
        // initialize and connect to engine 
        if (client.startEngine()) { 
         //System.out.println("Engine has started.."); 
        } 
        else{ 
         //System.out.println("Oops! Something went wrong.."); 
        } 

        // send commands manually 
        client.sendCommand("uci"); 

        // receive output dump 
        client.getOutput(0); 

        // get the best move for a position with a given think time 
        System.out.println("Best move : " + client.getBestMove(FEN, 100)); 

        // get all the legal moves from a given position 
        //System.out.println("Legal moves : " + client.getLegalMoves(FEN)); 

        // draw board from a given position 
        //System.out.println("Board state :"); 
        //client.drawBoard(FEN); 

        // get the evaluation score of current position 
        //System.out.println("Evaluation score : " + client.getEvalScore(FEN, 2000)); 

        // stop the engine 
        //System.out.println("Stopping engine.."); 
        //client.stopEngine(); 
   } 
}
