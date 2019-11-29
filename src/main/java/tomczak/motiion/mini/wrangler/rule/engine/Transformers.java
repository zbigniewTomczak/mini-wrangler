package tomczak.mini.wrangler.rule.engine;

public class Transformers {
    public static Transformer IntegerParser() {
        return new IntegerParser();
    }
    public static Transformer StringParser() {
        return new StringParser();
    }

    public static Transformer ProperCase() {
        return new ProperCase();
    }

    public static Transformer BigDecimalParser(String locale) {
        return new BigDecimalParser(locale);
    }

    public static Transformer PlainFiller(String string) {
        return new PlainFiller(string);
    }

    public static Transformer DateTimeParser(String formatFrom, String formatTo) {
        return new DateTimeParser(formatFrom, formatTo);
    }

    public static Transformer Concatenator(String delimiter, Transformer transformer) {
        return new Concatenator(delimiter, transformer);
    }
}
