package request.filter;


import org.apache.commons.lang3.StringUtils;
import request.Filter;

public class PrefixFilter implements Filter {
    private String field;
    private String prefix;

    public String getField() {
        return field;
    }


    public PrefixFilter(String field, String prefix) {
        this.field = field;
        this.prefix = StringUtils.trim(prefix);
    }

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(field) || StringUtils.isBlank(prefix);
    }

    public String getPrefix() {
        return prefix;
    }
}
