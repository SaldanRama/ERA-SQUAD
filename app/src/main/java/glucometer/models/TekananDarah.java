package glucometer.models;

public class TekananDarah {
    private int tekananSistolik;
    private int tekananDiastolik;
    private String tangan;
    private String catatan;
    private String tanggal;
    
    public TekananDarah(int tekananSistolik, int tekananDiastolik, String tangan, String catatan, String tanggal) {
        this.tekananSistolik = tekananSistolik;
        this.tekananDiastolik = tekananDiastolik;
        this.tangan = tangan;
        this.catatan = catatan;
        this.tanggal = tanggal;
    }
    
    public int getTekananSistolik() {
        return tekananSistolik;
    }
    public void setTekananSistolik(int tekananSistolik) {
        this.tekananSistolik = tekananSistolik;
    }
    public int getTekananDiastolik() {
        return tekananDiastolik;
    }
    public void setTekananDiastolik(int tekananDiastolik) {
        this.tekananDiastolik = tekananDiastolik;
    }
    public String getTangan() {
        return tangan;
    }
    public void setTangan(String tangan) {
        this.tangan = tangan;
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
