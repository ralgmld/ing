package com.example.YourVoice.ui.Record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.YourVoice.MainActivity;
import com.example.YourVoice.R;
import com.example.YourVoice.databinding.FragmentCalllogBinding;
import com.example.YourVoice.databinding.FragmentRecordBinding;

public class RecordFragment extends Fragment {

    View view; //
    private TextView callText; //레이아웃에 있는 번호적는 Text를 받기위한 변수 선언.
    MainActivity mainActivity;
    private FragmentCalllogBinding binding;

    /*public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecordViewModel recordViewModel =
                new ViewModelProvider(this).get(RecordViewModel.class);

        binding = FragmentRecordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.sttResult;
        recordViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }*/
    @Override //프래그먼트 생성시에 한번 실행되는 메소드
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override //프래그먼트 생성시에 뷰(화면)를 구성하는 메소드
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_record,null); //view를 불러온다.
        //SetLayout(view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}