package glucometer.models;

public abstract class ObatAbstract {
    private String namaObat;
    private int dosis;
    private String bentuk;
    private String catatan;
    private String tanggal;
    
    public ObatAbstract(String namaObat, int dosis, String bentuk, String catatan, String tanggal) {
        this.namaObat = namaObat;
        this.dosis = dosis;
        this.bentuk = bentuk;
        this.catatan = catatan;
        this.tanggal = tanggal;
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
    public String getBentuk() {
        return bentuk;
    }
    public void setBentuk(String bentuk) {
        this.bentuk = bentuk;
    }
    public String getCatatan() {
        return catatan;
    }
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
    public String getTanggal() {
        return tanggal;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    
   
}
