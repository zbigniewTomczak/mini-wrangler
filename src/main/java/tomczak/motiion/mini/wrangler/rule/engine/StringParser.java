package tomczak.mini.wrangler.rule.engine;

import org.apache.commons.csv.CSVRecord;

class StringParser implements Transformer {
    @Override
    public String transform(CSVRecord record, String[] fromHeaders) {
        return record.get(fromHeaders[0]);
    }
}
