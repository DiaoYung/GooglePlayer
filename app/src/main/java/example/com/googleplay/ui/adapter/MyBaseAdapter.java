package example.com.googleplay.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import example.com.googleplay.ui.holder.BaseHolder;
import example.com.googleplay.ui.holder.MoreHolder;
import example.com.googleplay.utils.UIUtils;

/**
 * Created by root on 16-12-13.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private ArrayList<T> data;

    private  static final int TYPE_MORE = 0;
    private  static final int TYPE_NORMAL = 1;

    public MyBaseAdapter(ArrayList<T> data){
        this.data = data;
    }

    @Override
    public int getCount(){
        return data.size() + 1;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public T getItem(int position){
        return data.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == (getCount() - 1 )){
            return TYPE_MORE;
        }
        return getInnerType(position);

    }

    public int getInnerType(int position) {
        return TYPE_NORMAL;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        BaseHolder holder;
        if (convertView == null) {

            if (getItemViewType(position) == TYPE_MORE){
                holder = new MoreHolder(hasMore());
            }else{
                holder = getHolder(position);
            }
        }else
        {
            holder = (BaseHolder) convertView.getTag();
        }

        if (getItemViewType(position) != TYPE_MORE){
            holder.setData(getItem(position));
        }else{

            MoreHolder moreHolder = (MoreHolder) holder;
            if (moreHolder.getData() == moreHolder.LOAD_MORE_MORE){
            loaMore(moreHolder);
            }
        }

        return holder.getRootView();
    }

    public boolean hasMore() {
        return true;
    }

    private boolean isLoadMore = false;

    public void loaMore(final MoreHolder holder){
        if (!isLoadMore){

            isLoadMore = true;
            new Thread(){
                @Override
                public void run(){
                    final ArrayList<T> moreData = onLoadMore();

                    UIUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            if (moreData != null){

                                if (moreData.size() < 20){
                                    Toast.makeText(UIUtils.getContext(), "NO More Data", Toast.LENGTH_SHORT).show();
                                    holder.setData(MoreHolder.LOAD_MORE_NONE);
                                }else{
                                    holder.setData(MoreHolder.LOAD_MORE_MORE);
                                }

                                data.addAll(moreData);
                            }else {
                                holder.setData(MoreHolder.LOAD_MORE_ERROR);
                            }
                            MyBaseAdapter.this.notifyDataSetChanged();

                            isLoadMore = false;

                        }
                    });
                }
            }.start();

        }
    }

    protected abstract ArrayList<T> onLoadMore();

    public abstract BaseHolder getHolder(int position);

    public int getListSize(){
        return data.size();
    }

}
