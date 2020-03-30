package request.filter;


import org.apache.commons.lang3.StringUtils;
import request.Filter;

public class WildcardFilter implements Filter {
    private String field;
    private String value;

    public WildcardFilter(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(field) || StringUtils.isBlank(value);
    }
}
