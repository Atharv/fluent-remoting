import com.github.tasubo.FluentRmi;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static com.github.tasubo.FluentRmi.config;
import static com.github.tasubo.FluentRmi.server;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NestedCallsTest {

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
        assertThat(remoteRmi.getNested().getCount(), is(0));


        remoteRmi = FluentRmi.client().get("someInterface").as(SomeInterface.class);
        assertThat(remoteRmi.getNested().getCount(), is(1));

    }

    public static class Nested implements Serializable {
        private static int count = 0;

        public int getCount() {
            return count++;
        }
    }

    public static interface SomeInterface {
        Nested getNested();
    }

    public static class SomeInterfaceImplementation implements SomeInterface {

        @Override
        public Nested getNested() {
            return new Nested();
        }
    }
}
