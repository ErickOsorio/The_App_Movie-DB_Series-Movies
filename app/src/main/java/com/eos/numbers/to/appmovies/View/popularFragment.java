package com.eos.numbers.to.appmovies.View;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eos.numbers.to.appmovies.Adapter.PaginationScrollListener;
import com.eos.numbers.to.appmovies.Adapter.itemAdapter;
import com.eos.numbers.to.appmovies.Helper.config;
import com.eos.numbers.to.appmovies.Helper.sessionHelper;
import com.eos.numbers.to.appmovies.Interface.popularInterface;
import com.eos.numbers.to.appmovies.Item.itemMain;
import com.eos.numbers.to.appmovies.Presenter.popularPresenter;
import com.eos.numbers.to.appmovies.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class popularFragment extends Fragment implements popularInterface.View {


    public popularFragment() {
        // Required empty public constructor
    }

    public itemAdapter adapter;
    public RecyclerView recyclerViewPopular;
    public List<itemMain> list;
    private sessionHelper session;
    private PaginationScrollListener scrollListener;
    private popularInterface.Presenter presenter;
    int page = 1;
    public View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_popular, container, false);
        presenter = new popularPresenter(this, getContext());
        session = new sessionHelper(getActivity());
        session.setCategory("popular");

        list = new ArrayList<>();
        recyclerViewPopular = root.findViewById(R.id.recyclerViewPopular);
        adapter = new itemAdapter(list, new itemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position, itemMain item) {

            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerViewPopular.setLayoutManager(layoutManager);
        recyclerViewPopular.setHasFixedSize(true);
        recyclerViewPopular.setAdapter(adapter);



        presenter.getPopularMovies(session.getApykey(), page, presenter);

        return root;
    }

    @Override
    public void requestResult(List<itemMain> list) {
        this.list = list;
        adapter.addAll(list);
    }
}
