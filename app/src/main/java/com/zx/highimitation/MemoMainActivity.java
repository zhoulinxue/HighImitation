package com.zx.highimitation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.zx.mvplibrary.BaseActivity;

import butterknife.BindView;

public class MemoMainActivity extends BaseActivity{

    @BindView(R.id.test_edit)
    MemoEdite mMemoEdite;
    @Override
    protected int initLayout() {
        return R.layout.memo_main_activity;
    }

    @Override
    protected void onCreateView() {
//        ButterKnife.bind(this);
        mMemoEdite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showToast(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mMemoEdite.requestFocus();
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }

    @Override
    public void onSuccess(String requestCode, Object object) {

    }

    @Override
    public void onError(String requestCode, String msg) {

    }
}
