// Generated by view binder compiler. Do not edit!
package com.sendbird.uikit.samples.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.sendbird.uikit.samples.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityOpenChannelMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout background;

  @NonNull
  public final View border;

  @NonNull
  public final TextView description;

  @NonNull
  public final TabLayout tabLayout;

  @NonNull
  public final Toolbar titleBar;

  @NonNull
  public final ViewPager2 viewPager;

  private ActivityOpenChannelMainBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout background, @NonNull View border, @NonNull TextView description,
      @NonNull TabLayout tabLayout, @NonNull Toolbar titleBar, @NonNull ViewPager2 viewPager) {
    this.rootView = rootView;
    this.background = background;
    this.border = border;
    this.description = description;
    this.tabLayout = tabLayout;
    this.titleBar = titleBar;
    this.viewPager = viewPager;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityOpenChannelMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityOpenChannelMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_open_channel_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityOpenChannelMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout background = (ConstraintLayout) rootView;

      id = R.id.border;
      View border = ViewBindings.findChildViewById(rootView, id);
      if (border == null) {
        break missingId;
      }

      id = R.id.description;
      TextView description = ViewBindings.findChildViewById(rootView, id);
      if (description == null) {
        break missingId;
      }

      id = R.id.tabLayout;
      TabLayout tabLayout = ViewBindings.findChildViewById(rootView, id);
      if (tabLayout == null) {
        break missingId;
      }

      id = R.id.titleBar;
      Toolbar titleBar = ViewBindings.findChildViewById(rootView, id);
      if (titleBar == null) {
        break missingId;
      }

      id = R.id.viewPager;
      ViewPager2 viewPager = ViewBindings.findChildViewById(rootView, id);
      if (viewPager == null) {
        break missingId;
      }

      return new ActivityOpenChannelMainBinding((ConstraintLayout) rootView, background, border,
          description, tabLayout, titleBar, viewPager);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
