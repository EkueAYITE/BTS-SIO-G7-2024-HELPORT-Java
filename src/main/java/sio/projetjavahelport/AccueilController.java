package sio.projetjavahelport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sio.projetjavahelport.tools.*;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class AccueilController implements Initializable {

    ConnexionBDD maCnx;
    HashMap<String, Integer> datasGraphiqueTopMatieres;
    XYChart.Series<String, Integer> serieGraphTopMatieres;
    XYChart.Series<String, Number> serieGraphDemandesStatuts;
    HashMap<String, Integer> datasGraphiqueDemandesStatuts;
    RequeteServiceController requeteServ;
    @javafx.fxml.FXML
    private Button btnCompetencesAccueil;
    @javafx.fxml.FXML
    private Button btnStatistiquesAccueil;
    @javafx.fxml.FXML
    private Label lblContactsAccueil;
    @javafx.fxml.FXML
    private Label lblMentionsLegagesAccueil;
    @javafx.fxml.FXML
    private Button btnAjouterCompetence;
    @javafx.fxml.FXML
    private Button btnSupprimerCompetence;
    @javafx.fxml.FXML
    private Button btnCreerDemande;
    @javafx.fxml.FXML
    private Button btnDeconnexionAccueil;
    @javafx.fxml.FXML
    private Button btnParametresAccueil;
    @javafx.fxml.FXML
    private AnchorPane apnStatistiques;
    @javafx.fxml.FXML
    private AnchorPane apnCompetences;
    @javafx.fxml.FXML
    private AnchorPane apnDemandes;
    private Stage fenetre = null;
    @javafx.fxml.FXML
    private Button btnModifierDemande1;
    @javafx.fxml.FXML
    private ComboBox cboStatistique;
    @javafx.fxml.FXML
    private Button btnSelectionnerDemande1;
    @javafx.fxml.FXML
    private Button btnSoutiensAccueil;
    @javafx.fxml.FXML
    private ComboBox cboMatiereCompetence;
    @javafx.fxml.FXML
    private Label lblNomEleve;
    @javafx.fxml.FXML
    private Label lblRoleEleve;
    private User user;
    private Demande demande;
    @FXML
    private ListView lstCompetence;

    @FXML
    private TableColumn clmNiveau;
    @FXML
    private TableColumn clmDate;
    @FXML
    private TableColumn clmMatiere;
    @FXML
    private TableColumn clmSousMatiere;
    public ObservableList<Demande> tabDemandes = FXCollections.observableArrayList();
    @FXML
    private TableView tbvDemandes;
    @FXML
    private TableView tbvMesDemandes;
    @FXML
    private TableColumn clmMesMatieres;
    @FXML
    private TableColumn clmMesSousMatieres;
    public ObservableList<Demande> tabMesDemandes = FXCollections.observableArrayList();
    @FXML
    private TableView tbvValiderSoutiens;
    @FXML
    private PieChart graphDemandesParUser;
    @FXML
    private TableColumn clmMesDates;
    @FXML
    private Button btnDemandesParUser;
    @FXML
    private Button btnTopMatieres;
    @FXML
    private BarChart graphTopMatieres;
    @FXML
    private LineChart graphDemandeStatuts;
    @FXML
    private Button btnDemandesStatuts;
    @FXML
    private AnchorPane apnDemandesParUser;
    @FXML
    private AnchorPane apnTopMatieres;
    @FXML
    private AnchorPane apnDemandesStatuts;
    @FXML
    private TableColumn clmSoutienNiveau;
    @FXML
    private TableColumn clmSoutienDate;
    @FXML
    private TableColumn clmSoutienMatiere;
    @FXML
    private TableColumn clmSoutienSousMatiere;
    @FXML
    private TableColumn clmSoutienEleve;
    @FXML
    private Button btnSupprimerDemande;


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

            ObservableList<Demande> tabDemandes = FXCollections.observableArrayList(requeteServ.GetDemande());


            tbvDemandes.setItems(tabDemandes);

            tbvDemandes.setOnMouseEntered(event -> {
                // Récupérer une nouvelle liste de soutiens mise à jour
                ObservableList<Demande> nouvelleListeDemandes = FXCollections.observableArrayList(requeteServ.GetDemande());

                // Mettre à jour la liste observable tabMesSoutiens avec la nouvelle liste
                tabDemandes.setAll(nouvelleListeDemandes);

                // Assurez-vous que le tableau est lié à cette liste observable mise à jour
                tbvDemandes.setItems(nouvelleListeDemandes);
            });




            //Pour afficher les demandes de l'élève connecté
            clmMesMatieres.setCellValueFactory(new PropertyValueFactory<Demande, String>("matiereDesignation"));
            clmMesSousMatieres.setCellValueFactory(new PropertyValueFactory<Demande, String>("sousMatiere"));
            clmMesDates.setCellValueFactory(new PropertyValueFactory<Demande, Date>("dateFinDemande"));

            ObservableList<Demande> tabMesDemandes = FXCollections.observableArrayList(requeteServ.GetMesDemande());

            tbvMesDemandes.setItems(tabMesDemandes);

            tbvMesDemandes.setOnMouseEntered(event -> {
                // Récupérer une nouvelle liste de soutiens mise à jour
                ObservableList<Demande> nouvelleListeMesDemandes = FXCollections.observableArrayList(requeteServ.GetMesDemande());

                // Mettre à jour la liste observable tabMesSoutiens avec la nouvelle liste
                tabMesDemandes.setAll(nouvelleListeMesDemandes);

                // Assurez-vous que le tableau est lié à cette liste observable mise à jour
                tbvMesDemandes.setItems(nouvelleListeMesDemandes);
            });


            //Pour afficher les soutiens de l'élève connecté
            clmSoutienNiveau.setCellValueFactory(new PropertyValueFactory<Demande, String>("niveauAssiste"));
            clmSoutienDate.setCellValueFactory(new PropertyValueFactory<Demande, String>("dateDuSoutien"));
            clmSoutienMatiere.setCellValueFactory(new PropertyValueFactory<Demande, String>("designation"));
            clmSoutienSousMatiere.setCellValueFactory(new PropertyValueFactory<Demande, String>("competence"));
            clmSoutienEleve.setCellValueFactory(new PropertyValueFactory<Demande, String>("nomAssiste"));

            ObservableList<Soutien> tabMesSoutiens = FXCollections.observableArrayList(requeteServ.getLesSoutiens(matiereSelected));

            tbvValiderSoutiens.setItems(tabMesSoutiens);


            tbvValiderSoutiens.setOnMouseEntered(event -> {
                // Récupérer une nouvelle liste de soutiens mise à jour
                ObservableList<Soutien> nouvelleListeSoutiens = FXCollections.observableArrayList(requeteServ.getLesSoutiens(matiereSelected));

                // Mettre à jour la liste observable tabMesSoutiens avec la nouvelle liste
                tabMesSoutiens.setAll(nouvelleListeSoutiens);

                // Assurez-vous que le tableau est lié à cette liste observable mise à jour
                tbvValiderSoutiens.setItems(tabMesSoutiens);
            });


            cboStatistique.getItems().addAll("Nombre de soutiens réalisés", "Nombre de demandes restées sans soutien", "Nombre de soutiens réalisés par niveau, par matière", "Demandes par niveau, par matière", "Etudiants qui ont réalisé le plus de soutiens", "Sous matières les plus sollicitées");


            HashMap<Integer, String> matieres = requeteServ.GetAllMatieres();
            for (int matiere : matieres.keySet()) {
                String nomMatiere = matieres.get(matiere);
                cboMatiereCompetence.getItems().add(nomMatiere);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tbvDemandes.refresh();


        user = UserHolder.getInstance().getUser();
        lblNomEleve.setText(user.getNom());
        lblRoleEleve.setText(user.getRole());

    }

    @javafx.fxml.FXML
    public void btnCompetencesAccueilClicked(ActionEvent actionEvent) {
        apnDemandes.setVisible(false);
        apnCompetences.setVisible(true);
        apnStatistiques.setVisible(false);
    }

    @javafx.fxml.FXML
    public void btnStatistiquesAccueilClicked(ActionEvent actionEvent) {
        apnDemandes.setVisible(false);
        apnCompetences.setVisible(false);
        apnStatistiques.setVisible(true);
    }

    @javafx.fxml.FXML
    public void lblContactsAccueilClicked(Event event) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contact-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Contacts");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }
    }

    @javafx.fxml.FXML
    public void lblMentionsLegagesAccueilClicked(Event event) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajouterSalle-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Mentions légales");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }
    }


    @javafx.fxml.FXML
    public void btnAjouterCompetenceClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajouterCompetence-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Connexion");
        stage.setScene(scene);


        Scene sceneActuelle = ((Node) actionEvent.getSource()).getScene();
        Stage stageActuel = (Stage) sceneActuelle.getWindow();
        stageActuel.close();
        stage.show();
    }

    @javafx.fxml.FXML
    public void btnSupprimerCompetenceClicked(ActionEvent actionEvent) throws IOException {
        String selectedCompetence = (String) lstCompetence.getSelectionModel().getSelectedItem();
        if (selectedCompetence != null) {
            int idMatiere = -1;
            String selectedMatiere = (String) cboMatiereCompetence.getSelectionModel().getSelectedItem();
            HashMap<Integer, String> matieres = requeteServ.GetAllMatieres();
            for (Map.Entry<Integer, String> entry : matieres.entrySet()) {
                if (entry.getValue().equals(selectedMatiere)) {
                    idMatiere = entry.getKey();
                    break;
                }
            }
            if (idMatiere != -1) {
                int idEtudiant = user.getId();
                requeteServ.DeleteCompetence(selectedCompetence, idMatiere, idEtudiant);
                // Rafraîchir l'interface ou effectuer d'autres opérations après la suppression
            } else {
                System.out.println("Erreur : Impossible de trouver l'ID de la matière.");
            }
        } else {
            System.out.println("Erreur : Aucune compétence sélectionnée.");
        }


    }

    @javafx.fxml.FXML
    public void btnCreerDemandeClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            // Si la fenêtre n'est pas encore ouverte on créer une instance
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("creerDemande-view.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Créer une demande");
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
    public void btnSelectionnerDemandeClicked(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void btnDeconnexionAccueilClicked(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("connexion-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Connexion");
        stage.setScene(scene);


        Scene sceneActuelle = ((Node) actionEvent.getSource()).getScene();
        Stage stageActuel = (Stage) sceneActuelle.getWindow();
        stageActuel.close();
        stage.show();
    }

    @javafx.fxml.FXML
    public void btnParametresAccueilClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            // Si la fenêtre n'est pas encore ouverte on créer une instance
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("parametres-view.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Paramètres");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(event -> {
                fenetre = null;
            });
            fenetre.show();
        }
    }

    @javafx.fxml.FXML
    public void btnModifierDemandeClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            // Si la fenêtre n'est pas encore ouverte on créer une instance
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifierDemande-view.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Modifier une demande");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(event -> {
                fenetre = null;
            });
            fenetre.show();
        }
    }


    @javafx.fxml.FXML
    public void btnSoutiensClicked(ActionEvent actionEvent) {
        apnDemandes.setVisible(true);
        apnCompetences.setVisible(false);
        apnStatistiques.setVisible(false);
    }

    @javafx.fxml.FXML
    public void cboMatiereCompetenceCliked(ActionEvent actionEvent) {
        user = UserHolder.getInstance().getUser();
        int idEtudiant = user.getId();
        int idMatiere = -1;
        String selectedValue;
        HashMap<Integer, String> matieres = requeteServ.GetAllMatieres();
        for (Map.Entry<Integer, String> entry : matieres.entrySet()) {
            Object selectedItem = cboMatiereCompetence.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                selectedValue = selectedItem.toString();
                if (entry.getValue().equals(cboMatiereCompetence.getSelectionModel().getSelectedItem().toString())) {
                    idMatiere = entry.getKey();
                    break;
                }
            } else {
                System.out.println("Aucune sélection dans la ComboBox.");
            }
        }
      /*   if (idMatiere != -1) {
         System.out.println("Clé trouvée pour la valeur " + cboMatiereCompetence.getSelectionModel().getSelectedItem().toString() + ": " +idMatiere+":"+ idEtudiant+ matieres);
          } else {
           System.out.println("Aucune clé trouvée pour la valeur " + idEtudiant);
         }*/
        ArrayList<String> tblSousMatiere = requeteServ.GetSousMatiereByMatiere(idEtudiant, idMatiere);
        lstCompetence.getItems().clear();
        lstCompetence.getItems().addAll(tblSousMatiere);

        System.out.println(tblSousMatiere);

    }


    @FXML
    public void cboStatistiqueClicked(ActionEvent actionEvent) {

    }

    @FXML
    public void btnDemandesParUserClicked(ActionEvent actionEvent) {
        apnDemandesParUser.setVisible(true);
        apnTopMatieres.setVisible(false);
        apnDemandesStatuts.setVisible(false);

        graphDemandesParUser.getData().clear();

        ObservableList<PieChart.Data> datasGraph2 = FXCollections.observableArrayList();
        HashMap<String, Integer> datasGraphique2 = requeteServ.getDatasGraphiqueDemandesParUser();

        for (String valeur : datasGraphique2.keySet()) {
            datasGraph2.add(new PieChart.Data(valeur, datasGraphique2.get(valeur)));
        }
        graphDemandesParUser.setData(datasGraph2);
        for (PieChart.Data entry : graphDemandesParUser.getData()) {
            Tooltip t = new Tooltip(entry.getPieValue() + " : " + entry.getName());
            t.setStyle("-fx-background-color:#3D9ADA");
            Tooltip.install(entry.getNode(), t);
        }
    }

    @FXML
    public void btnTopMatieresClicked(ActionEvent actionEvent) {
        apnDemandesParUser.setVisible(false);
        apnTopMatieres.setVisible(true);
        apnDemandesStatuts.setVisible(false);
        graphTopMatieres.getData().clear();
        datasGraphiqueTopMatieres = new HashMap<>();
        datasGraphiqueTopMatieres = requeteServ.getDatasGraphiqueTopMatieres();
        //datasGraphique1 =  graphiqueController.GetDatasGraphique1("FB");
        serieGraphTopMatieres = new XYChart.Series();
        serieGraphTopMatieres.setName("Top Matières");

        // Remplir la série nécessaire au graphique à partir des données provenant de la HashMap
        for (String valeur : datasGraphiqueTopMatieres.keySet()) {
            serieGraphTopMatieres.getData().add(new XYChart.Data(valeur, datasGraphiqueTopMatieres.get(valeur)));
        }
        graphTopMatieres.getData().add(serieGraphTopMatieres);
    }

    @FXML
    public void btnDemandesStatutsClicked(ActionEvent actionEvent) {
        apnDemandesParUser.setVisible(false);
        apnTopMatieres.setVisible(false);
        apnDemandesStatuts.setVisible(true);
        try {
            maCnx = new ConnexionBDD();
            requeteServ = new RequeteServiceController();
            datasGraphiqueDemandesStatuts = requeteServ.getDatasGraphiqueDemandesStatuts();

            // Ajoutez une vérification de nullité ici
            if (datasGraphiqueDemandesStatuts != null) {
                serieGraphDemandesStatuts = new XYChart.Series();
                serieGraphDemandesStatuts.setName("Statuts globaux des demandes actuelles");
                graphDemandeStatuts.getData().clear();

                // Remplir la série nécessaire au graphique à partir des données provenant de la HashMap
                for (String statut : datasGraphiqueDemandesStatuts.keySet()) {
                    serieGraphDemandesStatuts.getData().add(new XYChart.Data(statut, datasGraphiqueDemandesStatuts.get(statut)));
                }

                graphDemandeStatuts.getData().add(serieGraphDemandesStatuts);
            } else {
                System.out.println("La variable datasGraphiqueDemandesStatuts est null.");
                // Gérer le cas où datasGraphiqueDemandesStatuts est null
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void tbvDemandesClicked(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) { // Vérifie si le double clic a été effectué
            Demande selectedDemande = (Demande) tbvDemandes.getSelectionModel().getSelectedItem();
            if (selectedDemande != null) {
                int idDemande = selectedDemande.getIdDemande();
                // Passer les informations à la nouvelle page via le contrôleur AcceptationController
                FXMLLoader loader = new FXMLLoader(getClass().getResource("acceptation-view.fxml"));
                Parent root = loader.load();
                AcceptationController acceptationController = loader.getController();
                acceptationController.initData(selectedDemande); // Méthode pour initialiser les données dans AcceptationController
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Accepter une demande");
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    @FXML
    public void btnSupprimerDemandeClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void tbvMesDemandesClicked(Event event) throws IOException {
            Demande d = (Demande) tbvMesDemandes.getSelectionModel().getSelectedItem();
            if (d != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierDemande-view.fxml"));
                Parent root = loader.load();
                ModifierDemandeController modifierDemandeController = loader.getController();
                modifierDemandeController.initData(d); // Méthode pour initialiser les données dans AcceptationController
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Modifier une demande");
                stage.setScene(scene);
                stage.show();
            }
        }
    }

