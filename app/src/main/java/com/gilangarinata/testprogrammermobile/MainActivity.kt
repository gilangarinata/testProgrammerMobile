package com.gilangarinata.testprogrammermobile


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0
    private var currentPlayUrl : String? = null
    private var videos : MutableList<VideoModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVideos()
        initRecyclerView()
        initPlayer()
        currentPlayUrl?.let {
            initializePlayer(it)
        }
    }

    private fun initRecyclerView(){
        rvVideo?.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = VideoAdapter(videos)
        }
    }

    private fun initVideos(){
        videos.add(
            VideoModel(
                url = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                title = "Big Bunnies",
                description = "No Description",
                imageUrl = "https://wallpapercave.com/wp/wp7684186.jpg"
            )
        )
        videos.add(
            VideoModel(
                url = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                title = "Bernard Bear",
                description = "No Description",
                imageUrl = "https://wallpapercave.com/wp/wp7684186.jpg"
            )
        )
        videos.add(
            VideoModel(
                url = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                title = "Big Bunnies",
                description = "No Description",
                imageUrl = "https://wallpapercave.com/wp/wp7684186.jpg"
            )
        )
        videos.add(
            VideoModel(
                url = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                title = "Bernard Bear",
                description = "No Description",
                imageUrl = "https://wallpapercave.com/wp/wp7684186.jpg"
            )
        )
        videos.add(
            VideoModel(
                url = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                title = "Big Bunnies",
                description = "No Description",
                imageUrl = "https://wallpapercave.com/wp/wp7684186.jpg"
            )
        )
        videos.add(
            VideoModel(
                url = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4",
                title = "Bernard Bear",
                description = "No Description",
                imageUrl = "https://wallpapercave.com/wp/wp7684186.jpg"
            )
        )
        currentPlayUrl = videos[0].url
    }

    private fun initPlayer(){
        player = SimpleExoPlayer.Builder(this).build()
        videoView.player = player
    }

    private fun initializePlayer(url: String){
        val mediaItem = MediaItem.fromUri(url)
        player?.let {
            it.setMediaItem(mediaItem)
            it.play()
        }
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            currentPlayUrl?.let {
                initializePlayer(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT < 24 || player == null) {
            currentPlayUrl?.let {
                initializePlayer(it)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player?.let {
            playWhenReady = it.playWhenReady
            playbackPosition = it.currentPosition
            currentWindow = it.currentWindowIndex
            it.release()
        }
    }


}