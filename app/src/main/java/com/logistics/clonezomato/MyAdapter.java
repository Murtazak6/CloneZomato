package com.logistics.clonezomato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter implements Filterable {

    Context c;
    ArrayList<SingleRow> arrayList,templist;
    CustomFilter cs;
    public MyAdapter(Context c, ArrayList<SingleRow> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
        this.templist = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_listview,null);

        TextView title = (TextView) row.findViewById(R.id.title);
        TextView desc = (TextView) row.findViewById(R.id.desc);
        ImageView image = (ImageView) row.findViewById(R.id.image);

        title.setText(arrayList.get(i).getTitle());
        desc.setText(arrayList.get(i).getDesc());
        image.setImageResource(arrayList.get(i).getImage());
        return row;
    }

    @Override
    public Filter getFilter() {
        if(cs == null){
            cs = new CustomFilter();
        }
        return cs;
    }

    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if(constraint != null && constraint.length() >0){
                constraint = constraint.toString().toUpperCase();

                ArrayList<SingleRow> filters = new ArrayList<>();
                for(int i=0;i<templist.size();i++){
                    if(templist.get(i).getTitle().toUpperCase().contains(constraint) || templist.get(i).getDesc().toUpperCase().contains(constraint)){
                         SingleRow singleRow = new SingleRow(templist.get(i).getTitle(),templist.get(i).getDesc(),templist.get(i).getImage());
                         filters.add(singleRow);
                    }
                }
                filterResults.count = filters.size();
                filterResults.values = filters;
            }
            else{
                filterResults.count = templist.size();
                filterResults.values =templist;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            arrayList = (ArrayList<SingleRow>) filterResults.values;
            notifyDataSetChanged();
        }

    }
}
