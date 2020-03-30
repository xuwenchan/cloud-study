package request.filter;


import org.apache.commons.lang3.StringUtils;
import request.Filter;

public class NotEmptyFilter implements Filter {
    private String field;

    public NotEmptyFilter(String field) {
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
