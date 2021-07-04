package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;


public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private Context mContext;
    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private int nPosition;
        private ImageView img;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            img  = itemView.findViewById(R.id.btnUpImage);
        }
        public void setData(String note, int position) {
            wordItemView.setText(note);
            nPosition = position;

        }

        public void setListeners(final Word word) {
            wordItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopup(v,word);
                }
            });

        }
    }

    private List<Word> mWords; // Cached copy of words
    private ItemClicked itemClicked;
    private Context context;

    WordListAdapter(ItemClicked itemClicked) {
        this.itemClicked = itemClicked;
        mContext = context;
    }
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new WordListAdapter.WordViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item,null));
    }
    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {

            Word current = mWords.get(position);
             holder.setData(current.getWord(),position);
             holder.setListeners(current);

    }
    public void showPopup(View view, final Word word){
        itemClicked.wordUpdate(word);
    }
    void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }
    public Word getWordAtPosition (int position) {
        return mWords.get(position);
    }
    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
    public interface ItemClicked{
        void wordUpdate(Word word);
        void wordDelete(Word word);
    }
}


