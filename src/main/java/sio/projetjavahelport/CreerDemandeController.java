package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import sio.projetjavahelport.tools.ConnexionBDD;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreerDemandeController implements Initializable {
    @javafx.fxml.FXML
    private DatePicker dtDate;
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
    RequeteServiceController requeteServ;
    ConnexionBDD maCnx;



    @javafx.fxml.FXML
    public void dtDateClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnValiderClicked(ActionEvent actionEvent) {

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

            ArrayList<String> matieres = requeteServ.GetAllMatieres();
            for(String matiere : matieres){
                cboMatiere.getItems().add(matiere);
            }

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cboMatiere.getSelectionModel().selectFirst();
    }
}
