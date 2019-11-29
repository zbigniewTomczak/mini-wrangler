package tomczak.mini.wrangler.rule.engine;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.text.WordUtils;

class ProperCase implements Transformer {
    @Override
    public String transform(CSVRecord record, String[] fromHeaders) {
        return WordUtils.capitalizeFully(record.get(fromHeaders[0]));
    }
}
