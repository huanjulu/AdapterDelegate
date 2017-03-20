package adapterdelegate.huanjulu.com.adapterdelegate.delegatesmanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by huanjulu on 24/1/17.
 */


public abstract class BaseAdapterDelegate<T> {


    /**
     * @param items
     * @param position
     */
    protected abstract void onBindViewHolder(@NonNull T items, int position);


    /**
     * spec viewholder's view's creat and halder it's for itself
     *
     * @param viewGroup as creat viewholde's view
     *
     */
    protected abstract RecyclerView.ViewHolder onCreatViewHolder(ViewGroup viewGroup);


    /**
     * called by spce's achieve
     *
     * @return
     */

    protected abstract boolean isSpecfildValue(@NonNull T t, int position);


    /**
     * when life  Attached
     *
     * @param holder
     */
    protected void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
    }

    /**
     * when life remove
     *
     * @param holder
     */
    protected void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
    }


}
