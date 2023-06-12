import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FenDemineur extends Stage
{
	// constantes et varibales du programme
	private static final int 	NB_MINES			= 10;
	private static final int	DIMENSION			= 10;
	private int 				nbCasesDecouvertes	= 0;
	private int 				nbClics				= 0;
	private int 				leTerrain[][]		= new int[DIMENSION][DIMENSION] ;
	private Font 				police 				= Font.font("Arial", FontWeight.BOLD, 16);
	Random rand = new Random();
	
	// les composants de la fenêtre
	private VBox 		racine 			= new VBox();
	private HBox		zoneMessage		= new HBox();
	private GridPane	grilleDemineur 	= new GridPane();
	private Label 		message			= new Label("Nb clics = ");
	private Label		lblNbClics		= new Label("0");
	private Button 		laMap[][] 	= new Button[DIMENSION][DIMENSION];
	
	public FenDemineur()
	{
		//init de la fenêtre
		this.setTitle("Démineur");
		this.setResizable(false);
		initTerrain();
		Scene scene = new Scene(creerContenu());
		this.setScene(scene);
		this.sizeToScene();
	}
	
	private Parent creerContenu() {
		//à compléter en construisant le Scene Graph
		zoneMessage.getChildren().addAll(message,lblNbClics);
		for (int i = 0 ; i < 10 ; i++) {
			for(int j = 0 ; j < 10 ; j++) {
				laMap[i][j] = new Button();
				laMap[i][j].setOnAction(e -> gererClic(e));
				laMap[i][j].setOnMouseClicked(e -> gererClicDroit(e));
				grilleDemineur.add(laMap[i][j],j,i);
			}
		}
		
		racine.getChildren().addAll(zoneMessage,grilleDemineur);
		return racine;
	}

	
	private void initTerrain() {
		//placer les mines aléatoirement
		int cpt_mines = 0;
		
		while (cpt_mines < 10) {
			int colAleatoire = rand.nextInt(10);
			int ligneAleatoire = rand.nextInt(10);
			if (leTerrain[colAleatoire][ligneAleatoire] != -1) {
				leTerrain[colAleatoire][ligneAleatoire] = -1;
		}
		}
		
		// calculer les valeurs des autres cases (=les cases "non minées")
		
		for (int i = 0 ; i < 10 ; i++) {
			for(int j = 0 ; j < 10 ; j++) {
				int cpt = 0;
				if (i > 0 && j > 0 && leTerrain[i-1][j-1] == -1) {
					cpt++;
				}
				if (j > 0 && leTerrain[i][j-1] == -1) {
					cpt++;
				}
				if ((i < DIMENSION -1) && leTerrain[i+1][j-1] == -1) {
					cpt++;
				}
				if ((i > 0 && leTerrain[i-1][j] == -1)) {
					cpt++;
				}
				if ((i < DIMENSION -1) && leTerrain[i+1][j] == -1) {
					cpt++;
				}
				if (i > 0 && (j < DIMENSION -1) && leTerrain[i-1][j+1] == -1) {
					cpt++;
				}
				if ((j < DIMENSION -1) && leTerrain[i][j+1] == -1) {
					cpt++;
				}
				if ((i < DIMENSION -1 ) && (j < DIMENSION -1) && leTerrain[i+1][j+1] == -1) {
					cpt++;
				}
				leTerrain[i][j] = cpt;
				
			}
		}
		
	}
	
	private void decouvrir(int x, int y) {
		/* méthode récursive :
		 *		teste si la case (x,y) existe
		 *		puis teste si elle n'est pas déjà découverte (=si le Button correspondant est actif)
		 *		puis si sa valeur est 0, il faut la découvrir ainsi que ses 8 voisins éventuels
		 *			 sinon il faut juste la découvrir
		 */
		
		if ((x>=0 && x < DIMENSION) && (y>=0 && y < DIMENSION)) {
			if (!laMap[x][y].isDisabled()) {
				if (leTerrain[x][y] == 0) {
					laMap[x][y].setDisable(true);
					laMap[x][y].setText("");
					decouvrir(x-1,y-1);
					decouvrir(x,y-1);
					decouvrir(x+1,y-1);
					decouvrir(x-1,y);
					decouvrir(x+1,y);
					decouvrir(x-1,y+1);
					decouvrir(x,y+1);
					decouvrir(x+1,y+1);
				} else {
					laMap[x][y].setDisable(true);
					laMap[x][y].setText("" + leTerrain[x][y]);
				}
				nbCasesDecouvertes++;
			}
		}
		
		
		
	}
	
	private void afficherLesBombes() {
		// en cas de défaite, affiche le contenu du tableau :
		// les 0 ne sont pas affichés et les -1 (les mines) sont remplacés par un 'O' sur fond rouge
		
		
		
	}
	
	private void gererClic(ActionEvent e)	{
		// gère le clic 'gauche' sur l'un des boutons. Commence par chercher quel bouton a été cliqué
		
		
		if (nbCasesDecouvertes == 90) { // victoire du joueur
			this.close();
		}
		
		Button b = (Button)e.getSource();
		
		if (nbCasesDecouvertes == 90) { // defaite du joueur
			this.close();
		}
		
		if (!b.getText().equals("F")) {
			
			int ligne;
			int col;
			
			boolean trouve = false;
			int i = 0;
			while(i < DIMENSION && !trouve) {
				int j=0;
				while(j < DIMENSION && !trouve) {
					if (laMap[i][j] == e.getSource()) {
						trouve = false;
						ligne = i;
						col = j;
						decouvrir(ligne,col);
					}
					j++;
				}
				i++;
			}
		}
	}
	
	private void gererClicDroit(MouseEvent e)	{
		// gère le clic 'droit' sur l'un des boutons. Commence par chercher quel bouton a été cliqué	
		int ligne;
		int col;
		
		boolean trouve = false;
		int i = 0;
		while(i < DIMENSION && !trouve) {
			int j=0;
			while(j < DIMENSION && !trouve) {
				if (laMap[i][j] == e.getSource()) {
					trouve = false;
					ligne = i;
					col = j;
					laMap[i][j].setText(laMap[i][j].getText().isEmpty() ? "F" : "");
				}
				j++;
			}
			i++;
		}
	}
	
	


} 