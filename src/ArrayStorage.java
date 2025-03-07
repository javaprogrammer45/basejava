import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                size++;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null) {
                if (resume.uuid.equals(uuid)) {
                    return resume;
                }
            } else {
                break;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                if (storage[i].uuid.equals(uuid)) {
                    System.arraycopy(storage, i + 1, storage, i, storage.length - 1);
                    storage[size - 1] = null;
                }
            } else {
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int counter=0;
        for (Resume resume : storage) {
            if (resume != null) {
                counter++;
            }
        }
        return Arrays.copyOf(storage, counter);
    }

    int size() {
        return size;
    }
}
