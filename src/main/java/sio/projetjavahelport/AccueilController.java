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
    private Label lblContactsAccueil;
    @javafx.fxml.FXML
    private Label lblMentionsLegalesAccueil;
    @javafx.fxml.FXML
    private Button btnAjouterCompetenceAccueil;
    @javafx.fxml.FXML
    private Button btnSupprimerCompetenceAccueil;
    @javafx.fxml.FXML
    private Button btnModifierCompetenceAccueil;
    @javafx.fxml.FXML
    private Button btnCreerDemande;
    @javafx.fxml.FXML
    private Button btnSelectionnerDemandeAccueil;
    @javafx.fxml.FXML
    private Button btnDeconnexionAccueil;
    @javafx.fxml.FXML
    private Button btnParametreAccueil;
    @javafx.fxml.FXML
    private Button btnModifierMatiereAccueil;
    @javafx.fxml.FXML
    private Button btnAjouterMatiereAccueil;
    @javafx.fxml.FXML
    private Button btnSupprimerMatiereAccueil;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @javafx.fxml.FXML
    public void lblContactsAccueilClicked(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contact-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Contact");
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void lblMentionsLegalesAccueilClicked(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mentions-legales-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Mentions légales");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void btnAjouterCompetenceAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnSupprimerCompetenceAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnModifierCompetenceAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnCreeDemandeClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnSelectionnerDemandeAccueilClicked(ActionEvent actionEvent) {
    }

    @Deprecated
    public void btnDecconexionAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnParametreAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnModifierMatiereAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnAjouterMatiereAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnSupprimerMatiereAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnDeconnexionAccueilClicked(ActionEvent actionEvent) {
    }
}
