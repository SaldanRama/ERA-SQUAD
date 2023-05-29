package glucometer.models;

public class Obat {
    private String namaObat;
    private int dosis;
    private String catatan;
    
    public Obat(String namaObat, int dosis, String catatan) {
        this.namaObat = namaObat;
        this.dosis = dosis;
        this.catatan = catatan;
    }
    
    public String getNamaObat() {
        return namaObat;
    }
    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }
    public int getDosis() {
        return dosis;
    }
    public void setDosis(int dosis) {
        this.dosis = dosis;
    }
    public String getCatatan() {
        return catatan;
    }
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}

