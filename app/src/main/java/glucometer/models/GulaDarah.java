package glucometer.models;

public class GulaDarah {
    private int gulaDarah;
    private String waktu;
    private String catatan;
    
    public GulaDarah(int gulaDarah, String waktu, String catatan) {
        this.gulaDarah = gulaDarah;
        this.waktu = waktu;
        this.catatan = catatan;
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

   

}

