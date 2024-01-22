// Generated by view binder compiler. Do not edit!
package com.sendbird.uikit.samples.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.sendbird.uikit.samples.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHomeBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatButton btPatientregister;

  @NonNull
  public final RecyclerView patientRecyclerView;

  @NonNull
  public final TextView titleTextView;

  private ActivityHomeBinding(@NonNull RelativeLayout rootView,
      @NonNull AppCompatButton btPatientregister, @NonNull RecyclerView patientRecyclerView,
      @NonNull TextView titleTextView) {
    this.rootView = rootView;
    this.btPatientregister = btPatientregister;
    this.patientRecyclerView = patientRecyclerView;
    this.titleTextView = titleTextView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btPatientregister;
      AppCompatButton btPatientregister = ViewBindings.findChildViewById(rootView, id);
      if (btPatientregister == null) {
        break missingId;
      }

      id = R.id.patientRecyclerView;
      RecyclerView patientRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (patientRecyclerView == null) {
        break missingId;
      }

      id = R.id.titleTextView;
      TextView titleTextView = ViewBindings.findChildViewById(rootView, id);
      if (titleTextView == null) {
        break missingId;
      }

      return new ActivityHomeBinding((RelativeLayout) rootView, btPatientregister,
          patientRecyclerView, titleTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}