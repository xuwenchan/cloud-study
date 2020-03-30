package request;

import exception.RobotWorldException;
import org.apache.commons.lang3.StringUtils;
import request.filter.RangeFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchRequest {
    private String appname;

    private String keyword;
    private List<Filter> filters = new ArrayList<>();
    private List<Sorter> sorters = new ArrayList<>();
    private List<Aggregator> aggregators = new ArrayList<>();
    private List<String> fields = new ArrayList<>();

    private int start = 0;
    private int count = 18;
    private boolean constantScore = true;

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getKeyword() {
        return keyword;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public List<Sorter> getSorters() {
        return sorters;
    }

    public List<Aggregator> getAggregators() {
        return aggregators;
    }

    public List<String> getFields() {
        return fields;
    }

    public int getStart() {
        return start;
    }

    public int getCount() {
        return count;
    }

    public SearchRequest(String appname) {
        this.appname = appname;
    }

    public boolean isConstantScore() {
        return constantScore;
    }

    public static class Builder {
        private SearchRequest request;
        private boolean useTtl = false;

        public Builder(String appname) throws RobotWorldException {
            if (StringUtils.isBlank(appname)) throw new RobotWorldException("appname不能为空");
            this.request = new SearchRequest(appname);
        }

        public Builder(String appname, boolean useTtl) throws RobotWorldException {
            this(appname);
            this.useTtl = useTtl;
        }

        public Builder setKeyword(String keyword) {
            request.keyword = keyword;
            return this;
        }

        public Builder addFilter(Filter filter) {
            if (filter != null) request.filters.add(filter);
            return this;
        }

        public Builder addFilterIf(boolean condition, Filter filter) {
            if (condition && filter != null) request.filters.add(filter);
            return this;
        }

        public Builder addSorter(Sorter sorter) {
            if (sorter != null) request.sorters.add(sorter);
            return this;
        }

        public Builder addSorterIf(boolean condition, Sorter sorter) {
            if (condition && sorter != null) request.sorters.add(sorter);
            return this;
        }

        public Builder addAggregator(Aggregator aggregator) {
            if (aggregator != null) request.aggregators.add(aggregator);
            return this;
        }

        public Builder addField(String field) {
            if (!StringUtils.isBlank(field)) request.fields.add(field);
            return this;
        }

        public Builder setLimit(int start, int count) {
            if (start >= 0) request.start = start;
            if (count >= 0) request.count = count;
            return this;
        }

        public Builder setConstantScore(boolean constantScore) {
            request.constantScore = constantScore;
            return this;
        }

        public SearchRequest build() {
            if (useTtl) request.filters.add(new RangeFilter("ttl", new Date().getTime(), null, true, false));
            return request;
        }
    }
}
