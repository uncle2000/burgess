//package com.uncle2000.libbase;
//
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
//
//
// */
//public abstract  class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
//    public final List<T> data = new ArrayList<>();
//    protected Context context;
//
//
//    public BaseRecyclerViewAdapter(Context context) {
//        this.context = context;
//    }
//
//    public List<T> getData() {
//        return data;
//    }
//
//    public void setData(List<T> data) {
//        this.data.clear();
//        if (data != null) {
//            this.data.addAll(data);
//        }
//        notifyDataSetChanged();
//    }
//
//    public void addData(List<T> list) {
//        int size = data.size();
//        if (list != null && list.size() > 0) {
//            this.data.addAll(list);
////            notifyItemRangeInserted(size,size+list.size());
//            notifyDataSetChanged();
//        }
//    }
//
//    public void addData(T t) {
//        int size = data.size();
//        data.add(t);
//        notifyItemInserted(size);
//    }
//
//    public void delData(T t) {
//        int size = data.size();
//        if (data.contains(t)) {
//            int position = data.indexOf(t);
//            data.remove(t);
////            notifyItemRemoved(position);
//            notifyDataSetChanged();
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//    public T getItem(int pos) {
//        if (pos >= 0 && pos < data.size()) {
//            return data.get(pos);
//        }
//        return null;
//    }
//}
