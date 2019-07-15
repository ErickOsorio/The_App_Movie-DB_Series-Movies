package com.eos.numbers.to.appmovies.Interface;

import com.eos.numbers.to.appmovies.Item.itemMain;

import java.util.List;

public interface upcomingInterface {

    interface View{
        void requestResult(List<itemMain> list);
        void startShimmer();
        void stopShimmer();
    }

    interface Presenter{
        void getupComing(String apiKey, int page, upcomingInterface.Presenter presenter);
        void requestResult(List<itemMain> list);
        void startShimmer();
        void stopShimmer();
    }

    interface Model{
        void getupComing(String apiKey, int page, upcomingInterface.Presenter presenter);
    }

}
