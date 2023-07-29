package com.example.timepass.Models;

import java.util.List;

public class MemeModel {
    int count;
    List<ApiModel> memes;

    public MemeModel(int count, List<ApiModel> memes) {
        this.count = count;
        this.memes = memes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ApiModel> getMemes() {
        return memes;
    }

    public void setMemes(List<ApiModel> memes) {
        this.memes = memes;
    }
}
