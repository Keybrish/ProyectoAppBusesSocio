// Generated by view binder compiler. Do not edit!
package dev.android.appbusesdriver.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import dev.android.appbusesdriver.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CardView btnProfile;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final ConstraintLayout constraintLayout5;

  @NonNull
  public final TextView editTextTextPersonName;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ImageView imageView4;

  @NonNull
  public final ImageView imageView5;

  @NonNull
  public final ImageView imageView6;

  @NonNull
  public final ImageView imgProfile;

  @NonNull
  public final RecyclerView rvEmployees;

  @NonNull
  public final Spinner spinner;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView1;

  @NonNull
  public final TextView textView11;

  @NonNull
  public final TextView textView15;

  @NonNull
  public final TextView textView16;

  @NonNull
  public final TextView textView17;

  @NonNull
  public final TextView textView18;

  @NonNull
  public final TextView textView19;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView20;

  @NonNull
  public final TextView textView3;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView9;

  @NonNull
  public final View view2;

  @NonNull
  public final View view3;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull CardView btnProfile,
      @NonNull ConstraintLayout constraintLayout, @NonNull ConstraintLayout constraintLayout5,
      @NonNull TextView editTextTextPersonName, @NonNull ImageView imageView2,
      @NonNull ImageView imageView3, @NonNull ImageView imageView4, @NonNull ImageView imageView5,
      @NonNull ImageView imageView6, @NonNull ImageView imgProfile,
      @NonNull RecyclerView rvEmployees, @NonNull Spinner spinner, @NonNull TextView textView,
      @NonNull TextView textView1, @NonNull TextView textView11, @NonNull TextView textView15,
      @NonNull TextView textView16, @NonNull TextView textView17, @NonNull TextView textView18,
      @NonNull TextView textView19, @NonNull TextView textView2, @NonNull TextView textView20,
      @NonNull TextView textView3, @NonNull TextView textView7, @NonNull TextView textView9,
      @NonNull View view2, @NonNull View view3) {
    this.rootView = rootView;
    this.btnProfile = btnProfile;
    this.constraintLayout = constraintLayout;
    this.constraintLayout5 = constraintLayout5;
    this.editTextTextPersonName = editTextTextPersonName;
    this.imageView2 = imageView2;
    this.imageView3 = imageView3;
    this.imageView4 = imageView4;
    this.imageView5 = imageView5;
    this.imageView6 = imageView6;
    this.imgProfile = imgProfile;
    this.rvEmployees = rvEmployees;
    this.spinner = spinner;
    this.textView = textView;
    this.textView1 = textView1;
    this.textView11 = textView11;
    this.textView15 = textView15;
    this.textView16 = textView16;
    this.textView17 = textView17;
    this.textView18 = textView18;
    this.textView19 = textView19;
    this.textView2 = textView2;
    this.textView20 = textView20;
    this.textView3 = textView3;
    this.textView7 = textView7;
    this.textView9 = textView9;
    this.view2 = view2;
    this.view3 = view3;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnProfile;
      CardView btnProfile = ViewBindings.findChildViewById(rootView, id);
      if (btnProfile == null) {
        break missingId;
      }

      id = R.id.constraintLayout;
      ConstraintLayout constraintLayout = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout == null) {
        break missingId;
      }

      id = R.id.constraintLayout5;
      ConstraintLayout constraintLayout5 = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout5 == null) {
        break missingId;
      }

      id = R.id.editTextTextPersonName;
      TextView editTextTextPersonName = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPersonName == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.imageView4;
      ImageView imageView4 = ViewBindings.findChildViewById(rootView, id);
      if (imageView4 == null) {
        break missingId;
      }

      id = R.id.imageView5;
      ImageView imageView5 = ViewBindings.findChildViewById(rootView, id);
      if (imageView5 == null) {
        break missingId;
      }

      id = R.id.imageView6;
      ImageView imageView6 = ViewBindings.findChildViewById(rootView, id);
      if (imageView6 == null) {
        break missingId;
      }

      id = R.id.imgProfile;
      ImageView imgProfile = ViewBindings.findChildViewById(rootView, id);
      if (imgProfile == null) {
        break missingId;
      }

      id = R.id.rvEmployees;
      RecyclerView rvEmployees = ViewBindings.findChildViewById(rootView, id);
      if (rvEmployees == null) {
        break missingId;
      }

      id = R.id.spinner;
      Spinner spinner = ViewBindings.findChildViewById(rootView, id);
      if (spinner == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView1;
      TextView textView1 = ViewBindings.findChildViewById(rootView, id);
      if (textView1 == null) {
        break missingId;
      }

      id = R.id.textView11;
      TextView textView11 = ViewBindings.findChildViewById(rootView, id);
      if (textView11 == null) {
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

      id = R.id.textView17;
      TextView textView17 = ViewBindings.findChildViewById(rootView, id);
      if (textView17 == null) {
        break missingId;
      }

      id = R.id.textView18;
      TextView textView18 = ViewBindings.findChildViewById(rootView, id);
      if (textView18 == null) {
        break missingId;
      }

      id = R.id.textView19;
      TextView textView19 = ViewBindings.findChildViewById(rootView, id);
      if (textView19 == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView20;
      TextView textView20 = ViewBindings.findChildViewById(rootView, id);
      if (textView20 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = ViewBindings.findChildViewById(rootView, id);
      if (textView3 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = ViewBindings.findChildViewById(rootView, id);
      if (textView9 == null) {
        break missingId;
      }

      id = R.id.view2;
      View view2 = ViewBindings.findChildViewById(rootView, id);
      if (view2 == null) {
        break missingId;
      }

      id = R.id.view3;
      View view3 = ViewBindings.findChildViewById(rootView, id);
      if (view3 == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, btnProfile, constraintLayout,
          constraintLayout5, editTextTextPersonName, imageView2, imageView3, imageView4, imageView5,
          imageView6, imgProfile, rvEmployees, spinner, textView, textView1, textView11, textView15,
          textView16, textView17, textView18, textView19, textView2, textView20, textView3,
          textView7, textView9, view2, view3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
