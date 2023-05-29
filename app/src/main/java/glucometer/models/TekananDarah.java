package glucometer.models;

public class TekananDarah {
    private int tekananSistolik;
    private int tekananDiastolik;
    private String catatan;
    
    public TekananDarah(int tekananSistolik, int tekananDiastolik, String catatan) {
        this.tekananSistolik = tekananSistolik;
        this.tekananDiastolik = tekananDiastolik;
        this.catatan = catatan;
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
    public String getCatatan() {
        return catatan;
    }
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
