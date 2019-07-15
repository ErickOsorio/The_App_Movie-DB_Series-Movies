package com.eos.numbers.to.appmovies.View;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
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
import com.eos.numbers.to.appmovies.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class detailFragment extends DialogFragment {

    public detailFragment() {
        // Required empty public constructor
    }

    private ImageView imageView;
    public TextView textViewTitle, textViewDate, textViewVotes, textViewLanguage, textViewOverview;
    public String image, title, date, votes, language, overview;
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

        Picasso.get()
                .load(config.getUrlImages()+image)
                .into(imageView);

        textViewTitle.setText(title);
        textViewDate.setText(date);
        textViewVotes.setText(votes);
        textViewLanguage.setText(language);
        textViewOverview.setText(overview);

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

}
