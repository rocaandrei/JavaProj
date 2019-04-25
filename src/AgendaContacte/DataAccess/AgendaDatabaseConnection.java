package AgendaContacte.DataAccess;

import AgendaContacte.ExceptionClasses.NumePersoanaException;
import AgendaContacte.Models.Contact;
import AgendaContacte.Models.NrFix;
import AgendaContacte.Models.NrMobil;
import AgendaContacte.Models.NrTel;
import AgendaContacte.Models.Persoana;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Aici ne vom genera agenda din mySQL.
 *
 * @author AndreiR
 */
public class AgendaDatabaseConnection implements IAgendaLogic {

    private List<Contact> contacte;
    private int idContact;
    private final Connection dbConnection;

    public AgendaDatabaseConnection() {
        this.contacte = new ArrayList<>();
        MySQLDatabaseConnection database = MySQLDatabaseConnection.getDbConnect();
        dbConnection = database.getConnection();
    }

    public void dbAllContacts() {
        //exemplu de try with resources
        try (Statement st = dbConnection.createStatement()) {
            String selectQuery = "SELECT * FROM agendatable ORDER BY lastName ASC;";
            try (ResultSet resSet = st.executeQuery(selectQuery)) {
                while (resSet.next()) {

                    int contactId = resSet.getInt("id");
                    String firstName = resSet.getString("firstName");
                    String lastName = resSet.getString("lastName");
                    LocalDate dateOfBirth = resSet.getDate("dateOfBirth").toLocalDate();
                    String phone = resSet.getString("phoneNumber");

                    Persoana newPers = new Persoana(lastName, firstName, dateOfBirth);
                    NrTel newTel;

                    if (phone.substring(0, 2).equals("02") || phone.substring(0, 2).equals("03")) {
                        newTel = new NrFix(phone);
                    } else if (phone.substring(0, 2).equals("07")) {
                        newTel = new NrMobil(phone);
                    } else {
                        System.out.println("Eroare numarul de telefon nu e corect... de verificat.");
                        return;
                    }
                    //pe baza ResultSet ne cream un contact nou ce mai departe este adaugat in lista de contacte pentru manipularea ei mai rapida.
                    Contact newContact = new Contact(contactId, newPers, newTel);
                    this.contacte.add(newContact);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Eroare... verificati conexiunea.\n" + ex.getMessage());
        } catch (NumePersoanaException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void adaugaContact(Contact contact) {

        String firstName = contact.getContactPersoana().getPrenume();
        String lastName = contact.getContactPersoana().getNume();
        LocalDate dateOfBirth = contact.getContactPersoana().getDataNasterii();
        String phone = contact.getNumarTelefon().getNrTel();

        try {
            Statement st = dbConnection.createStatement();
            String insertQuery = "INSERT INTO agendatable (firstName, lastName, dateOfBirth, phoneNumber) VALUES ('"
                    + firstName + "', '" + lastName + "', '" + dateOfBirth + "','" + phone + "');";

            st.execute(insertQuery);
            System.out.println("insert done!");
            contacte.add(contact);
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        return this.contacte;
    }

    @Override
    public void modificaContact(Contact vechi, Contact nou) {

        idContact = vechi.getContactId();
        String firstName = nou.getContactPersoana().getPrenume();
        String lastName = nou.getContactPersoana().getNume();
        LocalDate dateOfBirth = nou.getContactPersoana().getDataNasterii();
        String phone = nou.getNumarTelefon().getNrTel();

        try {
            Statement st = dbConnection.createStatement();
            String updateQuery = "UPDATE agendatable set firstName='" + firstName + "', lastName ='" + lastName
                    + "', dateOfBirth='" + dateOfBirth + "', phoneNumber='" + phone + "' WHERE id = " + idContact;
            st.execute(updateQuery);

            this.contacte.remove(vechi);
            this.contacte.add(nou);

            st.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void stergeContact(int contactObjectIndex, int dbContactID) {

        try {
            Statement st = dbConnection.createStatement();
            String deleteQuery = "DELETE FROM agendatable WHERE id = " + dbContactID + ";";

            //ce ziceam... ca sa fie bine stergem si din baza de date si din sql
            st.execute(deleteQuery);
            contacte.remove(contactObjectIndex);

            st.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void setAllContacts(List<Contact> allContacts) {
        contacte = allContacts;
    }
}
