package sio.projetjavahelport.tools;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Soutien {
    private String id;
    private String designation;
    private String niveauAssiste;
    private String nomAssiste;
    private String prenomAssiste;
    private String nomAssistant;

    public String getNomAssistant() {
        return nomAssistant;
    }

    public void setNomAssistant(String nomAssistant) {
        this.nomAssistant = nomAssistant;
    }

    private List<String> competence;
    private String idDemande;
    private String idCompetence;
    private String idSalle;
    private String description;
    private int status;
    private String statusAttente;
    private Date dateUpdate;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private Date dateDuSoutien;
    private String sousMatiere;

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public void setDateDuSoutien(Date dateDuSoutien) {
        this.dateDuSoutien = dateDuSoutien;
    }

    public String getSousMatiere() {
        return sousMatiere;
    }

    public void setSousMatiere(String sousMatiere) {
        this.sousMatiere = sousMatiere;
    }

    public String getStatusAttente() {
        return statusAttente;
    }

    public void setStatusAttente(String statusAttente) {
        this.statusAttente = statusAttente;
    }

    public Soutien(String id, String designation, String niveauAssiste, String nomAssiste, String prenomAssiste, List<String> competence, String idDemande, String idCompetence, String idSalle, String description, int statue, Date dateUpdate, Date dateDuSoutien) {
        this.id = id;
        this.designation = designation;
        this.niveauAssiste = niveauAssiste;
        this.nomAssiste = nomAssiste;
        this.prenomAssiste = prenomAssiste;
        this.competence = competence;
        this.idDemande = idDemande;
        this.idCompetence = idCompetence;
        this.idSalle = idSalle;
        this.description = description;
        this.status = statue;
        this.dateUpdate = dateUpdate;
        this.dateDuSoutien = dateDuSoutien;
    }

    public Soutien(String soutienId, String niveauAssiste, String nomEtudiant, String matiere, String sousMatiereDemandee, java.sql.Date dateSoutien, String description, String idSalle, String nomAssistant, String status) {
        this.id = soutienId;
        this.niveauAssiste = niveauAssiste;
        this.nomAssiste = nomEtudiant;
        this.designation = matiere;
        this.sousMatiere = sousMatiereDemandee;
        this.dateDuSoutien = dateSoutien;
        this.description = description;
        this.idSalle = idSalle;
        this.nomAssistant = nomAssistant;
        this.statusAttente = status;

    }


    public String getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(String idDemande) {
        this.idDemande = idDemande;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getNiveauAssiste() {
        return niveauAssiste;
    }

    public void setNiveauAssiste(String niveau) {
        this.niveauAssiste = niveau;
    }

    public String getNomAssiste() {
        return nomAssiste;
    }

    public void setNomAssiste(String nomAssiste) {
        this.nomAssiste = nomAssiste;
    }

    public String getPrenomAssiste() {
        return prenomAssiste;
    }

    public void setPrenomAssiste(String prenomAssiste) {
        this.prenomAssiste = prenomAssiste;
    }

    public List<String> getCompetence() {
        return competence;
    }

    public void setCompetence(List<String> competence) {
        this.competence = competence;
    }

    public String getIdCompetence() {
        return idCompetence;
    }

    public void setIdCompetence(String idCompetence) {
        this.idCompetence = idCompetence;
    }

    public Soutien() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(String idSalle) {
        this.idSalle = idSalle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public java.util.Date getDateDuSoutien() {
        return dateDuSoutien;
    }

    public void setDateDuSoutien(Timestamp dateDuSoutien) {
        this.dateDuSoutien = dateDuSoutien;
    }
}
