package AgendaContacte.Models;

import AgendaContacte.Enums.CriteriuOrdonare;
import AgendaContacte.ExceptionClasses.NumePersoanaException;
import AgendaContacte.ExtensionMethods.AgendaValidation;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class Persoana implements Serializable {

    private String nume;
    private String prenume;
    private LocalDate dataNasterii;

    /**
     *
     * @param nume
     * @param prenume
     * @param dataNasterii
     * @throws AgendaContacte.ExceptionClasses.NumePersoanaException
     */
    public Persoana(String nume, String prenume, LocalDate dataNasterii) throws NumePersoanaException {

        if (AgendaValidation.validareNume(nume)) {
            this.nume = nume;
        } else {
            throw new NumePersoanaException(nume, "Numele introdus nu este valid");
        }
        if (AgendaValidation.validareNume(prenume)) {
            this.prenume = prenume;
        } else {
            throw new NumePersoanaException(prenume, "Prenumele introdus nu este valid.");
        }
        this.dataNasterii = dataNasterii;
    }
    
    @Override
    public boolean equals(Object pers) {
        if (this == pers) {
            return true;
        }
        if (pers == null) {
            return false;
        }
        if (getClass() != pers.getClass()) {
            return false;
        }
        final Persoana other = (Persoana) pers;
        if (!Objects.equals(this.nume, other.nume)) {
            return false;
        }
        if (!Objects.equals(this.prenume, other.prenume)) {
            return false;
        }
        if (!Objects.equals(this.dataNasterii, other.dataNasterii)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getFullName(CriteriuOrdonare.DUPA_NUME) +", "
                + this.dataNasterii.format(DateTimeFormatter.ofPattern("dd.MM.YYYY", Locale.forLanguageTag("RO")))+ ", ";
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setDataNasterii(LocalDate dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    /**
     * Returneaza numele intreg conform enum CriteriuOrdonare
     *
     * @param crt - Enum CriteriuOrdonare
     * @return Numele intreg
     */
    public String getFullName(CriteriuOrdonare crt) {
        switch (crt) {
            case DUPA_NUME:
                return nume + " " + prenume;
            case DUPA_PRENUME:
                return prenume + " " + nume;
            default:
                return null;
        }
    }

    public LocalDate getDataNasterii() {
        return dataNasterii;
    }

}
