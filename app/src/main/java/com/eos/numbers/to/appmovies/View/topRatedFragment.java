package com.eos.numbers.to.appmovies.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eos.numbers.to.appmovies.Interface.topRatedInterface;
import com.eos.numbers.to.appmovies.Presenter.topRatedPresenter;
import com.eos.numbers.to.appmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class topRatedFragment extends Fragment implements topRatedInterface.View {


    public topRatedFragment() {
        // Required empty public constructor
    }

    private topRatedInterface.Presenter presenter;
    public View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_top_rated, container, false);
        presenter = new topRatedPresenter(this, getContext());
        return root;
    }

}
