package sio.projetjavahelport;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sio.projetjavahelport.tools.ConnexionBDD;
import sio.projetjavahelport.tools.Demande;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ModifierDemandeController implements Initializable {
    @javafx.fxml.FXML
    private AnchorPane apnCreerDemande;
    @javafx.fxml.FXML
    private ComboBox cbModificationMatiere;
    @javafx.fxml.FXML
    private Button btnModificationValider;
    @javafx.fxml.FXML
    private Button btnModificationSupprimer;
    @javafx.fxml.FXML
    private ComboBox cbModificationSMatiere;
    @javafx.fxml.FXML
    private DatePicker dtModificationDate;
    ConnexionBDD maCnx;
    RequeteServiceController requeteServ;
    private int idDemande;
    Demande selectedDemande;
    String mDesignation;

    public void initData(Demande d) {
        selectedDemande = d;


    }
    @javafx.fxml.FXML
    public void btnModificationSupprimerClicked(ActionEvent actionEvent) {
        try {
            requeteServ.DeleteDemande(selectedDemande.getIdDemande());
            btnModificationSupprimer.getScene().getWindow().hide();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de suppression");
            alert.setHeaderText("Cette demande a déjà été acceptée par un élève. Suppression impossible.");
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void cbModificationMatiereClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void dtModificationDateClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnModificationValiderClicked(ActionEvent actionEvent) {
        try {
            if (requeteServ.isDemandeSelectedAsSoutien(selectedDemande.getIdDemande())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de modification");
                alert.setHeaderText("Cette demande a déjà été sélectionnée en tant que soutien. Modification impossible.");
                alert.showAndWait();
            } else {
                mDesignation = (String) cbModificationMatiere.getValue();
                LocalDate localDate = dtModificationDate.getValue(); // Récupération de la date sélectionnée
                java.sql.Date dateSoutien = java.sql.Date.valueOf(localDate); // Conversion de LocalDate en java.sql.Date
                int idMatiere = getIdMatiere((String) cbModificationMatiere.getValue());
                String sousMatiere = (String) cbModificationSMatiere.getValue();
                requeteServ.ModifyDemande(selectedDemande.getIdDemande(), sousMatiere, mDesignation, dateSoutien);
                btnModificationValider.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Problème dans la requête");
            alert.show();
        }
    }
    private int getIdMatiere(String matiere) {
        int idMatiere = -1; // Valeur par défaut si aucune correspondance n'est trouvée
        switch (matiere) {
            case "Anglais":
                idMatiere = 1;
                break;
            case "CEJM":
                idMatiere = 2;
                break;
            case "Français":
                idMatiere = 3;
                break;
            case "Mathématiques":
                idMatiere = 4;
                break;
            case "Informatique":
                idMatiere = 5;
                break;
            case "Histoire":
                idMatiere = 6;
            default:
                // Si aucune correspondance n'est trouvée, l'ID reste à sa valeur par défaut (-1)
                break;
        }
        return idMatiere;
    }

    @javafx.fxml.FXML
    public void cbModificationSMatiereClicked(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            maCnx = new ConnexionBDD();
            requeteServ = new RequeteServiceController();

            HashMap<Integer, String> matieres = requeteServ.GetAllMatieres();
            for(int matiere : matieres.keySet()){
                String nomMatiere = matieres.get(matiere);
                cbModificationMatiere.getItems().add(nomMatiere);
            }
            cbModificationMatiere.getSelectionModel().selectedItemProperty().addListener((observable,  oldValue, newValue )-> {
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

            dtModificationDate.setDayCellFactory(picker -> new DatePickerCell(minDate, maxDate));

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void remplirSousMatiere() throws SQLException, ClassNotFoundException {
        if(!(cbModificationMatiere.getSelectionModel().getSelectedItem() == null)) {
            maCnx = new ConnexionBDD();
            requeteServ = new RequeteServiceController();
            cbModificationSMatiere.getItems().clear();

            ArrayList<String> sousMatieres = requeteServ.GetAllSousMatieres(cbModificationMatiere.getSelectionModel().getSelectedItem().toString());
            for (String sousMatiere : sousMatieres) {
                cbModificationSMatiere.getItems().add(sousMatiere);
            }
        }
    }
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
