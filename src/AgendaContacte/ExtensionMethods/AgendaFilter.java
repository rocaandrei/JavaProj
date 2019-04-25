package AgendaContacte.ExtensionMethods;

import AgendaContacte.Enums.CriteriuOrdonare;
import AgendaContacte.Models.Contact;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Filtrele noastre se regasesc in implementarea clasei abstracte.
 *
 * @author AndreiR
 */
public abstract class AgendaFilter {

    public static List<Contact> filtreazaNrFix(List<Contact> listaContacte) {
        List<Contact> listaNrFixe = listaContacte.stream()
                .filter(nrTelFix -> "03".equals(nrTelFix.getNumarTelefon().toString().substring(0, 2)) || "02".equals(nrTelFix.getNumarTelefon().toString().substring(0, 2)))
                .collect(Collectors.toList());

        return listaNrFixe;
    }

    public static List<Contact> filtreazaNrMobil(List<Contact> listaContacte) {
        List<Contact> listaNrMobile = listaContacte.stream()
                .filter(nrTelMob -> "07".equals(nrTelMob.getNumarTelefon().toString().substring(0, 2)))
                .collect(Collectors.toList());

        return listaNrMobile;
    }

    public static List<Contact> filtreazaNascutiAstazi(List<Contact> listaContacte) {
        List<Contact> nascutiAzi = listaContacte.stream().filter(contact -> contact.getContactPersoana()
                .getDataNasterii().getDayOfMonth() == LocalDate.now().getDayOfMonth()).collect(Collectors.toList());

        return nascutiAzi;
    }

    public static List<Contact> filtreazaNascutiLunaCurenta(List<Contact> listaContacte) {
        List<Contact> nascutiLunaCurenta = listaContacte.stream().filter(contact -> contact.getContactPersoana()
                .getDataNasterii().getMonth() == LocalDate.now().getMonth()).collect(Collectors.toList());

        return nascutiLunaCurenta;
    }

    public static List<Contact> filtreazaPersonalizat(List<Contact> listaContacte, String filtru) {

        List<Contact> nascutiLunaCurenta = listaContacte.stream().filter(new Predicate<Contact>() {
            @Override
            public boolean test(Contact param) {
                boolean isValid = false;
                if (param.getContactPersoana().getFullName(CriteriuOrdonare.DUPA_PRENUME).toLowerCase().contains(filtru.toLowerCase())
                        || param.getNumarTelefon().getNrTel().toLowerCase().contains(filtru.toLowerCase())) {
                    isValid = true;
                }
                return isValid;
            }
        }
        ).collect(Collectors.toList());

        return nascutiLunaCurenta;
    }

    public static List<Contact> ordoneazaContacte(List<Contact> listaOrdonata, CriteriuOrdonare criteriu) {

        switch (criteriu) {
            case DUPA_NUME:
                listaOrdonata.sort((contact1, contact2) -> contact1.getContactPersoana().getNume().compareTo(contact2.getContactPersoana().getNume()));
                break;
            case DUPA_PRENUME:
                listaOrdonata.sort((contact1, contact2) -> contact1.getContactPersoana().getPrenume().compareTo(contact2.getContactPersoana().getPrenume()));
                break;
        }
        return listaOrdonata;
    }

}
