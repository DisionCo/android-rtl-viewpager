package com.dision.android.rtlviewpager.models;

import android.support.v4.app.Fragment;

public abstract class Tab {

    // variables
    private int id;
    private String title;

    // constructor
    public Tab(int id, String title) {
        setId(id);
        setTitle(title);
    }

    // methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract Fragment getFragment();
}
