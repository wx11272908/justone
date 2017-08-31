package framework.justone.com.justone.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import framework.justone.com.justone.R;
import framework.justone.com.justone.ui.base.BaseFragment;

/**
 * secondFragment
 * @author trap
 * @time 2017年08月31日16:12:43
 */
public class SecondFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getInflaterId() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initFragment(View view) {

    }

}
