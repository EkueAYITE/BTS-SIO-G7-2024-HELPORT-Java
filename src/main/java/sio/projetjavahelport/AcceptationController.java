package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sio.projetjavahelport.tools.Demande;
import sio.projetjavahelport.tools.User;
import sio.projetjavahelport.tools.UserHolder;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class AcceptationController implements Initializable {
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
    Demande selectedDemande;
    RequeteServiceController requeteServ;
    private User user;
    @javafx.fxml.FXML
    private TextArea txtADescription;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        requeteServ = new RequeteServiceController();

    }

    public void initData(Demande selectedDemande) {
        txtNiveau.setText(selectedDemande.getNiveau());
        txtMatiere.setText(selectedDemande.getMatiereDesignation());
        txtSousMatiere.setText(selectedDemande.getSousMatiere());
        txtDate.setText(selectedDemande.getDateFinDemande().toString());
    }

    @javafx.fxml.FXML
    public void btnConfirmerDemandeClicked(ActionEvent actionEvent) {
        user = UserHolder.getInstance().getUser();
        int idDemande = 1;
        int idCompetence = 1;
        String idSalle = "301";
        String description = txtADescription.getText();

        String dateString = txtDate.getText();
        Date dateSoutien = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format de la date
            java.util.Date parsedDate = dateFormat.parse(dateString); // Conversion de la chaîne en Date
            dateSoutien = new Date(parsedDate.getTime()); // Création de l'objet Date
        } catch (ParseException e) {
            e.printStackTrace(); // Gérer l'erreur de conversion
        }


        requeteServ = new RequeteServiceController();

        requeteServ.createSoutien(idDemande, idCompetence, idSalle, dateSoutien, description);
        btnConfirmerDemande.getScene().getWindow().hide();


    }
}
