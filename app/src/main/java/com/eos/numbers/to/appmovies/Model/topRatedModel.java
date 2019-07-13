package com.eos.numbers.to.appmovies.Model;

import android.content.Context;

import com.eos.numbers.to.appmovies.Interface.topRatedInterface;

public class topRatedModel implements topRatedInterface.Model {

    private topRatedInterface.Presenter presenter;
    public Context context;

    public topRatedModel(topRatedInterface.Presenter presenter) {
        this.presenter = presenter;
    }
}
