package model;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JOptionPane;


import view.*;
import controller.*;

/**
 * 
 * 
 * Classe per creare l'oggetto Pedina utilizzato nell'array di Board. Contiene i vari metodi di selezione,
 * mangiata e mossa per gestire le pedine della damiera.
 */

@SuppressWarnings("serial")
public class Pedina extends JButton {
    private int player; //0 = vuoto, 1 = CPU, 2 = giocatore
    public static Board board;
    int rig, col;
    public MoveChecker moveChecker = new MoveChecker();
    public MoveMaker moveMaker = new MoveMaker(this);
    public Eater eater = new Eater(this);
    public AI ai = new AI();


    public Pedina(int i, int j) {  

	if((i+j) % 2 == 0) {
	    this.setBackground(Board.black);

	    rig = i;
	    col = j;
	    ActionListener boardListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

		    if(!Board.selected)  
			moveChecker.select(rig, col);
		    else 				
			moveMaker.muovi(rig, col);

		    Board.solvedChecker();

		   }
	    };
	    addActionListener(boardListener);

	    if(i < 3) {  
		setPlayer(1);
	    }
	    if(i >= 5) {
		setPlayer(2);
	    }
	}

	else {
	    this.setBackground(Color.WHITE);
	    this.setEnabled(false);
	}
	setVisible(true);
    }

    public void setPlayer(int player){
	/**
	 * Setta l'icona in una determinata casella
	 * @param player
	 */
	this.player = player;
	if(player==01)
	    setIcon(new ImageIcon("Images/pedina1.png"));
	else if(player==02)
	    setIcon(new ImageIcon("Images/pedina2.png"));
	else if(player==11)
	    setIcon(new ImageIcon("Images/pedina11.png"));
	else if(player==12)
	    setIcon(new ImageIcon("Images/pedina12.png"));
	else
	    setIcon(null);

    }

    public final int getPlayer() {
	/**
	 * Restituisce il giocatore
	 * 
	 * @return player
	 */
	return player;
    }


    public final void select(String color) {
	/**
	 * Colora una casella
	 * @param String color
	 */
	if(color=="green")
	    setBackground(Board.green);
	else if(color=="yellow")
	    setBackground(Board.yellow);
	else if(color=="red")
	    setBackground(Board.red);
	else if(color=="cyan")
	    setBackground(Board.cyan);
    }



    public final boolean isSelected() {
	/**
	 * Ritorna vero se una casella è selezionata
	 * 
	 * @return true || false
	 */
	return getBackground() == Board.green || getBackground() == Board.yellow || getBackground() == Board.red || getBackground() == Board.cyan ;
    }

    public final void unselect() {
	/**
	 * Se una casella è selezionata la deseleziona
	 * 
	 */
	if(isSelected())
	    setBackground(Board.black);
    }



}
