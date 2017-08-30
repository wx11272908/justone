package framework.justone.com.justone.util.view;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by lizhenbin on 16/9/28 11:30.
 * Explain:
 */
public class ViewHolder {

    @SuppressWarnings("unchecked")
    public static View get(View view, int id) {
        SparseArray viewHolder = (SparseArray) view.getTag();
        if (viewHolder == null) {
            viewHolder = new

                    SparseArray();
            view.setTag(viewHolder);
        }
        View childView = (View) viewHolder.get(id);
        if (childView == null) {
            childView =

                    view.findViewById(id);
            viewHolder.put(id,

                    childView);
        }
        return childView;
    }
}
