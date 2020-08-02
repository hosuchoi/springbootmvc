package lake.pool.springbootmvc.handdler;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Event {

    interface ValidateLimit {}
    interface ValidateName {}

    private Integer id;
    @NotBlank //(groups = ValidateName.class)
    private String name;
    @Min(value = 0) //, groups = ValidateLimit.class)  // group을 지정하면 기존의 @Valid는 안먹음..ㄷㄷㄷ @Validated 의 같은 group은 동작....
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
