package tomczak.mini.wrangler.rule.engine;

import org.apache.commons.csv.CSVRecord;

class PlainFiller implements Transformer {
    private final String filler;

    public PlainFiller(String string) {
        this.filler = string;
    }

    @Override
    public String transform(CSVRecord record, String[] fromHeaders) {
        return filler;
    }
}
