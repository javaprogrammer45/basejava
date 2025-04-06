package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.put(searchKey.toString(), r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.putIfAbsent(searchKey.toString(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey.toString());
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey.toString());
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }
}
