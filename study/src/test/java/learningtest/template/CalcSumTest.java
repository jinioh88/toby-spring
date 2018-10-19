package learningtest.template;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class CalcSumTest {
    Calculator calculator;
    String numFIlepath;

    @Before
    public void setUp() {
        this.calculator = new Calculator();
        this.numFIlepath = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        assertThat(calculator.calcSum(numFIlepath),is(10));
    }

    @Test
    public void multipyOfNumbers() throws IOException {
        assertThat(calculator.calMultiply(this.numFIlepath),is(24));
    }

    @Test
    public void concatenateStrings() throws IOException {
        assertThat(calculator.concatenate(this.numFIlepath), is("1234"));
    }
}
