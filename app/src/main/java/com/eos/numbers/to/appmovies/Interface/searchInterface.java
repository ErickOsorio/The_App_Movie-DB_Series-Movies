package com.eos.numbers.to.appmovies.Interface;

import com.eos.numbers.to.appmovies.Item.itemMain;

import java.util.List;

public interface searchInterface {

    interface View{
        void requestResult(List<itemMain> list);
        void messageNoData(boolean isVisible);
    }

    interface Presenter{
        void getSearch(String apiKey, String query, int page, searchInterface.Presenter presenter);
        void requestResult(List<itemMain> list);
        void messageNoData(boolean isVisible);
    }

    interface Model{
        void getSearch(String apiKey, String query, int page, searchInterface.Presenter presenter);
    }

}
