package glucometer.models;

public class BeratBadan {
    private int beratBadan;
    private String catatan;
    
    public BeratBadan(int beratBadan, String catatan) {
        this.beratBadan = beratBadan;
        this.catatan = catatan;
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
    
}

