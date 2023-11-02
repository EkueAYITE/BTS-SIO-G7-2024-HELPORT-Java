package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccueilController implements Initializable {
    @javafx.fxml.FXML
    private Button btnDemandesAccueil;
    @javafx.fxml.FXML
    private Button btnCompetencesAccueil;
    @javafx.fxml.FXML
    private Button btnStatistiquesAccueil;
    @javafx.fxml.FXML
    private Label lblContactsAccueil;
    @javafx.fxml.FXML
    private Label lblMentionsLegagesAccueil;
    @javafx.fxml.FXML
    private Button btnMatiereAccueil;
    @javafx.fxml.FXML
    private Button btnAjouterCompetence;
    @javafx.fxml.FXML
    private Button btnSupprimerCompetence;
    @javafx.fxml.FXML
    private Button btnModifierCompetence;
    @javafx.fxml.FXML
    private Button btnCreerDemande;
    @javafx.fxml.FXML
    private Button btnSelectionnerDemande;
    @javafx.fxml.FXML
    private Button btnDeconnexionAccueil;
    @javafx.fxml.FXML
    private Button btnParametresAccueil;
    @javafx.fxml.FXML
    private Button btnModifierMatiere;
    @javafx.fxml.FXML
    private Button btnAjouterMatiere;
    @javafx.fxml.FXML
    private Button btnSupprimerMatiere;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @javafx.fxml.FXML
    public void btnDemandesAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnCompetencesAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnStatistiquesAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void lblContactsAccueilClicked(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contact-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Contacts");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void lblMentionsLegagesAccueilClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnMatiereAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnAjouterCompetenceClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnSupprimerCompetenceClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnModifierCompetenceClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnCreerDemandeClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnSelectionnerDemandeClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnDeconnexionAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnParametresAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnModifierMatiereClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnAjouterMatiereClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnSupprimerMatiereClicked(ActionEvent actionEvent) {
    }
}
