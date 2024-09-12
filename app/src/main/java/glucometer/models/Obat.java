package glucometer.models;

import glucometer.models.models_abstract.ObatAbstract;

public class Obat extends ObatAbstract {

    public Obat(String namaObat, int dosis, String bentuk, String catatan, String tanggal) {
        super(namaObat, dosis, bentuk, catatan, tanggal);
    }   
}


