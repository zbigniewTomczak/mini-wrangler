package tomczak.mini.wrangler.rule.engine;

import org.apache.commons.csv.CSVRecord;

class IntegerParser implements Transformer {
    @Override
    public String transform(CSVRecord record, String[] fromHeaders) {
        return "" + Integer.parseInt(record.get(fromHeaders[0]));
    }
}
