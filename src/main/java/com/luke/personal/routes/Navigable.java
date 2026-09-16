package com.luke.personal.routes;

public abstract class Navigable {

    protected final Navigator navigator;

    protected Navigable(Navigator navigator) {
        this.navigator = navigator;
    }

    public abstract void onShow(Object payload);
}
