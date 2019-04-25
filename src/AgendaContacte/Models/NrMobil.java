package AgendaContacte.Models;

import java.io.Serializable;

public class NrMobil extends NrTel implements Serializable {

    public NrMobil(String numarTelefon) {
        super(numarTelefon);
    }

    @Override
    public boolean validateNumar(String numarTelefon) {
        boolean output = false;

        if (numarTelefon.length() == 10) {
            char[] numere = numarTelefon.toCharArray();
            for (int i = 2; i < numere.length; i++) {
                if (Character.isDigit(numere[i])) {
                    output = true;
                } else {
                    output = false;
                    return output;
                }
            }  //conditii de validare pentru Numar Mobil ce incep cu prefixul 07
            if ("07".equals(numarTelefon.substring(0, 2))) {
                output = true;
            } else {
                output = false;
            }
        }
        return output;
    }

}
