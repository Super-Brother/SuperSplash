package com.wenchao.supersplash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ParallaxFragment extends Fragment {

    private List<View> parallaxViews = new ArrayList<>();

    public List<View> getParallaxViews() {
        return parallaxViews;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater original, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int layoutId = args.getInt("layoutId");
        ParallaxLayoutInflater inflater = new ParallaxLayoutInflater(original, getActivity(), this);
        return inflater.inflate(layoutId, null);
    }

}
