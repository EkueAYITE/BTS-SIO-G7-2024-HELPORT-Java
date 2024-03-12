package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
import java.util.Arrays;
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
    private int idDemande;
    private String niveauDemande;
    private String nomMatiere;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        requeteServ = new RequeteServiceController();

    }

    public void initData(Demande selectedDemande) {
        txtNiveau.setText(selectedDemande.getNiveau());
        txtMatiere.setText(selectedDemande.getMatiereDesignation());
        txtSousMatiere.setText(selectedDemande.getSousMatiere());
        txtDate.setText(selectedDemande.getDateFinDemande().toString());
        idDemande = selectedDemande.getIdDemande();
        nomMatiere = selectedDemande.getMatiereDesignation();
        niveauDemande = selectedDemande.getNiveau();
    }

    @javafx.fxml.FXML
    public void btnConfirmerDemandeClicked(ActionEvent actionEvent) {

        if (requeteServ.isDemandeSelectedAsSoutien(idDemande)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de modification");
            alert.setHeaderText("Cette demande a déjà été sélectionnée en tant que soutien. Aide impossible.");
            alert.showAndWait();
        } else  if (peutAccepterDemandeSoutien(niveauDemande)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de modification");
            alert.setHeaderText("Cette demande est supérieur à votre niveau. Aide impossible.");
            alert.showAndWait();
        }


            else {

        user = UserHolder.getInstance().getUser();
        int idCompetence = requeteServ.getIdCompetenceCorrespondantALaMatiere(user.getId(),nomMatiere);
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
    public boolean peutAccepterDemandeSoutien(String niveauDemande) {
        // Définir l'ordre des niveaux
        try {
            user = UserHolder.getInstance().getUser();
            String niveau = user.getNiveau();
            String[] ordreNiveaux = {"Master 2", "Master 1", "Bachelor", "BTS 2", "BTS 1", "Terminale"};

            // Récupérer l'index des niveaux dans l'ordre défini
            int indexUtilisateur = Arrays.asList(ordreNiveaux).indexOf(niveau);
            int indexDemande = Arrays.asList(ordreNiveaux).indexOf(niveauDemande);

            // Vérifier si l'utilisateur peut accepter la demande en soutien
            return indexUtilisateur >= indexDemande + 2;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
