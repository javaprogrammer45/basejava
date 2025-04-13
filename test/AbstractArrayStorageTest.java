import com.basejava.exception.StorageException;
import com.basejava.model.Resume;
import com.basejava.storage.AbstractArrayStorage;
import com.basejava.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test()
    public void saveOverflow() throws Exception {
        try {
            for (int i = 3; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assertions.fail();
        }
        storage.save(new Resume());
    }
}
