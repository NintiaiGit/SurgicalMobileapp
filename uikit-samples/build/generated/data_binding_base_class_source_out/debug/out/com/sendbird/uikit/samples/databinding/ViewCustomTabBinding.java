// Generated by view binder compiler. Do not edit!
package com.sendbird.uikit.samples.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.sendbird.uikit.samples.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ViewCustomTabBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final TextView badge;

  @NonNull
  public final ImageView icon;

  @NonNull
  public final TextView title;

  private ViewCustomTabBinding(@NonNull FrameLayout rootView, @NonNull TextView badge,
      @NonNull ImageView icon, @NonNull TextView title) {
    this.rootView = rootView;
    this.badge = badge;
    this.icon = icon;
    this.title = title;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ViewCustomTabBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ViewCustomTabBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.view_custom_tab, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ViewCustomTabBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.badge;
      TextView badge = ViewBindings.findChildViewById(rootView, id);
      if (badge == null) {
        break missingId;
      }

      id = R.id.icon;
      ImageView icon = ViewBindings.findChildViewById(rootView, id);
      if (icon == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      return new ViewCustomTabBinding((FrameLayout) rootView, badge, icon, title);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
