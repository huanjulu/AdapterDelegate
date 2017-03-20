# AdapterDelegate

An base adapter of your Recycle.Adapter

(1) you need to declare your own different types of adapterDelegate, let us defined two type of adapterDelegate ,every of them need extands BaseAdapterDelegate<T>, just like blow code of that :

```Java
public class DogAdapterDelegate extends BaseAdapterDelegate<T> {

    private LayoutInflater inflater;

    public DogAdapterDelegate(Activity activity) {
        inflater = activity.getLayoutInflater();
    }

    @Override
    public boolean isForViewType(@NonNull T items, int position) {
        /**
         * please instand of your own data type, like DogBean, CatBean , String , int,.. ig
         */
        return items instanceof Dog;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {

        return new DogViewHolder(inflater.inflate(R.layout.item_dog, parent, false));
    }

    @Override
    public void protected void onBindViewHolder(@NonNull T items, int position, @NonNull RecyclerView.ViewHolder viewHolder) {
        DogViewHolder vh = (DogViewHolder) holder;
       
    }

    static class DogViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public DogViewHolder(View itemView) {
            super(itemView);
          
        }
    }
}

```
then declare the other adapterDelegate 

```ruby
public class CatAdapterDelegate extends BaseAdapterDelegate<T> {

  private LayoutInflater inflater;

  public CatAdapterDelegate(Activity activity) {
    inflater = activity.getLayoutInflater();
  }

  @Override protected boolean isForViewType(@NonNull T items, int position) {
    return items instanceof Cat;
  }

  @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
    return new CatViewHolder(inflater.inflate(R.layout.item_cat, parent, false));
  }

  @Override     
  public void protected void onBindViewHolder(@NonNull T items, int position, @NonNull RecyclerView.ViewHolder viewHolder) {

    CatViewHolder vh = (CatViewHolder) holder;
    
  }

  static class CatViewHolder extends RecyclerView.ViewHolder {

    public TextView name;

    public CatViewHolder(View itemView) {
      super(itemView);
   
    }
  }
```

(2) you need to declare an adapterDelegate sense of 'Main' (or 'Major'), it used to added your different of your own adapterDelegate before .for instance :

```ruby
public class MainAdapterDelegate extends RecyclerView.Adapter {

    private List<? extends Object> objectDatas;

    private Context mContext;

    private AdapterDelegatesManager adapterDelegatesManager;

    public CashbackMainAdapterDelegate(Context context) {

        this.mContext = context;

        objectDatas = new ArrayList<>();

        adapterDelegatesManager = new AdapterDelegatesManager();

        adapterDelegatesManager.addDelegate(new CatAdapterDelegate(mContext));

        adapterDelegatesManager.addDelegate(new DogAdapterDelegate(mContext));

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return adapterDelegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        adapterDelegatesManager.onBindViewHolder(objectDatas.get(position), position, holder);

    }

    @Override
    public int getItemCount() {

        return objectDatas == null ? 0 : objectDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return adapterDelegatesManager.getItemViewType(objectDatas.get(position), position);
    }
}

```
（3）last step is that you need to defined MainAdapterdelegate 
```ruby

recycle.setAdapter(new MainAdapterDelegate(this))
```
 
