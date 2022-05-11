package com.gildedrose.rules;

import com.gildedrose.Item;

public class ConjuredRule implements Rule {

    @Override
    public void apply(Item item) {
        item.sellIn--;

        if (item.quality >= 50) {
            return;
        }

        if (item.sellIn < 0) {
            item.quality -= 4;
        } else {
            item.quality -= 2;
        }

    }

    @Override
    public String getName() {
        return "Conjured Mana Cake";
    }
}
