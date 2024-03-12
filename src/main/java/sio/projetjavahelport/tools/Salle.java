package sio.projetjavahelport.tools;

public class Salle {
    private int idSalle;
    private String codeSalle;
    private int etageSalle;

    public Salle(int idSalle, String codeSalle, int etageSalle) {
        this.idSalle = idSalle;
        this.codeSalle = codeSalle;
        this.etageSalle = etageSalle;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public String getCodeSalle() {
        return codeSalle;
    }

    public void setCodeSalle(String codeSalle) {
        this.codeSalle = codeSalle;
    }

    public int getEtageSalle() {
        return etageSalle;
    }

    public void setEtageSalle(int etageSalle) {
        this.etageSalle = etageSalle;
    }
}
