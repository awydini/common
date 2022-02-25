package net.aydini.common.doamin.dto.web;

import net.aydini.common.doamin.dto.BaseDto;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 * <p>
 * 25.02.22
 */
public class ResponseDataList<T extends BaseDto> extends ResponseMessage {

    /**
     *
     */
    private static final long serialVersionUID = 1840500920692185343L;


    /**
     *
     */
    private List<T> data;


    /**
     * total elements
     */
    private Long totalElements;

    /**
     *  total pages
     */
    private Integer totalPages;


    /**
     * number of elements within this page
     */
    private Integer numberOfElements;



    public ResponseDataList() {}

    public ResponseDataList(String message, Integer code, Page<T> page)
    {
        super(message, code);
        this.data = page != null ? page.getContent() : new ArrayList<>();
        this.numberOfElements= page != null ? page.getNumberOfElements() : 0;
        this.totalPages = page != null ? page.getTotalPages() : 0;
        this.totalElements = page != null ? page.getTotalElements() : 0;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }
}
