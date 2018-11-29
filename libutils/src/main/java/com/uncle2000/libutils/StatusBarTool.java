//package com.uncle2000.libutils;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.Color;
//import android.os.Build;
//import android.util.DisplayMetrics;
//import android.view.Display;
//import android.view.View;
//import android.view.ViewParent;
//import android.view.Window;
//import android.view.WindowManager;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
///**
// * 工具类
// * Created by SmileXie on 2017/6/29.
// */
//
//public class StatusBarTool {
//    private static final String TAG = "StatusBarTool";
//    public static int screenWidth;
//    public static int screenHeight;
//    public static int navigationHeight = 0;
//
//    private static DisplayMetrics mMetrics;
//    public static final String HOME_CURRENT_TAB_POSITION = "HOME_CURRENT_TAB_POSITION";
//
//    /**
//     * 通过反射的方式获取状态栏高度
//     *
//     * @return
//     */
//    public static int getStatusBarHeight(Context context) {
//        try {
//            Class<?> c = Class.forName("com.android.internal.R$dimen");
//            Object obj = c.newInstance();
//            Field field = c.getField("status_bar_height");
//            int x = Integer.parseInt(field.get(obj).toString());
//            return context.getResources().getDimensionPixelSize(x);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    /**
//     * 获取底部导航栏高度
//     *
//     * @return
//     */
//    public static int getNavigationBarHeight(Context context) {
//        Resources resources = context.getResources();
//        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
//        //获取NavigationBar的高度
//        navigationHeight = resources.getDimensionPixelSize(resourceId);
//        return navigationHeight;
//    }
//
//    //获取是否存在NavigationBar
//    public static boolean checkDeviceHasNavigationBar(Context context) {
//        boolean hasNavigationBar = false;
//        Resources rs = context.getResources();
//        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
//        if (id > 0) {
//            hasNavigationBar = rs.getBoolean(id);
//        }
//        try {
//            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
//            Method m = systemPropertiesClass.getMethod("get", String.class);
//            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
//            if ("1".equals(navBarOverride)) {
//                hasNavigationBar = false;
//            } else if ("0".equals(navBarOverride)) {
//                hasNavigationBar = true;
//            }
//        } catch (Exception e) {
//
//        }
//        return hasNavigationBar;
//
//    }
//
//    /**
//     * @param activity
//     * @param useThemestatusBarColor   是否要状态栏的颜色，不设置则为透明色
//     * @param withoutUseStatusBarColor 是否不需要使用状态栏为暗色调
//     */
//    public static void setStatusBar(Activity activity, boolean useThemestatusBarColor, boolean withoutUseStatusBarColor) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
//            // View decorView = activity.getWindow().getDecorView();
//            // int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            //         | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            // decorView.setSystemUiVisibility(option);
//            if (useThemestatusBarColor) {
//                activity.getWindow().setStatusBarColor(Color.WHITE);
//            } else {
//                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
//            }
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
//            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !withoutUseStatusBarColor) {
//            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
//    }
//
//    public static void reMeasure(Activity activity) {
//        Display display = activity.getWindowManager().getDefaultDisplay();
//        mMetrics = new DisplayMetrics();
//
//        if (Build.VERSION.SDK_INT >= 17) {
//            display.getRealMetrics(mMetrics);
//        } else {
//            display.getMetrics(mMetrics);
//        }
//
//        screenWidth = mMetrics.widthPixels;
//        screenHeight = mMetrics.heightPixels;
//    }
//
//    /**
//     * 改变魅族的状态栏字体为黑色，要求FlyMe4以上
//     */
//    private static void processFlyMe(boolean isLightStatusBar, Activity activity) {
//        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
//        try {
//            Class<?> instance = Class.forName("android.view.WindowManager$LayoutParams");
//            int value = instance.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON").getInt(lp);
//            Field field = instance.getDeclaredField("meizuFlags");
//            field.setAccessible(true);
//            int origin = field.getInt(lp);
//            if (isLightStatusBar) {
//                field.set(lp, origin | value);
//            } else {
//                field.set(lp, (~value) & origin);
//            }
//        } catch (Exception ignored) {
//            ignored.printStackTrace();
//        }
//    }
//
//    /**
//     * 改变小米的状态栏字体颜色为黑色, 要求MIUI6以上  lightStatusBar为真时表示黑色字体
//     */
//    private static void processMIUI(boolean lightStatusBar, Activity activity) {
//        Class<? extends Window> clazz = activity.getWindow().getClass();
//        try {
//            int darkModeFlag;
//            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
//            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
//            darkModeFlag = field.getInt(layoutParams);
//            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
//            extraFlagField.invoke(activity.getWindow(), lightStatusBar ? darkModeFlag : 0, darkModeFlag);
//        } catch (Exception ignored) {
//            ignored.printStackTrace();
//        }
//    }
//
//    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
//    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
//    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
//
//    /**
//     * 判断手机是否是小米
//     *
//     * @return
//     */
//    public static boolean isMIUI() {
//        try {
//            final BuildProperties prop = BuildProperties.newInstance();
//            return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
//                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
//                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
//        } catch (final IOException e) {
//            return false;
//        }
//    }
//
//    /**
//     * 判断手机是否是魅族
//     *
//     * @return
//     */
//    public static boolean isFlyme() {
//        try {
//            // Invoke Build.hasSmartBar()
//            final Method method = Build.class.getMethod("hasSmartBar");
//            return method != null;
//        } catch (final Exception e) {
//            return false;
//        }
//    }
//
//    /**
//     * 设置状态栏文字色值为深色调
//     *
//     * @param useDart  是否使用深色调
//     * @param activity
//     */
//    public static void setStatusTextColor(boolean useDart, Activity activity) {
//        if (isFlyme()) {
//            processFlyMe(useDart, activity);
//        } else if (isMIUI()) {
//            processMIUI(useDart, activity);
//        } else {
//            if (useDart) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    activity.getWindow().getDecorView().setSystemUiVisibility(/*View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | */View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//                }
//            } else {
//                activity.getWindow().getDecorView().setSystemUiVisibility(
//                        /*View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |*/ View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            }
//            activity.getWindow().getDecorView().findViewById(android.R.id.content).setPadding(0, 0, 0, navigationHeight);
//        }
//    }
//
//
//    public static void initWithActivity(Activity activity) {
////        setStatusBar(activity, false, false);
////        setStatusTextColor(true, activity);
//    }
//
//    public static int getYInScreen(View view) {
//        int y = view.getTop();
//        ViewParent parent = view.getParent();
//        while (parent != null) {
//            if (parent instanceof View) {
//                y += ((View) parent).getTop();
//            } else {
//                break;
//            }
//            parent = parent.getParent();
//
//        }
//        return y;
//    }
//
//    public static void fitStatusBar(View view) {
//        view.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
//                    int tagId = Integer.MAX_VALUE - 10000;
//                    final Boolean haveFitStatusBar = (Boolean) view.getTag(tagId);
//                    /*if (haveFitStatusBar == null || !haveFitStatusBar)*/
//                    {
//
//                        // haveFitStatusBar = true;
//                        view.setTag(tagId, true);
//                        int[] loc = new int[2];
//                        view.getLocationOnScreen(loc);
//                        // L.get().e(TAG, String.format(Locale.getDefault(), "OnLayout:%d,%d, h:%d,    (%d,%d,%d,%d)(%d,%d,%d,%d)%d %d", loc[0], loc[1],
//                        //         StatusBarTool.getStatusBarHeight(view.getContext()),
//                        //
//                        //         left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom,
//                        //         view.isInLayout() ? 1 : 0,
//                        //         getYInScreen(view)
//                        //         )
//                        // );
//                        int statusBarHeight = StatusBarTool.getStatusBarHeight(view.getContext());
//                        int y = loc[1];
//                        int requiredPaddingTop = y == 0 ? statusBarHeight : 0;
//                        if (view.getPaddingTop() != requiredPaddingTop) {
//                            view.post(() -> {
//                                view.setPadding(view.getPaddingLeft(), requiredPaddingTop, view.getPaddingRight(), view.getPaddingBottom());
//
//                            });
//                        }
//                    }
//                }
//        );
//
//    }
//}
