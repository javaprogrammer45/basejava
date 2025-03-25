package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public abstract class  AbstractArrayStorage extends AbstractStorage {
    public static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void deleteElements() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void updateElement(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume " + r.getUuid() + " not exist");
        } else {
            storage[index] = r;
        }
    }

    public void saveResume(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            insertResume(r, index);
            size++;
        }
    }

    public Resume getResume(String uuid) {
        return storage[getIndex(uuid)];
    }

    public void removeResume(String uuid) {
        fillRemoved(getIndex(uuid));
        storage[size - 1] = null;
        size--;
    }

    public Resume[] getAllResumes() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int sizeStorage() {
        return size;
    }

    protected abstract void fillRemoved(int index);

    protected abstract void insertResume(Resume r, int index);

}
