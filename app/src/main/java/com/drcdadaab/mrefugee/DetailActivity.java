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

public class DetailActivity extends AppCompatActivity {
    TextView textViewShowNewsTitle, textViewShowNewsAuthor, textViewShowNewsContent;
    RelativeTimeTextView textViewShowNewsTimestamp;
    String title, author, content, imageUrl;
    Long timestamp;
    ImageView imageViewShowNewsImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        textViewShowNewsTitle = (TextView) findViewById(R.id.textViewShowNewsTitle);
        textViewShowNewsAuthor = (TextView) findViewById(R.id.textViewShowNewsAuthor);
        textViewShowNewsTimestamp = (RelativeTimeTextView) findViewById(R.id.textViewShowNewsTimestamp);
        textViewShowNewsContent = (TextView) findViewById(R.id.textViewShowNewsContent);
        imageViewShowNewsImage = (ImageView) findViewById(R.id.imageViewShowNewsImage);


        Intent i = getIntent();
        title = i.getStringExtra("titleKey");
        author = i.getStringExtra("authorKey");
        timestamp = i.getLongExtra("timestampKey", 12);
        content = i.getStringExtra("contentKey");
        imageUrl = i.getStringExtra("imageKey");

        textViewShowNewsTitle.setText(title);
        textViewShowNewsAuthor.setText(author);
        textViewShowNewsTimestamp.setReferenceTime(new Date((timestamp)).getTime());
        textViewShowNewsContent.setText(content);
        Picasso.with(DetailActivity.this).load(imageUrl).placeholder(R.drawable.news_placeholder).into(imageViewShowNewsImage);

        getSupportActionBar().setTitle(title);

    }
}
