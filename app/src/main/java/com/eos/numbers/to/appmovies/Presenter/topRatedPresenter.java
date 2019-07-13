package com.eos.numbers.to.appmovies.Presenter;

import android.content.Context;

import com.eos.numbers.to.appmovies.Interface.topRatedInterface;
import com.eos.numbers.to.appmovies.Model.topRatedModel;

public class topRatedPresenter implements topRatedInterface.Presenter {

    private topRatedInterface.View view;
    private topRatedInterface.Model model;
    private Context context;

    public topRatedPresenter(topRatedInterface.View view, Context context) {
        this.view = view;
        this.context = context;
        model = new topRatedModel(this);
    }
}
