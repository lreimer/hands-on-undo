package hands.on.undo.integration;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.microprofile.config.spi.Converter;

public class UriConverter implements Converter<URI> {

    @Override
    public URI convert(String value) {
        if (value == null || "".equals(value)) {
            return null;
        }
        
        try {
            return new URI(value);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
}
