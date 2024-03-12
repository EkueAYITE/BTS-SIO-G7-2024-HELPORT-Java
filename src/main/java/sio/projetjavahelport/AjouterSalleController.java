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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AjouterSalleController implements Initializable {
    @javafx.fxml.FXML
    private Button btnValider;
    @javafx.fxml.FXML
    private TextField txtCodeSalle;
    Stage fenetre = null;
    @javafx.fxml.FXML
    private Button btnRetour;
    RequeteServiceController requetServ;

    @javafx.fxml.FXML
    public void btnValiderClicked(ActionEvent actionEvent) throws IOException {
        String codeSalleSaisis = txtCodeSalle.getText();

        // Vérifier si le code de salle est valide
        if (!codeSalleSaisis.matches("\\d*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'ajout");
            alert.setHeaderText("Le code de la salle est un nombres à 3 chiffres.");
            alert.showAndWait();
        } else if (txtCodeSalle.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'ajout");
            alert.setHeaderText("Veuillez saisir un code");
            alert.showAndWait();
        } else if (!validerCodeSalle(codeSalleSaisis)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'ajout");
            alert.setHeaderText("Code de la salle non valide, ce doit être un code à 3 chiffres.");
            alert.showAndWait();
        } else {
        try {
            // Extraire l'étage à partir du code de salle
            int etage = extraireEtage(codeSalleSaisis);
            String codeConcatene = "Salle " + codeSalleSaisis;
            int code =  Integer.parseInt(txtCodeSalle.getText());
            // Insérer la nouvelle salle dans la base de données
            requetServ.insererNouvelleSalle(code,codeConcatene, etage);

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



        } catch (SQLException e) {
            // Gérer l'erreur d'insertion de la salle dans la base de données
            e.printStackTrace();

        }
        }
    }
    private boolean validerCodeSalle(String codeSalle) {
        // Utiliser une expression régulière pour valider le format du code de salle
        String regex = "^\\d{3}$"; // Un code de salle valide doit contenir exactement 3 chiffres
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(codeSalle);
        return matcher.matches();
    }

    // Méthode pour extraire l'étage à partir du code de salle
    private int extraireEtage(String codeSalle) {
        // L'étage est déterminé par le premier chiffre du code de salle
        return Integer.parseInt(codeSalle.substring(0, 1));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        requetServ = new RequeteServiceController();

    }
}
