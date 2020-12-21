package com.gildedrose;

import com.gildedrose.core.GildedRose;
import com.gildedrose.core.Item;
import com.gildedrose.items.SwitchItem;
import org.junit.jupiter.api.Test;

public class SwitchItemTest {

    private Item[] items = new Item[] {new SwitchItem("+5 Dexterity Vest", 10, 50)};
    private GildedRose app = new GildedRose(items);

    @Test
    void increaseQualityNormal() {
        app.calculateNextDay();
        assert(app.items[0].sellIn == 9);
        assert(app.items[0].quality == 50);
        app.calculateNextDay();
        assert(app.items[0].sellIn == 8);
        assert(app.items[0].quality == 50);
    }

    @Test
    void decreaseQualityExpired() {
        app.items[0].sellIn = 0;
        app.items[0].quality = 20;
        app.calculateNextDay();
        assert(app.items[0].sellIn == -1);
        assert(app.items[0].quality == 19);
        app.calculateNextDay();
        assert(app.items[0].sellIn == -2);
        assert(app.items[0].quality == 18);
        app.calculateNextDay();
        assert(app.items[0].sellIn == -3);
        assert(app.items[0].quality == 17);
    }

    @Test
    void noQualityBelowZero() {
        app.items[0].sellIn = -1;
        app.items[0].quality = 1;
        app.calculateNextDay();
        assert(app.items[0].sellIn == -2);
        assert(app.items[0].quality == 0);
        app.calculateNextDay();
        assert(app.items[0].sellIn == -3);
        assert(app.items[0].quality == 0);
        app.calculateNextDay();
        assert(app.items[0].sellIn == -4);
        assert(app.items[0].quality == 0);
    }
}