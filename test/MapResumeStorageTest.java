import com.basejava.model.Resume;
import com.basejava.storage.MapResumeStorage;

import java.util.HashMap;

public class MapResumeStorageTest extends AbstractStorageTest {
    public MapResumeStorageTest() {
        super(new MapResumeStorage());
    }
}
