package sio.projetjavahelport.tools;

import java.util.Date;

public class Demande {
    private String niveau;
    private Date dateFinDemande;
    private String matiereDesignation;
    private String sousMatiere;

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