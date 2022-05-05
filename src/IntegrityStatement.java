
public final class IntegrityStatement {
    public static String signature() {
        String names = "Amit Madmoni 209502376, Roey Lange 318568383"; // <- Fill in your names here!
        if (names.length() == 0) {
            throw new UnsupportedOperationException("You should sign here");
        }
        return names;
    }
}
