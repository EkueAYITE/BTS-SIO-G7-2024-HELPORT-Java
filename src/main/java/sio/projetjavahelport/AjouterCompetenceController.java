package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sio.projetjavahelport.tools.ConnexionBDD;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;
import sio.projetjavahelport.tools.User;
import sio.projetjavahelport.tools.UserHolder;

public class AjouterCompetenceController implements Initializable {
    @javafx.fxml.FXML
    private Button btnValider;
    @javafx.fxml.FXML
    private Button btnAjouter;
    ConnexionBDD maCnx;
    RequeteServiceController requeteServ;
    @javafx.fxml.FXML
    private ComboBox cboMatiereAjouterCompetence;
    @javafx.fxml.FXML
    private ComboBox cboListeSousMatiere;
    User user ;


    @javafx.fxml.FXML
    public void btnValiderClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnAjouterClicked(ActionEvent actionEvent) {
        user = UserHolder.getInstance().getUser();
       int idEtudiant = user.getId();
       int idMatiere = -1;
       String selectedValue;
        HashMap<Integer, String> matieres = requeteServ.GetAllMatieres();
        for (Map.Entry<Integer, String> entry : matieres.entrySet()){
            Object selectedItem = cboMatiereAjouterCompetence.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                selectedValue = selectedItem.toString();
                if (entry.getValue().equals(cboMatiereAjouterCompetence.getSelectionModel().getSelectedItem().toString())) {
                    idMatiere = entry.getKey();
                    break;
                }
            } else {
                System.out.println("Aucune sélection dans la ComboBox.");
            }
        }
        if (idMatiere != -1) {
            System.out.println("Clé trouvée pour la valeur " + cboMatiereAjouterCompetence.getSelectionModel().getSelectedItem().toString() + ": " +idMatiere+""+ idEtudiant+ matieres);
        } else {
            System.out.println("Aucune clé trouvée pour la valeur " + idEtudiant);
        }
        ArrayList<String> sousMatieres = requeteServ.GetAllSousMatieres(cboMatiereAjouterCompetence.getSelectionModel().getSelectedItem().toString());
        String laSousMatiere = null;
        for (String sousMatiere : sousMatieres) {
            cboListeSousMatiere.getItems().add(sousMatiere);
            laSousMatiere  = cboListeSousMatiere.getSelectionModel().getSelectedItem().toString();
        }
        requeteServ.saveUserCompetence(idMatiere, idEtudiant, laSousMatiere);



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            maCnx = new ConnexionBDD();
            requeteServ = new RequeteServiceController();

           HashMap<Integer, String> matieres = requeteServ.GetAllMatieres();
            for(int matiere : matieres.keySet()){
                String nomMatiere = matieres.get(matiere);
                cboMatiereAjouterCompetence.getItems().add(nomMatiere);
            }
            cboMatiereAjouterCompetence.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue, newValue )-> {
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


    @javafx.fxml.FXML
    public void cboListeSousMatiereCliked(ActionEvent actionEvent) {

    }


    @javafx.fxml.FXML
    public void cboMatiereCompetenceCiked(Event event) {
        try{

           remplirSousMatiere();

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void remplirSousMatiere() throws SQLException, ClassNotFoundException {
        if(!(cboMatiereAjouterCompetence.getSelectionModel().getSelectedItem() == null)) {
            maCnx = new ConnexionBDD();
            requeteServ = new RequeteServiceController();
            cboListeSousMatiere.getItems().clear();

            ArrayList<String> sousMatieres = requeteServ.GetAllSousMatieres(cboMatiereAjouterCompetence.getSelectionModel().getSelectedItem().toString());
            for (String sousMatiere : sousMatieres) {
                cboListeSousMatiere.getItems().add(sousMatiere);
            }
        }
    }
}
