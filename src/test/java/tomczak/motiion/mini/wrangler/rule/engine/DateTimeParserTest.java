package tomczak.mini.wrangler.rule.engine;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

public class DateTimeParserTest {

    @Test
    public void reformatDate() throws ParseException {
        //given
        DateTimeParser parser = new DateTimeParser("yyyy/M/d", "yyyy-MM-dd HH:mm");
        String inputDate = "2019/5/5";
        //when
        String outDate = parser.reformatDate(inputDate);
        //then
        Assert.assertEquals("2019-05-05 00:00", outDate);
    }

    @Test
    public void reformatDate2() throws ParseException {
        //given
        DateTimeParser parser = new DateTimeParser("yyyy/M/d", "yyyy-MM-dd HH:mm");
        String inputDate = "2019/12/12";
        //when
        String outDate = parser.reformatDate(inputDate);
        //then
        Assert.assertEquals("2019-12-12 00:00", outDate);
    }

}