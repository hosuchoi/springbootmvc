package lake.pool.springbootmvc.handdler;

import javax.validation.constraints.Min;

public class Event {

    private Integer id;
    private String name;
    @Min(0)
    private Integer limit;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}