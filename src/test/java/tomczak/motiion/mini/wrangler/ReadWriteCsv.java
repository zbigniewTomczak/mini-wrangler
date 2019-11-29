package tomczak.mini.wrangler;

import org.junit.Assert;
import org.junit.Test;
import tomczak.mini.wrangler.rule.engine.TransformRules;

import java.io.*;

import static tomczak.mini.wrangler.rule.engine.TransformRuleBuilder.Column;
import static tomczak.mini.wrangler.rule.engine.TransformRulesBuilder.Rules;
import static tomczak.mini.wrangler.rule.engine.Transformers.*;

public class ReadWriteCsv {

    @Test
    public void transformCsv() throws IOException {
            TransformRules rules = getRules();
            Reader in = new StringReader(
                    "Order Number,Year,Month,Day,Product Number,Product Name,Count\n" +
                    "11,2019,05,05,22,aa bb,111.111");
            Writer out = new StringWriter();
            CSVWrangler wrangler = new CSVWrangler(null, rules, null);
            wrangler.process(in, out);
            Assert.assertEquals(
                    "OrderID,OrderDate,ProductID,ProductName,Quantity,Unit" + System.lineSeparator() +
                    "11,2019-05-05 00:00,22,Aa Bb,111.111,kg"  + System.lineSeparator(),
                    out.toString());


        }

    private TransformRules getRules() {
        return Rules(
                Column("OrderID").from("Order Number").transformWith(IntegerParser()),
                Column("OrderDate").from("Year","Month","Day").transformWith(Concatenator("/",DateTimeParser("yyyy/M/d", "yyyy-MM-dd HH:mm"))),
                Column("ProductID").from("Product Number").transformWith(StringParser()),
                Column("ProductName").from("Product Name").transformWith(ProperCase()),
                Column("Quantity").from("Count").transformWith(BigDecimalParser("en-US")),
                Column("Unit").transformWith(PlainFiller("kg"))
        );
    }

}
