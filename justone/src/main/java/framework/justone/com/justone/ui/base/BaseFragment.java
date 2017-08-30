package framework.justone.com.justone.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by trap on 17/8/30.
 */
public abstract class BaseFragment extends Fragment {

    protected ViewGroup viewGroup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(getInflaterId(),container,false);
        initFragment(viewGroup);
        return viewGroup;
    }

    /**
     * 获取fragment布局
     * @author trap
     * @time  2017年08月30日16:46:45
     */
    abstract protected int getInflaterId();

    /**
     * 初始化fragment view
     * @author trap
     * @time  2017年08月30日16:47:10
     */
    abstract protected void initFragment(View view);

    /**
     * 设置titlebar
     */
    public void setActionBar() {

    }
}
