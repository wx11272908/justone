package framework.justone.com.justone.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import framework.justone.com.justone.R;
import framework.justone.com.justone.ui.base.BaseFragment;

public class FourthFragment extends BaseFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getInflaterId() {
        return R.layout.fragment_fourth;
    }

    @Override
    protected void initFragment(View view) {

    }
}
