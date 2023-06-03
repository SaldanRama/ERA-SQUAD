package glucometer.models;

public abstract class BeratBadanAbstract {
    private int beratBadan;
    private String catatan;
    private String tanggal;

    public BeratBadanAbstract(int beratBadan, String catatan, String tanggal) {
        this.beratBadan = beratBadan;
        this.catatan = catatan;
        this.tanggal = tanggal;
    }
    
    public int getBeratBadan() {
        return beratBadan;
    }
    public void setBeratBadan(int beratBadan) {
        this.beratBadan = beratBadan;
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
