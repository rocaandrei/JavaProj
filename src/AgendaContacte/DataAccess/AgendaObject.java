package AgendaContacte.DataAccess;

import AgendaContacte.ExceptionClasses.NumePersoanaException;
import AgendaContacte.Models.Contact;
import AgendaContacte.Models.NrFix;
import AgendaContacte.Models.NrMobil;
import AgendaContacte.Models.Persoana;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Aici vom genera agenda doar sub forma de obiecte, fara a le salva undeva
 * anume
 *
 * @author AndreiR
 */
public class AgendaObject implements IAgendaLogic {

    private List<Contact> listaContacte;

    public AgendaObject() {
        this.listaContacte = new ArrayList<Contact>();
    }

    @Override
    public void adaugaContact(Contact c) {
        if (c != null) {
            this.listaContacte.add(c);
        }
    }

    //Metoda utilizata mai mult pentru test.
    public void objectAllContacts() {
        List<Contact> contacte = new ArrayList<>();
        try {
            contacte.add(new Contact(new Persoana("Roca", "Andrei", LocalDate.of(1991, 1, 5)), new NrMobil("0756493104")));
            contacte.add(new Contact(new Persoana("Axinte", "Ion", LocalDate.of(2000, 6, 12)), new NrFix("0265789696")));
            contacte.add(new Contact(new Persoana("Escu", "Elena", LocalDate.now()), new NrFix("0365789696")));
            contacte.add(new Contact(new Persoana("Ciobanu", "Irina", LocalDate.of(1991, 7, 19)), new NrMobil("0756493999")));

        } catch (NumePersoanaException ex) {
            System.out.println(ex.getMessage());
        }
        this.listaContacte = contacte;
    }

    @Override
    public List<Contact> getAllContacts() {
        return listaContacte;
    }

    /**
     * Verificam mai intai daca nr de tel al contactului de actualizat exista,
     * iar daca exista in stergem cu totul contactul din lista si-l adaugam in
     * locul lui pe cel actualizat cu datele noi.
     */
    @Override
    public void modificaContact(Contact ContactVechi, Contact contactActualizat) {

        this.listaContacte.remove(ContactVechi);
        this.listaContacte.add(contactActualizat);
    }

    @Override
    public void stergeContact(int idContact, int idContactDB) {
        if (idContact >= 0) {
            this.listaContacte.remove(idContact);
        }
    }

    //Utila pentru serializare.
    @Override
    public void setAllContacts(List<Contact> allContacts) {
        this.listaContacte = allContacts;
    }

}
