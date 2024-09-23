import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {

            if (r != null) {
                storage[i] = r;
            } else if (r == null) {
                Resume r1 = new Resume();
                r1 = storage[i + 1];
                storage[i + 1] = storage[i];
                storage[i] = r;
            }


        }
    }

    Resume get(String uuid) {

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                if (storage[i].uuid.equals(uuid)) {
                    return storage[i];
                }
            } else continue;
        }
        return new Resume();
    }

    void delete(String uuid) {
        Resume[] resume1 = new Resume[storage.length - 1];
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i].uuid.equals(uuid)) {
                int index = i;
                System.arraycopy(storage, i, resume1, i, index);
                System.arraycopy(storage, index + 1, resume1, index, storage.length - index - 1);
            }
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                return new Resume[]{storage[i]};
            } else continue;
        }
        return new Resume[0];
    }

    int size() {
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                size += 1;
                return size;
            }
        }
        return 0;
    }
}







