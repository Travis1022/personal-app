package txw.com.whatnew.recycler;

import android.view.View;

/**
 * item 点击事件
 * Created by txw on 2018/6/7.
 */
public interface OnItemClickListener {

    //点击
    void onItemClick(View view, int position);

    //长按
    void onItemLongClick(View view, int position);

}
