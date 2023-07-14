// Generated by view binder compiler. Do not edit!
package dev.android.appbusesdriver.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import dev.android.appbusesdriver.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemPassengerBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final CardView btnFrequency;

  @NonNull
  public final CardView btnProfile;

  @NonNull
  public final ImageView imgProfile;

  @NonNull
  public final TextView txtID;

  @NonNull
  public final TextView txtSeat;

  private ItemPassengerBinding(@NonNull CardView rootView, @NonNull CardView btnFrequency,
      @NonNull CardView btnProfile, @NonNull ImageView imgProfile, @NonNull TextView txtID,
      @NonNull TextView txtSeat) {
    this.rootView = rootView;
    this.btnFrequency = btnFrequency;
    this.btnProfile = btnProfile;
    this.imgProfile = imgProfile;
    this.txtID = txtID;
    this.txtSeat = txtSeat;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemPassengerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemPassengerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_passenger, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemPassengerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnFrequency;
      CardView btnFrequency = ViewBindings.findChildViewById(rootView, id);
      if (btnFrequency == null) {
        break missingId;
      }

      id = R.id.btnProfile;
      CardView btnProfile = ViewBindings.findChildViewById(rootView, id);
      if (btnProfile == null) {
        break missingId;
      }

      id = R.id.imgProfile;
      ImageView imgProfile = ViewBindings.findChildViewById(rootView, id);
      if (imgProfile == null) {
        break missingId;
      }

      id = R.id.txtID;
      TextView txtID = ViewBindings.findChildViewById(rootView, id);
      if (txtID == null) {
        break missingId;
      }

      id = R.id.txtSeat;
      TextView txtSeat = ViewBindings.findChildViewById(rootView, id);
      if (txtSeat == null) {
        break missingId;
      }

      return new ItemPassengerBinding((CardView) rootView, btnFrequency, btnProfile, imgProfile,
          txtID, txtSeat);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
