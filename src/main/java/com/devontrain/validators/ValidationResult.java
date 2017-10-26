package com.devontrain.validators;

import java.util.Map;
import java.util.function.BooleanSupplier;

/**
 * Created by @author <a href="mailto:piotr.tarnowski.dev@gmail.com">Piotr Tarnowski</a> on 13.10.17.
 */
public interface ValidationResult extends BooleanSupplier {
    Map<String, String> getErrors();
}
