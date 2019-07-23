package com.eos.numbers.to.appmovies.Interface;


public interface detailInterface {

    interface View{

    }
    interface Presenter{
        void getDetails(String apiKey, int id, String append_to_response, detailInterface.Presenter presenter);

    }
    interface Model{
        void getDetails(String apiKey, int id, String append_to_response, detailInterface.Presenter presenter);
    }
}
