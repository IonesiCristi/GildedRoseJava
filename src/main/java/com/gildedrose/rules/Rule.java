package com.gildedrose.rules;

import com.gildedrose.Item;

public interface Rule {

    void apply(Item item);

    String getName();

}
