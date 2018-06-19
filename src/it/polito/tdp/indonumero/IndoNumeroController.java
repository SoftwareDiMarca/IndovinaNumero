/**
 * Sample Skeleton for 'IndoNumero.fxml' Controller Class
 */

package it.polito.tdp.indonumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {
	
	private int NMAX = 100;
	private int TMAX = 7;
	
	
	private int segreto;		//numero da indovinare
	private int tentativi;		//tentativi già fatti
	
	private boolean inGame = false; //stato del gioco settato a partita non iniziata (false)

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="bottoneNuovaPartita"
    private Button bottoneNuovaPartita; // Value injected by FXMLLoader

    @FXML // fx:id="numTentativi"
    private TextField numTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="tentativiTotali"
    private TextField tentativiTotali; // Value injected by FXMLLoader

    @FXML // fx:id="boxDiGioco"
    private HBox boxDiGioco; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="bottoneProva"
    private Button bottoneProva; // Value injected by FXMLLoader

    @FXML // fx:id="textRisposta"
    private TextArea textRisposta; // Value injected by FXMLLoader

    @FXML
    void handleNuovaPartita(ActionEvent event) {
    	
    	this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativi = 0;
    	this.inGame = true;
    	
    	bottoneNuovaPartita.setDisable(true);
    	textRisposta.setDisable(false);
    	boxDiGioco.setDisable(false);
    	numTentativi.setText(String.format("%d", this.tentativi));
    	tentativiTotali.setText(String.format("%d", this.TMAX)); 
    	textRisposta.clear();
    	txtTentativo.clear();
    	textRisposta.appendText(String.format("Indovina un numero tra %d e %d\n", 1, NMAX));
    }


    @FXML
    void handleProva(ActionEvent event) {
    	
    	String numString = txtTentativo.getText();
    	
		if(numString.length() == 0) {
			textRisposta.appendText("Devi inserire un numero\n");
    		return;
    	}
		
		try {		
			int num = Integer.parseInt(numString);  //se non è numero lancia un'eccezione NumberFormatException
			if(num == segreto) {
				//ha indovinato
				textRisposta.appendText("Hai vinto!\n");
				//chiudi la partita
				boxDiGioco.setDisable(true);
				bottoneNuovaPartita.setDisable(false);
				inGame = false;
			}
			else {
				//tentativo errato
				tentativi++;
				numTentativi.setText(String.format("%d", this.tentativi));
				if(this.tentativi == this.TMAX) {
					//ha perso
					textRisposta.appendText(String.format("Hai perso! Il numero era: %d\n", this.segreto));
					boxDiGioco.setDisable(true);
					bottoneNuovaPartita.setDisable(false);
					inGame = false;
				}
				else {
					//sono ancora in gioco
					if(num < segreto) {
						//troppo basso
						textRisposta.appendText("Troppo basso\n");
					}
					else {
						//troppo alto
						textRisposta.appendText("troppo alto\n");
					}
				}
			}
		} catch(NumberFormatException ex) {			
			textRisposta.appendText("Il dato inserito non è numerico\n");
			return;
		}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert bottoneNuovaPartita != null : "fx:id=\"bottoneNuovaPartita\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert numTentativi != null : "fx:id=\"numTentativi\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert tentativiTotali != null : "fx:id=\"tentativiTotali\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert boxDiGioco != null : "fx:id=\"boxDiGioco\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert bottoneProva != null : "fx:id=\"bottoneProva\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert textRisposta != null : "fx:id=\"textRisposta\" was not injected: check your FXML file 'IndoNumero.fxml'.";

    }
}

	





	




