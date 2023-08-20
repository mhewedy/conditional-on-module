package com.github.mhewedy.condition.module;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.*;
import java.util.Map;

/**
 * Means the module with the provided name is found on the classpath
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ConditionalOnModule.OnModuleCondition.class)
public @interface ConditionalOnModule {

    String value();

    class OnModuleCondition extends SpringBootCondition {

        @Override
        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {

            Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnModule.class.getName());
            String moduleName = (String) attributes.get("value");

            if (ModuleUtil.isModuleExists(moduleName)) {
                return ConditionOutcome.match();
            }

            return ConditionOutcome.noMatch("module %s not found".formatted(moduleName));
        }
    }
}
