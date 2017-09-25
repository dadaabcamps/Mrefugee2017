package com.drcdadaab.mrefugee;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    DatabaseReference db;
    FirebaseRecyclerAdapter<NewsModel, NewsViewHolder> firebasenewsRecycleAdapter;
    ProgressBar progressBarNewsList;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        //Initialize Firebase DB
        db = FirebaseDatabase.getInstance().getReference();


        //SETUP RECYCLER
        rv = (RecyclerView) rootView.findViewById(R.id.recyclerViewNewsList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(false);


        progressBarNewsList = (ProgressBar) rootView.findViewById(R.id.progressBarNewsList);
        progressBarNewsList.setVisibility(View.VISIBLE);

        Query query = db.child("News").orderByChild("timestamp");

        firebasenewsRecycleAdapter = new FirebaseRecyclerAdapter<NewsModel, NewsViewHolder>(NewsModel.class, R.layout.news_list_item, NewsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(NewsViewHolder viewHolder, final NewsModel model, final int position) {
                viewHolder.textViewNewsListTitle.setText(model.getTitle());
                viewHolder.textViewNewsListAuthor.setText(model.getAuthor());
                viewHolder.textViewNewsListSince.setReferenceTime(new Date((model.getTimestamp())).getTime());
                Picasso.with(getActivity()).load(model.getImageUrl()).into(viewHolder.imageViewNewsListImage);
                progressBarNewsList.setVisibility(View.GONE);
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //firebasenewsRecycleAdapter.getRef(position).removeValue();
                        openNewsDetailActivity(model.getTitle(), model.getAuthor(), model.getTimestamp(), model.getContent(), model.getImageUrl());
                    }
                });
            }

            private void openNewsDetailActivity(String title, String author, Long timestamp, String content, String imageUrl) {
                Intent newsIntent = new Intent(getActivity(), DetailActivity.class);
                newsIntent.putExtra("titleKey", title);
                newsIntent.putExtra("authorKey", author);
                newsIntent.putExtra("timestampKey", timestamp);
                newsIntent.putExtra("contentKey", content);
                newsIntent.putExtra("imageKey", imageUrl);

                startActivity(newsIntent);
            }
        };
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(firebasenewsRecycleAdapter);
        return rootView;
    }

    public void onResume(){
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Home");
    }
}
