package com.eos.numbers.to.appmovies.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eos.numbers.to.appmovies.Adapter.itemAdapter;
import com.eos.numbers.to.appmovies.Helper.sessionHelper;
import com.eos.numbers.to.appmovies.Interface.topRatedInterface;
import com.eos.numbers.to.appmovies.Interface.upcomingInterface;
import com.eos.numbers.to.appmovies.Item.itemMain;
import com.eos.numbers.to.appmovies.Presenter.topRatedPresenter;
import com.eos.numbers.to.appmovies.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class topRatedFragment extends Fragment implements topRatedInterface.View {


    public topRatedFragment() {
        // Required empty public constructor
    }

    public itemAdapter adapter;
    public RecyclerView recyclerViewTopRated;
    public List<itemMain> list;
    private sessionHelper session;
    private topRatedInterface.Presenter presenter;
    int page = 1;
    public View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_top_rated, container, false);
        presenter = new topRatedPresenter(this, getContext());

        session = new sessionHelper(getActivity());
        session.setCategory("top_rated");

        list = new ArrayList<>();
        recyclerViewTopRated = root.findViewById(R.id.recyclerViewTopRated);
        adapter = new itemAdapter(list, new itemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position, itemMain item) {

            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerViewTopRated.setLayoutManager(layoutManager);
        recyclerViewTopRated.setHasFixedSize(true);
        recyclerViewTopRated.setAdapter(adapter);



        presenter.getTopRated(session.getApykey(), page, presenter);

        return root;
    }

    @Override
    public void requestResult(List<itemMain> list) {
        adapter.addAll(list);
    }
}
