package com.eos.numbers.to.appmovies.Presenter;

import android.content.Context;

import com.eos.numbers.to.appmovies.Interface.popularInterface;
import com.eos.numbers.to.appmovies.Model.popularModel;

public class popularPresenter implements popularInterface.Presenter {

    private popularInterface.View view;
    private popularInterface.Model model;
    private Context context;

    public popularPresenter(popularInterface.View view, Context context) {
        this.view = view;
        this.context = context;
        model = new popularModel(this);
    }
}
