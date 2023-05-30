package com.github.akagawatsurunaki.ankeito.dao.impl;

import com.github.akagawatsurunaki.ankeito.dao.ModelDao;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
public class ModelDaoImpl<Model> implements ModelDao<Model> {

    @Override
    public List<Model> findAllModels() {
        // TODO: 需要你去实现捏
        return null;
    }

    @Override
    public Model findModelById(@NonNull Integer id) {
        // TODO: 需要你去实现捏
        return null;
    }

    @Override
    public int addModels(@NonNull Collection<Model> modelCollection) {
        // TODO: 需要你去实现捏
        return 0;
    }

    // TODO: 需要你去实现捏
}
