package net.aydini.common.doamin.model;

/**
 * 
 * @author  <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *Dec 13, 2020
 */
public interface PagableFilter extends Filter {
    
    int getPage();
    
    int getPageSize();  

}
