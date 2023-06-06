package glucometer.models;

import glucometer.models.models_abstract.TekananDarahAbstract;

public class TekananDarah extends TekananDarahAbstract {

    public TekananDarah(int tekananSistolik, int tekananDiastolik, String tangan, String catatan, String tanggal) {
        super(tekananSistolik, tekananDiastolik, tangan, catatan, tanggal);
    }
   
}
