package com.devontrain.validators;

import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.*;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by @author <a href="mailto:piotr.tarnowski.dev@gmail.com">Piotr Tarnowski</a> on 13.10.17.
 */
public class ScriptValidator implements Function<Map<String, Object>, ValidationResult> {

    private static final ScriptEngine engine = new NashornScriptEngineFactory()
            .getScriptEngine("-strict", "--no-java", "--no-syntax-extensions");

    private final JSObject validator;
    private final Map<String, String> messages;

    /*package*/ ScriptValidator(String definition, Map<String, String> messages) throws ScriptException {
        this.messages = messages;
        CompiledScript script = ((Compilable) engine).compile(definition);
        Bindings bindings = engine.createBindings();
        script.eval(bindings);
        validator = (JSObject) bindings.get("validator");
    }

    @Override
    public ValidationResult apply(Map<String, Object> ctx) {
        ValidationResult result = new ValidationResultImpl(messages);
        validator.call(null, ctx, result);
        return result;
    }
}
