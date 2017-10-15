package com.wzj.fuzzer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.wzj.fuzzer.R;
import com.wzj.fuzzer.vo.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzj on 2017/10/14.
 */
public class AppInfoAdapter extends BaseAdapter implements Filterable
{
    /**
     * app列表
     */
    private List<AppInfo> appInfoList;

    private List<AppInfo> mOriginalValues;

    LayoutInflater infater = null;

    /**
     * 锁
     */
    private final Object mLock = new Object();


    private AppInfoFilter appInfoFilter;

    public AppInfoAdapter(Context context, List<AppInfo> appInfoList)
    {
        this.appInfoList = appInfoList;
        this.infater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount()
    {
        return appInfoList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position)
    {
        return appInfoList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = null;
        ViewHolder holder = null;
        if (convertView == null || convertView.getTag() == null)
        {
            view = infater.inflate(R.layout.app_info_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else
        {
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }
        AppInfo appInfo = (AppInfo) getItem(position);
        holder.appIcon.setImageDrawable(appInfo.getAppIcon());
        holder.appName.setText(appInfo.getAppName());
        holder.packageName.setText(appInfo.getPackageName());
        holder.index.setText(String.valueOf(position));
        return view;
    }

    /**
     * <p>Returns a filter that can be used to constrain data with a filtering
     * pattern.</p>
     * <p>
     * <p>This method is usually implemented by {@link Adapter}
     * classes.</p>
     *
     * @return a filter used to constrain data
     */
    @Override
    public Filter getFilter()
    {
        if (appInfoFilter == null)
        {
            appInfoFilter = new AppInfoFilter();
        }

        return appInfoFilter;
    }

    private class ViewHolder
    {
        TextView index;
        ImageView appIcon;
        TextView appName;
        TextView packageName;

        public ViewHolder(View view)
        {
            this.index = (TextView) view.findViewById(R.id.index);
            this.appIcon = (ImageView) view.findViewById(R.id.app_icon);
            this.appName = (TextView) view.findViewById(R.id.app_name);
            this.packageName = (TextView) view.findViewById(R.id.package_name);
        }
    }

    private class AppInfoFilter extends Filter
    {

        /**
         * <p>Invoked in a worker thread to filter the data according to the
         * constraint. Subclasses must implement this method to perform the
         * filtering operation. Results computed by the filtering operation
         * must be returned as a {@link FilterResults} that
         * will then be published in the UI thread through
         * <p>
         * <p><strong>Contract:</strong> When the constraint is null, the original
         * data must be restored.</p>
         *
         * @param constraint the constraint used to filter the data
         * @return the results of the filtering operation
         * @see #filter(CharSequence, FilterListener)
         * @see #publishResults(CharSequence, FilterResults)
         * @see FilterResults
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            FilterResults results = new FilterResults();

            if (mOriginalValues == null)
            {
                synchronized (mLock)
                {
                    mOriginalValues = new ArrayList<>(appInfoList);
                }
            }

            if (constraint == null || constraint.length() == 0)
            {
                ArrayList<AppInfo> list;
                synchronized (mLock)
                {//同步复制一个原始备份数据
                    list = new ArrayList<>(mOriginalValues);
                }
                results.values = list;
                results.count = list.size();//此时返回的results就是原始的数据，不进行过滤
            }
            else
            {
                String content = constraint.toString().toLowerCase();
                ArrayList<AppInfo> values;

                synchronized (mLock)
                {
                    values = new ArrayList<>(mOriginalValues);
                }


                final ArrayList<AppInfo> newValues = new ArrayList<>();

                for (AppInfo appInfo : values)
                {
                    if (appInfo.getAppName().toLowerCase().contains(content)
                            || appInfo.getPackageName().toLowerCase().contains(content))
                    {
                        newValues.add(appInfo);
                    }
                }

                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        /**
         * <p>Invoked in the UI thread to publish the filtering results in the
         * user interface. Subclasses must implement this method to display the
         * results computed in {@link #performFiltering}.</p>
         *
         * @param constraint the constraint used to filter the data
         * @param results    the results of the filtering operation
         * @see #filter(CharSequence, FilterListener)
         * @see #performFiltering(CharSequence)
         * @see FilterResults
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            appInfoList = (List<AppInfo>) results.values;

            if (results.count > 0)
            {
                notifyDataSetChanged();
            }
            else
            {
                notifyDataSetInvalidated();
            }
        }
    }
}
