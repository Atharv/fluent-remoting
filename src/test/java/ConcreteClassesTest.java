import com.github.tasubo.FluentRmi;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConcreteClassesTest {


    @Test
    public void shouldWorkWithInterfacelessClasses() {

        FluentRmi.config().setPort(1299);
        FluentRmi.config().setHostname("localhost");

        FluentRmi.server().bind(new SomeClass()).to("someInterface");

        SomeClass remoteRmi = FluentRmi.client().get("someInterface").as(SomeClass.class);
        assertThat(remoteRmi.getText(), is("trying"));
    }


    public static class SomeClass {

        public String getText() {
            return "trying";
        }
    }
}
