package com.drcdadaab.mrefugee;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class NoticeDetailActivity extends AppCompatActivity {
    TextView textViewShowNoticeTitle, textViewShowNoticeAuthor, textViewShowNoticeContent;
    RelativeTimeTextView textViewShowNoticeTimestamp;
    ImageView imageViewShowAgencyLogo;
    String title, author, content, imageUrl;
    Long timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        textViewShowNoticeTitle = (TextView) findViewById(R.id.textViewShowNoticeTitle);
        textViewShowNoticeAuthor = (TextView) findViewById(R.id.textViewShowNoticeAuthor);
        textViewShowNoticeTimestamp = (RelativeTimeTextView) findViewById(R.id.textViewShowNoticeTimestamp);
        textViewShowNoticeContent = (TextView) findViewById(R.id.textViewShowNoticeContent);
        imageViewShowAgencyLogo = (ImageView) findViewById(R.id.imageViewShowAgencyLogo);

        Intent i = getIntent();
        title = i.getStringExtra("titleKey");
        author = i.getStringExtra("authorKey");
        timestamp = i.getLongExtra("timestampKey", 12);
        content = i.getStringExtra("contentKey");
        imageUrl = i.getStringExtra("imageKey");

        textViewShowNoticeTitle.setText(title);
        textViewShowNoticeAuthor.setText(author);
        textViewShowNoticeTimestamp.setReferenceTime(new Date((timestamp) * -1).getTime());
        textViewShowNoticeContent.setText(content);
        Picasso.with(NoticeDetailActivity.this).load(imageUrl).into(imageViewShowAgencyLogo);


        getSupportActionBar().setTitle(title);
    }
}
