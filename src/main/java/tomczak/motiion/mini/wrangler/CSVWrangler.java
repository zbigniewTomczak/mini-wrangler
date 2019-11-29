package tomczak.mini.wrangler;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import tomczak.mini.wrangler.rule.engine.TransformRules;

import java.io.*;
import java.util.logging.Logger;

public class CSVWrangler {
    private final static Logger LOG = Logger.getLogger(CSVWrangler.class.getName());
    private String inputCSVFile;
    private TransformRules rules;
    private String outputCSVFile;


    public CSVWrangler(String inputCSVFile, TransformRules rules, String outputCSVFile) {
        this.inputCSVFile = inputCSVFile;
        this.rules = rules;
        this.outputCSVFile = outputCSVFile;
    }

    public int transform() {
        try (Reader in = new FileReader(inputCSVFile); Writer out = new FileWriter(outputCSVFile)) {
            return process(in, out);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot open file: " + inputCSVFile, e);
        } catch (IOException e) {
            throw new RuntimeException("Error read/write csv", e);
        }

    }

    @SuppressWarnings("")
    int process(Reader in, Writer out) throws IOException {
        int successfullyProcessedRows = 0;
        CSVFormat formatIn = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        try (CSVParser parser = formatIn.parse(in)) {
            String[] headers = rules.getHeaders();
            CSVFormat formatOut = CSVFormat.DEFAULT.withHeader(headers);
            try (CSVPrinter printer = new CSVPrinter(out, formatOut)) {
                for (CSVRecord record : parser) {
                    try {
                        String[] values = rules.getRowValues(record);
                        printer.printRecord((Object[]) values);
                        successfullyProcessedRows++;
                    } catch (Exception e) {
                        LOG.info("Parse error in row: " + record.getRecordNumber() + " " + e.toString());
                    }
                }
            }
        }
        return successfullyProcessedRows;
    }

}
