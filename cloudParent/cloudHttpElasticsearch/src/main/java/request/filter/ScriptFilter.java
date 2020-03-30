package request.filter;


import org.apache.commons.lang3.StringUtils;
import request.Filter;

public class ScriptFilter implements Filter {
    private String script;

    public ScriptFilter(String script) {
        this.script = script;
    }

    public String getScript() {
        return script;
    }

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(script);
    }
}
