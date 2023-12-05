package sio.projetjavahelport;

import javafx.event.ActionEvent;
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

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void cboMatiereCompetenceCiked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void cboListeSousMatiereCliked(ActionEvent actionEvent) {
    }
}
