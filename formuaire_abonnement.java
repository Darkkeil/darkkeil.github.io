import java.awt.Checkbox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class F1 extends Stage {
	
	private VBox racine = new VBox();
	private Button BnAnnuler = new Button("Annuler");
	private Button BnValider = new Button("OK");
	private Label identite = new Label("Identité");
	private Label abonnement = new Label("Abonnement");
	private Label reglement = new Label("Règlement");
	private Label nom = new Label("Nom* :");
	private TextField t_nom = new TextField();
	private Label prenom = new Label("Prenom* :");
	private TextField t_prenom = new TextField();
	private Label adresse = new Label("Adresse* :");
	private TextField t_adresse1 = new TextField();
	private TextField t_adresse2 = new TextField();
	private Label Code_postal = new Label("Code postal* :");
	private TextField t_code = new TextField();
	private Label ville = new Label("Ville* :");
	private TextField t_ville = new TextField();
	private Label tel = new Label("Telephone");
	private Label err_nom = new Label("Maximum 5 caractères, pas de chiffres dans le nom");
	//private HBox box = new HBox();
	private TextField t_tel = new TextField();
	private Label mail = new Label("E-mail");
	private TextField t_mail = new TextField();
	private VBox zone_id = new VBox();
	private VBox zone_abo = new VBox();
	private VBox zone_regl = new VBox();
	private VBox abo1 = new VBox();
	private VBox abo2 = new VBox();
	private VBox zone_bas = new VBox();
	private HBox boutons_bas = new HBox();
	private HBox prenom_nom = new HBox();
	private HBox add1 = new HBox();
	private HBox add2 = new HBox();
	private HBox c_v = new HBox();
	private HBox teleph = new HBox();
	private HBox mailo = new HBox();
	private Label vide = new Label("       ");
	private RadioButton offr_duo = new RadioButton("Je m’abonne à l’offre DUO 1 an pour un montant de 81,40 euros");
	private Label c_offr_1 = new Label("(journal papier + version numérique)");
	private RadioButton offr_num = new RadioButton("Je m’abonne à l’offre NUMERIQUE 1 an pour un montant de 76,40 euros");
	private Label c_offr_2 = new Label("(version numérique)");
	private RadioButton prelevement = new RadioButton("Je règle par prélèvement");
	private RadioButton cheque = new RadioButton("Je règle par chèque");
	private ToggleGroup g1 = new ToggleGroup();
	private ToggleGroup g2 = new ToggleGroup();
	private CheckBox coche = new CheckBox("J’accepte de recevoir des informations sur mon journal");
	
	public F1(){ 
		this.setTitle("Formule 1"); 
		this.setResizable(false);
		Scene laScene = new Scene(creerScene());
		this.setScene(laScene);
		this.sizeToScene();
	}
	
	private Parent creerScene(){
		
		nom.setMinWidth(100);
		prenom.setMinWidth(70);
		adresse.setMinWidth(100);
		vide.setMinWidth(100);
		Code_postal.setMinWidth(100);
		ville.setMinWidth(50);
		tel.setMinWidth(100);
		mail.setMinWidth(100);
		
		t_nom.setMaxWidth(120);
		t_prenom.setMaxWidth(120);
		t_adresse1.setMinWidth(250);
		t_adresse1.setMaxWidth(1000);
		t_adresse2.setMinWidth(250);
		t_adresse2.setMaxWidth(1000);
		t_code.setMaxWidth(50);
		t_mail.setPrefWidth(250);
		t_tel.setPrefWidth(135);
		
		prenom.setPadding(new Insets(0,10,0,10));
		ville.setPadding(new Insets(0,10,0,10));
		
		BnValider.setDefaultButton(true);
		BnAnnuler.setCancelButton(true);
		
		prenom_nom.getChildren().addAll(nom,t_nom,prenom,t_prenom);
		add1.getChildren().addAll(adresse,t_adresse1);
		add2.getChildren().addAll(vide,t_adresse2);
		c_v.getChildren().addAll(Code_postal,t_code,ville,t_ville);
		teleph.getChildren().addAll(tel,t_tel);
		mailo.getChildren().addAll(mail,t_mail);
		/*zone_id.setMinHeight(200);
		zone_id.setMinWidth(400);*/
		/*zone_abo.setMinHeight(200);
		zone_abo.setMinWidth(400);*/
		/*zone_regl.setMinHeight(200);
		zone_regl.setMinWidth(400);*/
		
		g1.getToggles().add(offr_duo);
		g1.getToggles().add(offr_num);
		
		g1.selectToggle(offr_num);
		
		g2.getToggles().add(prelevement);
		g2.getToggles().add(cheque);
		
		g2.selectToggle(cheque);
		
		
		
		abo1.getChildren().addAll(offr_duo,c_offr_1);
		abo2.getChildren().addAll(offr_num,c_offr_2);
		
		BnValider.setPrefWidth(80);
		BnAnnuler.setPrefWidth(80);
		
		
		boutons_bas.getChildren().addAll(BnValider,BnAnnuler);
		boutons_bas.setSpacing(10);
		
		t_nom.setOnKeyReleased(e ->{
			if (t_nom.getText().length() >=16)
			{
			t_nom.deletePreviousChar();
			}
			});
		
		this.t_code.setOnKeyTyped(e -> {
			if (t_code.getText().length() >= 5) {
			e.consume();
			Alert erreur = new Alert(AlertType.ERROR, "Le code postal doit être sur 5 caractères. Veuillez modifier ce champ.", ButtonType.OK);
			erreur.setTitle("Code postal : format incorrect");
			erreur.showAndWait();
			}
			});
		
		this.t_adresse1.setOnKeyTyped(e -> {
			if (t_adresse1.getText().length() > 38) {
			e.consume();
			Alert erreur = new Alert(AlertType.ERROR, "L'adresse doit être sur 38 caractères. Veuillez modifier ce champ.", ButtonType.OK);
			erreur.setTitle("Adresse : format incorrect");
			erreur.showAndWait();
			}
			});
		
		this.t_adresse2.setOnKeyTyped(e -> {
			if (t_adresse2.getText().length() > 38) {
			e.consume();
			Alert erreur = new Alert(AlertType.ERROR, "L'adresse doit être sur 38 caractères. Veuillez modifier ce champ.", ButtonType.OK);
			erreur.setTitle("Adresse : format incorrect");
			erreur.showAndWait();
			}
			});
		
		this.t_ville.setOnKeyTyped(e -> {
			if (t_ville.getText().length() > 32) {
			e.consume();
			Alert erreur = new Alert(AlertType.ERROR, "Le champ ville doit être sur 32 caractères. Veuillez modifier ce champ.", ButtonType.OK);
			erreur.setTitle("Adresse : format incorrect");
			erreur.showAndWait();
			}
			});
		
		BnValider.setOnAction(e -> {
			if (!t_mail.getText().contains("@")) {
				e.consume();
				Alert erreur = new Alert(AlertType.ERROR, "Votre mail doit comporter un @. Veuillez modifier ce champ.", ButtonType.OK);
				erreur.setTitle("Adresse mail : format incorrect");
				erreur.showAndWait();
				};
			String offr;
			if (offr_duo.isSelected()) {
				offr = "DUO 1 an";
			} else {
				offr = "NUMERIQUE 1 an";
			}
			String prelevemente;
			if(prelevement.isSelected()) {
				prelevemente = "Chèque";
			} else {
				prelevemente = "Prélèvement";
			}
			boolean check;
			if(coche.isSelected()) {
				check = true;
			} else {
				check = false;
			}
			Abonnement abo = new Abonnement(t_nom.getText(),t_prenom.getText(),t_adresse1.getText(),t_adresse2.getText(),t_ville.getText(),t_code.getText(),t_tel.getText(),t_mail.getText(),offr,prelevemente,check);
			abo.afficher();
			this.close();
			});
		
		err_nom.setStyle("-fx-font-size:10 ; -fx-text-fill:RED");
		
		
		err_nom.setVisible(false);
		
		t_nom.setOnKeyTyped(e ->{
			if (notValide(t_nom)) {
				err_nom.setVisible(true);
			} else {
				err_nom.setVisible(false);
			}
		});
		
		if (t_nom.getText().equals("")) {
			BnValider.setDisable(true);
		}
		BnValider.setDisable(false);
		BnAnnuler.setOnAction(e -> this.close() );
		
		zone_id.setBackground(new Background (new BackgroundFill(Color.AZURE, new CornerRadii(0), new Insets(0))));
		zone_id.getChildren().addAll(prenom_nom,err_nom,add1,add2,c_v,teleph,mailo);
		zone_id.setSpacing(10);
		zone_id.setPadding(new Insets(10));
		zone_abo.setBackground(new Background (new BackgroundFill(Color.AZURE, new CornerRadii(0), new Insets(0))));
		zone_abo.getChildren().addAll(abo1,abo2);
		zone_abo.setSpacing(10);
		zone_abo.setPadding(new Insets(10));
		zone_regl.setBackground(new Background (new BackgroundFill(Color.AZURE, new CornerRadii(0), new Insets(0))));
		zone_regl.getChildren().addAll(prelevement,cheque);
		zone_regl.setSpacing(10);
		zone_regl.setPadding(new Insets(10));
		
		zone_bas.getChildren().addAll(coche,boutons_bas);
		zone_bas.setSpacing(10);
		zone_bas.setPadding(new Insets(10));
		boutons_bas.setAlignment(Pos.BASELINE_RIGHT);
		
		racine.getChildren().addAll(identite,zone_id,abonnement,zone_abo,reglement,zone_regl,zone_bas);
		racine.setPadding(new Insets(10));
		this.initModality(Modality.APPLICATION_MODAL);
		return racine;
		
	}
	
	public boolean notValide(TextField t) {
		boolean res = false;
		if (t.getText().length() > 5) {
			res = true;
		}
		return res;
	}

}
