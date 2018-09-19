import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JUnitTest.class)
public class JUnitTest {
    @Autowired
    ApplicationContext context;

    static Set<JUnitTest> testObjects = new HashSet<>();
    static ApplicationContext contextObject = null;

    @Test public void test1() {
        assertThat(testObjects,not(hasItem(this)));
        testObjects.add(this);
        assertThat(contextObject==null || contextObject==this.context, is(true));
        contextObject = this.context;
    }

    @Test public void test2() {
        assertThat(testObjects,not(hasItem(this)));
        testObjects.add(this);
        assertThat(contextObject==null || contextObject==this.context, is(true));
        contextObject = this.context;
    }

    @Test public void test3() {
        assertThat(testObjects,not(hasItem(this)));
        testObjects.add(this);
        assertThat(contextObject==null || contextObject==this.context, is(true));
        contextObject = this.context;
    }
}
