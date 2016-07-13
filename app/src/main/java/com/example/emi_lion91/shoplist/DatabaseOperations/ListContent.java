package com.example.emi_lion91.shoplist.DatabaseOperations;

import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ListContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();





    public static void addItem(DummyItem item) {
        ITEMS.add(item);
    }


    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static void deleteItem(DummyItem mItem) {
        ITEMS.remove(mItem);
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {

        public final String content;

        public DummyItem(String id, String content, String details) {

            this.content = content;

        }

        @Override
        public String toString() {
            return content;
        }
    }
}
