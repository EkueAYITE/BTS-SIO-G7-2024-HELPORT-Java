package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterSousMatiereController implements Initializable {
    @javafx.fxml.FXML
    private Button btnValider;
    @javafx.fxml.FXML
    private TextField txtSousMatiere;
    @javafx.fxml.FXML
    private Button btnRetour;
    RequeteServiceController requetServ;
    private AccueilAdminController accueilAdminController;
    private Stage fenetre = null;
    private String nomMatiereSelectionnee;
    @javafx.fxml.FXML
    private ComboBox cboMatiere;


    @javafx.fxml.FXML
    public void btnValiderClicked(ActionEvent actionEvent) throws IOException {
        String sousMatiere = txtSousMatiere.getText();
        if (cboMatiere.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de modification");
            alert.setHeaderText("Veuillez choisir une matière.");
            alert.showAndWait();
        }else if (txtSousMatiere.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de modification");
            alert.setHeaderText("Veuillez remplir le champs des sous-matières.");
            alert.showAndWait();
        } else if (!txtSousMatiere.getText().contains("#")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de modification");
            alert.setHeaderText("Non respect de la règle du #.");
            alert.showAndWait();
        } else if (sousMatiere.matches(".*\\d.*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de modification");
            alert.setHeaderText("Les sous-matières doivent être en lettres.");
            alert.showAndWait();
        }else {


            String nomMatiere = cboMatiere.getValue().toString();
            String sousMatieresText = txtSousMatiere.getText().trim();
            List<String> sousMatieres = Arrays.asList(sousMatieresText.split("\\s*,\\s*"));
            requetServ.ajouterSousMatieres(nomMatiere, sousMatieres);

            if (fenetre == null) {
                // Si la fenêtre n'est pas encore ouverte on créer une instance
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccueilAdmin-view.fxml"));
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

    @javafx.fxml.FXML
    public void btnRetourClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            // Si la fenêtre n'est pas encore ouverte on créer une instance
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccueilAdmin-view.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        requetServ = new RequeteServiceController();
        HashMap<Integer, String> matieres = requetServ.GetAllMatieres();
        for (int matiere : matieres.keySet()) {
            String nomMatiere = matieres.get(matiere);
            cboMatiere.getItems().add(nomMatiere);
        }
    }
}
