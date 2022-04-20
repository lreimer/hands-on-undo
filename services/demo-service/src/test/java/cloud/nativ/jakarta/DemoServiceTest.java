package cloud.nativ.jakarta;

import io.undo.lr.UndoLR;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static org.junit.Assert.*;

public class DemoServiceTest {

    /*
    @Rule
    public final TestRule wrapper =
            (base, description) -> new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    UndoLR.setEventLogSize(1000 * 1000 * 1000);
                    UndoLR.start();
                    try {
                        base.evaluate();
                    } catch (Throwable e) {
                        UndoLR.save(description + ".undo");
                    } finally {
                        UndoLR.stop();
                    }
                }
            };
     */

    private DemoService demoService;

    @Before
    public void setUp() throws Exception {
        demoService = new DemoService();
    }

    @Test
    public void getTestDemoMessage() {
        String message = demoService.getMessage("Test");
        assertEquals("Test Demo", message);
    }

    @Test
    public void getFailingDemoMessage() {
        String message = demoService.getMessage(null);
        assertEquals("Demo", message);
    }
}