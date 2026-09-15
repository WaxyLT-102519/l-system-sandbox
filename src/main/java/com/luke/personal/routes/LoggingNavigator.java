package com.luke.personal.routes;

public class LoggingNavigator implements Navigator {

    @Override
    public void goTo(Route route, Object payload) {
        String maybeString = payload == null ? "nothing" : "[%s]".formatted(payload);

        System.out.printf("Navigating to [%s], carrying %s%n", route, maybeString);
    }
}
