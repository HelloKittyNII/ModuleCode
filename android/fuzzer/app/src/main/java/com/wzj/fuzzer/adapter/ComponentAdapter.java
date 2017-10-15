package com.wzj.fuzzer.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wzj.fuzzer.R;
import com.wzj.fuzzer.vo.ComponentInfo;

import java.util.List;

/**
 * Created by wzj on 2017/10/14.
 */
public class ComponentAdapter extends BaseAdapter
{
    private List<ComponentName> components;

    LayoutInflater infater = null;

    public ComponentAdapter(Context context, List<ComponentName> components)
    {
        this.infater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.components = components;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount()
    {
        return components.size();
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
        return components.get(position);
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
            view = infater.inflate(R.layout.component, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else
        {
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }
        ComponentInfo componentInfo = (ComponentInfo) getItem(position);
        holder.componentName.setText(componentInfo.getComponentName());
        return view;
    }

    private class ViewHolder
    {
        public TextView componentName;

        public ViewHolder(View view)
        {

            this.componentName = (TextView) view.findViewById(R.id.component_name);
        }
    }
}
