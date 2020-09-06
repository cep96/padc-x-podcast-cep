package com.cep.cep_podcast.device.player

import android.content.Context
import android.net.Uri
import android.support.v4.media.session.MediaSessionCompat
import com.cep.cep_podcast.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util

class MediaPlayerImpl : MediaPlayer {

  companion object {
    private const val TAG = "MediaPlayerTag"
  }

  private lateinit var exoPlayer: ExoPlayer
  private lateinit var context: Context
//  private lateinit var mediaSession: MediaSessionCompat

  override fun play(url: String) {

    val userAgent = Util.getUserAgent(context, context.getString(R.string.app_name))

    val httpDataSourceFactory = DefaultHttpDataSourceFactory(
      userAgent,
      DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
      DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
      true
    )


    val mediaSource = ProgressiveMediaSource.Factory(DefaultDataSourceFactory(context, null, httpDataSourceFactory))
      .createMediaSource(Uri.parse(url))

    exoPlayer.prepare(mediaSource)

    exoPlayer.playWhenReady = true
  }

  override fun getPlayerImpl(context: Context): ExoPlayer {
    this.context = context
    initializePlayer()
    return exoPlayer
  }

  override fun releasePlayer() {
    exoPlayer.stop()
    exoPlayer.release()
  }

  private fun initializePlayer() {

    val trackSelector = DefaultTrackSelector(context)
    trackSelector.setParameters(trackSelector.buildUponParameters().setMaxVideoSizeSd())

    exoPlayer = SimpleExoPlayer.Builder(context)
      .setTrackSelector(trackSelector)
      .build()
  }

}