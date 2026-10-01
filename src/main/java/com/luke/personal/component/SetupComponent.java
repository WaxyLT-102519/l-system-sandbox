package com.luke.personal.component;

import com.luke.personal.presenter.SetupPresenter;
import com.luke.personal.view.setup.SetupView;

public record SetupComponent(SetupView view, SetupPresenter presenter) {}
