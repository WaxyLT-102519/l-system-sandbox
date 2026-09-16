package com.luke.personal.routes;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class NavigatorSpy implements Navigator {

    List<RegisterArgs> registerCalls = new ArrayList<>();
    List<GoToArgs> goToCalls = new ArrayList<>();

    @Override
    public JPanel getNavigationPanel() {
        return null;
    }

    @Override
    public void register(Route route, JPanel view, Navigable presenter) {
        registerCalls.add(new RegisterArgs(route, view, presenter));
    }

    @Override
    public void goTo(Route route, Object payload) {
        goToCalls.add(new GoToArgs(route, payload));
    }

    public record RegisterArgs(Route route, JPanel view, Navigable presenter) {}
    public record GoToArgs(Route route, Object payload) {}
}
