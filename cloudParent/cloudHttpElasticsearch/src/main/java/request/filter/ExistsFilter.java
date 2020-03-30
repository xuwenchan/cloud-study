package request.filter;


import org.apache.commons.lang3.StringUtils;
import request.Filter;

public class ExistsFilter implements Filter {
    private String field;

    public ExistsFilter(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(field);
    }
}
