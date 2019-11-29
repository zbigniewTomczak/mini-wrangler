package tomczak.mini.wrangler;

import tomczak.mini.wrangler.rule.engine.TransformRules;

import static tomczak.mini.wrangler.rule.engine.TransformRuleBuilder.Column;
import static tomczak.mini.wrangler.rule.engine.TransformRulesBuilder.Rules;
import static tomczak.mini.wrangler.rule.engine.Transformers.*;

public class ExampleDsl {
    public static TransformRules getDsl() {
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
