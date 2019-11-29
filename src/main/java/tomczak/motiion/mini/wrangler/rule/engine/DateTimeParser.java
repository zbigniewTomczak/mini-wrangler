package tomczak.mini.wrangler.rule.engine;

import org.apache.commons.csv.CSVRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class DateTimeParser implements Transformer {
    private final SimpleDateFormat formatFrom;
    private final SimpleDateFormat formatTo;

    public DateTimeParser(String formatFrom, String formatTo) {
        this.formatFrom = new SimpleDateFormat(formatFrom);
        this.formatTo = new SimpleDateFormat(formatTo);
    }

    @Override
    public String transform(CSVRecord record, String[] fromHeaders) throws ParseException {
        return reformatDate(record.get(fromHeaders[0]));
    }

    String reformatDate(String dateString) throws ParseException {
        return formatTo.format(formatFrom.parse(dateString));
    }

}
