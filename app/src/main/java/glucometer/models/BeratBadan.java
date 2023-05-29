package glucometer.models;

public class BeratBadan {
    private int berat;
    private String catatan;
    
    public BeratBadan(int berat, String catatan) {
        this.berat = berat;
        this.catatan = catatan;
    }
    
    public int getBerat() {
        return berat;
    }
    public void setBerat(int berat) {
        this.berat = berat;
    }
    public String getCatatan() {
        return catatan;
    }
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}

