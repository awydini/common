package net.aydini.common.doamin.entity;

import java.io.Serializable;

/**
 * @Author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * Jul 9, 2020
 */
public interface EntityModel<ID extends Serializable> extends Serializable {
    ID getId();
}