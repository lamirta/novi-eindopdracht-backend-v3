package nl.novi.eindopdrachtv3.exceptions;

public class TitleNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TitleNotFoundException(String title) {
        super("Kan woordenlijst " + title + " niet vinden.");
    }

}
