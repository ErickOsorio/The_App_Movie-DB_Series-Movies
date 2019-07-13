package com.eos.numbers.to.appmovies.Model;

import android.content.Context;

import com.eos.numbers.to.appmovies.Interface.popularInterface;

public class popularModel implements popularInterface.Model {

    private popularInterface.Presenter presenter;
    public Context context;

    public popularModel(popularInterface.Presenter presenter) {
        this.presenter = presenter;
    }
}
