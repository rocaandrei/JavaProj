
package AgendaContacte.ExceptionClasses;

/**
 *Clasa utila in aruncarea exceptiei legata de formatul numelui.
 * @author AndreiR
 */
public class NumePersoanaException extends Exception {

    private String nume;

    public NumePersoanaException(String nume, String message) {
        super(message);
        this.nume = nume;
    }
}
