package com.luke.personal.model.fixtures;

import com.luke.personal.model.ProductionRules;

public interface Rules {

    ProductionRules NONE = ProductionRules.builder().build();

    ProductionRules AB = ProductionRules.builder()
            .mapping('a', "ab")
            .mapping('b', "a")
            .build();
}
