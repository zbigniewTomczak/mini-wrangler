package tomczak.mini.wrangler.rule.engine;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TransformRulesBuilder {
    public static TransformRules Rules(TransformRuleBuilder... builders) {
        TransformRule[] rulesArray = Arrays.asList(builders).stream().map(b -> b.build()).collect(Collectors.toList()).toArray(new TransformRule[]{});
        return new TransformRules(rulesArray);
    }
}
