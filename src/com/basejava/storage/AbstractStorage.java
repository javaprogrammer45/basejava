package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    List<Resume> resumes = new ArrayList<>();

    public final void clear() {
        deleteElements();
    }

    public final void update(Resume r) {
        updateElement(r);
    }

    public final void save(Resume r) {
        saveResume(r);
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return getResume(uuid);
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            removeResume(uuid);
        }
    }

    public final Resume[] getAll() {
        return getAllResumes();
    }

    public final int size() {
        return sizeStorage();
    }


    protected abstract void deleteElements();

    protected abstract void updateElement(Resume r);

    protected abstract void saveResume(Resume r);

    protected abstract Resume getResume(String uuid);

    protected abstract int getIndex(String uuid);

    protected abstract void removeResume(String uuid);

    protected abstract Resume[] getAllResumes();

    protected abstract int sizeStorage();
}