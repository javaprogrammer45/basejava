/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int size = 0;
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length - 1; i++) {
            storage[i + 1] = storage[i];
            storage[i] = r;
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
                storage[index] = storage[storage.length - 1];
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







