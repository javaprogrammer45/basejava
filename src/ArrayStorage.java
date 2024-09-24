/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    int size = 0;
    Resume[] storage = new Resume[10000];

    void clear() {
        size();
        for (int i = 0; i < storage.length; i++) {
            size--;
            for (int j = 0; j < size(); j++) {
                storage[j] = storage[j + 1];
            }
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return new Resume();
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i].uuid.equals(uuid)) {
                int index = i;
                storage[index] = storage[size()];
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        for (int i = 0; i < size(); i++) {
            Resume[] resumes = new Resume[size()];
            resumes[i] = storage[i];
            return resumes;
        }
        return new Resume[0];
    }

    int size() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                size++;
            }
        }
        return size;
    }
}







