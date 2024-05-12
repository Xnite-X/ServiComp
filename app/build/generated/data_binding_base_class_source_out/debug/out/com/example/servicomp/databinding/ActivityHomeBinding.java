// Generated by view binder compiler. Do not edit!
package com.example.servicomp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.servicomp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView checkSeries;

  @NonNull
  public final ImageView checkSlowLaptop;

  @NonNull
  public final ImageView hardiskCheck;

  @NonNull
  public final ImageView icLaptop;

  @NonNull
  public final ImageView icSmartphone;

  @NonNull
  public final ImageButton imageButton5;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final ImageView keyboardCheck;

  @NonNull
  public final LinearLayout linearLayout3;

  @NonNull
  public final ImageView ramCheck;

  @NonNull
  public final ScrollView scrollView2;

  @NonNull
  public final TextView textView14;

  @NonNull
  public final TextView textView15;

  @NonNull
  public final TextView textView16;

  @NonNull
  public final ImageView touchscreenCheck;

  @NonNull
  public final TextView txtCheckSeries;

  @NonNull
  public final TextView txtComputer;

  @NonNull
  public final TextView txtHardiskCheck;

  @NonNull
  public final TextView txtKeyboardCheck;

  @NonNull
  public final TextView txtLaptop;

  @NonNull
  public final TextView txtRamCheck;

  @NonNull
  public final TextView txtSlowLaptop;

  @NonNull
  public final TextView txtSmartphone;

  @NonNull
  public final TextView txtTouchscreenCheck;

  private ActivityHomeBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView checkSeries,
      @NonNull ImageView checkSlowLaptop, @NonNull ImageView hardiskCheck,
      @NonNull ImageView icLaptop, @NonNull ImageView icSmartphone,
      @NonNull ImageButton imageButton5, @NonNull ImageView imageView4,
      @NonNull ImageView keyboardCheck, @NonNull LinearLayout linearLayout3,
      @NonNull ImageView ramCheck, @NonNull ScrollView scrollView2, @NonNull TextView textView14,
      @NonNull TextView textView15, @NonNull TextView textView16,
      @NonNull ImageView touchscreenCheck, @NonNull TextView txtCheckSeries,
      @NonNull TextView txtComputer, @NonNull TextView txtHardiskCheck,
      @NonNull TextView txtKeyboardCheck, @NonNull TextView txtLaptop,
      @NonNull TextView txtRamCheck, @NonNull TextView txtSlowLaptop,
      @NonNull TextView txtSmartphone, @NonNull TextView txtTouchscreenCheck) {
    this.rootView = rootView;
    this.checkSeries = checkSeries;
    this.checkSlowLaptop = checkSlowLaptop;
    this.hardiskCheck = hardiskCheck;
    this.icLaptop = icLaptop;
    this.icSmartphone = icSmartphone;
    this.imageButton5 = imageButton5;
    this.imageView4 = imageView4;
    this.keyboardCheck = keyboardCheck;
    this.linearLayout3 = linearLayout3;
    this.ramCheck = ramCheck;
    this.scrollView2 = scrollView2;
    this.textView14 = textView14;
    this.textView15 = textView15;
    this.textView16 = textView16;
    this.touchscreenCheck = touchscreenCheck;
    this.txtCheckSeries = txtCheckSeries;
    this.txtComputer = txtComputer;
    this.txtHardiskCheck = txtHardiskCheck;
    this.txtKeyboardCheck = txtKeyboardCheck;
    this.txtLaptop = txtLaptop;
    this.txtRamCheck = txtRamCheck;
    this.txtSlowLaptop = txtSlowLaptop;
    this.txtSmartphone = txtSmartphone;
    this.txtTouchscreenCheck = txtTouchscreenCheck;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
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
      id = R.id.check_series;
      ImageView checkSeries = ViewBindings.findChildViewById(rootView, id);
      if (checkSeries == null) {
        break missingId;
      }

      id = R.id.check_slow_laptop;
      ImageView checkSlowLaptop = ViewBindings.findChildViewById(rootView, id);
      if (checkSlowLaptop == null) {
        break missingId;
      }

      id = R.id.hardisk_check;
      ImageView hardiskCheck = ViewBindings.findChildViewById(rootView, id);
      if (hardiskCheck == null) {
        break missingId;
      }

      id = R.id.ic_laptop;
      ImageView icLaptop = ViewBindings.findChildViewById(rootView, id);
      if (icLaptop == null) {
        break missingId;
      }

      id = R.id.ic_smartphone;
      ImageView icSmartphone = ViewBindings.findChildViewById(rootView, id);
      if (icSmartphone == null) {
        break missingId;
      }

      id = R.id.imageButton5;
      ImageButton imageButton5 = ViewBindings.findChildViewById(rootView, id);
      if (imageButton5 == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.keyboard_check;
      ImageView keyboardCheck = ViewBindings.findChildViewById(rootView, id);
      if (keyboardCheck == null) {
        break missingId;
      }

      id = R.id.linearLayout3;
      LinearLayout linearLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout3 == null) {
        break missingId;
      }

      id = R.id.ram_check;
      ImageView ramCheck = ViewBindings.findChildViewById(rootView, id);
      if (ramCheck == null) {
        break missingId;
      }

      id = R.id.scrollView2;
      ScrollView scrollView2 = ViewBindings.findChildViewById(rootView, id);
      if (scrollView2 == null) {
        break missingId;
      }

      id = R.id.textView14;
      TextView textView14 = ViewBindings.findChildViewById(rootView, id);
      if (textView14 == null) {
        break missingId;
      }

      id = R.id.textView15;
      TextView textView15 = ViewBindings.findChildViewById(rootView, id);
      if (textView15 == null) {
        break missingId;
      }

      id = R.id.textView16;
      TextView textView16 = ViewBindings.findChildViewById(rootView, id);
      if (textView16 == null) {
        break missingId;
      }

      id = R.id.touchscreen_check;
      ImageView touchscreenCheck = ViewBindings.findChildViewById(rootView, id);
      if (touchscreenCheck == null) {
        break missingId;
      }

      id = R.id.txt_check_series;
      TextView txtCheckSeries = ViewBindings.findChildViewById(rootView, id);
      if (txtCheckSeries == null) {
        break missingId;
      }

      id = R.id.txt_computer;
      TextView txtComputer = ViewBindings.findChildViewById(rootView, id);
      if (txtComputer == null) {
        break missingId;
      }

      id = R.id.txt_hardisk_check;
      TextView txtHardiskCheck = ViewBindings.findChildViewById(rootView, id);
      if (txtHardiskCheck == null) {
        break missingId;
      }

      id = R.id.txt_keyboard_check;
      TextView txtKeyboardCheck = ViewBindings.findChildViewById(rootView, id);
      if (txtKeyboardCheck == null) {
        break missingId;
      }

      id = R.id.txt_laptop;
      TextView txtLaptop = ViewBindings.findChildViewById(rootView, id);
      if (txtLaptop == null) {
        break missingId;
      }

      id = R.id.txt_ram_check;
      TextView txtRamCheck = ViewBindings.findChildViewById(rootView, id);
      if (txtRamCheck == null) {
        break missingId;
      }

      id = R.id.txt_slow_laptop;
      TextView txtSlowLaptop = ViewBindings.findChildViewById(rootView, id);
      if (txtSlowLaptop == null) {
        break missingId;
      }

      id = R.id.txt_smartphone;
      TextView txtSmartphone = ViewBindings.findChildViewById(rootView, id);
      if (txtSmartphone == null) {
        break missingId;
      }

      id = R.id.txt_touchscreen_check;
      TextView txtTouchscreenCheck = ViewBindings.findChildViewById(rootView, id);
      if (txtTouchscreenCheck == null) {
        break missingId;
      }

      return new ActivityHomeBinding((ConstraintLayout) rootView, checkSeries, checkSlowLaptop,
          hardiskCheck, icLaptop, icSmartphone, imageButton5, imageView4, keyboardCheck,
          linearLayout3, ramCheck, scrollView2, textView14, textView15, textView16,
          touchscreenCheck, txtCheckSeries, txtComputer, txtHardiskCheck, txtKeyboardCheck,
          txtLaptop, txtRamCheck, txtSlowLaptop, txtSmartphone, txtTouchscreenCheck);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}