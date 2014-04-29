package controller;

import model.*;

//import java.awt.Color;

import view.*;

/**
 * 
 * 
 * Questa classe si occupa delle mangiate
 * 
 */

public class Eater {

    private Pedina pedina;

    public Eater(Pedina pedina){
	this.pedina = pedina;

    }

    public int selectMangia(int rig, int col) {

	/**
	 * Seleziona le caselle della mangiata
	 * @param riga e colonna
	 */

	if((rig-2) >= 0 && (col-2) >= 0)
	    if(((Board.casella[rig][col].getPlayer() == 2  || Board.casella[rig][col].getPlayer() == 12 )&& (Board.casella[rig-1][col-1].getPlayer() == 1)  && Board.casella[rig-2][col-2].getPlayer() == 0)  || (Board.casella[rig][col].getPlayer() == 12 && Board.casella[rig-1][col-1].getPlayer() == 11 && Board.casella[rig-2][col-2].getPlayer() == 0)) { //mangiamo a sx con giocatore2
		Board.casella[rig][col].select("green");
		Board.casella[rig-1][col-1].select("red");
		Board.casella[rig-2][col-2].select("yellow");
		return 1;
	    }

	if((rig-2) >= 0 && (col+2) <= 7)
	    if(((Board.casella[rig][col].getPlayer() == 2 || Board.casella[rig][col].getPlayer() == 12) && (Board.casella[rig-1][col+1].getPlayer() == 1) && Board.casella[rig-2][col+2].getPlayer() == 0) || (Board.casella[rig][col].getPlayer() == 12 && Board.casella[rig-1][col+1].getPlayer() == 1 && Board.casella[rig-2][col+2].getPlayer() == 0)) { //mangiamo a dx con giocatore2
		Board.casella[rig][col].select("green");
		Board.casella[rig-1][col+1].select("red");
		Board.casella[rig-2][col+2].select("yellow");
		return 2;
	    }

	if((rig-2) >= 0 && (col-2) >= 0)
	    if((Board.casella[rig][col].getPlayer() == 12 && Board.casella[rig-1][col-1].getPlayer() == 11  && Board.casella[rig-2][col-2].getPlayer() == 0)  || (Board.casella[rig][col].getPlayer() == 12 && Board.casella[rig-1][col-1].getPlayer() == 11 && Board.casella[rig-2][col-2].getPlayer() == 0)) { //mangiamo a sx con giocatore2
		Board.casella[rig][col].select("green");
		Board.casella[rig-1][col-1].select("red");
		Board.casella[rig-2][col-2].select("yellow");
		return 1;
	    }

	if((rig-2) >= 0 && (col+2) <= 7)
	    if((Board.casella[rig][col].getPlayer() == 12 && Board.casella[rig-1][col+1].getPlayer() == 11 && Board.casella[rig-2][col+2].getPlayer() == 0) || (Board.casella[rig][col].getPlayer() == 12 && Board.casella[rig-1][col+1].getPlayer() == 1 && Board.casella[rig-2][col+2].getPlayer() == 0)) { //mangiamo a dx con giocatore2
		Board.casella[rig][col].select("green");
		Board.casella[rig-1][col+1].select("red");
		Board.casella[rig-2][col+2].select("yellow");
		return 2;
	    }


	if((rig+2) <= 7 && (col-2) >= 0)
	    if((Board.casella[rig][col].getPlayer() == 12 && (Board.casella[rig+1][col-1].getPlayer() == 1 || Board.casella[rig+1][col-1].getPlayer() == 11) && Board.casella[rig+2][col-2].getPlayer() == 0)) { //mangiamo a sx con giocatore2
		Board.casella[rig][col].select("green");
		Board.casella[rig+1][col-1].select("red");
		Board.casella[rig+2][col-2].select("yellow");
		return 1;
	    }

	if((rig+2) <= 7 && (col+2) <= 7)
	    if((Board.casella[rig][col].getPlayer() == 12 && (Board.casella[rig+1][col+1].getPlayer() == 1 || Board.casella[rig+1][col+1].getPlayer() == 11) && Board.casella[rig+2][col+2].getPlayer() == 0)) { //mangiamo a sx con giocatore2
		Board.casella[rig][col].select("green");
		Board.casella[rig+1][col+1].select("red");
		Board.casella[rig+2][col+2].select("yellow");
		return 2;
	    }

	return 0;
    }


    public void doEat(int rig, int col) {
	/**
	 * Esegue la mangiata
	 * @param riga e colonna
	 */


	if((rig+1) <= 7 && (col-1) >= 0 && (rig+2) <= 7 && (col-2) >= 0)
	    if(Board.casella[rig][col].getPlayer()==0 && Board.casella[rig+1][col-1].getPlayer()==1 && Board.casella[rig+2][col-2].getPlayer() == 2 && Board.casella[rig+2][col-2].getBackground() == Board.green) {//mangio a destra
		Board.casella[rig][col].setPlayer(2);
		Board.casella[rig+1][col-1].setPlayer(0);
		Board.casella[rig+2][col-2].setPlayer(0);
		Board.pedine1--;
		Board.casella[rig][col].moveMaker.unselectAll();
		pedina.moveMaker.damaCheck(rig,col);
		if(selectMangia(rig, col)==0)
		    moveAI();
		return;

	    }

	if((rig+1) <= 7 && (col+1) <= 7 && (rig+2) <= 7 && (col+2) <= 7)
	    if(Board.casella[rig][col].getPlayer()==0 && Board.casella[rig+1][col+1].getPlayer()==1 && Board.casella[rig+2][col+2].getPlayer() == 2 && Board.casella[rig+2][col+2].getBackground() == Board.green) {//mangio a sinistra
		Board.casella[rig][col].setPlayer(2);
		Board.casella[rig+1][col+1].setPlayer(0);
		Board.casella[rig+2][col+2].setPlayer(0);
		Board.casella[rig][col].moveMaker.unselectAll();
		Board.pedine1--;	
		pedina.moveMaker.damaCheck(rig,col);
		if(selectMangia(rig, col)==0)
		    moveAI();
		return;
	    }






	//Mangio pedina con damone
	//in basso a sinistra
	if((rig+1) <= 7 && (col-1) >= 0 && (rig+2) <= 7 && (col-2) >= 0)
	    if(Board.casella[rig][col].getPlayer()==0 && Board.casella[rig+1][col-1].getPlayer()==1  && Board.casella[rig+2][col-2].getPlayer() == 12) {//mangio a destra
		Board.casella[rig][col].setPlayer(12);
		Board.casella[rig+1][col-1].setPlayer(0);
		Board.casella[rig+2][col-2].setPlayer(0);
		Board.pedine1--;	
		Board.casella[rig][col].moveMaker.unselectAll();
		if(selectMangia(rig, col)==0)
		    moveAI();
		return;
	    }

	//Mangio damone con damone
	//in basso a sinistra
	if((rig+1) <= 7 && (col-1) >= 0 && (rig+2) <= 7 && (col-2) >= 0)
	    if(Board.casella[rig][col].getPlayer()==0 && Board.casella[rig+1][col-1].getPlayer()==11 && Board.casella[rig+2][col-2].getPlayer() == 12) {//mangio a destra
		Board.casella[rig][col].setPlayer(12);
		Board.casella[rig+1][col-1].setPlayer(0);
		Board.casella[rig+2][col-2].setPlayer(0);
		Board.pedine11--;	
		Board.casella[rig][col].moveMaker.unselectAll();
		if(selectMangia(rig, col)==0)
		    moveAI();
		return;
	    }

	//Mangio pedina con damone
	//in basso a destra


	if((rig+1) <= 7 && (col+1) <= 7 && (rig+2) <= 7 && (col+2) <= 7)
	    if(Board.casella[rig][col].getPlayer()==0 && Board.casella[rig+1][col+1].getPlayer()==1 && Board.casella[rig+2][col+2].getPlayer() == 12) {//mangio a sinistra
		Board.casella[rig][col].setPlayer(12);
		Board.casella[rig+1][col+1].setPlayer(0);
		Board.casella[rig+2][col+2].setPlayer(0);
		Board.pedine1--;	
		Board.casella[rig][col].moveMaker.unselectAll();
		if(selectMangia(rig, col)==0)
		    moveAI();
		return;
	    }

	//Mangio damone con damone
	//in basso a destra

	if((rig+1) <= 7 && (col+1) <= 7 && (rig+2) <= 7 && (col+2) <= 7)
	    if(Board.casella[rig][col].getPlayer()==0 && Board.casella[rig+1][col+1].getPlayer()==11 && Board.casella[rig+2][col+2].getPlayer() == 12) {//mangio a sinistra
		Board.casella[rig][col].setPlayer(12);
		Board.casella[rig+1][col+1].setPlayer(0);
		Board.casella[rig+2][col+2].setPlayer(0);
		Board.pedine11--;	
		Board.casella[rig][col].moveMaker.unselectAll();
		if(selectMangia(rig, col)==0)
		    moveAI();
		return;
	    }

	//Mangio pedina con damone
	//in alto a sinistra


	if((rig-1) >= 0 && (col-1) >= 0 && (rig-2) >= 0 && (col-2) >= 0)
	    if(Board.casella[rig][col].getPlayer()==0 && Board.casella[rig-1][col-1].getPlayer()==1 && Board.casella[rig-2][col-2].getPlayer() == 12) {//mangio a destra
		Board.casella[rig][col].setPlayer(12);
		Board.casella[rig-1][col-1].setPlayer(0);
		Board.casella[rig-2][col-2].setPlayer(0);
		Board.pedine1--;	
		Board.casella[rig][col].moveMaker.unselectAll();
		if(selectMangia(rig, col)==0)
		    moveAI();
		return;
	    }

	//mangio damone con damone
	//in alto a sinistra


	if((rig-1) >= 0 && (col-1) >= 0 && (rig-2) >= 0 && (col-2) >= 0)
	    if(Board.casella[rig][col].getPlayer()==0 && Board.casella[rig-1][col-1].getPlayer()==11 && Board.casella[rig-2][col-2].getPlayer() == 12) {//mangio a destra
		Board.casella[rig][col].setPlayer(12);
		Board.casella[rig-1][col-1].setPlayer(0);
		Board.casella[rig-2][col-2].setPlayer(0);
		Board.pedine11--;	
		Board.casella[rig][col].moveMaker.unselectAll();
		if(selectMangia(rig, col)==0)
		    moveAI();
		return;
	    }

	//mangio pedina con damone
	//in alto a destra


	if((rig-1) >= 0 && (col+1) <= 7 && (rig-2) >= 0 && (col+2) <= 7)
	    if(Board.casella[rig][col].getPlayer()==0 && Board.casella[rig-1][col+1].getPlayer()==1 && Board.casella[rig-2][col+2].getPlayer() == 12) {//mangio a destra
		Board.casella[rig][col].setPlayer(12);
		Board.casella[rig-1][col+1].setPlayer(0);
		Board.casella[rig-2][col+2].setPlayer(0);
		Board.pedine1--;	
		Board.casella[rig][col].moveMaker.unselectAll();
		if(selectMangia(rig, col)==0)
		    moveAI();
		return;
	    }

	//mangio damone con damone
	//in alto a destra

	if((rig-1) >= 0 && (col+1) <= 7 && (rig-2) >= 0 && (col+2) <= 7)
	    if(Board.casella[rig][col].getPlayer()==0 && Board.casella[rig-1][col+1].getPlayer()==11 && Board.casella[rig-2][col+2].getPlayer() == 12) {//mangio a destra
		Board.casella[rig][col].setPlayer(12);
		Board.casella[rig-1][col+1].setPlayer(0);
		Board.casella[rig-2][col+2].setPlayer(0);
		Board.pedine11--;	
		Board.casella[rig][col].moveMaker.unselectAll();
		if(selectMangia(rig, col)==0)
		    moveAI();
		return;
	    }


    }



    private void moveAI() {	
	/**
	 * Chiama l'IA, inserito temporizzatore per rendere "percepibili" le mosse del pc
	 * 
	 */
	//impostiamo un timer di un secondo prima che l'avversario muova
	Board.text.setText("E' il turno dell'avversario.");
	new java.util.Timer().schedule( 
		new java.util.TimerTask() {
		    @Override
		    public void run() {
			pedina.ai.moveAI();				            }
		}, 
		500 
		);
    }




}
