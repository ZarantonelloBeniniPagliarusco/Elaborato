package controller;

//import model.*;

//import java.awt.Color;

import view.*;

/**
 * 
 * 
 * Questa classe serve a controllare dove il giocatore può muovere
 */

public class MoveChecker {

    private int c=0;
    private int x = 0;

    public MoveChecker() {

    }


    public void select(int rig, int col) {

	/**
	 * Seleziona la casella in cui posso muovermi se non ho mangiate da fare
	 * @param riga e colonna
	 */

	scanSelected();

	if(x==1) {
	    Board.casella[rig][col].eater.selectMangia(rig, col);
	    x=0;
	    return;
	}

	if(Board.casella[rig][col].getPlayer() != 0) { //se la casella selezionata contiene una pedina & le sottostanti sono vuote
	    for(int i = 0; i < 8; i++) 
		for(int j = 0; j < 8; j++)	
		    if(Board.casella[i][j].eater.selectMangia(i,j) != 0) { 
			Board.text.setText("Devi mangiare la pedina avversaria.");
			Board.selected = true;
			c++; //variabile c rappresenta quante mangiate ci sono da fare nell'intera Board
		    }
	    if(c==1){
		c=0;
		return;
	    }

	    else if(c>1){
		c=0;
		for(int i = 0; i < 8; i++) 
		    for(int j = 0; j < 8; j++)	
			if(Board.casella[i][j].getBackground() == Board.green) {
			    //Board.casella[i][j].select("cyan");
			    Board.text.setText("Più di una mangiata disponibile. Scegline una.");
			    x=1;
			    select(rig,col);

			}
		return;
	    }
	    //se non ho mangiate possibili muovo		

	    if((rig-1) >= 0 && (col+1) <= 7) {
		if(Board.casella[rig][col].getPlayer() == 2 && Board.casella[rig-1][col+1].getPlayer() == 0) {
		    Board.casella[rig][col].select("green");
		    showMoves(rig,col);
		    Board.selected = true;
		} 

	    }


	    if((col-1) >= 0 && (rig-1) >= 0){
		if(Board.casella[rig][col].getPlayer() == 2 && Board.casella[rig-1][col-1].getPlayer() == 0) {
		    Board.casella[rig][col].select("green");
		    showMoves(rig,col);
		    Board.selected = true;

		}

	    }

	    if (Board.casella[rig][col].getPlayer() == 12) {
		Board.casella[rig][col].select("green");
		showMovesD(rig,col);
		Board.selected = true;
	    }



	}


    }






    private void showMoves(int rig, int col) { 

	/**
	 * Colora di giallo le caselle in cui posso spostarmi
	 * @param riga e colonna
	 */

	if((rig-1)>=0 && (col-1)>=0){
	    if(Board.casella[rig][col].getPlayer() == 2 && Board.casella[rig-1][col-1].getPlayer() == 0) { //caso 2: sopra a sx è libero
		Board.casella[rig-1][col-1].select("yellow");

		Board.selected = false;
	    }
	}



	if ((rig-1) >= 0 && (col+1) <= 7) {

	    if(Board.casella[rig][col].getPlayer() == 2 && Board.casella[rig-1][col+1].getPlayer() == 0) { //caso 3: sopra a dx è libero
		Board.casella[rig-1][col+1].select("yellow");

		Board.selected = false;
	    }


	}




    }

    private void showMovesD(int rig, int col) {

	/**
	 * Mostra le possibili mosse di un damone
	 * @param riga e colonna
	 */

	if((rig-1)>=0 && (col-1)>=0)
	    if(Board.casella[rig-1][col-1].getPlayer() == 0) { //caso 2: sopra a sx è libero
		Board.casella[rig-1][col-1].select("yellow");
		Board.selected = false;
	    }

	if ((rig-1) >= 0 && (col+1) <= 7) 		
	    if(Board.casella[rig-1][col+1].getPlayer() == 0) { //caso 3: sopra a dx è libero
		Board.casella[rig-1][col+1].select("yellow");
		Board.selected = false;

	    }


	if((rig+1) <= 7 && (col+1) <= 7)
	    if(Board.casella[rig+1][col+1].getPlayer() == 0) {
		Board.casella[rig+1][col+1].select("yellow");
		Board.selected = false;
	    }

	if((rig+1) <= 7 && (col-1) >= 0)
	    if(Board.casella[rig+1][col-1].getPlayer() == 0) {
		Board.casella[rig+1][col-1].select("yellow");
		Board.selected = false;
	    }

    }


    private void scanSelected() { 

	/**
	 * Controllo se ci sono altre caselle già selezionate, in quel caso le deseleziono
	 * @param nessuno
	 */

	for(int i = 0; i < 8; i++)
	    for(int j = 0; j < 8; j++) 

		Board.casella[i][j].unselect();

    }

}




