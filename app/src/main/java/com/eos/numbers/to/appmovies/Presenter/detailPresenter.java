package com.eos.numbers.to.appmovies.Presenter;

import android.content.Context;

import com.eos.numbers.to.appmovies.Interface.detailInterface;
import com.eos.numbers.to.appmovies.Model.detailModel;

public class detailPresenter implements detailInterface.Presenter {

    private detailInterface.View view;
    private detailInterface.Model model;
    private Context context;

    public detailPresenter(detailInterface.View view, Context context) {
        this.view = view;
        this.context = context;
        model = new detailModel(context);
    }

    @Override
    public void getDetails(String apiKey, int id, String append_to_response, detailInterface.Presenter presenter) {
        model.getDetails(apiKey,id,append_to_response,presenter);
    }
}
