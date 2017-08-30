package framework.justone.com.justone.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by lizhenbin on 16/9/28 12:22.
 * Explain:
 */
public class MyAaapter extends BaseAdapter {

    private Context context;

    public MyAaapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        if(convertView == null){
//            convertView = LayoutInflater.from(context).inflate(R.layout.banana_phone, parent,false);
//        }
//
//        ImageView bananaView = ViewHolder.get(convertView,R.id.phone);
        return convertView;
    }

}
