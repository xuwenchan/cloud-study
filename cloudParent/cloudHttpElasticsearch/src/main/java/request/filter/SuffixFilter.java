package request.filter;

import org.apache.commons.lang3.StringUtils;
import request.Filter;

public class SuffixFilter implements Filter {
    private String field;
    private String suffix;

    public String getField() {
        return field;
    }

    public String getSuffix() {
        return suffix;
    }

    public SuffixFilter(String field, String suffix) {
        this.field = field;
        this.suffix = StringUtils.trim(suffix);
    }

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(field) || StringUtils.isBlank(suffix);
    }
}
