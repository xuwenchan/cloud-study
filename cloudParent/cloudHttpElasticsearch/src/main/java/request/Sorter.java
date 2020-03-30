package request;


import org.apache.commons.lang3.StringUtils;

public class Sorter {
    private String field;
    private String order;
    private FieldType type = FieldType.NONE_TEXT;

    public String getField() {
        return field;
    }

    public String getOrder() {
        return order;
    }

    public FieldType getType() {
        return type;
    }

    public Sorter(String field, String order) {
        this.field = field;
        this.order = order;
    }

    public Sorter(String field, String order, FieldType type) {
        this.field = field;
        this.order = order;
        if (type != null) this.type = type;
    }

    public boolean isInvalid() {
        return StringUtils.isBlank(field) || (!"asc".equalsIgnoreCase(order) && !"desc".equalsIgnoreCase(order));
    }
}
