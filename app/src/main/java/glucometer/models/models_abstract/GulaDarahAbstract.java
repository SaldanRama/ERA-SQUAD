package glucometer.models.models_abstract;

public abstract class GulaDarahAbstract {
    private int gulaDarah;
    private String waktu;
    private String catatan;
    private String tanggal;
    
    public GulaDarahAbstract(int gulaDarah, String waktu, String catatan, String tanggal) {
        this.gulaDarah = gulaDarah;
        this.waktu = waktu;
        this.catatan = catatan;
        this.tanggal = tanggal;
    }
    
    public int getGulaDarah() {
        return gulaDarah;
    }
    public void setGulaDarah(int gulaDarah) {
        this.gulaDarah = gulaDarah;
    }
    public String getWaktu() {
        return waktu;
    }
    public void setWaktu(String waktu) {
        this.waktu = waktu;
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
