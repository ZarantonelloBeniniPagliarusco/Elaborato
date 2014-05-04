package view;
import model.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Questa classe crea la damiera e i vari componenti della finestra quali
 * label di stato, menu a tendina e alcune variabili statiche utilizzate da altre classi.
 */

@SuppressWarnings("serial")
public class Board extends JFrame {

    public static JLabel text;
    public JButton reset;
    public static Pedina[][] casella = new Pedina[8][8];
    public static boolean selected;
    public JPanel scacchiera;
    private Container box = getContentPane();

    private JMenu menu;
    private JMenuItem newGame;
    private JMenuItem about;
    private JMenuItem patta;
    private JMenuItem salva;
    private JMenuItem carica;
    public static Color black = new Color(97,45,0);
    public static Color green = new Color(140,255,0);
    public static Color yellow = new Color(247,255,87);
    public static Color red = new Color(255,55,0);
    public static Color cyan = new Color(110,255,255);

    private JMenuBar bar = new JMenuBar();

    public static int pedine1, pedine2;
    public static int pedine11, pedine12;

   
    public Board() {

	super("Dama italiana");
	setSize(700,700);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	scacchiera = new JPanel();
	pedine1=12;
	pedine2=12;
	pedine11=0;
	pedine12=0;
	menu = new JMenu("Partita");
	newGame = new JMenuItem("Nuova partita");
	newGame.addActionListener(new newGameListener());
	
	about = new JMenuItem("About Dama");
	about.addActionListener(new aboutListener());
	
	patta = new JMenuItem("Proponi patta");
	patta.addActionListener(new pattaListener());

	salva = new JMenuItem("Salva");
	salva.addActionListener(new salvaListener());
	
	carica = new JMenuItem("Carica");
	carica.addActionListener(new caricaListener());
	
	menu.add(newGame);
	menu.add(salva);
	menu.add(carica);
	menu.add(patta);
	menu.addSeparator();
	menu.add(about);
	bar.add(menu);
	box.setLayout(new BorderLayout());


	this.setJMenuBar(bar);
	drawBoard();
	drawMenu();

	setVisible(true);

    }



    private void drawMenu() {
	/**
	 * Disegna il menu
	 * 
	 */
	reset = new JButton();
	text = new JLabel("Muovi una pedina.", JLabel.CENTER);
	text.setFont(new Font(null, Font.BOLD, 20));
	box.add(text, BorderLayout.SOUTH);

    }





    private void drawBoard() {  
	/**
	 * Disegna la scacchiera
	 * 
	 */
	scacchiera.setLayout(new GridLayout(8,8));


	for(int i = 0; i < 8; i++) {
	    for(int j = 0; j < 8; j++) {

		casella[i][j] = new Pedina(i,j);
		scacchiera.add(casella[i][j]);

	    }

	}

	box.add(scacchiera, BorderLayout.CENTER);

    }
    public static void annulla(){  
	/**
	 * Questo metodo serve a riportare la scacchiera alle condizioni di inizio 
	 * 
	 */
	pedine1 = 12;
	pedine2 = 12;
	for(int i = 0; i < 8; i++) 
	    for(int j = 0; j < 8; j++){
		casella[i][j].setBackground(black); //imposta la pedina 0 senza icona
		casella[i][j].setPlayer(0);
		if((i + j) % 2 != 0) 
		    casella[i][j].setBackground(Color.WHITE);
		if((i+j)%2==0){
		    if(i<3)
			casella[i][j].setPlayer(1);
		    else if(i>4)
			casella[i][j].setPlayer(2);}
		else casella[i][j].setPlayer(0);


	    }

    }

    public Pedina getCasella(int i, int j){
	/**
	 * restituisce la casella indicata
	 * @param Riga e colonna
	 * @return casella[riga][colonna]
	 */
	return casella[i][j];
    }

    public boolean getSel(){
	/**
	 * restituisce la variabile selected
	 * 
	 * @return selected
	 */
	return selected;
    }


    public static void solvedChecker() {
	/**
	 * Controlla se uno dei giocatori ha vinto e mostra una finestra di vittoria/sconfitta
	 * 
	 */
	if(!ancoraMossePossibili1()) {
	    JOptionPane.showMessageDialog(null, "Hai vinto!", "Vittoria", JOptionPane.INFORMATION_MESSAGE, new ImageIcon ("Images/win.png"));
	    annulla();
	}
	else if(!ancoraMossePossibili2()) {
	    JOptionPane.showMessageDialog(null, "Hai perso", "Sconfitta", JOptionPane.INFORMATION_MESSAGE, new ImageIcon ("Images/lose.png"));
	    annulla();
	}

    }

    private static boolean ancoraMossePossibili1() {
	/**
	 * Controlla se il computer ha ancora mosse possibili
	 * @param nessuno
	 * @return true || false
	 */
	for(int i = 0; i < 8; i++) 
	    for(int j = 0; j < 8; j++){
		if(i+1 <= 7 && j+1 <= 7) 
		    if((casella[i][j].getPlayer() == 1 || casella[i][j].getPlayer() == 11) && casella[i+1][j+1].getPlayer() == 0)
			return true;
		if(i+1 <= 7 && j-1 >= 0)
		    if((casella[i][j].getPlayer() == 1 || casella[i][j].getPlayer() == 11) && casella[i+1][j-1].getPlayer() == 0)
			return true;
		if(i-1 >= 0 && j+1 <= 7) 
		    if(casella[i][j].getPlayer() == 11 && casella[i-1][j+1].getPlayer() == 0)
			return true;
		if(i-1 >= 0 && j-1 >= 0)
		    if(casella[i][j].getPlayer() == 11 && casella[i-1][j-1].getPlayer() == 0)
			return true;

	    }
	return false;
    }

    private static boolean ancoraMossePossibili2() {
	/**
	 * Controlla se il giocatore ha ancora mosse possibili
	 * 
	 * @return true || false
	 */
	for(int i = 0; i < 8; i++) 
	    for(int j = 0; j < 8; j++){
		if(i-1 >= 0 && j+1 <= 7) 
		    if((casella[i][j].getPlayer() == 2 || casella[i][j].getPlayer() == 12) && casella[i-1][j+1].getPlayer() == 0)
			return true;
		if(i-1 >= 0 && j-1 >= 0)
		    if((casella[i][j].getPlayer() == 2 || casella[i][j].getPlayer() == 12) && casella[i-1][j-1].getPlayer() == 0)
			return true;
		if(i+1 <= 7 && j+1 <= 7) 
		    if(casella[i][j].getPlayer() == 12 && casella[i+1][j+1].getPlayer() == 0)
			return true;
		if(i+1 <= 7 && j-1 >= 0)
		    if(casella[i][j].getPlayer() == 12 && casella[i+1][j-1].getPlayer() == 0)
			return true;


	    }
	return false;
    }

    private class newGameListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    annulla();
	    patta.setEnabled(true);
	}


    }

    private class aboutListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    JOptionPane.showMessageDialog(null, "Gioco della dama italiana\n\tBy Zarantonello Gionata & Benini Alberto & Pagliarusco Luca for UNiVR 2014\nCopyright all rights reserved", "About Dama", JOptionPane.INFORMATION_MESSAGE);
	}
    }

    private class salvaListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    try {
		write();
	    } catch(IOException ex) {
		JOptionPane.showMessageDialog(null, "Impossibile salvare la partita corrente", "Errore di salvataggio", JOptionPane.ERROR_MESSAGE);

	    }
	}
    }

    private class caricaListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    try {
		load();
	    } catch(IOException ex) {
		JOptionPane.showMessageDialog(null, "Non ci sono partite salvate!", "Errore di caricamento", JOptionPane.ERROR_MESSAGE);

	    }
	}
    }

    private class pattaListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {

	    if(((pedine1>=pedine2 && pedine11>pedine12) || (pedine1<pedine2 && pedine11>=pedine12)))
		JOptionPane.showMessageDialog(null, "Computer non ha accettato la partita patta.", "NO", JOptionPane.INFORMATION_MESSAGE);
	    else{
		JOptionPane.showMessageDialog(null, "Computer ha accettato la partita patta.", "OK", JOptionPane.INFORMATION_MESSAGE);
		annulla();
	    }
	}
    }
    
    private static void write () throws IOException{
	BufferedWriter outputWriter = null;
	outputWriter = new BufferedWriter(new FileWriter("state.txt"));
	for (int i = 0; i < 8; i++) {
	    for(int j = 0; j < 8; j++) {

		outputWriter.write(Integer.toString(casella[i][j].getPlayer()));

		outputWriter.newLine();
	    }
	}
	outputWriter.flush();  
	outputWriter.close();  
	text.setText("Partita salvata.");
    }

    private static void load() throws IOException {
	Scanner s = new Scanner(new File("state.txt"));
	int player;
	pedine1=0;
	pedine2=0;
	pedine11=0;
	pedine12=0;
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {

		player = s.nextInt();
		casella[i][j].setPlayer(player);
		if(player == 1)
		    pedine1++;
		if(player == 2)
		    pedine2++;
		if(player == 11)
		    pedine11++;
		if(player == 12)
		    pedine12++;
	    }
	}
	text.setText("Partita caricata.");
	s.close();
    }
}
