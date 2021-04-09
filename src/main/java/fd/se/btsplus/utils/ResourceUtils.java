package fd.se.btsplus.utils;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
@Component
public class ResourceUtils {

    public String readFileAsString(String path) throws IOException {
        final InputStream input = this.getClass().getClassLoader().getResourceAsStream(path);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        assert input != null;
        IOUtils.copy(input, output);
        return output.toString();
    }

}
