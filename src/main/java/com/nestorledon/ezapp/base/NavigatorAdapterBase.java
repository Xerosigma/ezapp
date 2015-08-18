package com.nestorledon.ezapp.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nestorledon.ezapp.R;

import java.util.List;

/**
 * This adapter provides views for navigation
 * items in a given Navigator.
 *
 * This class should remain ignorant of concrete
 * implementations.
 *
 * Created by nestorledon on 2/20/15.
 */
public class NavigatorAdapterBase extends BaseAdapter {

    private Context context;
    private Navigator navigator;
    private List<String> sections;


    public NavigatorAdapterBase(final Context context, final Navigator navigator, final List<String> sections){
        this.context = context;
        this.navigator = navigator;
        this.sections = sections;
    }

    @Override
    public int getCount() {
        return sections.size();
    }

    @Override
    public Object getItem(int position) {
        return sections.get( position );
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder;


        if ( convertView == null || convertView.getTag() instanceof String ) {

            convertView = LayoutInflater.from(context).inflate( R.layout.ez_drawer_item, null );
            holder = new ViewHolder();

            holder.icon = (ImageView) convertView.findViewById( R.id.nav_drawer_item_icon_left );
            holder.text = (TextView) convertView.findViewById( R.id.nav_drawer_text );
            holder.divider = convertView.findViewById(R.id.ez_drawer_item_divider);

            convertView.setTag( holder );

        }

        else {
            holder = (ViewHolder) convertView.getTag();
        }


        final String section = sections.get( position );

        holder.text.setText(section);

        for(int ii = 0; ii < navigator.getSectionRules().length; ii++) {
            if(section.equalsIgnoreCase(navigator.getSectionRules()[ii])) {

                holder.divider.setVisibility(View.VISIBLE);
            }
        }

        final int leftDrawable = navigator.getIconResourceId(section, false);
        holder.icon.setImageDrawable(context.getResources().getDrawable(leftDrawable));

        final Navigator navigator = (Navigator) context;
        final ImageView icon = (ImageView) convertView.findViewById( R.id.nav_drawer_item_icon_left );
        final TextView text = (TextView) convertView.findViewById(R.id.nav_drawer_text);
        if (section.equals(navigator.getSelectedItem())) {
            text.setTextColor(context.getResources().getColor(R.color.navigation_active_item));
            icon.setImageDrawable(context.getResources().getDrawable(navigator.getIconResourceId(section, true)));
        } else {
            text.setTextColor(context.getResources().getColor(R.color.nav_drawer_text));
            icon.setImageDrawable(context.getResources().getDrawable(navigator.getIconResourceId(section, false)));

        }

        return convertView;
    }

    public class ViewHolder {
        TextView text;
        ImageView icon;
        View divider;
    }
}
