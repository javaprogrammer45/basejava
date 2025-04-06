package com.basejava.storage;

import com.basejava.exception.StorageException;
import com.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

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



    public void doSave(Resume r, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertResume(r, (Integer) index);
            size++;
        }
    }

    public Resume doGet(Object index) {
        return storage[(Integer) index];
    }


    public void doDelete(Object index) {
        fillRemoved((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    @Override
    public  List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract void fillRemoved(int index);

    protected abstract void insertResume(Resume r, int index);

    protected abstract Integer getSearchKey(String uuid);
}
