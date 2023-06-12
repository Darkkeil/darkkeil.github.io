
import Databading.Aperçu;
import Databading.Titre;
import Databading.Total;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Principale2 extends Application {

	public void start(Stage maFenetre){
		maFenetre = new Total();
		//maFenetre.setWidth(250);
		//maFenetre.setHeight(250);
		maFenetre.show();
	}

	public static void main(String[] args) {
		Application.launch();
	}

}
