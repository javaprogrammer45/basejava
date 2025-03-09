package com.basejava.storage;

import com.basejava.model.Resume;

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

    void update(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(resume)) {
                storage[i] = resume;
            }
        }
    }

    Resume isExists(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Storage is full");
            return;
        }
        if (isExists(r.uuid) == null) {
            storage[size++] = r;
        } else {
            update(r);
        }
    }


    Resume get(String uuid) {
        if (isExists(uuid) != null) {
            return isExists(uuid);
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (isExists(uuid).uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                storage[size - 1] = null;
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
