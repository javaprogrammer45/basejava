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

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void doUpdate(Resume r) {
        int index = (int) getSearchKey(r.getUuid());
        if (index < 0) {
            System.out.println("Resume " + r.getUuid() + " not exist");
        } else {
            storage[index] = r;
        }
    }

    public void doSave(Resume r) {
        int index = (int) getSearchKey(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            insertResume(r, index);
            size++;
        }
    }

    public Resume doGet(String uuid) {
        return storage[(int) getSearchKey(uuid)];
    }


    public void doDelete(Object index) {
        fillRemoved((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    protected boolean isExist(Integer key) {
        return (Integer) key >= 0;
    }

    public Resume[] getAllResumes() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract void fillRemoved(int index);

    protected abstract void insertResume(Resume r, int index);

    protected abstract Integer getSearchKey(String uuid);
}
