package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sio.projetjavahelport.tools.Salle;
import sio.projetjavahelport.tools.Soutien;
import sio.projetjavahelport.tools.User;
import sio.projetjavahelport.tools.UserHolder;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GererSoutienController implements Initializable {
    @javafx.fxml.FXML
    private TextField txtNiveau;
    @javafx.fxml.FXML
    private TextField txtMatiere;
    @javafx.fxml.FXML
    private TextField txtSousMatiere;
    @javafx.fxml.FXML
    private TextField txtDate;
    @javafx.fxml.FXML
    private Button btnConfirmerDemande;
    @javafx.fxml.FXML
    private TextArea txtADescription;
    @javafx.fxml.FXML
    private ComboBox cbSalle;
    RequeteServiceController requetServ;
    String idSoutien;
    User user;

    @javafx.fxml.FXML
    public void btnConfirmerDemandeClicked(ActionEvent actionEvent) {
        try {
            // Récupère l'ID de la salle choisie dans la ComboBox
            Integer idSalleChoisie = (Integer) cbSalle.getSelectionModel().getSelectedItem();

            // Modifie l'ID de la salle des soutiens dans la base de données
            requetServ.modifierIdSalleSoutien(idSoutien, idSalleChoisie);

            requetServ.modifierStatusSoutien(idSoutien, 3);
            btnConfirmerDemande.getScene().getWindow().hide();


        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez les erreurs de modification de l'ID de la salle des soutiens
        } catch (NumberFormatException e) {
            // Gérez les erreurs de conversion de l'ID de la salle en entier
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void cbSalle(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            user = UserHolder.getInstance().getUser();
            requetServ = new RequeteServiceController();
            // Récupère les salles disponibles depuis la base de données
            List<Salle> salles = requetServ.getSalle();

            // Vide la ComboBox avant d'ajouter de nouveaux éléments
            cbSalle.getItems().clear();

            // Ajoute les codes de salle à la ComboBox
            for (Salle salle : salles) {
                cbSalle.getItems().add(salle.getIdSalle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initData(Soutien selectedSoutien) {
        idSoutien = selectedSoutien.getId();
        txtMatiere.setText(selectedSoutien.getDesignation());
        txtDate.setText(selectedSoutien.getDateDuSoutien().toString());
        txtNiveau.setText(selectedSoutien.getNiveauAssiste());
        txtSousMatiere.setText(selectedSoutien.getSousMatiere());
        txtADescription.setText(selectedSoutien.getDescription());
    }
}
