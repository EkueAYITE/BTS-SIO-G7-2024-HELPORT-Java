package sio.projetjavahelport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sio.projetjavahelport.tools.ConnexionBDD;
import sio.projetjavahelport.tools.Demande;
import sio.projetjavahelport.tools.User;
import sio.projetjavahelport.tools.UserHolder;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class AccueilController implements Initializable {

    ConnexionBDD maCnx;
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
    private Button btnSelectionnerDemande;
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
    @FXML
    private TableColumn clmMesDates;
    public ObservableList<Demande> tabMesDemandes = FXCollections.observableArrayList();
    @FXML
    private TableView tbvValiderSoutiens;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{maCnx = new ConnexionBDD();
            requeteServ = new RequeteServiceController();

            clmNiveau.setCellValueFactory(new PropertyValueFactory<Demande,String>("niveau"));
            clmDate.setCellValueFactory(new PropertyValueFactory<Demande, Date>("dateFinDemande"));
            clmMatiere.setCellValueFactory(new PropertyValueFactory<Demande,String>("matiereDesignation"));
            clmSousMatiere.setCellValueFactory(new PropertyValueFactory<Demande,String>("sousMatiere"));
            ObservableList<Demande> tabDemandes = FXCollections.observableArrayList(requeteServ.GetDemande());

            tbvDemandes.setItems(tabDemandes);

            clmMesMatieres.setCellValueFactory(new PropertyValueFactory<Demande,String>("matiereDesignation"));
            clmMesSousMatieres.setCellValueFactory(new PropertyValueFactory<Demande, String>("sousMatiere"));
            clmMesDates.setCellValueFactory(new PropertyValueFactory<Demande,Date>("dateFinDemande"));
            ObservableList<Demande> tabMesDemande = FXCollections.observableArrayList(requeteServ.GetMesDemande());

            tbvMesDemandes.setItems(tabMesDemande);

            cboStatistique.getItems().addAll("Nombre de soutiens réalisés","Nombre de demandes restées sans soutien","Nombre de soutiens réalisés par niveau, par matière","Demandes par niveau, par matière","Etudiants qui ont réalisé le plus de soutiens","Sous matières les plus sollicitées");




           HashMap<Integer, String> matieres = requeteServ.GetAllMatieres();
           for(int matiere : matieres.keySet()){
               String nomMatiere = matieres.get(matiere);
               cboMatiereCompetence.getItems().add(nomMatiere);
           }

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } tbvDemandes.refresh();


       user = UserHolder.getInstance().getUser();
       lblNomEleve.setText(user.getNom());
       lblRoleEleve.setText(user.getRole());


        tbvDemandes.setOnMouseMoved(event -> handleTableMouseAction());
        tbvMesDemandes.setOnMouseMoved(event -> handleTableMouseAction());
    }

    private void handleTableMouseAction() {
        ObservableList<Demande> updatedTabDemande = FXCollections.observableArrayList(requeteServ.GetDemande());
        tbvDemandes.setItems(updatedTabDemande);
        tbvDemandes.refresh();

        ObservableList<Demande> updatedTabMesDemandes = FXCollections.observableArrayList(requeteServ.GetMesDemande());
        tbvMesDemandes.setItems(updatedTabMesDemandes);
        tbvMesDemandes.refresh();
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mentions-legales-view.fxml"));
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
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajouterCompetence-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Ajouter une compétence");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }
    }

    @javafx.fxml.FXML
    public void btnSupprimerCompetenceClicked(ActionEvent actionEvent) throws IOException {
        // recupération de toutes les compétences
        List<String> tabCompetence = lstCompetence.getItems();
        List<String>competenceUpdte = new ArrayList<>();

       // System.out.println("Élément mis à jour : " + competenceUpdte);

        if (tabCompetence != null) {
            System.out.println("liste sélectionné : " + tabCompetence);
            // Faites quelque chose avec l'élément sélectionné
        }


        //recupération de la compétences selectionné
        String laCompetence = String.valueOf(lstCompetence.getSelectionModel().getSelectedItem());
        //test si la compétence fonctionne
        if (laCompetence != null) {
            System.out.println("Élément sélectionné : " + laCompetence);

        }
       // competenceUpdte.addAll(tabCompetence);
        System.out.println("nouveau tableau sélectionné : " + competenceUpdte);

        //on refait un tableau sans la valeur selectionné
        assert tabCompetence != null;
        for (String competence : tabCompetence) {
            if (!competence.equals(laCompetence)) {
                competenceUpdte.add(competence);
                System.out.println("Élément mis à jour : " + competenceUpdte);
                //  break; // Sort de la boucle une fois que l'élément est trouvé
            }

        }
        //Chaine pour stoké le résultat
        StringBuilder resultat = new StringBuilder();
        for (String element : competenceUpdte) {
            // Ajouter le séparateur "#" avant chaque élément (sauf pour le premier)
            if (!resultat.isEmpty()) {
                resultat.append("#");
            }
            // Ajouter l'élément au résultat
            resultat.append(element);

            System.out.println("Élément indenté : " + resultat);
        }
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
        String sousMatiere = String.valueOf(resultat);
        System.out.println("Élément string : " + resultat);
        /*if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("supprimerCompetence-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Supprimer une compétence");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
        }*/
        Boolean sousMatiereExiste;
        ArrayList<String> tabCompetenceDemande = requeteServ.CheckMesDemande();

        for( String competenceD : tabCompetenceDemande){
            for(String competence : tabCompetence){
                if(!competenceD.equals(competence)){
                    requeteServ.DeletCompetence(sousMatiere, idMatiere, idEtudiant );
                }
            }
        }

    }

    @Deprecated
    public void btnModifierCompetenceClicked(ActionEvent actionEvent) throws IOException {
        if (fenetre == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifierCompetence-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            fenetre = new Stage();
            fenetre.setTitle("Modifier une compétence");
            fenetre.setScene(scene);
            fenetre.setOnCloseRequest(e -> {
                fenetre = null; // Réinitialisez la référence lorsque la fenêtre est fermée
            });
            fenetre.show();
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
        String selectedValue ;
        HashMap<Integer, String>  matieres = requeteServ.GetAllMatieres();
        for (Map.Entry<Integer, String> entry : matieres.entrySet()){
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
       ArrayList<String> tblSousMatiere = requeteServ.GetSousMatiereByMatiere(idEtudiant,idMatiere);
       lstCompetence.getItems().clear();
        lstCompetence.getItems().addAll(tblSousMatiere);

        System.out.println( tblSousMatiere);

    }


}
