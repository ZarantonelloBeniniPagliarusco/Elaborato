package model;
import view.*;

/**
 * 
 * 
 * Questa classe si occupa dell'intelligenza artificiale
 */

public class AI {
    
    private static int check=0;
    private static boolean AIHasMoved = false;


    public AI() {
	
    }

    public void moveAI() {
	/**
	 * Questo metodo chiama i vari metodi per le mosse del computer, se ne eseguo uno non eseguo gli altri
	 */
	
	if(check==0)
	    damaMangia();
	if(check==0)
	    pedinaMangia();
	if(check==0)
	    muoviADamone();
	if(check == 0)
	    pedinaScappa();
	if(check==0)
	    mossaPedinaSicura();
	if(check==0)
	    mossaDamoneSicura();
	if(check==0)
	    mossaDamoneNonSicura();
	if(check==0)
	    mossaPedina1();
	if(check==0)
	    mossaPedina2();
	if(check!=0)
	    AIHasMoved=true;
	check=0;
	unselectAll(); //se ha mangiato la pedina che era selezionata, deselezioniamo tutto
	Board.text.setText("Muovi una pedina.");
	if(AIHasMoved)
	    Board.solvedChecker();
	AIHasMoved = false;
    }


    private void damaCheckAI(int i, int j) {
	/**
	 * Controlla se una pedina deve diventare damone a seguito di una mossa
	 * @param riga e colonna
	 */
	if(Board.casella[7][j].getPlayer() == 1) {
	    Board.casella[7][j].setPlayer(11);
	    Board.pedine11++;
	    Board.pedine1--;

	}
    }

    private void damaMangia() {
	/**
	 * Controlla se un damone pu� mangiare
	 */
	for(int rig = 0; rig < 8; rig++)
	    for(int col = 0; col < 8; col++) {
		//priorit� alla mangiata

		//controllo damone per mangiare un damone sotto

		if((rig+2 <= 7) && (col+2 <= 7) && (rig+1 <= 7) && (col+1 <= 7))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig+1][col+1].getPlayer() == 12 && Board.casella[rig+2][col+2].getPlayer() == 0) {  //mangio a destra
			Board.casella[rig][col].setPlayer(0);
			Board.casella[rig+1][col+1].setPlayer(0);
			Board.casella[rig+2][col+2].setPlayer(11);
			Board.pedine12--;
			check=1;
			mangiaAncoraAI(rig+2,col+2);
			return;
		    }
		if((rig+2 <= 7) && (col - 2 >= 0) && (rig+1 <= 7) && (col-1 >= 0))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig+1][col-1].getPlayer() == 12 && Board.casella[rig+2][col-2].getPlayer() == 0) {  //mangio a sinistra
			Board.casella[rig][col].setPlayer(0);
			Board.casella[rig+1][col-1].setPlayer(0);
			Board.casella[rig+2][col-2].setPlayer(11);
			Board.pedine12--;
			check=1;
			mangiaAncoraAI(rig+2,col-2);
			return;
		    }
		//controllo damone per mangiare un damone sopra

		if((rig-2 >= 0) && (col+2 <= 7) && (rig-1 >= 0) && (col+1 <= 7))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig-1][col+1].getPlayer() == 12 && Board.casella[rig-2][col+2].getPlayer() == 0) {  //mangio a dx
			Board.casella[rig][col].setPlayer(0);
			Board.casella[rig-1][col+1].setPlayer(0);
			Board.casella[rig-2][col+2].setPlayer(11);
			Board.pedine12--;
			check=1;
			mangiaAncoraAI(rig-2,col+2);
			return;

		    }
		if((rig-2 >= 0) && (col - 2 >= 0) && (rig-1 >= 0) && (col-1 >= 0))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig-1][col-1].getPlayer() == 12 && Board.casella[rig-2][col-2].getPlayer() == 0) {  //mangio a sx
			Board.casella[rig][col].setPlayer(0);
			Board.casella[rig-1][col-1].setPlayer(0);
			Board.casella[rig-2][col-2].setPlayer(11);
			Board.pedine12--;
			check=1;
			mangiaAncoraAI(rig-2,col-2);
			return;
		    }

		//controllo damone per mangiare una pedina sotto
		if((rig+2 <= 7) && (col+2 <= 7) && (rig+1 <= 7) && (col+1 <= 7))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig+1][col+1].getPlayer() == 2 && Board.casella[rig+2][col+2].getPlayer() == 0) {  //dx
			Board.casella[rig][col].setPlayer(0);
			Board.casella[rig+1][col+1].setPlayer(0);
			Board.casella[rig+2][col+2].setPlayer(11);
			Board.pedine2--;
			check=1;
			mangiaAncoraAI(rig+2,col+2);
			return;
		    }
		if((rig+2 <= 7) && (col - 2 >= 0) && (rig+1 <= 7) && (col-1 >= 0))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig+1][col-1].getPlayer() == 2 && Board.casella[rig+2][col-2].getPlayer() == 0) {  //sx
			Board.casella[rig][col].setPlayer(0);
			Board.casella[rig+1][col-1].setPlayer(0);
			Board.casella[rig+2][col-2].setPlayer(11);
			Board.pedine2--;
			check=1;
			mangiaAncoraAI(rig+2,col-2);
			return;
		    }
		//controllo damone per mangiare una pedina sopra
		if((rig-2 >= 0) && (col+2 <= 7) && (rig-1 >= 0) && (col+1 <= 7))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig-1][col+1].getPlayer() == 2 && Board.casella[rig-2][col+2].getPlayer() == 0) {
			Board.casella[rig][col].setPlayer(0);
			Board.casella[rig-1][col+1].setPlayer(0);
			Board.casella[rig-2][col+2].setPlayer(11);
			Board.pedine2--;
			check=1;
			mangiaAncoraAI(rig-2,col+2);
			return;
		    }
		if((rig-2 >= 0) && (col - 2 >= 0) && (rig-1 >= 0) && (col-1 >= 0))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig-1][col-1].getPlayer() == 2 && Board.casella[rig-2][col-2].getPlayer() == 0) {
			Board.casella[rig][col].setPlayer(0);
			Board.casella[rig-1][col-1].setPlayer(0);
			Board.casella[rig-2][col-2].setPlayer(11);
			Board.pedine2--;
			check=1;
			mangiaAncoraAI(rig-2,col-2);
			return;
		    }
	    }
    }

    private void pedinaMangia() {
	/**
	 * Controlla se una pedina pu� mangiare
	 * Se fa mangiata chiama AIMangiataConsecutiva per vedere se si possono fare mangiate multiple
	 */
	for(int rig = 0; rig < 8; rig++)
	    for(int col = 0; col < 8; col++) {

		//controllo pedina per mangiare pedina sotto
		if((rig+2 <= 7) && (col+2 <= 7) && (rig+1 <= 7) && (col+1 <= 7))
		    if(Board.casella[rig][col].getPlayer() == 1 && Board.casella[rig+1][col+1].getPlayer() == 2 && Board.casella[rig+2][col+2].getPlayer() == 0) {
			Board.casella[rig][col].setPlayer(0);
			Board.casella[rig+1][col+1].setPlayer(0);
			Board.casella[rig+2][col+2].setPlayer(1);
			Board.pedine2--;
			check=1;
			damaCheckAI(rig+2,col+2);
			mangiaAncoraAI(rig+2,col+2);
			return;
		    }
		if((rig+2 <= 7) && (col - 2 >= 0) && (rig+1 <= 7) && (col-1 >= 0))
		    if(Board.casella[rig][col].getPlayer() == 1 && Board.casella[rig+1][col-1].getPlayer() == 2 && Board.casella[rig+2][col-2].getPlayer() == 0) {
			Board.casella[rig][col].setPlayer(0);
			Board.casella[rig+1][col-1].setPlayer(0);
			Board.casella[rig+2][col-2].setPlayer(1);
			Board.pedine2--;
			check=1;
			damaCheckAI(rig+2,col-2);
			mangiaAncoraAI(rig+2,col-2);
			return;
		    }
	    }

    }

    private void mangiaAncoraAI(int rig, int col) {
	/**
	 * Controlla se una pedina che ha gi� mangiato pu� farlo nuovamete
	 * @param riga e colonna
	 */
	damaCheckAI(rig,col);

	if(Board.casella[rig][col].getPlayer() == 1) {

	    if((rig+1) <= 7 && (col-1) >= 0 && (rig+2) <= 7 && (col-2) >= 0)
		if(Board.casella[rig+1][col-1].getPlayer() == 2 && Board.casella[rig+2][col-2].getPlayer() == 0) {
		    Board.casella[rig][col].setPlayer(0);
		    Board.casella[rig+1][col-1].setPlayer(0);
		    Board.casella[rig+2][col-2].setPlayer(1);
		    mangiaAncoraAI(rig+2,col-2);
		    return;
		}

	    if((rig+1) <= 7 && (col+1) <= 7 && (rig+2) <= 7 && (col+2) <= 7)
		if(Board.casella[rig+1][col+1].getPlayer() == 2 && Board.casella[rig+2][col+2].getPlayer() == 0) {
		    Board.casella[rig][col].setPlayer(0);
		    Board.casella[rig+1][col+1].setPlayer(0);
		    Board.casella[rig+2][col+2].setPlayer(1);
		    mangiaAncoraAI(rig+2,col+2);

		    return;
		}

	}

	if(Board.casella[rig][col].getPlayer() == 11) {

	    if((rig+1) <= 7 && (col-1) >= 0 && (rig+2) <= 7 && (col-2) >= 0)
		if(Board.casella[rig+1][col-1].getPlayer() == 2 && Board.casella[rig+2][col-2].getPlayer() == 0) {
		    Board.casella[rig][col].setPlayer(0);
		    Board.casella[rig+1][col-1].setPlayer(0);
		    Board.casella[rig+2][col-2].setPlayer(11);
		    mangiaAncoraAI(rig+2,col-2);

		    return;
		}

	    if((rig+1) <= 7 && (col+1) <= 7 && (rig+2) <= 7 && (col+2) <= 7)

		if(Board.casella[rig+1][col+1].getPlayer() == 2 && Board.casella[rig+2][col+2].getPlayer() == 0) {
		    Board.casella[rig][col].setPlayer(0);
		    Board.casella[rig+1][col+1].setPlayer(0);
		    Board.casella[rig+2][col+2].setPlayer(11);
		    mangiaAncoraAI(rig+2,col+2);

		    return;
		}

	}

	if((rig-1) >= 0 && (col-1) >= 0 && (rig-2) >= 0 && (col-2) >= 0)


	    if(Board.casella[rig-1][col-1].getPlayer() == 12 && Board.casella[rig-2][col-2].getPlayer() == 0) {
		Board.casella[rig][col].setPlayer(0);
		Board.casella[rig-1][col-1].setPlayer(0);
		Board.casella[rig-2][col-2].setPlayer(11);
		mangiaAncoraAI(rig-2,col-2);

		return;
	    }

	if((rig-1) >= 0 && (col+1) <= 7 && (rig-2) >= 0 && (col+2) <= 7)

	    if(Board.casella[rig-1][col+1].getPlayer() == 12 && Board.casella[rig-2][col+2].getPlayer() == 0) {
		Board.casella[rig][col].setPlayer(0);
		Board.casella[rig-1][col+1].setPlayer(0);
		Board.casella[rig-2][col+2].setPlayer(11);
		mangiaAncoraAI(rig-2,col+2);

		return;


	    }

    }

    private void muoviADamone() {
	/**
	 * Muove un pedina per farla andare a damone
	 */
	for(int rig = 0; rig < 8; rig++)
	    for(int col = 0; col < 8; col++) {
		//priorit� a fare il damone
		if((col-1) >= 0)
		    if(Board.casella[7][col].getPlayer() == 0 && Board.casella[6][col-1].getPlayer() == 1) {
			sposta(1,6,col-1,7,col);

			return;
		    }

		if((col+1) <= 7)
		    if(Board.casella[7][col].getPlayer() == 0 && Board.casella[6][col+1].getPlayer() == 1) {
			sposta(1,6,col+1,7,col);

			return;
		    }
	    }
    }


    private void mossaDamoneSicura() {
	/**
	 * Muove un damone in una posizione sicura
	 */
	for(int rig = 0; rig < 8; rig++)
	    for(int col = 0; col < 8; col++) {
		//priorit� a 2 celle libere sotto
		//priorit� alle mosse del damone

		if((rig-2 >= 0) && (col - 2 >= 0) && (rig-1 >= 0) && (col-1 >= 0))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig-1][col-1].getPlayer() == 0 && Board.casella[rig-2][col-2].getPlayer() == 0) {
			sposta(11,rig,col,rig-1,col-1);
			return;
		    }

		if((rig-2 >= 0) && (col + 2 <= 7) && (rig-1 >= 0) && (col+1 <= 7))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig-1][col+1].getPlayer() == 0 && Board.casella[rig-2][col+2].getPlayer() == 0) {
			sposta(11,rig,col,rig-1,col+1);
			return;
		    }


		if((rig+2 <= 7) && (col - 2 >= 0) && (rig+1 <= 7) && (col-1 >= 0))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig+1][col-1].getPlayer() == 0 && Board.casella[rig+2][col-2].getPlayer() == 0) {
			sposta(11,rig,col,rig+1,col-1);
			return;
		    }

		if((rig+2 <= 7) && (col + 2 <= 7) && (rig+1 <= 7) && (col+1 <= 7))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig+1][col+1].getPlayer() == 0 && Board.casella[rig+2][col+2].getPlayer() == 0) {
			sposta(11,rig,col,rig+1,col+1);
			return;
		    }

		if((rig-1) == 0 && (col-1) >= 0 && Board.casella[rig][col].getPlayer() == 11 && Board.casella[0][col-1].getPlayer() == 0 ){
		    sposta(11, rig, col, 0, col-1); //sposto in bordo alto a sinistra
		    return;
		}

		if((rig-1) == 0 && (col+1) <= 7 && Board.casella[rig][col].getPlayer() == 11 && Board.casella[0][col+1].getPlayer() == 0 ){
		    sposta(11, rig, col, 0, col+1); //sposto in bordo alto a destra
		    return;
		}

		if((col-1) == 0 && (rig-1) >= 0 && Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig-1][0].getPlayer() == 0 ){
		    sposta(11, rig, col, rig-1, 0);	//sposto bordo sinistro in alto
		    return;
		}

		if((col-1) == 0 && (rig+1) <= 7 && Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig+1][0].getPlayer() == 0 ){
		    sposta(11, rig, col, rig-1, 0);	 //sposto bordo sinistro in basso
		    return;
		}

		if((col+1) == 7 && (rig-1) >= 0 && Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig-1][7].getPlayer() == 0 ){
		    sposta(11, rig, col, rig-1, 7);	//sposto bordo destro in alto
		    return;
		}

		if((col+1) == 7 && (rig+1) <= 7 && Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig-1][0].getPlayer() == 0 ){
		    sposta(11, rig, col, rig+1, 7);	//sposto bordo sinistro in alto
		    return;
		}

		if((rig+1) == 7 && (col-1) >= 0 && Board.casella[rig][col].getPlayer() == 11 && Board.casella[7][col-1].getPlayer() == 0 ){
		    sposta(11, rig, col, 7, col-1); //sposto in bordo basso a sinistra
		    return;
		}

		if((rig+1) == 7 && (col+1) <= 7 && Board.casella[rig][col].getPlayer() == 11 && Board.casella[7][col+1].getPlayer() == 0 ){
		    sposta(11, rig, col, 7, col+1); //sposto in bordobasso a destra
		    return;
		}

	    }
    }

    private void mossaPedinaSicura() {
	/**
	 * Muovo una pedina in una posizione sicura
	 */
	for(int rig = 0; rig < 8; rig++)
	    for(int col = 0; col < 8; col++) {

		if(rig != 0 && (rig+2 <= 7) && (col - 2 >= 0) && (rig+1 <= 7) && (col-1 >= 0))
		    if(Board.casella[rig][col].getPlayer() == 1 && Board.casella[rig+1][col-1].getPlayer() == 0 && (Board.casella[rig+2][col-2].getPlayer() != 2 || Board.casella[rig+2][col-2].getPlayer() != 12) && (Board.casella[rig+2][col].getPlayer()!=2 || Board.casella[rig+2][col].getPlayer()!=12) && Board.casella[rig][col-2].getPlayer() != 12) {
			sposta(1,rig,col,rig+1,col-1);
			return;
		    }

		if(rig != 0 && (rig+2 <= 7) && (col + 2 <= 7) && (rig+1 <= 7) && (col+1 <= 7))
		    if(Board.casella[rig][col].getPlayer() == 1 && Board.casella[rig+1][col+1].getPlayer() == 0 && (Board.casella[rig+2][col+2].getPlayer() != 2 || Board.casella[rig+2][col+2].getPlayer() != 12) && (Board.casella[rig+2][col].getPlayer()!=2 || Board.casella[rig+2][col].getPlayer()!=12) && Board.casella[rig][col+2].getPlayer() != 12) {
			sposta(1,rig,col,rig+1,col+1);
			return;
		    }


	    }
    }

    private void mossaDamoneNonSicura() {
	/**
	 * Muovo un damone in una posizione non sicura
	 */
	for(int rig = 0; rig < 8; rig++)
	    for(int col = 0; col < 8; col++) {
		//priorit� a 1 cella libera sotto
		//damone
		if((rig-2 >= 0) && (col - 2 >= 0) && (rig-1 >= 0) && (col-1 >= 0))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig-1][col-1].getPlayer() == 0) {
			sposta(11,rig,col,rig-1,col-1);
			return;
		    }

		if((rig-2 >= 0) && (col + 2 <= 7) && (rig-1 >= 0) && (col+1 <= 7))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig-1][col+1].getPlayer() == 0) {
			sposta(11,rig,col,rig-1,col+1);
			return;
		    }


		if((rig+2 <= 7) && (col - 2 >= 0) && (rig+1 <= 7) && (col-1 >= 0))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig+1][col-1].getPlayer() == 0) {
			sposta(11,rig,col,rig+1,col-1);
			return;
		    }

		if((rig+2 <= 7) && (col + 2 <= 7) && (rig+1 <= 7) && (col+1 <= 7))
		    if(Board.casella[rig][col].getPlayer() == 11 && Board.casella[rig+1][col+1].getPlayer() == 0) {
			sposta(11,rig,col,rig+1,col+1);
			return;
		    }
	    }
    }

    private void mossaPedina2() {
	/**
	 * Muovo una pedina in una posizione non sicura
	 */

	for(int rig = 0; rig < 8; rig++)
	    for(int col = 0; col < 8; col++) {

		if((rig+1 <= 7) && (col+1 <= 7) && (col-2 >= 0))
		    if(Board.casella[rig][col-2].getPlayer() == 1 &&  Board.casella[rig][col].getPlayer() == 1 && Board.casella[rig+1][col+1].getPlayer() == 0) {
			sposta(1,rig,col,rig+1,col+1);
			return;
		    }
		if((rig+1 <= 7) && (col-1 >= 0) && (col+2 <= 7))
		    if(Board.casella[rig][col+2].getPlayer() == 1 && Board.casella[rig][col].getPlayer() == 1 && Board.casella[rig+1][col-1].getPlayer() == 0) {
			sposta(1,rig,col,rig+1,col-1);
			return;
		    }

		if((rig+1 <= 7) && (col+1 <= 7))
		    if(Board.casella[rig][col].getPlayer() == 1 && Board.casella[rig+1][col+1].getPlayer() == 0) {
			sposta(1,rig,col,rig+1,col+1);
			return;
		    }
		if((rig+1 <= 7) && (col-1 >= 0))
		    if(Board.casella[rig][col].getPlayer() == 1 && Board.casella[rig+1][col-1].getPlayer() == 0) {
			sposta(1,rig,col,rig+1,col-1);
			return;
		    }




	    }
    }

    private void mossaPedina1() {
	/**
	 * Muovo una pedina
	 */
	for(int rig = 0; rig < 8; rig++)
	    for(int col = 0; col < 8; col++) {

		if(rig != 0 && (rig+1 <= 7) && (col+1 <= 7) && (col-2 >= 0))
		    if(Board.casella[rig][col-2].getPlayer() == 1 &&  Board.casella[rig][col].getPlayer() == 1 && Board.casella[rig+1][col+1].getPlayer() == 0) {
			sposta(1,rig,col,rig+1,col+1);
			return;
		    }
		if(rig != 0 && (rig+1 <= 7) && (col-1 >= 0) && (col+2 <= 7))
		    if(Board.casella[rig][col+2].getPlayer() == 1 && Board.casella[rig][col].getPlayer() == 1 && Board.casella[rig+1][col-1].getPlayer() == 0) {
			sposta(1,rig,col,rig+1,col-1);
			return;
		    }

		if(rig != 0 && (rig+1 <= 7) && (col+1 <= 7))
		    if(Board.casella[rig][col].getPlayer() == 1 && Board.casella[rig+1][col+1].getPlayer() == 0) {
			sposta(1,rig,col,rig+1,col+1);
			return;
		    }
		if(rig != 0 && (rig+1 <= 7) && (col-1 >= 0))
		    if(Board.casella[rig][col].getPlayer() == 1 && Board.casella[rig+1][col-1].getPlayer() == 0) {
			sposta(1,rig,col,rig+1,col-1);
			return;
		    }




	    }

    }


    private void pedinaScappa() {
	/**
	 * Faccio spostare una pedina che verrebbe mangiata il turno dopo, solo se spostandosi non si mette in condizione di essere mangiata 
	 */

	for(int rig = 0; rig < 8; rig++)
	    for(int col = 0; col < 8; col++) {
		if((rig+1 <= 7) && (col-1 >= 0) && (rig+2 <= 7) && (col+2 <= 7) && (col+1 <= 7)) {

		    if(Board.casella[rig][col].getPlayer() == 1 /*&& Board.casella[rig+1][col-1].getPlayer() == 2 /*&& Board.casella[rig-1][col+1].getPlayer() == 0*/) 
			if(Board.casella[rig][col+2].getPlayer() != 0 && Board.casella[rig+2][col+2].getPlayer() != 2 && Board.casella[rig+1][col+1].getPlayer() == 0) {
			    sposta(1,rig,col,rig+1,col+1);
			    return;
			}
		}

		if((col+1 <= 7) && (rig-2 >= 0) && (col-2 >= 0) && (col-1 >= 0) && (rig+1 <= 7))
		    if((Board.casella[rig][col].getPlayer() == 1))
			if(Board.casella[rig+1][col-1].getPlayer() == 0 && Board.casella[rig+2][col].getPlayer() == 0 && Board.casella[rig][col-2].getPlayer() != 0 && Board.casella[rig+2][col-2].getPlayer() != 2) {
			    sposta(1,rig,col,rig+1,col-1);
			    return;
			}

	    }
    }


    private void sposta(int player, int fromRig, int fromCol, int toRig, int toCol) {
	/**
	 * Esegue lo spostamento della pedina
	 */
	if(Board.casella[fromRig][fromCol].getPlayer() == 1 || Board.casella[fromRig][fromCol].getPlayer() == 11)
	    if(Board.casella[toRig][toCol].getPlayer() == 0) {
		Board.casella[fromRig][fromCol].setPlayer(0);
		Board.casella[toRig][toCol].setPlayer(player);
		if(player == 1)
		    damaCheckAI(toRig,toCol);
		check = 1;
	    }
    }

    private void unselectAll() {
	/**
	 * Deseleziona ogni casella 
	 */
	for(int rig = 0; rig < 8; rig++)
	    for(int col = 0; col < 8; col++) 
		Board.casella[rig][col].unselect();

    }


}
