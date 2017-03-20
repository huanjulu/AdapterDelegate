package adapterdelegate.huanjulu.com.adapterdelegate.delegatesmanager;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by huanjulu on 24/1/17.
 */

public class AdapterDelegatesManager<T> {


    private static final int MAX_VALUE_KEY = Integer.MAX_VALUE;

    private SparseArrayCompat<BaseAdapterDelegate<T>> sparseArrayCompat = new SparseArrayCompat<>();

    /**
     * @param t
     * @param position
     * @param viewHolder
     */

    public void onBindViewHolder(T t, int position, @NonNull RecyclerView.ViewHolder viewHolder) {

        BaseAdapterDelegate<T> delegate = getDelegate(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No delegate found for item at position = "
                    + position
                    + " for viewType = "
                    + viewHolder.getItemViewType());
        }
        delegate.onBindViewHolder(t, position);

    }


    /**
     * @param t
     * @param position
     */
    public int getItemViewType(@NonNull T t, @NonNull int position) {


        if (t == null) {
            throw new NullPointerException("Sorry, the data is null!");
        }

        int delegatesCount = sparseArrayCompat.size();

        for (int i = 0; i < delegatesCount; i++) {

            BaseAdapterDelegate<T> baseAdapterDelegate = sparseArrayCompat.valueAt(i);

            if (baseAdapterDelegate.isSpecfildValue(t, position)) {

                return sparseArrayCompat.keyAt(i);
            }
        }

        throw new NullPointerException("No AdapterDelegate added that matches position=" + position + " in data source");
    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        BaseAdapterDelegate<T> delegate = getDelegate(viewType);

        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }

        RecyclerView.ViewHolder viewHolder = delegate.onCreatViewHolder(parent);


        if (viewHolder == null) {

            throw new NullPointerException("ViewHolder returned from AdapterDelegate"
                    + delegate
                    + "for ViewType ="
                    + viewType
                    + "is null"

            );
        }

        return viewHolder;
    }

    /**
     * @param baseAdapterDelegate
     */
    public void addDelegate(@NonNull BaseAdapterDelegate baseAdapterDelegate) {

        int key = sparseArrayCompat.size();

        while (sparseArrayCompat.get(key) != null) {
            if (key == MAX_VALUE_KEY) {
                throw new IllegalArgumentException("sorry , the key isn't Integer.MAX_VALUE !");
            }
            if (baseAdapterDelegate == null) {
                throw new IllegalArgumentException("sorry , the base delegate adapter is not null ! ");
            }
            key++;

        }
        sparseArrayCompat.put(key, baseAdapterDelegate);
    }


    /**
     * @param baseAdapterDelegate
     * @return the key of viewtype
     */
    public int getViewTypeKey(@NonNull BaseAdapterDelegate baseAdapterDelegate) {


        if (baseAdapterDelegate == null) {

            throw new IllegalArgumentException("sorry , the base delegate  is not null ! ");
        }

        return sparseArrayCompat.indexOfValue(baseAdapterDelegate);
    }


    /**
     * @param viewTypekey
     * @return
     */
    public BaseAdapterDelegate<T> getDelegate(@NonNull int viewTypekey) {


        while (sparseArrayCompat.get(viewTypekey) == null) {


            throw new NullPointerException("sorry , you have no set delegate adapter for this viewTypekey !");

        }

        return sparseArrayCompat.get(viewTypekey);

    }

    /**
     * @param viewTypekey
     */
    public void removeDelegate(@NonNull int viewTypekey) {


        while (sparseArrayCompat.get(viewTypekey) == null) {


            throw new NullPointerException("sorry , you have no set delegate adapter for this key !");

        }

        sparseArrayCompat.remove(viewTypekey);

    }

    /**
     * @param delegate
     */
    public void removeDelegate(@NonNull BaseAdapterDelegate delegate) {

        if (delegate == null
                ) {
            throw new NullPointerException("sorry , the  delegate is null! ");
        }

        int viewTypeKey = sparseArrayCompat.indexOfValue(delegate);


        removeDelegate(viewTypeKey);
    }


}
