package request.filter;


import org.apache.commons.lang3.StringUtils;
import request.Filter;

public class PhraseFilter implements Filter {
    private String field;
    private String keyword;

    public String getField() {
        return field;
    }

    public String getKeyword() {
        return keyword;
    }

    public PhraseFilter(String field, String keyword) {
        this.field = field;
        this.keyword = StringUtils.trim(keyword);
    }

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(field) || StringUtils.isBlank(keyword);
    }
}
