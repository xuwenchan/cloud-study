package request.filter;


import org.apache.commons.lang3.StringUtils;
import request.Filter;

public class EqFilter implements Filter {
    private String field;
    private Object value;

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public EqFilter(String field, Object value) {
        this.field = field;
        this.value = value instanceof String ? StringUtils.trim((String) value) : value;
    }

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(field) || value == null || (value instanceof String && StringUtils.isBlank((String) value));
    }
}
