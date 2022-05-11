package com.gildedrose;

import com.gildedrose.rules.Rule;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class GildedRose {

    Item[] items;
    private static final Map<String, Rule> mapItemNameToRule = new HashMap<>();

    public GildedRose(Item[] items) {
        this.items = items;
        RuleFactory.getAll().forEach(rule -> mapItemNameToRule.put(rule.getName(), rule));
    }

    public void updateQuality() {
        Arrays.stream(items).forEach(this::updateQuality);
    }

    private void updateQuality(Item item) {
        if (mapItemNameToRule.get(item.name) != null) {
            mapItemNameToRule.get(item.name).apply(item);
        } else {
            mapItemNameToRule.get("Default").apply(item);
        }
    }
}
