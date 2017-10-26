package com.devontrain.validators;

import javax.script.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by @author <a href="mailto:piotr.tarnowski.dev@gmail.com">Piotr Tarnowski</a> on 12.10.17.
 */
public class ScriptValidatorBuilder {


    private final StringBuilder definitions = new StringBuilder(4096);
    private final StringBuilder function = new StringBuilder(1024);
    private final Map<String, String> messages = new HashMap<>();

    public ScriptValidatorBuilder() {
        function.append("var validator = new Function('ctx','errors','");
    }

    public ScriptValidatorBuilder register(String id, String template) {
        definitions
                .append("var ")
                .append(id)
                .append(" = new Function('return ' + ")
                .append(template)
                .append(")();\n");
        function.append("errors[\"")
                .append(id)
                .append("\"]=")
                .append(id)
                .append("(ctx[\"")
                .append(id)
                .append("\"]);");
        return this;
    }

    public ScriptValidatorBuilder register(String id, String template, String message) {
        messages.put(id, message);
        register(id, template);
        return this;
    }

    public ScriptValidator build() throws ScriptException {
        function.append("');");
        definitions.append(function);
        return new ScriptValidator(definitions.toString(), messages);
    }

}
