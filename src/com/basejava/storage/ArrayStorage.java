package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;


    public void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    public void update(Resume resume) {
        int index = findSearchKey(resume.uuid);
        if (isExisting(index)) {
            storage[index] = resume;
        }
    }

    protected int findSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected boolean isExisting(int index) {
        return index >= 0 && index < size;
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Storage is full");
            return;
        }
        storage[size++] = r;
    }


    public Resume get(String uuid) {
        int index = findSearchKey(uuid);
        if (isExisting(index)) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = findSearchKey(uuid);
        if (isExisting(index)) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
