package AgendaContacte.Models;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {

    private int contactId;
    private Persoana contactPersoana;
    private NrTel numarTelefon;

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getContactId() {
        return contactId;
    }

    public Persoana getContactPersoana() {
        return contactPersoana;
    }

    public void setContactPersoana(Persoana contactPersoana) {
        this.contactPersoana = contactPersoana;
    }

    public NrTel getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(NrTel numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public Contact(Persoana contactPersoana, NrTel numarTelefon) {
        this.contactPersoana = contactPersoana;
        this.numarTelefon = numarTelefon;
    }

    public Contact(int contactId, Persoana contactPersoana, NrTel numarTelefon) {
        this.contactId = contactId;
        this.contactPersoana = contactPersoana;
        this.numarTelefon = numarTelefon;
    }

    public Contact() {
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
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.contactPersoana, other.contactPersoana)) {
            return false;
        }
        if (!Objects.equals(this.numarTelefon, other.numarTelefon)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contact{" + "contactPersoana=" + contactPersoana.toString() + ", numarTelefon=" + numarTelefon.toString() + '}';
    }
}
