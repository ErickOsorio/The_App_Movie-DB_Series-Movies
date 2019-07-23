package com.eos.numbers.to.appmovies.View;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.eos.numbers.to.appmovies.Helper.config;
import com.eos.numbers.to.appmovies.Interface.detailInterface;
import com.eos.numbers.to.appmovies.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class detailFragment extends DialogFragment implements detailInterface.View {

    public detailFragment() {
        // Required empty public constructor
    }

    private ImageView imageView;
    public TextView textViewTitle, textViewDate, textViewVotes,
            textViewLanguage, textViewOverview, textViewGenres;
    public String id, image, title, date, votes, language, overview;
    private YouTubePlayer youTubePlayer;
    private YouTubePlayerSupportFragment youTubePlayerFragment;
    private Toolbar toolbar;
    public View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        toolbar = rootView.findViewById(R.id.toolBar);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        Bundle bundle= this.getArguments();
        if (bundle != null){
            id = bundle.getString("id");
            title = bundle.getString("title");
            image = bundle.getString("poster");
            votes = bundle.getString("votes");
            language = bundle.getString("language");
            date = bundle.getString("date");
            overview = bundle.getString("overview");
            toolbar.setTitle(title);
        }

        imageView = rootView.findViewById(R.id.image);
        textViewTitle = rootView.findViewById(R.id.textViewTitle);
        textViewDate = rootView.findViewById(R.id.textViewDate);
        textViewLanguage = rootView.findViewById(R.id.textViewLan);
        textViewVotes = rootView.findViewById(R.id.textViewVotes);
        textViewOverview = rootView.findViewById(R.id.textViewOverview);
        textViewGenres = rootView.findViewById(R.id.textViewGenres);

        Picasso.get()
                .load(config.getUrlImages()+image)
                .resize(6000, 2000)
                .onlyScaleDown()
                .into(imageView);

        textViewTitle.setText(title);
        textViewDate.setText(date);
        textViewVotes.setText(votes);
        textViewLanguage.setText(language);
        textViewOverview.setText(overview);

        youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtubeFragment, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(getResources().getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer yplayer, boolean b) {
                if (!b) {
                    youTubePlayer = yplayer;
                    youTubePlayer.setFullscreen(false);
                    //youTubePlayer.loadVideo("eh_qPcQoFK0");
                    youTubePlayer.cueVideo("eh_qPcQoFK0");
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {

            }
        });



        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                getActivity().onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
