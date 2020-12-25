package net.aydini.common.doamin.model;

import org.primefaces.model.SortOrder;


/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Dec 25, 2020
 */
public class SortProperty {
    private final String name;
    private final SortOrder sortOrder;

    public SortProperty(String name, SortOrder sortOrder) {
        this.name = name;
        this.sortOrder = sortOrder;
    }

    public String getName() {
        return this.name;
    }

    public SortOrder getSortOrder() {
        return this.sortOrder;
    }
}