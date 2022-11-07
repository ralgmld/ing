// Generated by view binder compiler. Do not edit!
package com.example.YourVoice.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.YourVoice.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentRecordBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button endCallBtn;

  @NonNull
  public final TextView speechToText;

  @NonNull
  public final TextView timer;

  private FragmentRecordBinding(@NonNull ConstraintLayout rootView, @NonNull Button endCallBtn,
      @NonNull TextView speechToText, @NonNull TextView timer) {
    this.rootView = rootView;
    this.endCallBtn = endCallBtn;
    this.speechToText = speechToText;
    this.timer = timer;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRecordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRecordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_record, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRecordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.endCallBtn;
      Button endCallBtn = ViewBindings.findChildViewById(rootView, id);
      if (endCallBtn == null) {
        break missingId;
      }

      id = R.id.speechToText;
      TextView speechToText = ViewBindings.findChildViewById(rootView, id);
      if (speechToText == null) {
        break missingId;
      }

      id = R.id.timer;
      TextView timer = ViewBindings.findChildViewById(rootView, id);
      if (timer == null) {
        break missingId;
      }

      return new FragmentRecordBinding((ConstraintLayout) rootView, endCallBtn, speechToText,
          timer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
