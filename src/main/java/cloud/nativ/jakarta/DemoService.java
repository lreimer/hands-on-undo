package cloud.nativ.jakarta;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DemoService {
    public String getMessage(String name) {
        if (name == null) {
            throw new NullPointerException("Demo name must not be NULL.");
        } else {
            return name + " Demo";
        }
    }
}
