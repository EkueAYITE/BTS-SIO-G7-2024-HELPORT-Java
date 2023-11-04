package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SupprimerMatiereController {
    @javafx.fxml.FXML
    private Button btnRetour;
    @javafx.fxml.FXML
    private Button btnSupprimerMatiere;

    @javafx.fxml.FXML
    public void btnRetourClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnSupprimerMatiereClicked(ActionEvent actionEvent) {
        // Obtenez la scène (Stage) actuelle à partir de l'objet ActionEvent
        Stage stageActuel = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Fermez la fenêtre actuelle
        stageActuel.close();
    }
}
