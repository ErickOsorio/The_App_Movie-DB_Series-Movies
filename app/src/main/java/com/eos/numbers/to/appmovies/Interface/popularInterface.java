package com.eos.numbers.to.appmovies.Interface;

import com.eos.numbers.to.appmovies.Item.itemMain;
import java.util.List;

public interface popularInterface {

    interface View{
        void requestResult(List<itemMain> list);
        void startShimmer();
        void stopShimmer();
    }

    interface Presenter{
        void getPopularMovies(String apiKey, int page, popularInterface.Presenter presenter);
        void requestResult(List<itemMain> list);
        void startShimmer();
        void stopShimmer();
    }

    interface Model{
        void getPopularMovies(String apiKey, int page, popularInterface.Presenter presenter);
    }

}
