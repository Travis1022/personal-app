package txw.com.whatnew.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import txw.com.whatnew.R;

/**
 * Adapter
 * Created by txw on 2018/6/7.
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private List<String> mDataList;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public InfoAdapter(List<String> dataList, Context context) {
        mDataList = dataList;
        mContext = context;
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_info, parent, false);
        return new InfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final InfoViewHolder holder, final int position) {
        holder.mIvInfo.setImageResource(R.drawable.logo);
        holder.mTvInfo.setText(mDataList.get(position) + (position+1));

        //点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 删除item
     *
     * @param position
     */
    public void removeData(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * item对应的view
     */
    static class InfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_info)
        ImageView mIvInfo;
        @BindView(R.id.tv_info)
        TextView mTvInfo;

        public InfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
