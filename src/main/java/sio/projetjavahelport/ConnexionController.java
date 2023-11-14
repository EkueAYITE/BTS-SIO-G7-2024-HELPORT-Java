package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnexionController implements Initializable {


    @FXML
    private TextField txtIdentifiant;
    @FXML
    private TextField txtMotDePasse;
    @FXML
    private Button btnConnexion;
    @FXML
    private Label lblMotDePasseOublie;
    @FXML
    private Label lblMentionsLegales;
    @FXML
    private Label lblContact;
    private Stage fenetre = null;
    @FXML
    private Label lblInscription;

    @FXML
    public void btnConnexionClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueil-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Accueil");
        stage.setScene(scene);
        stage.show();
        // Obtenez la scène (Stage) actuelle à partir de l'objet ActionEvent
        Stage stageActuel = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Fermez la fenêtre actuelle
        stageActuel.close();

        // Affichez la nouvelle fenêtre
        stage.show();
    }

    @FXML
    public void lblMotDePasseOublieClicked(Event event) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mdpOublie-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Mot de passe oublié");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }
    }

    @FXML
    public void lblMentionLegalesClicked(Event event) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mentions-legales-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Mentions légales");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }

    }

    @FXML
    public void lblContactClicked(Event event) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contact-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Contacts");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void lblInscriptionClicked(Event event) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("verification-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Inscription");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }
    }
}