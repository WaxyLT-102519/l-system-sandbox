package com.luke.personal.routes;

import com.luke.personal.model.LSystem;

public class LoggingNavigator implements Navigator {

    @Override
    public void goToAxiom(LSystem lsystem) {
        System.out.println("Navigating to the Axiom page. Payload: " + lsystem);
    }
}
