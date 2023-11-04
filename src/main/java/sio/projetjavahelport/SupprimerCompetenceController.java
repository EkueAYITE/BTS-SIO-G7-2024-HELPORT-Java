package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SupprimerCompetenceController {
    @javafx.fxml.FXML
    private Button btnSupprimerCompetence;
    @javafx.fxml.FXML
    private Button btnRetour;

    @javafx.fxml.FXML
    public void btnSupprimerCompetenceClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnRetourClicked(ActionEvent actionEvent) {
        // Obtenez la scène (Stage) actuelle à partir de l'objet ActionEvent
        Stage stageActuel = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Fermez la fenêtre actuelle
        stageActuel.close();
    }
}
