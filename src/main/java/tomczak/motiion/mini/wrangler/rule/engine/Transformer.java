package tomczak.mini.wrangler.rule.engine;

import org.apache.commons.csv.CSVRecord;

public interface Transformer {
    String transform(CSVRecord record, String... fromHeaders) throws Exception;
}
