package com.eos.numbers.to.appmovies.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eos.numbers.to.appmovies.Interface.popularInterface;
import com.eos.numbers.to.appmovies.Presenter.popularPresenter;
import com.eos.numbers.to.appmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class popularFragment extends Fragment implements popularInterface.View {


    public popularFragment() {
        // Required empty public constructor
    }

    private popularInterface.Presenter presenter;
    public View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_popular, container, false);
        presenter = new popularPresenter(this, getContext());
        return root;
    }

}
