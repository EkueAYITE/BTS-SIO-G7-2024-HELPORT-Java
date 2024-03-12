package sio.projetjavahelport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sio.projetjavahelport.tools.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AccueilAdminController implements Initializable {
    ConnexionBDD maCnx;
    private User user;
    @javafx.fxml.FXML
    private Button btnDemandesAccueil;
    @javafx.fxml.FXML
    private Button btnCompetencesAccueil;
    @javafx.fxml.FXML
    private Button btnStatistiquesAccueil;
    @javafx.fxml.FXML
    private AnchorPane apnStatistiques;
    @javafx.fxml.FXML
    private AnchorPane apnCompetences;
    @javafx.fxml.FXML
    private AnchorPane apnDemandes;
    @javafx.fxml.FXML
    private Button btnCreerDemande;
    @javafx.fxml.FXML
    private Button btnDeconnexionAccueil;
    @javafx.fxml.FXML
    private Button btnParametresAccueil;
    @javafx.fxml.FXML
    private Button btnAjouterSalle;
    @javafx.fxml.FXML
    private Button btnAjouterMatière;
    @javafx.fxml.FXML
    private TableView tbvSalle;
    @javafx.fxml.FXML
    private Button btnSoutienParEtudiant;
    @javafx.fxml.FXML
    private Button btnDemandeParMatiere;
    @javafx.fxml.FXML
    private Button btnTopSousMatiere;
    @javafx.fxml.FXML
    private AnchorPane apnDemandeParMatiere;
    @javafx.fxml.FXML
    private AnchorPane apnSoutienParEtudiant;
    @javafx.fxml.FXML
    private AnchorPane apnTopSousMatiere;
    @javafx.fxml.FXML
    private TableView tbvDemandes;
    @javafx.fxml.FXML
    private TableView tbvValiderSoutiens;
    @javafx.fxml.FXML
    private PieChart graphSoutienParEtudiant;
    RequeteServiceController requeteServ;
    @javafx.fxml.FXML
    private AreaChart graphTopSousMatiere;
    @javafx.fxml.FXML
    private ScatterChart graphDemandeParMatiere;
    private Stage fenetre = null;
    @javafx.fxml.FXML
    private AnchorPane apnSalle;
    @javafx.fxml.FXML
    private Button btnSalle;
    @javafx.fxml.FXML
    private TableColumn clmDate;
    @javafx.fxml.FXML
    private TableColumn clmNiveau;
    @javafx.fxml.FXML
    private TableColumn clmMatiere;
    @javafx.fxml.FXML
    private TableColumn clmSousMatiere;
    @javafx.fxml.FXML
    private TableColumn clmEleve;
    @javafx.fxml.FXML
    private TableColumn clmSoutienNiveau;
    @javafx.fxml.FXML
    private TableColumn clmSoutienDate;
    @javafx.fxml.FXML
    private TableColumn clmSoutienMatiere;
    @javafx.fxml.FXML
    private TableColumn clmSoutienSousMatiere;
    @javafx.fxml.FXML
    private TableColumn clmSoutienEleve;
    @javafx.fxml.FXML
    private ComboBox cboMatiere;
    @javafx.fxml.FXML
    private ListView lstMatiere;
    @javafx.fxml.FXML
    private TableColumn clmSalle;
    @javafx.fxml.FXML
    private TableColumn clmEtage;

    @javafx.fxml.FXML
    public void btnDemandesAccueilClicked(ActionEvent actionEvent) {
        apnSalle.setVisible(false);
        apnStatistiques.setVisible(false);
        apnDemandes.setVisible(true);
        apnCompetences.setVisible(false);
    }

    @javafx.fxml.FXML
    public void btnCompetencesAccueilClicked(ActionEvent actionEvent) {
        apnSalle.setVisible(false);
        apnStatistiques.setVisible(false);
        apnDemandes.setVisible(false);
        apnCompetences.setVisible(true);
    }

    @javafx.fxml.FXML
    public void btnStatistiquesAccueilClicked(ActionEvent actionEvent) {
        apnDemandes.setVisible(false);
        apnCompetences.setVisible(false);
        apnStatistiques.setVisible(true);
        apnSalle.setVisible(false);
    }


    @javafx.fxml.FXML
    public void btnCreerDemandeClicked(ActionEvent actionEvent) {
    }


    @javafx.fxml.FXML
    public void btnDeconnexionAccueilClicked(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void btnParametresAccueilClicked(ActionEvent actionEvent) {
    }


    @javafx.fxml.FXML
    public void btnAjouterSalleClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            // Si la fenêtre n'est pas encore ouverte on crée une instance
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajouterSalle-view.fxml"));
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


    @javafx.fxml.FXML
    public void btnAjouterMatièreClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            // Si la fenêtre n'est pas encore ouverte on créer une instance
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajouterMatiere-view.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Ajouter une matière");
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

    @javafx.fxml.FXML
    public void tbvSalleClicked(Event event) {
    }

    @javafx.fxml.FXML
    public void btnSoutienParEtudiantClicked(ActionEvent actionEvent) {
        apnSoutienParEtudiant.setVisible(true);
        apnTopSousMatiere.setVisible(false);
        apnDemandeParMatiere.setVisible(false);

        graphSoutienParEtudiant.getData().clear();

        ObservableList<PieChart.Data> datasGraph2 = FXCollections.observableArrayList();
        HashMap<String, Integer> datasGraphique2 = requeteServ.getDatasGraphiqueSoutiensParUser();

        for (String valeur : datasGraphique2.keySet()) {
            datasGraph2.add(new PieChart.Data(valeur, datasGraphique2.get(valeur)));
        }
        graphSoutienParEtudiant.setData(datasGraph2);
        for (PieChart.Data entry : graphSoutienParEtudiant.getData()) {
            Tooltip t = new Tooltip(entry.getPieValue() + " : " + entry.getName());
            t.setStyle("-fx-background-color:#3D9ADA");
            Tooltip.install(entry.getNode(), t);
        }
    }

    @javafx.fxml.FXML
    public void btnDemandeParMatiereClicked(ActionEvent actionEvent) {
        apnTopSousMatiere.setVisible(false);
        apnSoutienParEtudiant.setVisible(false);
        apnDemandeParMatiere.setVisible(true);

        graphDemandeParMatiere.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        HashMap<String, Integer> datasGraphique = requeteServ.getDatasGraphiqueDemandeParMatieres();

        for (String sousMatiere : datasGraphique.keySet()) {
            series.getData().add(new XYChart.Data<>(sousMatiere, datasGraphique.get(sousMatiere)));
        }

        graphDemandeParMatiere.getData().add(series);

    }

    @javafx.fxml.FXML
    public void btnTopSousMatiereClicked(ActionEvent actionEvent) {
        apnTopSousMatiere.setVisible(true);
        apnSoutienParEtudiant.setVisible(false);
        apnDemandeParMatiere.setVisible(false);
        ObservableList<XYChart.Series<Number, Number>> seriesList = requeteServ.getDatasGraphiqueTopSousMatiere();

        // Création du graphique
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        AreaChart<Number, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("Top Sous Matière");

        // Ajout des séries au graphique
        for (XYChart.Series<Number, Number> series : seriesList) {
            areaChart.getData().add(series);
        }

        // Ajout du graphique à votre conteneur (panneau)
        apnTopSousMatiere.getChildren().add(areaChart);


    }

    @javafx.fxml.FXML
    public void tbvDemandesClicked(Event event) {
    }

    @Deprecated
    public void tbvValiderSoutienClicked(Event event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            maCnx = new ConnexionBDD();
            requeteServ = new RequeteServiceController();
            user = UserHolder.getInstance().getUser();
            String matiereSelected = "";


            //Pour afficher les demandes générales
            clmNiveau.setCellValueFactory(new PropertyValueFactory<Demande, String>("niveau"));
            clmDate.setCellValueFactory(new PropertyValueFactory<Demande, Date>("dateFinDemande"));
            clmMatiere.setCellValueFactory(new PropertyValueFactory<Demande, String>("matiereDesignation"));
            clmSousMatiere.setCellValueFactory(new PropertyValueFactory<Demande, String>("sousMatiere"));
            //clmEleve.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("status"));

            ObservableList<Demande> tabDemandes = FXCollections.observableArrayList(requeteServ.GetDemande());

            tbvDemandes.setItems(tabDemandes);

            clmSoutienNiveau.setCellValueFactory(new PropertyValueFactory<Demande, String>("niveauAssiste"));
            clmSoutienDate.setCellValueFactory(new PropertyValueFactory<Demande, String>("dateDuSoutien"));
            clmSoutienMatiere.setCellValueFactory(new PropertyValueFactory<Demande, String>("designation"));
            clmSoutienSousMatiere.setCellValueFactory(new PropertyValueFactory<Demande, String>("sousMatiere"));
            clmSoutienEleve.setCellValueFactory(new PropertyValueFactory<Demande, String>("nomAssiste"));

            ObservableList<Soutien> tabMesSoutiens = FXCollections.observableArrayList(requeteServ.getLesSoutiensGeneraux());

            tbvValiderSoutiens.setItems(tabMesSoutiens);

            clmSalle.setCellValueFactory(new PropertyValueFactory<Salle, String>("codeSalle"));
            clmEtage.setCellValueFactory(new PropertyValueFactory<Salle, Integer>("etageSalle"));

            ObservableList<Salle> tabSalles = FXCollections.observableArrayList(requeteServ.getSalle());

            tbvSalle.setItems(tabSalles);

            tbvSalle.setOnMouseEntered(event -> {
                // Récupérer une nouvelle liste de soutiens mise à jour
                ObservableList<Salle> nouvelleListeSoutiens = null;
                try {
                    nouvelleListeSoutiens = FXCollections.observableArrayList(requeteServ.getSalle());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                // Mettre à jour la liste observable tabMesSoutiens avec la nouvelle liste
                tabSalles.setAll(nouvelleListeSoutiens);

                // Assurez-vous que le tableau est lié à cette liste observable mise à jour
                tbvSalle.setItems(tabSalles);
            });




            HashMap<Integer, String> matieres = requeteServ.GetAllMatieres();
            for (int matiere : matieres.keySet()) {
                String nomMatiere = matieres.get(matiere);
                cboMatiere.getItems().add(nomMatiere);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void btnSalleClicked(ActionEvent actionEvent) {
        apnSalle.setVisible(true);
        apnStatistiques.setVisible(false);
        apnDemandes.setVisible(false);
        apnCompetences.setVisible(false);
    }

    @javafx.fxml.FXML
    public void cboMatiereClicked(ActionEvent actionEvent) {
        try {
            user = UserHolder.getInstance().getUser();
            int idEtudiant = user.getId();
            int idMatiere = -1;
            String selectedValue = cboMatiere.getSelectionModel().getSelectedItem().toString();

            HashMap<Integer, String> matieres = requeteServ.GetAllMatieres();
            for (Map.Entry<Integer, String> entry : matieres.entrySet()) {
                if (entry.getValue().equals(selectedValue)) {
                    idMatiere = entry.getKey();
                    break;
                }
            }

            if (idMatiere != -1) {
                ArrayList<String> tblSousMatiere = requeteServ.GetSousMatiereByMatiereAdmin(idMatiere);
                lstMatiere.getItems().clear();
                lstMatiere.getItems().addAll(tblSousMatiere);
            } else {
                System.out.println("Aucune clé trouvée pour la valeur " + selectedValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
