package net.aydini.common.spring.service;

import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;

/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Dec 14, 2020
 * @param <T>
 */
public abstract class RestrictionTemplate<T extends Serializable> implements JPARestriction<T>{

    @Override
    public <E> Specification<T> countSpec(CriteriaBuilder criteriaBuilder, CriteriaQuery<E> criteriaQuery, Root<T> root) {
        return (rootEntity, query, builder) -> applyFilter(rootEntity, query, criteriaBuilder);
    }

    @Override
    public <E> Specification<T> listSpec(CriteriaBuilder criteriaBuilder, CriteriaQuery<E> criteriaQuery, Root<T> root) {
        return (rootEntity, query, builder) -> applyFilter(rootEntity, query, builder);
    }

    protected abstract Predicate applyFilter(Root<T> rootEntity, @SuppressWarnings("rawtypes") CriteriaQuery query, CriteriaBuilder criteriaBuilder);

}
