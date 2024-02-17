package com.example.mini_proj.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
    private CodeScanner scanner;
    FragmentQrScannerBinding binding;
    String u ="j";

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
        binding.scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanner.startPreview();
            }
        });
        Toast.makeText(getContext(),"ji", Toast.LENGTH_SHORT).show();

        return binding.getRoot();
    }

    @Override
    public void onDecoded(@NonNull Result result) {
                u=result.getText();
        while(true)
        {
            Log.d("chk", u);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        scanner.startPreview();
    }

    @Override
    public void onPause() {
        scanner.releaseResources();
        super.onPause();
    }
}