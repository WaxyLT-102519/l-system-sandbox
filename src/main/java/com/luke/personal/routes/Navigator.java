package com.luke.personal.routes;

import com.luke.personal.model.LSystem;

public interface Navigator {
    void goTo(Route route, Object payload);
}
