package AgendaContacte.ExtensionMethods;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Clasa destinata metodelor de validare.
 *
 * @author AndreiR
 */
public abstract class AgendaValidation {

    /**
     * Validam daca data introdusa este una corecta.
     *
     * @param year - an
     * @param month - luna
     * @param day - zi
     * @return
     */
    public static boolean isDateValid(int year, int month, int day) {
        boolean dateIsValid = true;
        try {
            //anul sa fie macar peste 1800 dar nu mai mare ca cel curent
            if (year > 1800 && year <= LocalDate.now().getYear()) {
                LocalDate.of(year, month, day);
            } else {
                dateIsValid = false;
            }

        } catch (DateTimeException e) {
            dateIsValid = false;
        }
        return dateIsValid;
    }

    /**
     * Metoda pentru a valida numele conform criterilor specificate.
     *
     * @param n - nume sau prenume
     * @return
     */
    public static boolean validareNume(String n) {
        boolean output = false;

        if (!n.isEmpty()) {
            //verificam daca primul caracter este litera
            if (Character.isAlphabetic(n.charAt(0))) {
                //si daca cuvantul este compus din minim 2 litere
                if (n.length() >= 2) {
                    output = true;
                }
            }
        }
        return output;
    }

}
