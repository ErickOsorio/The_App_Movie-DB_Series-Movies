package com.eos.numbers.to.appmovies.Presenter;

import android.content.Context;
import com.eos.numbers.to.appmovies.Interface.upcomingInterface;
import com.eos.numbers.to.appmovies.Item.itemMain;
import com.eos.numbers.to.appmovies.Model.upcomingModel;

import java.util.List;

public class upcomingPresenter implements upcomingInterface.Presenter{

    private upcomingInterface.View view;
    private upcomingInterface.Model model;
    private Context context;

    public upcomingPresenter(upcomingInterface.View view, Context context) {
        this.view = view;
        this.context = context;
        model = new upcomingModel(context);
    }

    @Override
    public void getupComing(String apiKey, int page, upcomingInterface.Presenter presenter) {
        model.getupComing(apiKey, page, presenter);
    }

    @Override
    public void requestResult(List<itemMain> list) {
        view.requestResult(list);
    }
}
