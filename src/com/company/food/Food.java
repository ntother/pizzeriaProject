package com.company.food;

import com.company.MenuItem;
import com.company.Sauce;

public interface Food extends MenuItem {
    static Sauce selectSauce() {
        return Sauce.NONE;
    };
}
