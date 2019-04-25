package AgendaContacte.Models;

import java.io.Serializable;
import java.util.Objects;

public abstract class NrTel implements Comparable, Serializable{

    private String numarTelefon;

    public String getNrTel() {
        return numarTelefon;
    }
    
    public NrTel(String nrTel) {
        if (validateNumar(nrTel)) {
            this.numarTelefon = nrTel;
        } else {
            this.numarTelefon = "N/A";
        }
    }
    
    @Override
    public int compareTo(Object o) {
        if (this.numarTelefon.charAt(0) > ((NrTel) o).numarTelefon.charAt(0)) {
            return 1;
        } else if (this.numarTelefon.charAt(0) < ((NrTel) o).numarTelefon.charAt(0)) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NrTel other = (NrTel) obj;
        if (!Objects.equals(this.numarTelefon, other.numarTelefon)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numarTelefon;
    }

    public abstract boolean validateNumar(String numarTelefon);
}
