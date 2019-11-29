package tomczak.mini.wrangler;

public class ExampleUsage {
    public static void main(String[] args) {
        CSVWrangler wrangler = new CSVWrangler("in.csv", ExampleDsl.getDsl(), "out.csv");
        wrangler.transform();
    }
}
