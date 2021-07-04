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

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;


public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private ItemClicked itemClicked;
    private Context context;

    WordListAdapter(ItemClicked itemClicked) {
        this.itemClicked = itemClicked;
    }
    public Word getWordAtPosition (int position) {
        return mWords.get(position);
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        public LinearLayout mainLayout;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            mainLayout = itemView.findViewById(R.id.recyclerview);
        }
    }

    private List<Word> mWords = Collections.emptyList(); // Cached copy of words

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new WordListAdapter.WordViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item,null));
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, final int position) {
        if (mWords != null) {
        Word current = mWords.get(position);

        holder.wordItemView.setText(current.getWord());
        holder.wordItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v,current);
            }
        });
        holder.wordItemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                itemClicked.updateWord(current);
                return true;
            }
        });
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }
    public void showPopup(View view, final Word word){
     itemClicked.updateWord(word);
    }


    void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
    public interface ItemClicked{
        void updateWord(Word word);
        void deleteWord(Word word);
    }
}


