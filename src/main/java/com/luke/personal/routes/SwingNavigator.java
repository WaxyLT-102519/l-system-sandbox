package com.luke.personal.routes;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class SwingNavigator implements Navigator {

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel deck = new JPanel(cardLayout);
    private final Map<Route, Navigable> presenters = new EnumMap<>(Route.class);

    @Override
    public JPanel getNavigationPanel() {
        return deck;
    }

    @Override
    public void register(Route route, JPanel view, Navigable presenter) {
        deck.add(view, route.name());
        presenters.put(route, presenter);
    }

    @Override
    public void goTo(Route route, Object payload) {
        Navigable presenter = presenters.get(route);
        if (presenter == null) {
            throw new IllegalStateException("No presenter registered for route %s.".formatted(route));
        }

        presenter.onShow(payload);
        cardLayout.show(deck, route.name());
    }
}
