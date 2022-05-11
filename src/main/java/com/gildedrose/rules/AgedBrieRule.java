package com.gildedrose.rules;

import com.gildedrose.Item;

public class AgedBrieRule implements Rule {

    @Override
    public void apply(Item item) {
        item.sellIn--;

        if (item.quality >= 50) {
            return;
        }

        item.quality++;
    }

    @Override
    public String getName() {
        return "Aged Brie";
    }
}
