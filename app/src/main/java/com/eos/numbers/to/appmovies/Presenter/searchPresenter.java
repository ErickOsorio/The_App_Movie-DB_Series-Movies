package com.eos.numbers.to.appmovies.Presenter;

import android.content.Context;

import com.eos.numbers.to.appmovies.Interface.searchInterface;
import com.eos.numbers.to.appmovies.Interface.searchInterface;
import com.eos.numbers.to.appmovies.Item.itemMain;
import com.eos.numbers.to.appmovies.Model.searchModel;
import com.eos.numbers.to.appmovies.Model.upcomingModel;

import java.util.List;

public class searchPresenter implements searchInterface.Presenter{

    private searchInterface.View view;
    private searchInterface.Model model;
    private Context context;

    public searchPresenter(searchInterface.View view, Context context) {
        this.view = view;
        this.context = context;
        model = new searchModel(context);
    }

    @Override
    public void getSearch(String apiKey, String query, int page, searchInterface.Presenter presenter) {
        model.getSearch(apiKey, query, page, presenter);
    }

    @Override
    public void requestResult(List<itemMain> list) {
        view.requestResult(list);
    }
}
