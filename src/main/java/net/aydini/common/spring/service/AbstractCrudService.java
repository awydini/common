package net.aydini.common.spring.service;

import org.springframework.transaction.annotation.Transactional;

import net.aydini.common.doamin.entity.BaseEntityModel;
import net.aydini.common.spring.dao.BaseDao;

import java.util.Date;

/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Dec 14, 2020
 * @param <E>
 */
@Transactional
public abstract class AbstractCrudService<E extends BaseEntityModel> extends AbstractService<E> {
    public AbstractCrudService() {
    }

    @Transactional
    public void deleteSoft(Long id) {
        ((BaseDao<E>)this.getDao()).deleteSoft(id, new Date());
    }
}
