package com.example.mediaplayertest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {

    private static final String TAG = "MediaPlayer";

    private MediaPlayer mediaPlayer;
//    private VideoView videoView;

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnInfoListener(this);

        surfaceView = findViewById(R.id.surfaceview);
        surfaceHolder = surfaceView.getHolder();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.i(TAG, "surfaceCreated: ");
                mediaPlayer.setDisplay(holder);
                setPlayPath();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.i(TAG, "surfaceDestroyed: ");
            }
        });

    }

    private void setPlayPath(){
        Log.i(TAG, "setPlayPath: ");
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource("https://r4---sn-i3belnl7.googlevideo.com/videoplayback/id/3623871776ee178a/itag/234/source/youtube/cpn/6ZsIzbDrmkiLZnsW/expire/1578552315/ei/m3cWXrmvN9borQSW1rTQBg/ip/42.200.115.24/requiressl/yes/ratebypass/yes/pfa/1/goi/133/sgoap/clen%3D11648351%3Bdur%3D719.702%3Bgir%3Dyes%3Bitag%3D140%3Blmt%3D1573349853012408/hls_chunk_host/r4---sn-i3belnl7.googlevideo.com/mm/31,26/mn/sn-i3belnl7,sn-npoeene6/ms/au,onr/mv/m/mvi/3/pl/19/initcwndbps/1396250/vprv/1/playlist_type/DVR/txp/5531432/mt/1578530627/fvip/4/keepalive/yes/sparams/expire,ei,ip,id,itag,source,requiressl,ratebypass,pfa,goi,sgoap,vprv,playlist_type/sig/ALgxI2wwRgIhAOKf8A-EMUCZ9TpYCIB7kFS6p2jAFLEtKl1L-FzAFwS2AiEAm5UdLQ085RtUbKi2EsRkPbfj0BWgAOqAwIquUlcs7f4%3D/lsparams/hls_chunk_host,mm,mn,ms,mv,mvi,pl,initcwndbps/lsig/AHylml4wRAIgT-LVytognNrSc6-rcJifSiAlYD6hTparBB82z-S56KQCIE_t_7d-hax4_bWcEmuyYuaBBziwopm3yc2ZS_YZ7iAy/playlist/index.m3u8/begin/0/len/5072/goap/slices%3D0-163430/gosq/0");
//            mediaPlayer.setDataSource("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            Log.i(TAG, "setPlayPath: "+e);
            e.printStackTrace();
        }
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.i(TAG, "onCompletion: ");
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.i(TAG, "onPrepared: ");
        mediaPlayer.start();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.i(TAG, "onError: ");
        return false;
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "onBackPressed: ");
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
//        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_1){
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnInfoListener(this);
            mediaPlayer.setDisplay(surfaceHolder);
            setPlayPath();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Log.i(TAG, "onBufferingUpdate: ");
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        Log.i(TAG, "onInfo: " + what);
        return false;
    }
}
