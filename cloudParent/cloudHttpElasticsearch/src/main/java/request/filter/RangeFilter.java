package request.filter;


import org.apache.commons.lang3.StringUtils;
import request.Filter;

public class RangeFilter implements Filter {
    private String field;
    private Number lower = null;
    private Number upper = null;
    private boolean includeLower;
    private boolean includeUpper;

    public String getField() {
        return field;
    }

    public Number getLower() {
        return lower;
    }

    public Number getUpper() {
        return upper;
    }

    public boolean isIncludeLower() {
        return includeLower;
    }

    public boolean isIncludeUpper() {
        return includeUpper;
    }

    public RangeFilter(String field, Number lower, Number upper, boolean includeLower, boolean includeUpper) {
        this.field = field;
        this.lower = lower;
        this.upper = upper;
        this.includeLower = includeLower;
        this.includeUpper = includeUpper;
    }

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(field) || (lower == null && upper == null);
    }
}
