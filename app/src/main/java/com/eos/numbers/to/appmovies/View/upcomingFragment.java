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
import com.eos.numbers.to.appmovies.Interface.popularInterface;
import com.eos.numbers.to.appmovies.Interface.upcomingInterface;
import com.eos.numbers.to.appmovies.Item.itemMain;
import com.eos.numbers.to.appmovies.Presenter.upcomingPresenter;
import com.eos.numbers.to.appmovies.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class upcomingFragment extends Fragment implements upcomingInterface.View {


    public upcomingFragment() {
        // Required empty public constructor
    }

    public itemAdapter adapter;
    public RecyclerView recyclerViewUpComing;
    public List<itemMain> list;
    private sessionHelper session;
    private upcomingInterface.Presenter presenter;
    int page = 1;
    public View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_upcoming, container, false);
        presenter = new upcomingPresenter(this, getContext());

        session = new sessionHelper(getActivity());
        session.setCategory("upcoming");

        list = new ArrayList<>();
        recyclerViewUpComing = root.findViewById(R.id.recyclerViewUpcoming);
        adapter = new itemAdapter(list, new itemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position, itemMain item) {

            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerViewUpComing.setLayoutManager(layoutManager);
        recyclerViewUpComing.setHasFixedSize(true);
        recyclerViewUpComing.setAdapter(adapter);



        presenter.getupComing(session.getApykey(), page, presenter);

        return root;
    }

    @Override
    public void requestResult(List<itemMain> list) {
        adapter.addAll(list);
    }
}
