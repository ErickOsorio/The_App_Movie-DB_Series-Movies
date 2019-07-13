package com.eos.numbers.to.appmovies.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eos.numbers.to.appmovies.Interface.upcomingInterface;
import com.eos.numbers.to.appmovies.Presenter.upcomingPresenter;
import com.eos.numbers.to.appmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class upcomingFragment extends Fragment implements upcomingInterface.View {


    public upcomingFragment() {
        // Required empty public constructor
    }

    private upcomingInterface.Presenter presenter;
    public View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_upcoming, container, false);
        presenter = new upcomingPresenter(this, getContext());
        return root;
    }

}
