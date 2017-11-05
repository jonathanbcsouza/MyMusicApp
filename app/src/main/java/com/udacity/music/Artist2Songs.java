package com.udacity.music;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Artist2Songs extends AllSongs {

    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(getString(R.string.artist2music1), R.drawable.ic_play_arrow_white_24dp, R.raw.felguk_dead_man_wag));
        words.add(new Word(getString(R.string.artist2music2), R.drawable.ic_play_arrow_white_24dp, R.raw.felguk_white_horse));
        words.add(new Word(getString(R.string.artist2music3), R.drawable.ic_play_arrow_white_24dp, R.raw.felguk_hands_up_for_detroit));

        final WordAdapter itemsAdapter = new WordAdapter(this, words);
        ListView listView = (ListView) findViewById(R.id.word_list);
        listView.setAdapter(itemsAdapter);

        // Getting Model of Header from XML
        ViewGroup headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.instructions_header, listView, false);
        TextView makeHeader = (TextView) headerView.findViewById(R.id.text_listview_header);
        makeHeader.setText(getString(R.string.header_allSongs));
        listView.addHeaderView(headerView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                if (position == 0) {
                } else {

                    final Word word = words.get(position - 1);

                    releaseMediaPlayer();

                    mMediaPlayer = MediaPlayer.create(Artist2Songs.this, word.getAudioResourceId());

                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {

                            Word songPosition = words.get(position - 1);
                            String figureOutMusicName = songPosition.getMusicName().toUpperCase();
                            Toast.makeText(Artist2Songs.this, "The song " + figureOutMusicName + " is finished", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    /**
     * Clean up the media player by releasing its resources.
     */

    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {

            mMediaPlayer.release();

            mMediaPlayer = null;
        }
    }
}




