package net.aydini.common.spring.service;

import net.aydini.common.doamin.dto.CustomPage;
import net.aydini.common.doamin.entity.AbstractEntityModel;
import net.aydini.common.exception.NotFoundException;
import net.aydini.common.exception.ServiceException;
import net.aydini.common.spring.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Dec 14, 2020
 */
public abstract class AbstractCrudService<E extends AbstractEntityModel>
{
    private static final Logger log = LoggerFactory.getLogger(AbstractCrudService.class.getName());


    @PersistenceContext(unitName = "entityManagerFactory")
    protected EntityManager entityManager;


    public abstract BaseDao<E> getDao();

    protected EntityManager getEntityManager()
    {
        return entityManager;
    }

    private final Class<E> entityType;

    public AbstractCrudService()
    {
        entityType = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }


    @Transactional(rollbackFor = {  ServiceException.class })
    public void insert(E entity, boolean flush) throws ServiceException
    {
        try
        {
            beforeInsert(entity);
            getDao().save(entity);
            afterInsert(entity, flush);
        }
        catch (Exception ex)
        {
            onError(entity,ex);
        }
    }

    protected void onError(E entity,Exception exception)
    {
        log.error(exception.getMessage());
        throw new ServiceException(exception);
    }

    @Transactional(rollbackFor = { Exception.class, ServiceException.class })
    public void insert(E entity) throws ServiceException
    {
        insert(entity, true);
    }

    public void delete(Long id)
    {
        getDao().deleteSoft(id,new Date());
        log.info("entity with id {} successfully deleted.",id);
    }

    @Transactional(rollbackFor = { Exception.class, ServiceException.class })
    public void update(E entity, boolean flush) throws ServiceException
    {
        try
        {
            beforeUpdate(entity);
            getDao().save(entity);
            afterUpdate(entity,flush);
        }
        catch (Exception ex)
        {
           onError(entity,ex);
        }
    }

    @Transactional(rollbackFor = { Exception.class, ServiceException.class })
    public void update(E entity) throws ServiceException
    {
        update(entity, true);
    }

    public void flush()
    {
        getDao().flush();
    }



    protected void afterUpdate(E entity, boolean flush)
    {
        if (flush)
            flush();
    }

    protected void beforeUpdate(E entity)
    {
    }

    protected void afterInsert(E entity, boolean flush)
    {
        if (flush)
            flush();
        log.info("new {} with id {} successfully persisted.",entityType.getSimpleName(),entity.getId());
    }

    protected void beforeInsert(E entity)
    {
        log.info("persisting {}",entity.toString());

    }

    @Transactional(readOnly = true)
    public E findById( Object id)
    {
        E entity = getEntityManager().find(entityType, id);
        if(entity == null)
            throw new NotFoundException(NotFoundException.DATA_WITH_ID_NOT_FOUND + id);
        return entity;
    }

    public Page<E> findAll(Pageable pageable)
    {
        Page<E> page = getDao().findAll(pageable);
        if(page==null || page.getContent() == null || page.getContent().isEmpty())
            throw new NotFoundException(NotFoundException.NOT_FOUND);
        return page;
    }



    @Transactional(readOnly = true)
    public Page<E> load(Pageable pageable,  JPARestriction restriction)
    {
        return load(pageable, restriction,  null);
    }

    @Transactional(readOnly = true)
    public Page<E> load(Pageable pageable, JPARestriction restriction, Sort sort)
    {
        Long count = count(restriction);
        if(count ==null || count.longValue()==0l)
            throw new NotFoundException();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteria = builder.createQuery(entityType);
        Root<E> root = criteria.from(entityType);
        criteria.select(root);
        setRestriction(restriction,builder,criteria,root);
        criteria.orderBy(getOrders(sort,builder,root));

        TypedQuery<E> typedQuery = entityManager.createQuery(criteria);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<E> result = typedQuery.getResultList();

        if(result == null || result.isEmpty())
            throw new NotFoundException();
        return new CustomPage<>(result,pageable,count);
    }

    @Transactional(readOnly = true)
    public Long count( JPARestriction restriction)
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<E> root = criteria.from(entityType);
        criteria.select(builder.count(root));
        setRestriction(restriction,builder,criteria,root);
        return entityManager.createQuery(criteria).getSingleResult();
    }


    private void setRestriction(JPARestriction restriction, CriteriaBuilder builder, CriteriaQuery criteria, Root<E> root) {
        if (restriction == null)
            return;
        Specification<E> listSpec = restriction.countSpec(builder, criteria, root);
        if (listSpec != null) {
            Predicate predicate = listSpec.toPredicate(root, criteria, builder);
            if (predicate != null)
                criteria.where(predicate);
        }
    }

    private List<Order> getOrders(Sort sort, CriteriaBuilder builder, Root<E> root)
    {
        List<Order> orderList = new ArrayList<>();
        if(sort == null)
            return orderList;
        log.info("sorts : {}",sort);

        sort.stream().filter(s -> s.getProperty() != null && !s.getProperty().isEmpty()).forEach((s) ->
        {
            if (s.isAscending())
                orderList.add(builder.asc(root.get(s.getProperty())));
            else
                orderList.add(builder.desc(root.get(s.getProperty())));
        });
        return orderList;
    }
}
