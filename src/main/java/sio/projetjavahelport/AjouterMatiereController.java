package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sio.projetjavahelport.tools.User;

import java.io.IOException;
import java.net.URL;
import java.lang.String;
import java.util.ResourceBundle;

public class AjouterMatiereController implements Initializable {
    @javafx.fxml.FXML
    private Button btnValider;
    @javafx.fxml.FXML
    private TextField txtMatiere;
    @javafx.fxml.FXML
    private TextField txtSousMatiere;
    RequeteServiceController requetServ;
    User user;
    private Stage fenetre = null;
    @javafx.fxml.FXML
    private Button btnRetour;

    @javafx.fxml.FXML
    public void btnValiderClicked(ActionEvent actionEvent) {

        String matiere = txtMatiere.getText();
        String sousMatiere = txtSousMatiere.getText();

        //Gérer les erreurs
        if (txtMatiere.getText().isEmpty()) {
            showAlert("Erreur de saisie", "Veuillez ajouter une matière.");
        } else if (txtSousMatiere.getText().isEmpty()) {
            showAlert("Erreur de saisie", "Veuillez ajouter une ou des sous-matières.");
        }  else if (!txtSousMatiere.getText().contains("#")) {
            showAlert("Erreur d'ajout", "Non respect de la règle du #.");
        } else if (matiere.matches(".*\\d.*")) {
            showAlert("Erreur d'ajout", "La matière doit être en lettres.");
        } else if (sousMatiere.matches(".*\\d.*")) {
            showAlert("Erreur d'ajout", "La sous-matière doit être en lettres.");
        } else {
            try {

                int dernierCode = requetServ.getDernierCodeUtilise();

                // Générer un nouveau code unique
                int nouveauCode = dernierCode + 1;

                requetServ.ajouterMatiereAvecSousMatieres(matiere, sousMatiere, String.valueOf(nouveauCode));

                if (fenetre == null) {
                    // Si la fenêtre n'est pas encore ouverte on crée une instance
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueilAdmin-view.fxml"));
                    Parent root = fxmlLoader.load();

                    Scene scene = new Scene(root);
                    fenetre = new Stage();
                    fenetre.setTitle("Accueil");
                    fenetre.setScene(scene);
                    fenetre.setOnCloseRequest(event -> {
                        fenetre = null;
                    });
                    Scene sceneActuelle = ((Node) actionEvent.getSource()).getScene();
                    Stage stageActuel = (Stage) sceneActuelle.getWindow();
                    stageActuel.close();
                    fenetre.show();
                }



            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        requetServ = new RequeteServiceController();

    }

    @javafx.fxml.FXML
    public void btnRetourClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            // Si la fenêtre n'est pas encore ouverte on crée une instance
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueilAdmin-view.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Accueil");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(event -> {
                fenetre = null;
            });
            Scene sceneActuelle = ((Node) actionEvent.getSource()).getScene();
            Stage stageActuel = (Stage) sceneActuelle.getWindow();
            stageActuel.close();
            fenetre.show();
        }

    }
}
