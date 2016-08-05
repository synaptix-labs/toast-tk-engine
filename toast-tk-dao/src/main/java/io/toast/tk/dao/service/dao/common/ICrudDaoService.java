package io.toast.tk.dao.service.dao.common;

import com.github.jmkgreen.morphia.Key;

public interface ICrudDaoService<T> {

	Key<T> saveAndIndex(final T entity);
}