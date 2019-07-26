package com.eos.numbers.to.appmovies.Interface;


import java.util.List;

public interface detailInterface {

    interface View{
        void requestResult(List<String> genres, List<String> videos);

    }
    interface Presenter{
        void getDetails(String apiKey, int id, detailInterface.Presenter presenter);
        void requestResult(List<String> genres, List<String> videos);

    }
    interface Model{
        void getDetails(String apiKey, int id, detailInterface.Presenter presenter);
    }
}
