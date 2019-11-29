package tomczak.mini.wrangler.rule.engine;

public class TransformRuleBuilder {
    private final TransformRule transformRule;

    private TransformRuleBuilder(String columnName) {
        this.transformRule = new TransformRule();
        this.transformRule.setHeader(columnName);
    }

    public static TransformRuleBuilder Column(String columnName) {
        return new TransformRuleBuilder(columnName);
    }

    public TransformRuleBuilder from(String... columnNames) {
        transformRule.setFromHeaders(columnNames);
        return this;
    }

    public TransformRuleBuilder transformWith(Transformer transformer) {
        transformRule.setTransformer(transformer);
        return this;
    }

    TransformRule build() {
        return this.transformRule;
    }
}
