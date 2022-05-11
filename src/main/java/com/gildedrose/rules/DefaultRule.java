package com.gildedrose.rules;

import com.gildedrose.Item;

public class DefaultRule implements Rule {

    @Override
    public void apply(Item item) {
        item.sellIn--;

        if (item.quality >= 50 || item.quality == 0) {
            return;
        }

        if (item.sellIn < 0) {
            item.quality -= 2;
        } else {
            item.quality--;
        }
    }

    @Override
    public String getName() {
        return "Default";
    }
}
