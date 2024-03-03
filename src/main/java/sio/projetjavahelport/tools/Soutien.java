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
    private List<String> competence;
    private String idDemande;
    private String idCompetence;
    private String idSalle;
    private String description;
    private int statue;
    private Date dateUpdate;
    private Date dateDuSoutien;

    public Soutien(String id, String designation, String niveauAssiste, String nomAssiste, String prenomAssiste, List<String> competence, String idDemande, String idCompetence,String idSalle, String description, int statue, Date dateUpdate, Date dateDuSoutien) {
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
        this.statue = statue;
        this.dateUpdate = dateUpdate;
        this.dateDuSoutien = dateDuSoutien;
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

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
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
