package com.github.mhewedy.condition.module;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.*;
import java.util.Map;

/**
 * Means the module with the provided name is not found on the classpath
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ConditionalOnMissingModule.OnMissingModuleCondition.class)
public @interface ConditionalOnMissingModule {

    String value();

    class OnMissingModuleCondition extends SpringBootCondition {

        @Override
        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {

            Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnMissingModule.class.getName());
            String moduleName = (String) attributes.get("value");

            if (!ModuleUtil.isModuleExists(moduleName)) {
                return ConditionOutcome.match();
            }

            return ConditionOutcome.noMatch("module %s found".formatted(moduleName));
        }
    }
}
