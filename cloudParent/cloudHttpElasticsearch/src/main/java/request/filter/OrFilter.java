package request.filter;


import request.Filter;

import java.util.ArrayList;
import java.util.List;

public class OrFilter implements Filter {
    private List<Filter> subFilters = new ArrayList<>();

    public List<Filter> getSubFilters() {
        return subFilters;
    }

    public OrFilter(List<Filter> filters) {
        this.subFilters = filters;
    }

    @Override
    public boolean isInvalid() {
        return subFilters.isEmpty();
    }
}
