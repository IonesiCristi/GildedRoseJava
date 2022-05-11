package com.gildedrose;

import com.gildedrose.rules.Rule;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RuleFactory {

    public static List<Rule> getAll() {
        List<Rule> rules = new ArrayList<>();
        Reflections reflections = new Reflections("com.gildedrose");
        Set<Class<? extends Rule>> allClasses = reflections.getSubTypesOf(Rule.class);

        for (Class<? extends Rule> rule : allClasses) {
            Constructor[] constructors = rule.getConstructors();
            try {
                rules.add((Rule) constructors[0].newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return allClasses.stream().map(RuleFactory::createRule).collect(Collectors.toList());
    }

    private static Rule createRule(Class<? extends Rule> ruleClass) {
        Constructor[] constructors = ruleClass.getConstructors();
        try {
            return (Rule) constructors[0].newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
