package com.gildedrose.rules;

import com.gildedrose.Item;

public class BackstagePassRule implements Rule {


    @Override
    public void apply(Item item) {
        if (item.quality >= 50) {
            return;
        }

        if (item.sellIn <= 5) {
            item.quality += 3;
        } else if (item.sellIn <= 10) {
            item.quality += 2;
        } else {
            item.quality++;
        }

        item.sellIn--;

        if (item.sellIn < 0) {
            item.quality = 0;
        }

    }

    @Override
    public String getName() {
        return "Backstage passes to a TAFKAL80ETC concert";
    }
}
