import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyMode;
import org.junit.Test;
import test.TestPageAction;

import java.nio.file.Paths;

/**
 * Created by i331123 (Luke) on 18/09/2017.
 */
public class HoverflyTest {

    @Test
    public void testHoverfly() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver");
        boolean hoverFlyToggle = (System.getProperty("hoverfly")!=null) ? Boolean.valueOf(System.getProperty("hoverfly")) : false;
        if (hoverFlyToggle) {
            inHoverflyCaptureMode();
        } else {
            noHoverfly();
        }
    }


    private void noHoverfly() {
        new TestPageAction().logInNoHoverfly();
    }

    private void inHoverflyCaptureMode() {
        Hoverfly hoverfly = new Hoverfly(HoverflyMode.CAPTURE);
        hoverfly.start();
        int portNumber = hoverfly.getHoverflyConfig().getProxyPort();
        new TestPageAction().logInWithHoverfly(portNumber);
        hoverfly.exportSimulation(Paths.get("simulations/testSimulation.json"));
    }
}
