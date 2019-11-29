package tomczak.mini.wrangler;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tomczak.mini.wrangler.rule.engine.TransformRules;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static tomczak.mini.wrangler.rule.engine.TransformRuleBuilder.Column;
import static tomczak.mini.wrangler.rule.engine.TransformRulesBuilder.Rules;
import static tomczak.mini.wrangler.rule.engine.Transformers.*;

public class ReadWriteBigCsv {

    private static final int rows = 375000;
    private static final String fileName = "test.csv";
    private static final String outFileName = "out.csv";

    @Before
    public void setUp() throws Exception {
        FileWriter out = new FileWriter(fileName);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader("Order Number","Year","Month","Day","Product Number","Product Name","Count"))) {
            for (int i = 0; i < rows; i ++) {
                printer.printRecord("11","2019","05","05","22","aa bb","111.111");
            }
        }
    }

    @After
    public void tearDown() {
        new File(fileName).delete();
        new File(outFileName).delete();
    }

    @Test
    public void transformCsv() throws IOException {
        TransformRules rules = getRules();
        CSVWrangler wrangler = new CSVWrangler(fileName, rules, outFileName);
        wrangler.transform();
        Assert.assertTrue(new File(outFileName).exists());
    }


    private TransformRules getRules() {
        return Rules(
                Column("OrderID").from("Order Number").transformWith(IntegerParser()),
                Column("OrderDate").from("Year","Month","Day").transformWith(Concatenator("/", DateTimeParser("yyyy/M/d", "yyyy-MM-dd HH:mm"))),
                Column("ProductID").from("Product Number").transformWith(StringParser()),
                Column("ProductName").from("Product Name").transformWith(ProperCase()),
                Column("Quantity").from("Count").transformWith(BigDecimalParser("en-US")),
                Column("Unit").transformWith(PlainFiller("kg"))
        );
    }
}
