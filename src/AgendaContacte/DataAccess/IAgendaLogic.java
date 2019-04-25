package AgendaContacte.DataAccess;

import AgendaContacte.Models.Contact;
import java.util.List;

/**
 * Metodele CRUD necesare Agendei.
 *
 * @author AndreiR
 */
public interface IAgendaLogic {

    public void adaugaContact(Contact c);
    List<Contact> getAllContacts();
    public void setAllContacts(List<Contact> allContacts);
    public void modificaContact(Contact vechi, Contact nou);

    //e mai mult o situatie de avarie pentru metoda stergeContact() de a avea doi parametrii, ideea este ca pentru conexiunea cu baza de date as avea nevoie si de id-ul 
    //selectat ca index din JList cat si de cel din baza de date - pentru info comparati implementarea din  AgendaObject si AgendaDatabaseConnection
    //todo: refactor stergeContact method - needs improvement...
    public void stergeContact(int idContactObjectType, int idContactDatabaseType);

}
