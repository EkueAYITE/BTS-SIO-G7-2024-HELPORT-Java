package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sio.projetjavahelport.tools.ConnexionBDD;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;

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


    @javafx.fxml.FXML
    public void btnValiderClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnAjouterClicked(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            maCnx = new ConnexionBDD();
            requeteServ = new RequeteServiceController();

            ArrayList<String> matieres = requeteServ.GetAllMatieres();
            for(String matiere : matieres){
                cboMatiereAjouterCompetence.getItems().add(matiere);
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
