package com.eos.numbers.to.appmovies.View;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.eos.numbers.to.appmovies.Adapter.itemAdapter;
import com.eos.numbers.to.appmovies.Helper.sessionHelper;
import com.eos.numbers.to.appmovies.Interface.topRatedInterface;
import com.eos.numbers.to.appmovies.Item.itemMain;
import com.eos.numbers.to.appmovies.Presenter.topRatedPresenter;
import com.eos.numbers.to.appmovies.R;
import com.facebook.shimmer.ShimmerFrameLayout;
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
    public GridLayoutManager layoutManager;
    public List<itemMain> list;
    private sessionHelper session;
    private ShimmerFrameLayout shimmerFrameLayout;
    private topRatedInterface.Presenter presenter;
    private boolean isLoadig = true;
    private int visibleItemCount, totalItemCount, pasVisibleItems, previous_total = 0;
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
        shimmerFrameLayout = root.findViewById(R.id.shimmer_top_rated);
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
        recyclerViewTopRated.setLayoutManager(layoutManager);
        recyclerViewTopRated.setHasFixedSize(true);
        recyclerViewTopRated.setAdapter(adapter);
        recyclerViewTopRated.addOnScrollListener(new RecyclerView.OnScrollListener() {

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
                    presenter.getTopRated(session.getApykey(), page, presenter);

                }

            }
        });



        presenter.getTopRated(session.getApykey(), page, presenter);

        return root;
    }

    @Override
    public void requestResult(List<itemMain> list) {
        adapter.addAll(list);
    }

    @Override
    public void startShimmer() {
        shimmerFrameLayout.startShimmerAnimation();
    }

    @Override
    public void stopShimmer() {
        shimmerFrameLayout.stopShimmerAnimation();
        shimmerFrameLayout.setVisibility(View.GONE);
    }
}
