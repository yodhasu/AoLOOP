import java.io.IOException;
import java.util.List;

public interface ProductRepository {
    List<String> load() throws IOException;
    void save(List<String> data) throws IOException;
    void append(List<String> data) throws IOException;
}
