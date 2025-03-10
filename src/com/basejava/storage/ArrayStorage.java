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
        if (isExisting(findSearchKey(resume.uuid))) {
            storage[findSearchKey(resume.uuid)] = resume;
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
        if (!isExisting(findSearchKey(r.uuid))) {
            storage[size++] = r;
        } else {
            update(r);
        }
    }


    public Resume get(String uuid) {
        if (isExisting(findSearchKey(uuid))) {
            return storage[findSearchKey(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        if (isExisting(findSearchKey(uuid))) {
            System.arraycopy(storage, findSearchKey(uuid) + 1, storage, findSearchKey(uuid),
                    size - findSearchKey(uuid) - 1);
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
