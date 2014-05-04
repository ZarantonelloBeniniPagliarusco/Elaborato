package controller;
import model.*;

/*import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;
import java.awt.Color;*/

import view.Board;

/**
 * 
 * 
 * Questa classe si occupa di fare le mosse delle pedine
 */

public class MoveMaker {

      Pedina pedina;
    

    public MoveMaker(Pedina pedina){
	this.pedina=pedina;
    }


    public void muovi(int rig, int col) {

	/**
	 * Se non ho da fare mangiate, questo metodo esegue lo spostamento di pedina
	 * Dopo una mia mossa chiamo l'intelligienza artificiale
	 * @param riga e colonna
	 */


	pedina.eater.doEat(rig, col);


	if((rig-1) >= 0 && (col-1) >= 0)
	    if(Board.casella[rig][col].getPlayer()==0 && Board.casella[rig-1][col-1].getPlayer()==1 && Board.casella[rig-1][col-1].isSelected()) { 
		Board.casella[rig][col].setPlayer(1);
		Board.casella[rig-1][col-1].setPlayer(0);
		damaCheck(rig,col);
		unselectAll();
		moveAI();
		return;

	    }

	if((rig-1) >= 0 && (col+1) <= 7)
	    if(Board.casella[rig][col].getPlayer() == 0 && Board.casella[rig-1][col+1].getPlayer() == 1 && Board.casella[rig-1][col+1].isSelected()) {
		Board.casella[rig][col].setPlayer(1);
		Board.casella[rig-1][col+1].setPlayer(0);
		damaCheck(rig,col);
		unselectAll();
		moveAI();
		return;

	    }

	if((rig+1) <= 7 && (col+1) <= 7)
	    if(Board.casella[rig][col].getPlayer() == 0 && Board.casella[rig+1][col+1].getPlayer() == 2 && Board.casella[rig+1][col+1].isSelected()) {
		Board.casella[rig][col].setPlayer(2);
		Board.casella[rig+1][col+1].setPlayer(0);
		damaCheck(rig,col);
		unselectAll();
		moveAI();
		return;

	    }

	if((rig+1) <= 7 && (col-1) >= 0)
	    if(Board.casella[rig][col].getPlayer() == 0 && Board.casella[rig+1][col-1].getPlayer() == 2 && Board.casella[rig+1][col-1].isSelected()) {
		Board.casella[rig][col].setPlayer(2);
		Board.casella[rig+1][col-1].setPlayer(0);
		damaCheck(rig,col);
		unselectAll();
		moveAI();

		return;

	    }

	if((rig+1) <= 7 && (col-1) >= 0)
	    if(Board.casella[rig][col].getPlayer() == 0 && Board.casella[rig+1][col-1].getPlayer() == 2 && Board.casella[rig+1][col-1].isSelected()) {
		Board.casella[rig][col].setPlayer(2);
		Board.casella[rig+1][col-1].setPlayer(0);
		damaCheck(rig,col);
		unselectAll();
		moveAI();

		return;

	    }
	    
	muoviD(rig,col); //se arriviamo a questo punto, vuol dire che abbiamo selezionato un damone
	Board.selected = false;
	pedina.moveChecker.select(rig,col);

    }



    public void muoviD(int rig, int col) {

	/**
	 * Se non ho mangiate da fare, questo metodo esegue lo spostamento di un damone
	 * Dopo una mia mossa chiamo l'intelligienza artificiale
	 * @param riga e colonna
	 */

	pedina.eater.doEat(rig, col);
	Board.text.setText("Muovi una pedina.");

	if(Board.casella[0][col].getPlayer() == 12) {
	    try {

		if(Board.casella[rig][col].getPlayer() == 0 && Board.casella[rig-1][col+1].getPlayer() == 12 && Board.casella[rig-1][col+1].isSelected()) {
		    Board.casella[rig][col].setPlayer(12);
		    Board.casella[rig-1][col+1].setPlayer(0);
		    unselectAll();
		    moveAI();
		    return;


		}



		else if(Board.casella[rig][col].getPlayer() == 0 && Board.casella[rig-1][col-1].getPlayer() == 12 && Board.casella[rig-1][col-1].isSelected()) {
		    Board.casella[rig][col].setPlayer(12);
		    Board.casella[rig-1][col-1].setPlayer(0);
		    unselectAll();
		    moveAI();
		    return;

		}
	    } 
	    catch(ArrayIndexOutOfBoundsException e) {
	    }
	}

	if((rig+1) <= 7 && (col+1) <= 7)
	    if(Board.casella[rig][col].getPlayer() == 0 && Board.casella[rig+1][col+1].getPlayer() == 12 && Board.casella[rig+1][col+1].isSelected()) {
		Board.casella[rig][col].setPlayer(12);
		Board.casella[rig+1][col+1].setPlayer(0);
		unselectAll();
		moveAI();
		return;
	    }

	if((rig+1) <= 7 && (col-1) >= 0)
	    if(Board.casella[rig][col].getPlayer() == 0 && Board.casella[rig+1][col-1].getPlayer() == 12 && Board.casella[rig+1][col-1].isSelected()) {
		Board.casella[rig][col].setPlayer(12);
		Board.casella[rig+1][col-1].setPlayer(0);
		unselectAll();
		moveAI();
		return;
	    }



	if((rig-1) >= 0 && (col-1) >= 0)
	    if(Board.casella[rig][col].getPlayer() == 0 && Board.casella[rig-1][col-1].getPlayer() == 12 && Board.casella[rig-1][col-1].isSelected()) {
		Board.casella[rig][col].setPlayer(12);
		Board.casella[rig-1][col-1].setPlayer(0);
		unselectAll();
		moveAI();
		return;
	    }

	if((rig-1) >= 0 && (col+1) <= 7)
	    if(Board.casella[rig][col].getPlayer() == 0 && Board.casella[rig-1][col+1].getPlayer() == 12 && Board.casella[rig-1][col+1].isSelected()) {
		Board.casella[rig][col].setPlayer(12);
		Board.casella[rig-1][col+1].setPlayer(0);
		unselectAll();
		moveAI();
		return;
	    }

	Board.selected = false;
	pedina.moveChecker.select(rig,col);

    }




    public void unselectAll() {

	/**
	 * Deseleziona tutte le caselle sulla scacchiera
	 * 
	 */
	for(int i = 0; i < 8; i++)
	    for(int j = 0; j < 8; j++) {
		if(Board.casella[i][j].isSelected())
		    Board.casella[i][j].unselect();
	    }
    }

    public void damaCheck(int rig, int col) {
	/**
	 * Controlla se una pedina a seguito di una mossa � diventata dama
	 * @param riga e colonna
	 */
	if(rig == 0) {
	    Board.casella[rig][col].setPlayer(12);
	    Board.selected = true;
	    Board.pedine12++;
	    Board.pedine2--;
	}
    }

    private void moveAI() {
	/**
	 * Chiama l'IA, inserito temporizzatore per rendere "percepibili" le mosse del pc
	 * 
	 */
	Board.text.setText("E' il turno dell'avversario");

	new java.util.Timer().schedule( 
		new java.util.TimerTask() {
		    @Override
		    public void run() {
			pedina.ai.moveAI();		
		    }
		}, 
		500 
		);
    }
}
