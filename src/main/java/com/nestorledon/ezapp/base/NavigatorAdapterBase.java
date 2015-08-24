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
import java.util.Map;


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

    public final static String COLOR_TEXT_ACTIVE = "COLOR_TEXT_ACTIVE";
    public final static String COLOR_TEXT_INACTIVE = "COLOR_TEXT_INACTIVE";

    private Context mContext;
    private Navigator mNavigator;
    private List<String> mSections;
    private Map<String, Integer> mColors;


    public NavigatorAdapterBase(final Context context, final Navigator navigator, final List<String> sections, Map<String, Integer> colors){
        mContext = context;
        mNavigator = navigator;
        mSections = sections;
        mColors = colors;
    }

    @Override
    public int getCount() {
        return mSections.size();
    }

    @Override
    public Object getItem(int position) {
        return mSections.get( position );
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if ( convertView == null || convertView.getTag() instanceof String ) {

            convertView = LayoutInflater.from(mContext).inflate( R.layout.ez_drawer_item, null );
            holder = new ViewHolder();

            holder.icon = (ImageView) convertView.findViewById( R.id.nav_drawer_item_icon_left );
            holder.text = (TextView) convertView.findViewById( R.id.nav_drawer_text );
            holder.divider = convertView.findViewById(R.id.ez_drawer_item_divider);

            convertView.setTag( holder );
        }

        else {
            holder = (ViewHolder) convertView.getTag();
        }

        final String section = mSections.get( position );

        holder.text.setText(section);

        for(int ii = 0; ii < mNavigator.getSectionRules().length; ii++) {
            if(section.equalsIgnoreCase(mNavigator.getSectionRules()[ii])) {

                holder.divider.setVisibility(View.VISIBLE);
            }
        }

        final int leftDrawable = mNavigator.getIconResourceId(section, false);
        if(leftDrawable != 0) {
            holder.icon.setImageDrawable(mContext.getResources().getDrawable(leftDrawable));
        }

        final Navigator navigator = (Navigator) mContext;
        final ImageView icon = (ImageView) convertView.findViewById( R.id.nav_drawer_item_icon_left );
        final TextView text = (TextView) convertView.findViewById(R.id.nav_drawer_text);

        if (section.equals(navigator.getSelectedItem())) {
            text.setTextColor(mContext.getResources().getColor(resolveColor(COLOR_TEXT_ACTIVE)));
            int rid = navigator.getIconResourceId(section, true);
            if(rid != 0) {
                icon.setImageDrawable(mContext.getResources().getDrawable(rid));
            }
        }

        else {
            text.setTextColor(mContext.getResources().getColor(resolveColor(COLOR_TEXT_INACTIVE)));
            int rid = navigator.getIconResourceId(section, false);
            if(rid != 0) {
                icon.setImageDrawable(mContext.getResources().getDrawable(rid));
            }

        }

        return convertView;
    }

    private int resolveColor(String colorName) {
        Integer result = 0;
        for(Map.Entry ent : mColors.entrySet()) {
            if (ent.getKey().equals(colorName)) {
                result =  (Integer) ent.getValue();
            }
        }
        return result;
    }

    public class ViewHolder {
        TextView text;
        ImageView icon;
        View divider;
    }
}
