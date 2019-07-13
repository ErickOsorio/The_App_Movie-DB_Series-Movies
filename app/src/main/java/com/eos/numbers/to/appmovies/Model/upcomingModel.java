package com.eos.numbers.to.appmovies.Model;

import android.content.Context;

import com.eos.numbers.to.appmovies.Interface.upcomingInterface;

public class upcomingModel implements upcomingInterface.Model {

    private upcomingInterface.Presenter presenter;
    public Context context;

    public upcomingModel(upcomingInterface.Presenter presenter) {
        this.presenter = presenter;
    }
}
