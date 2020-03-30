package request.filter;


import request.Filter;

public class NotFilter implements Filter {
    private Filter filter;

    public NotFilter(Filter filter) {
        this.filter = filter;
    }

    public Filter getFilter() {
        return filter;
    }

    @Override
    public boolean isInvalid() {
        return filter.isInvalid();
    }
}
