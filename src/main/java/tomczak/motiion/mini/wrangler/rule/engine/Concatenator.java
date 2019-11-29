package tomczak.mini.wrangler.rule.engine;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Concatenator implements Transformer {
    private static final String intermediateHeader = "Concatenator";
    private String delimiter;
    private Transformer transformer;

    public Concatenator(String delimiter, Transformer transformer) {
        this.delimiter = delimiter;
        this.transformer = transformer;
    }

    @Override
    public String transform(CSVRecord record, String[] fromHeaders) throws Exception {
        List<String> values = Arrays.asList(fromHeaders).stream().map(h -> record.get(h)).collect(Collectors.toList());
        String value = values.stream().collect(Collectors.joining(delimiter));
        Reader in = new StringReader(intermediateHeader + System.lineSeparator() + value);
        try (CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in)) {
            CSVRecord intermediateRecord = parser.iterator().next();
            return transformer.transform(intermediateRecord, intermediateHeader);
        }

    }
}
