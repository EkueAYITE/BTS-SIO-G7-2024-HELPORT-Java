package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sio.projetjavahelport.tools.ConnexionBDD;
import sio.projetjavahelport.tools.Demande;
import sio.projetjavahelport.tools.User;
import sio.projetjavahelport.tools.UserHolder;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CreerDemandeController implements Initializable {
    @javafx.fxml.FXML
    private Button btnValider;
    @javafx.fxml.FXML
    private ComboBox cboMatiere;
    @javafx.fxml.FXML
    private ComboBox cboSousMatiere;
    @javafx.fxml.FXML
    private TreeView root;
    @javafx.fxml.FXML
    private Button btnAjouterSousMatiere;
    private User user;
    RequeteServiceController requeteServ;
    ConnexionBDD maCnx;
    @javafx.fxml.FXML
    private DatePicker dpDate;
    private Stage fenetre = null;
    @javafx.fxml.FXML
    private AnchorPane apCreerDemande;
    @javafx.fxml.FXML
    private Button btnRetour;



    @javafx.fxml.FXML
    public void dtDateClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnValiderClicked(ActionEvent actionEvent) throws IOException, SQLException {
        // On gère les erreurs
        if(dpDate.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("Veuillez choisir une date");
            alert.showAndWait();
        } else if (cboMatiere.getSelectionModel().getSelectedItem()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("Veuillez choisir une matière");
            alert.showAndWait();

        } else if (cboSousMatiere.getSelectionModel().getSelectedItem()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("Veuillez choisir une sous matière");
            alert.showAndWait();

        } else {

            // Variable qui contient la date actuelle
            LocalDate currentDate = LocalDate.now();

            user = UserHolder.getInstance().getUser();


            Demande demande = new Demande();

            String mDesignation = (String) cboMatiere.getValue();

            // Pour insérer les données remplies par l'utilisateur dans la bdd
            // Matière choisie par l'utilisateur
            switch (cboMatiere.getSelectionModel().getSelectedIndex() + 1) {
                case 1: // Anglais
                    demande.setId_matiere(1);
                    break;
                case 2: // CEJM
                    demande.setId_matiere(2);
                    break;
                case 3: // Français
                    demande.setId_matiere(3);
                    break;
                case 4: // Mathématiques
                    demande.setId_matiere(4);
                    break;
                case 5: // Informatique
                    demande.setId_matiere(5);
                    break;
                case 6: // Histoire
                    demande.setId_matiere(6);
                    break;

            }

            demande.setSousMatiere((String) cboSousMatiere.getValue());
            demande.setDateFinDemande(java.sql.Date.valueOf(dpDate.getValue()));
            demande.setDate_updated(java.sql.Date.valueOf(currentDate));
            demande.setId_user(user.getId());
            demande.setStatus(1);


            requeteServ = new RequeteServiceController();

            try {
                requeteServ.ajouterDemande(demande);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (fenetre == null) {
                // Si la fenêtre n'est pas encore ouverte on crée une instance
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueil-view.fxml"));
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
    public void btnAjouterSousMatiereClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void cboMatiereClicked(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void cboSousMatiereClicked(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Pour remplir la combobox des sous-matières en fonction des matières
        try{
            maCnx = new ConnexionBDD();
            requeteServ = new RequeteServiceController();

            HashMap<Integer, String> matieres = requeteServ.GetAllMatieres();
            for(int matiere : matieres.keySet()){
                String nomMatiere = matieres.get(matiere);
                cboMatiere.getItems().add(nomMatiere);
            }
            cboMatiere.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue, newValue )-> {
                try {
                    remplirSousMatiere();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

            // Pour instaurer une intervalle sur le choix de la date avec l'utilisation de la classe DatePickerCell
            LocalDate minDate = LocalDate.now();
            LocalDate maxDate = LocalDate.of(2024, 12, 31);

            dpDate.setDayCellFactory(picker -> new DatePickerCell(minDate, maxDate));

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void remplirSousMatiere() throws SQLException, ClassNotFoundException {
        if(!(cboMatiere.getSelectionModel().getSelectedItem() == null)) {
            maCnx = new ConnexionBDD();
            requeteServ = new RequeteServiceController();
            cboSousMatiere.getItems().clear();

            ArrayList<String> sousMatieres = requeteServ.GetAllSousMatieres(cboMatiere.getSelectionModel().getSelectedItem().toString());
            for (String sousMatiere : sousMatieres) {
                cboSousMatiere.getItems().add(sousMatiere);
            }
        }
    }
    private void closeCurrentStage(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @javafx.fxml.FXML
    public void btnRetourClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            // Si la fenêtre n'est pas encore ouverte on crée une instance
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accueil-view.fxml"));
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
    // Classe qui permet d'instaurer une intervalle dans la sélection de date
    private class DatePickerCell extends DateCell {
        private final LocalDate minDate;
        private final LocalDate maxDate;

        public DatePickerCell(LocalDate minDate, LocalDate maxDate) {
            this.minDate = minDate;
            this.maxDate = maxDate;
        }

        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            setDisable(date.isBefore(minDate) || date.isAfter(maxDate));
        }
    }
}
