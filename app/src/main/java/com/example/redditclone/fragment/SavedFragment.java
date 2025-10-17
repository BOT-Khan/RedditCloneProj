package com.example.redditclone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.redditclone.MainActivity;
import com.example.redditclone.R;

public class SavedFragment extends Fragment {

    public SavedFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);

        // --- Back Button ---
        ImageButton backButton = view.findViewById(R.id.back_button);
        if (backButton != null) {
            backButton.setOnClickListener(v -> {
                if (getActivity() instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.switchFragment(MainActivity.FRAGMENT_HOME, new HomeFragment());
                }
            });
        }

        // --- Tabs: Posts & Comments ---
        TextView tabPosts = view.findViewById(R.id.tab_saved_posts);
        TextView tabComments = view.findViewById(R.id.tab_saved_comments);

        // Default = show Posts
        getChildFragmentManager().beginTransaction()
                .replace(R.id.saved_content_container, new SavedPostsFragment())
                .commit();

        tabPosts.setOnClickListener(v -> {
            // Update tab colors
            tabPosts.setTextColor(getResources().getColor(android.R.color.black));
            tabPosts.setTypeface(null, android.graphics.Typeface.BOLD);
            tabComments.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tabComments.setTypeface(null, android.graphics.Typeface.NORMAL);

            // Load Posts fragment
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.saved_content_container, new SavedPostsFragment())
                    .commit();
        });

        tabComments.setOnClickListener(v -> {
            // Update tab colors
            tabComments.setTextColor(getResources().getColor(android.R.color.black));
            tabComments.setTypeface(null, android.graphics.Typeface.BOLD);
            tabPosts.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tabPosts.setTypeface(null, android.graphics.Typeface.NORMAL);

            // Load Comments fragment
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.saved_content_container, new SavedCommentsFragment())
                    .commit();
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() != null) {
            View toolbar = getActivity().findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setVisibility(View.VISIBLE);
            }
        }
    }
}
