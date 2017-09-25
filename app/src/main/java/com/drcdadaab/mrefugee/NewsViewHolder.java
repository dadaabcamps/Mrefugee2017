package com.drcdadaab.mrefugee;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;

/**
 * Created by oriaso on 9/22/17.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {
    TextView textViewNewsListTitle, textViewNewsListAuthor;
    RelativeTimeTextView textViewNewsListSince;
    View mView;
    ImageView imageViewNewsListImage;


    public NewsViewHolder(View itemView){
        super(itemView);
        this.mView = itemView;
        textViewNewsListTitle = (TextView) itemView.findViewById(R.id.textViewNewsListTitle);
        textViewNewsListAuthor = (TextView) itemView.findViewById(R.id.textViewNewsListAuthor);
        textViewNewsListSince = (RelativeTimeTextView) itemView.findViewById(R.id.textViewNewsListSince);
        imageViewNewsListImage = (ImageView) itemView.findViewById(R.id.imageViewNewsListImage);
    }
}
