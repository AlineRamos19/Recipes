package br.com.android.aline.recipes.ui.steps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import br.com.android.aline.recipes.R;
import br.com.android.aline.recipes.models.Step;

public class StepDetailsActivity extends AppCompatActivity implements ExoPlayer.EventListener  {

    private static final String EXO_PLAYER_POSITION = "extra_exo_player_position";
    private Step mStep;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private TrackSelector trackSelector;
    private long position;
    private TextView mDescription;
    private ImageView mImageRecipe;
    private String mVideoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);

        if(savedInstanceState != null){
            position = savedInstanceState.getLong(EXO_PLAYER_POSITION);
        }

        initToolbar();

        mPlayerView = (SimpleExoPlayerView) findViewById(R.id.player_view);
        mDescription = findViewById(R.id.description);
        mImageRecipe = findViewById(R.id.image_description);

        mStep = (Step) getIntent().getExtras().getSerializable("step");

        if(mStep != null){
            mDescription.setText(mStep.getDescription());
            if(!mStep.getThumbnailURL().isEmpty()){
                mImageRecipe.setVisibility(View.VISIBLE);
                Glide.with(this).load(mStep.getThumbnailURL()).into(mImageRecipe);
            }else{
               mImageRecipe.setVisibility(View.GONE);
            }

            mVideoUrl = mStep.getVideoURL();
            if(!mVideoUrl.isEmpty()){
                mPlayerView.setVisibility(View.VISIBLE);
                initializePlayer(Uri.parse(mVideoUrl));
            }else{
                mPlayerView.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        position = mExoPlayer != null ? mExoPlayer.getCurrentPosition() : 0;
        releasePlayer();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(EXO_PLAYER_POSITION, position);
    }




    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_details_steps);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView titleToolbar = findViewById(R.id.title_toolbar);
        titleToolbar.setText(R.string.toolbar_title_steps_details);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {

            trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);




            String userAgent = Util.getUserAgent(this, getString(R.string.app_name));
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    this, userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);

            mExoPlayer.seekTo(position);
        }
    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(false);
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
            trackSelector = null;
        }
    }






    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

}
