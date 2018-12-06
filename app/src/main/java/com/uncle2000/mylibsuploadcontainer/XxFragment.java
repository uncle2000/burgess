package com.uncle2000.mylibsuploadcontainer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.uncle2000.libbase.BaseFragment;
import com.uncle2000.mylibsuploadcontainer.databinding.SettingBinding;

public class XxFragment extends BaseFragment {
    private SettingBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.setting, container, false);
        return binding.getRoot();
    }
}
