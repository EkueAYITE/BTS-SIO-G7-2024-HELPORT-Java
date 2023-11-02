package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @FXML
    public void btnConnexionClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void lblMotDePasseOublieClicked(Event event) {
    }

    @FXML
    public void lblMentionLegalesClicked(Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mentions-legales-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Mentions légales");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void lblContactClicked(Event event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}