package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private static final String USUAL_ITEM_NAME = "Usual Item";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED = "Conjured";

    @Test
    public void testUsualItemQualityAndSellInShouldDecrease() {
        Item[] items = new Item[] { new Item(USUAL_ITEM_NAME, 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    public void testUsualItemQualityShouldDegradeTwiceAsFast() {
        Item[] items = new Item[] { new Item(USUAL_ITEM_NAME, -1, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    public void testUsualItemQualityShouldNotBeNegative() {
        Item[] items = new Item[] { new Item(USUAL_ITEM_NAME, 2, 1) };
        GildedRose app = new GildedRose(items);
        iterateNumberOfDays(app, 3);

        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    public void testAgedBrieQualityShouldIncrease() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(1, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    public void testAddedBrieQualityShouldNotSurpassThreshold() {
        int qualityThreshold = 50;
        Item[] items = new Item[] { new Item(AGED_BRIE, 1, qualityThreshold) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(qualityThreshold, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    public void testSulfurasShouldNotDecreaseInQuality() {
        Item[] items = new Item[] { new Item(SULFURAS, 1, 80) };
        GildedRose app = new GildedRose(items);
        iterateNumberOfDays(app, 2);

        assertEquals(80, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    public void testBackstagePassShouldIncreaseQuality() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 11, 11) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(12, app.items[0].quality);
        assertEquals(10, app.items[0].sellIn);

        app.updateQuality();
        assertEquals(14, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);

        iterateNumberOfDays(app, 5);
        assertEquals(25, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }

    @Test
    public void testBackstagePassShouldDropToZero() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    public void testConjuredQualityShouldDecreaseTwiceAsFast() {
        Item[] items = new Item[] { new Item(CONJURED, 1, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

    private void iterateNumberOfDays(GildedRose gildedRose, int dayToIterate) {
        for (int i = 1; i <= dayToIterate; i++) {
            gildedRose.updateQuality();
        }
    }

}
