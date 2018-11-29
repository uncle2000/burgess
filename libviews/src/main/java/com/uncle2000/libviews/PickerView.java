//package com.uncle2000.libviews;
//
//import android.content.Context;
//import android.support.v4.content.ContextCompat;
//
//import com.bigkoo.pickerview.OptionsPickerView;
//
//import java.util.List;
//
//public class PickerView<T> {
//    private OptionsPickerView pvOptions;
//
//    public PickerView(Context context, String title, OptionsPickerView.OnOptionsSelectListener listener) {
//        pvOptions = new OptionsPickerView.Builder(context, listener)
//                .setTitleColor(ContextCompat.getColor(context, R.color.dark_text_color))
//                .setCancelColor(ContextCompat.getColor(context, R.color.widgetColorPrimary))
//                .setSubmitColor(ContextCompat.getColor(context, R.color.dark_text_color))
//                .setSubCalSize(15)
//                .setBgColor(ContextCompat.getColor(context, R.color.windowBackground))
//                .setTitleBgColor(ContextCompat.getColor(context, R.color.windowBackground))
//                .setTitleText(title)
//                .setDividerColor(ContextCompat.getColor(context, R.color.divider))
//                .setTextColorCenter(ContextCompat.getColor(context, R.color.dark_text_color)) //设置选中项文字颜色
//                .setContentTextSize(16)
//                .build();
//    }
//
//    public void show1(List<T> options1Items) {// 弹出选择器
//        pvOptions.setPicker(options1Items);//一级选择器
////        pvOptions.setPicker(options1Items, options2Items);//二级选择器
////        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
//        pvOptions.show();
//    }
//
//    public void show2(List<T> options1Items,
//                      List<List<T>> options2Items) {// 弹出选择器
//        /*pvOptions.setPicker(options1Items);//一级选择器*/
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器
////        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
//        pvOptions.show();
//    }
//
//    public void show3(List<T> options1Items,
//                      List<List<T>> options2Items,
//                      List<List<List<T>>> options3Items) {// 弹出选择器
//        /*pvOptions.setPicker(options1Items);//一级选择器
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
//        pvOptions.show();
//    }
//}
