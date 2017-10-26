package com.devontrain.validators;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by @author <a href="mailto:piotr.tarnowski.dev@gmail.com">Piotr Tarnowski</a> on 13.10.17.
 */
public class ValidationResultImpl extends HashMap<String, Boolean> implements ValidationResult {

    private final Map<String, String> messages;
    private final Map<String, String> errors;

    /*package*/ ValidationResultImpl(Map<String, String> messages) {
        this.messages = messages;
        this.errors = new HashMap<>(messages.size());
    }

    @Override
    public Boolean put(String key, Boolean value) {
        return value || errors.put(key, messages.get(key)) == null;
    }

    @Override
    public boolean getAsBoolean() {
        return errors.isEmpty();
    }

    @Override
    public Map<String, String> getErrors() {
        return errors;
    }
}
