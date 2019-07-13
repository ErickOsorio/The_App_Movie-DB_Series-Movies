package com.eos.numbers.to.appmovies.Presenter;

import android.content.Context;
import com.eos.numbers.to.appmovies.Interface.upcomingInterface;
import com.eos.numbers.to.appmovies.Model.upcomingModel;

public class upcomingPresenter implements upcomingInterface.Presenter{

    private upcomingInterface.View view;
    private upcomingInterface.Model model;
    private Context context;

    public upcomingPresenter(upcomingInterface.View view, Context context) {
        this.view = view;
        this.context = context;
        model = new upcomingModel(this);
    }
}
