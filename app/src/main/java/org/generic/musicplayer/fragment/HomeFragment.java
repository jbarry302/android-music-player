package org.generic.musicplayer.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

import org.generic.musicplayer.R;
import org.generic.musicplayer.utils.Log;
import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements MediaPlayer.OnPreparedListener {

    public static final String TAG = "HomeFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Context context;

    TextView musicTitle;
    MediaPlayer player;
    Slider timer;
    Button stop, play, pause;
    private Handler handler;
    private Runnable work;


    public HomeFragment() {}


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        handler = new Handler();
        work = new Runnable() {
            @Override
            public void run() {
                if(player == null) {
                    Log.e(TAG, "player null stopping handler");
                    closeHandler();
                    return;
                }

            }
        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();
        musicTitle = v.findViewById(R.id.music_title);
        timer = v.findViewById(R.id.duration_seeker);
        stop = v.findViewById(R.id.btn_stop);
        pause = v.findViewById(R.id.btn_pause);
        play = v.findViewById(R.id.btn_play);


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopMusic();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseMusic();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic();
            }
        });
        musicTitle.setSelected(true);


        return v;
    }

    private void stopMusic() {
        Log.e(TAG, "music stop");
        if(player != null) {
            if(player.isPlaying()) {
                player.stop();
            }
            player.release();
            player = null;
        }
    }

    private void pauseMusic() {
        Log.e(TAG, "music pause");
    }


    private void playMusic() {
        Log.e(TAG, "music play");
        if(player != null) {
            stopMusic();
        }
        player = new MediaPlayer();
        player.setOnPreparedListener(this);
        player.prepareAsync();

    }


    private void closeHandler() {
        handler.removeCallbacks(work);
    }


    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}