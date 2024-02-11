package com.example.mini_proj.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.mini_proj.R;
import com.example.mini_proj.databinding.FragmentQrScannerBinding;
import com.google.zxing.Result;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QrScanner#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QrScanner extends Fragment implements DecodeCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private CodeScanner scanner;
    FragmentQrScannerBinding binding;

    public QrScanner() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QrScanner.
     */
    // TODO: Rename and change types and number of parameters
    public static QrScanner newInstance() {
        QrScanner fragment = new QrScanner();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQrScannerBinding.inflate(inflater, container, false);
        scanner = new CodeScanner(getActivity(), binding.scannerView);
        return binding.getRoot();
    }

    @Override
    public void onDecoded(@NonNull Result result) {

                Toast.makeText(getContext(), result.getText(), Toast.LENGTH_SHORT).show();

    }
}