// Generated by view binder compiler. Do not edit!
package com.example.servicomp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.servicomp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView TextView48;

  @NonNull
  public final TextView alamatProfile;

  @NonNull
  public final BottomNavigationView bottomNavigationView;

  @NonNull
  public final TextView emailProfile;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView namaProfile;

  @NonNull
  public final TextView textView13;

  @NonNull
  public final TextView textView14;

  @NonNull
  public final TextView textView20;

  private FragmentProfileBinding(@NonNull ConstraintLayout rootView, @NonNull TextView TextView48,
      @NonNull TextView alamatProfile, @NonNull BottomNavigationView bottomNavigationView,
      @NonNull TextView emailProfile, @NonNull ConstraintLayout main, @NonNull TextView namaProfile,
      @NonNull TextView textView13, @NonNull TextView textView14, @NonNull TextView textView20) {
    this.rootView = rootView;
    this.TextView48 = TextView48;
    this.alamatProfile = alamatProfile;
    this.bottomNavigationView = bottomNavigationView;
    this.emailProfile = emailProfile;
    this.main = main;
    this.namaProfile = namaProfile;
    this.textView13 = textView13;
    this.textView14 = textView14;
    this.textView20 = textView20;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.TextView48;
      TextView TextView48 = ViewBindings.findChildViewById(rootView, id);
      if (TextView48 == null) {
        break missingId;
      }

      id = R.id.alamatProfile;
      TextView alamatProfile = ViewBindings.findChildViewById(rootView, id);
      if (alamatProfile == null) {
        break missingId;
      }

      id = R.id.bottomNavigationView;
      BottomNavigationView bottomNavigationView = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavigationView == null) {
        break missingId;
      }

      id = R.id.emailProfile;
      TextView emailProfile = ViewBindings.findChildViewById(rootView, id);
      if (emailProfile == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.namaProfile;
      TextView namaProfile = ViewBindings.findChildViewById(rootView, id);
      if (namaProfile == null) {
        break missingId;
      }

      id = R.id.textView13;
      TextView textView13 = ViewBindings.findChildViewById(rootView, id);
      if (textView13 == null) {
        break missingId;
      }

      id = R.id.textView14;
      TextView textView14 = ViewBindings.findChildViewById(rootView, id);
      if (textView14 == null) {
        break missingId;
      }

      id = R.id.textView20;
      TextView textView20 = ViewBindings.findChildViewById(rootView, id);
      if (textView20 == null) {
        break missingId;
      }

      return new FragmentProfileBinding((ConstraintLayout) rootView, TextView48, alamatProfile,
          bottomNavigationView, emailProfile, main, namaProfile, textView13, textView14,
          textView20);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}