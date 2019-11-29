package tomczak.mini.wrangler.rule.engine;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalParserTest {
    @Test
    public void testTransform() {
        //given
        String input = "5,250.50";
        BigDecimalParser parser = new BigDecimalParser("en-US");
        //when
        BigDecimal decimal = parser.parse(input);
        //then
        Assert.assertEquals(new BigDecimal("5250.50"), decimal);
    }

}