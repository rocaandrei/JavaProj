package AgendaContacte.ExtensionMethods;

import AgendaContacte.Models.Contact;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa menita sa ne ajute in functionalitatea serializarii/deserializarii
 * agendei.
 *
 * @author AndreiR
 */
public class AgendaIO {

    public static void saveToFile(List<Contact> listOfContacts, File caleFisier) {

        try {
            FileOutputStream outStream = new FileOutputStream(caleFisier);
            ObjectOutputStream outContacts = new ObjectOutputStream(outStream);
            outContacts.writeObject(listOfContacts);
            outStream.close();
            System.out.println("The list is saved!");

        } catch (FileNotFoundException ex) {
            System.out.println("Can't serialize this file.\n" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Can't write this file.\n" + ex.getMessage());
        }
    }

    public static List<Contact> openContactsFile(File caleFisier) {

        List<Contact> savedContacts = new ArrayList<Contact>();

        try {
            FileInputStream inputStream = new FileInputStream(caleFisier);
            ObjectInputStream inputListOfContacts = new ObjectInputStream(inputStream);
            savedContacts = (List<Contact>) inputListOfContacts.readObject();
            System.out.println("The object is loaded!");
            inputStream.close();
            inputListOfContacts.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Can't connect to the stream.\n" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Can't colect the list of contacts.\n" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Cand find the object cast Class.\n" + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("The list is " + ex.getMessage());
        }
        return savedContacts;
    }
}
