package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sio.projetjavahelport.tools.ConnexionBDD;
import sio.projetjavahelport.tools.Demande;
import sio.projetjavahelport.tools.User;
import sio.projetjavahelport.tools.UserHolder;

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
    private AnchorPane apnCreerDemande;
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


    @javafx.fxml.FXML
    public void dtDateClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnValiderClicked(ActionEvent actionEvent) {
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
            /*demande.setMatiere((String) cboMatiere.getValue());
            demande.setSousMatiere((String) cboSousMatiere.getValue());
            demande.setDate(dtDate.getValue());
            AccueilController.recevoirNouvelleDemande(demande);*/
            demande.setSousMatiere((String) cboSousMatiere.getValue());
            demande.setDateFinDemande(java.sql.Date.valueOf(dpDate.getValue()));
            demande.setDate_updated(java.sql.Date.valueOf(currentDate));
            demande.setId_user(user.getId());
            demande.setId_matiere(1);
            demande.setStatus(1);



            requeteServ = new RequeteServiceController();

            try {
                requeteServ.ajouterDemande(demande);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Scene sceneActuelle = ((Node) actionEvent.getSource()).getScene();
            Stage stageActuel = (Stage) sceneActuelle.getWindow();
            stageActuel.close();
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
}
