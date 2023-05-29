package glucometer.models;

public class GulaDarah {
    private int gulaDarah;
    private String catatan;

    public GulaDarah(int gulaDarah, String catatan) {
        this.gulaDarah = gulaDarah;
        this.catatan = catatan;
    }
    
    public int getGulaDarah() {
        return gulaDarah;
    }

    public void setGulaDarah(int gulaDarah) {
        this.gulaDarah = gulaDarah;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

}

