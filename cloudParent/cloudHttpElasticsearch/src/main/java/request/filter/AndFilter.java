package request.filter;


import request.Filter;

import java.util.ArrayList;
import java.util.List;

public class AndFilter implements Filter {
    private List<Filter> subFilters = new ArrayList<>();

    public List<Filter> getSubFilters() {
        return subFilters;
    }

    public AndFilter(List<Filter> filters) {
        this.subFilters = filters;
    }

    @Override
    public boolean isInvalid() {
        return subFilters.isEmpty();
    }
}
