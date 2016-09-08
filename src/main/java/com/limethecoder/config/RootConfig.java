package com.limethecoder.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.util.regex.Pattern;

@Configuration
@ComponentScan(basePackages = {"com.limethecoder"},
        excludeFilters={
                @ComponentScan.Filter(type= FilterType.CUSTOM, value=RootConfig.ControllerPackage.class)
})
public class RootConfig {
    public static class ControllerPackage extends RegexPatternTypeFilter {
        public ControllerPackage() {
            super(Pattern.compile("com\\.limethecoder\\.controller"));
        }
    }
}

