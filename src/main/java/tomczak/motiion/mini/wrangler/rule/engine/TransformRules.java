package tomczak.mini.wrangler.rule.engine;

import org.apache.commons.csv.CSVRecord;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TransformRules {

    private final List<TransformRule> rules;

    public TransformRules(TransformRule... rules) {
        this.rules = Arrays.asList(rules);
    }

    public String[] getHeaders() {
        return rules.stream().map(r -> r.getHeader()).collect(Collectors.toList()).toArray(new String[]{});
    }

    public String[] getRowValues(CSVRecord record) throws Exception {
        String[] rowValue = new String[rules.size()];
        for (int i = 0; i < rules.size(); i++) {
            rowValue[i] = rules.get(i).getValue(record);
        }
        return rowValue;
    }
}
