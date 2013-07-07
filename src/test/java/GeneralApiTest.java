import com.github.tasubo.FluentRmi;
import org.junit.Before;
import org.junit.Test;

import static com.github.tasubo.FluentRmi.config;
import static com.github.tasubo.FluentRmi.server;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GeneralApiTest {

    static SomeInterface object = new SomeInterfaceImplementation();

    @Before
    public void setUp() {
        server().kill();
        config().reset();
    }

    @Test
    public void howServerShouldWork() {

        FluentRmi.config().setPort(1299);
        FluentRmi.config().setHostname("localhost");

        FluentRmi.server().bind(object).to("someInterface");

        SomeInterface remoteRmi = FluentRmi.client().get("someInterface").as(SomeInterface.class);


        assertThat(remoteRmi.getText(), is("test test"));

    }

    public static interface SomeInterface {
        String getText();
    }

    public static class SomeInterfaceImplementation implements SomeInterface {

        @Override
        public String getText() {
            return "test test";
        }
    }
}
