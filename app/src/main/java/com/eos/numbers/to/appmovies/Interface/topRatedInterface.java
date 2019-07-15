package com.eos.numbers.to.appmovies.Interface;

import com.eos.numbers.to.appmovies.Item.itemMain;

import java.util.List;

public interface topRatedInterface {

    interface View{
        void requestResult(List<itemMain> list);
    }

    interface Presenter{
        void getTopRated(String apiKey, int page, topRatedInterface.Presenter presenter);
        void requestResult(List<itemMain> list);
    }

    interface Model{
        void getTopRated(String apiKey, int page, topRatedInterface.Presenter presenter);
    }
}
