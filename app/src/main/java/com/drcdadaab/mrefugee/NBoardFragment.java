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
public class NBoardFragment extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    DatabaseReference db;
    FirebaseRecyclerAdapter<NoticeModel, NoticeViewHolder> firebasenewsRecycleAdapter;
    ProgressBar progressBarNoticeList;

    public NBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_nboard, container, false);
        //Initialize Firebase DB
        db = FirebaseDatabase.getInstance().getReference();


        //SETUP RECYCLER
        rv = (RecyclerView) rootView.findViewById(R.id.recyclerViewNoticeList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(false);


        progressBarNoticeList = (ProgressBar) rootView.findViewById(R.id.progressBarNoticeList);
        progressBarNoticeList.setVisibility(View.VISIBLE);

        Query query = db.child("Notices").orderByChild("visible").equalTo(true);

        firebasenewsRecycleAdapter = new FirebaseRecyclerAdapter<NoticeModel, NoticeViewHolder>(NoticeModel.class, R.layout.notice_list_item, NoticeViewHolder.class, query) {
            @Override
            protected void populateViewHolder(NoticeViewHolder viewHolder, final NoticeModel model, final int position) {
                viewHolder.textViewNoticeListTitle.setText(model.getTitle());
                viewHolder.textViewNoticeListSource.setText(model.getSource());
                viewHolder.textViewNoticeListSince.setReferenceTime(new Date((model.getTimestamp() * -1)).getTime());
                Picasso.with(getActivity()).load(model.getAgencyLogo()).into(viewHolder.imageViewNoticeListImage);

                progressBarNoticeList.setVisibility(View.GONE);
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //firebasenewsRecycleAdapter.getRef(position).removeValue();
                        openNoticeDetailActivity(model.getTitle(), model.getSource(), model.getTimestamp(), model.getDescription(), model.getAgencyLogo());
                    }
                });
            }

            private void openNoticeDetailActivity(String title, String source, Long timestamp, String description, String imageUrl) {
                Intent newsIntent = new Intent(getActivity(), NoticeDetailActivity.class);
                newsIntent.putExtra("titleKey", title);
                newsIntent.putExtra("authorKey", source);
                newsIntent.putExtra("timestampKey", timestamp);
                newsIntent.putExtra("contentKey", description);
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
