package tomczak.mini.wrangler.rule.engine;

import org.apache.commons.csv.CSVRecord;

class TransformRule {
    private String header;
    private String[] fromHeaders;
    private Transformer transformer;

    public String getHeader() {
        return header;
    }

    public String getValue(CSVRecord record) throws Exception {
        return transformer.transform(record, fromHeaders);
    }

    public void setFromHeaders(String... headers) {
        this.fromHeaders = headers;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setTransformer(Transformer transformer) {
        this.transformer = transformer;
    }
}
