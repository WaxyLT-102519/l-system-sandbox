package com.luke.personal.routes;

import com.luke.personal.model.LSystem;

import javax.swing.*;

public interface Navigator {

    JPanel getNavigationPanel();
    void register(Route route, JPanel view, Navigable presenter);
    void goTo(Route route, Object payload);

    default void goTo(Route route) {
        this.goTo(route, null);
    }
}
