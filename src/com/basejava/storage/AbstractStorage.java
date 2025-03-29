package com.basejava.storage;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    List<Resume> resumes = new ArrayList<>();

    public final void update(Resume r) {
        doUpdate(r);
    }

    public final void save(Resume r) {
        doSave(r);
    }

    public final Resume get(String uuid) {
        int index = (int) getSearchKey(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return doGet(uuid);
    }

    public final void delete(String uuid) {
        int index = (int) getSearchKey(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            doDelete(uuid);
        }
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }


    protected abstract void doDelete(Object searchKey);
    protected abstract void doUpdate(Resume r);

    protected abstract void doSave(Resume r);

    protected abstract Resume doGet(String uuid);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object object);

}