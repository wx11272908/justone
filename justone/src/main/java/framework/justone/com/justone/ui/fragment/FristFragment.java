package framework.justone.com.justone.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import framework.justone.com.justone.R;
import framework.justone.com.justone.ui.activity.MainActivity;
import framework.justone.com.justone.ui.base.BaseFragment;


public class FristFragment extends BaseFragment {

    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getInflaterId() {
        return R.layout.fragment_frist;
    }

    @Override
    protected void initFragment(View view) {
    }


    @Override
    public void onResume() {
        super.onResume();
        setActionBar();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public void setActionBar() {
        ((MainActivity) context).setActionbar("首页", false);
    }
}
