package com.example.jeevan.testapp.listeners;

import com.example.jeevan.testapp.local_models.Test2Item;

/**
 * Created by jeevan on 5/21/17.
 */

public interface Test2StarListener {
    enum StarEnum {
        FROM_CONTENT, FROM_FAV
    }
    void star(Test2Item item, StarEnum from);
}
