package com.eos.numbers.to.appmovies.View;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.eos.numbers.to.appmovies.Adapter.itemAdapter;
import com.eos.numbers.to.appmovies.Helper.sessionHelper;
import com.eos.numbers.to.appmovies.Interface.searchInterface;
import com.eos.numbers.to.appmovies.Item.itemMain;
import com.eos.numbers.to.appmovies.Presenter.searchPresenter;
import com.eos.numbers.to.appmovies.R;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class searchFragment extends Fragment implements searchInterface.View, TextWatcher {


    public searchFragment() {
        // Required empty public constructor
    }

    public itemAdapter adapter;
    public RecyclerView recyclerViewSearch;
    public GridLayoutManager layoutManager;
    public LinearLayout linearLayoutData;
    public List<itemMain> list;
    private sessionHelper session;
    public EditText editTextBusqueda;
    private searchInterface.Presenter presenter;
    private boolean isLoadig = true;
    private int visibleItemCount, totalItemCount, pasVisibleItems, previous_total = 0;
    int page = 1;
    String query = "";
    public View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_search, container, false);

        presenter = new searchPresenter(this, getContext());
        session = new sessionHelper(getActivity());


        list = new ArrayList<>();
        recyclerViewSearch = root.findViewById(R.id.recyclerViewSearch);
        linearLayoutData = root.findViewById(R.id.linearLayoutMessageNoData);
        adapter = new itemAdapter(list, new itemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position, itemMain item) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", item.getId());
                bundle.putString("title", item.getTitle());
                bundle.putString("poster", item.getPoster());
                bundle.putString("votes", item.getVotes());
                bundle.putString("language", item.getLanguage());
                bundle.putString("date", item.getDate());
                bundle.putString("overview", item.getOverview());


                detailFragment dialog = new detailFragment();
                dialog.setArguments(bundle);
                FragmentTransaction ft = ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                dialog.show(ft, "DIALOG");

            }
        });

        layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerViewSearch.setLayoutManager(layoutManager);
        recyclerViewSearch.setHasFixedSize(true);
        recyclerViewSearch.setAdapter(adapter);
        recyclerViewSearch.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isLoadig = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pasVisibleItems = layoutManager.findFirstVisibleItemPosition();

                if(isLoadig && (visibleItemCount + pasVisibleItems == totalItemCount))
                {
                    page++;
                    isLoadig = false;
                    presenter.getSearch(session.getApykey(), query, page, presenter);

                }

            }
        });

        editTextBusqueda = root.findViewById(R.id.editTextSearch);
        editTextBusqueda.addTextChangedListener(this);

        return root;
    }

    @Override
    public void requestResult(List<itemMain> list) {
        adapter.addAll(list);
    }

    @Override
    public void messageNoData(boolean isVisible) {
        linearLayoutData.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * This method is called to notify you that, within <code>s</code>,
     * the <code>count</code> characters beginning at <code>start</code>
     * have just replaced old text that had length <code>before</code>.
     * It is an error to attempt to make changes to <code>s</code> from
     * this callback.
     *
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (count > 3){
            presenter.getSearch(session.getApykey(), s.toString(), page, presenter);
        }

    }


    @Override
    public void afterTextChanged(Editable s) {

    }
}
