package net.aydini.common.spring.service;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Dec 14, 2020
 */
public interface JPARestriction<T> {
   <E> Specification<T> countSpec(CriteriaBuilder criteriaBuilder, CriteriaQuery<E> criteriaQuery, Root<T> root);

   <E> Specification<T> listSpec(CriteriaBuilder criteriaBuilder, CriteriaQuery<E> criteriaQuery, Root<T> root);
}
