package net.aydini.common.spring.service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import net.aydini.common.doamin.entity.AbstractEntityModel;
import net.aydini.common.spring.dao.BaseDao;

/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Dec 14, 2020
 * @param <E>
 */
@Transactional
public abstract class AbstractCrudService<E extends AbstractEntityModel> extends AbstractService<E> {
    public AbstractCrudService() {
    }

    @Transactional
    public void deleteSoft(Long id) {
        ((BaseDao<E>)this.getDao()).deleteSoft(id, new Date());
    }
}
