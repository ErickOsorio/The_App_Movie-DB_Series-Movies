package com.eos.numbers.to.appmovies.Presenter;

import android.content.Context;

import com.eos.numbers.to.appmovies.Interface.popularInterface;
import com.eos.numbers.to.appmovies.Item.itemMain;
import com.eos.numbers.to.appmovies.Model.popularModel;

import java.util.ArrayList;
import java.util.List;

public class popularPresenter implements popularInterface.Presenter {

    private popularInterface.View view;
    private popularInterface.Model model;
    private Context context;

    public popularPresenter(popularInterface.View view, Context context) {
        this.view = view;
        this.context = context;
        model = new popularModel(context);
    }

    @Override
    public void getPopularMovies(String apiKey, int page, popularInterface.Presenter presenter) {
        model.getPopularMovies(apiKey, page, presenter);
    }

    @Override
    public void requestResult(List<itemMain> list) {
        view.requestResult(list);
    }

    @Override
    public void startShimmer() {
        view.startShimmer();
    }

    @Override
    public void stopShimmer() {
        view.stopShimmer();
    }
}
