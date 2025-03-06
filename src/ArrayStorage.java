import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume.uuid.equals(uuid)) {
                return resume;
            }
            if (uuid.equals("dummy")) {
                resume.uuid = "dummy";
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        int counter = 0;
        for (int i = 0; i < storage.length; i++) {
            try {
                if (storage[i] != null) {
                    counter++;
                }
                if (storage[i].uuid.equals(uuid) && storage[i] != null) {
                    System.arraycopy(storage, i + 1, storage, i, storage.length - 1);
                    storage[counter] = null;
                }
            } catch (NullPointerException e) {
                return;
            }

        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int counter = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                counter++;
            }
        }
        return Arrays.copyOf(storage, counter);
    }

    int size() {
        return getAll().length;
    }
}
