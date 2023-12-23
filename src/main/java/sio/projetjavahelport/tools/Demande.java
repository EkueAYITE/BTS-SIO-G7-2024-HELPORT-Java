package sio.projetjavahelport.tools;

import java.sql.Date;


public class Demande {

    private String niveau;
    private Date date_updated;
    private Date dateFinDemande;
    private String matiereDesignation;
    private String sousMatiere;
    private int id_user;

    public Date getDate_updated() {
        return date_updated;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public int getStatus() {
        return status;
    }

    private int id_matiere;

    public void setDate_updated(Date date_updated) {
        this.date_updated = date_updated;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;

    public Demande()
    {

    }

    public Demande(String niveau, Date dateFinDemande, String matiereDesignation, String sousMatiere) {
        this.niveau = niveau;
        this.dateFinDemande = dateFinDemande;
        this.matiereDesignation = matiereDesignation;
        this.sousMatiere = sousMatiere;
    }

    public String getNiveau() {
        return niveau;
    }

    public Date getDateFinDemande() {
        return dateFinDemande;
    }

    public String getMatiereDesignation() {
        return matiereDesignation;
    }

    public String getSousMatiere() {
        return sousMatiere;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setDateFinDemande(Date dateFinDemande) {
        this.dateFinDemande = dateFinDemande;
    }

    public void setMatiereDesignation(String matiereDesignation) {
        this.matiereDesignation = matiereDesignation;
    }

    public void setSousMatiere(String sousMatiere) {
        this.sousMatiere = sousMatiere;
    }
}
