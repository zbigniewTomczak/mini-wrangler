package tomczak.mini.wrangler.rule.engine;

import org.apache.commons.csv.CSVRecord;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

class BigDecimalParser implements Transformer {

    private String locale;

    public BigDecimalParser(String locale) {
        this.locale = locale;
    }

    @Override
    public String transform(CSVRecord record, String[] fromHeaders) {
        String value = record.get(fromHeaders[0]);
        return parse(value).toString();
    }

    BigDecimal parse(String value) {
        Locale locale = new Locale(this.locale);
        DecimalFormat nf = (DecimalFormat) NumberFormat.getInstance(locale);
        nf.setParseBigDecimal(true);
        return (BigDecimal)nf.parse(value, new ParsePosition(0));
    }
}
