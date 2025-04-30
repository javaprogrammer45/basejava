import com.basejava.model.Resume;
import com.basejava.storage.Storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private final static String DUMMY = "dummy";

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME1 = new Resume(UUID_1, "Name1");
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME2 = new Resume(UUID_2, "Name2");
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME3 = new Resume(UUID_3, "Name3");

    private static final Resume[] expected = new Resume[]{RESUME1, RESUME2, RESUME3};

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME1);
        storage.save(RESUME2);
        storage.save(RESUME3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertTrue(newResume == storage.get(UUID_1));
    }

    @Test()
    public void updateNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(RESUME1, RESUME2, RESUME3));
    }

    @Test
    public void save() {
        storage.save(RESUME3);
        assertSize(4);
        assertGet(RESUME3);
    }

    @Test()
    public void saveExist() {
        storage.save(RESUME1);
    }

    @Test()
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test()
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void get() {
        assertGet(RESUME1);
        assertGet(RESUME2);
        assertGet(RESUME3);
    }

    @Test()
    public void getNotExist() {
        storage.get("dummy");
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}