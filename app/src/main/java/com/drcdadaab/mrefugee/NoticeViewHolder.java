package com.drcdadaab.mrefugee;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;

/**
 * Created by oriaso on 9/22/17.
 */

public class NoticeViewHolder extends RecyclerView.ViewHolder{
    TextView textViewNoticeListTitle, textViewNoticeListSource;
    ImageView imageViewNoticeListImage;
    RelativeTimeTextView textViewNoticeListSince;
    View mView;


    public NoticeViewHolder(View itemView){
        super(itemView);
        this.mView = itemView;
        textViewNoticeListTitle = (TextView) itemView.findViewById(R.id.textViewNewsListTitle);
        textViewNoticeListSource = (TextView) itemView.findViewById(R.id.textViewNewsListAuthor);
        textViewNoticeListSince = (RelativeTimeTextView) itemView.findViewById(R.id.textViewNoticeListSince);
        imageViewNoticeListImage = (ImageView) itemView.findViewById(R.id.imageViewNoticeListImage);
    }
}
