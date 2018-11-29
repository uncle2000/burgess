package com.uncle2000.libviews;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by danger on 2017/6/1.
 */
public class TabPagerIndicator extends LinearLayout {

    public static final int TAB_MODE_FILL = 0;
    public static final int TAB_MODE_TILE = 1;


    public static final int INDICATOR_MODE_WRAP = 0;
    public static final int INDICATOR_MODE_FIXED = 1;

    int tabMode = TAB_MODE_FILL;
    int indicatorMode = INDICATOR_MODE_WRAP;


    /*** -以下是属性- ***/
    int tabIndicatorColor = 0xffff0000; // " format="dimension" />
    int tabIndicatorHeight = 21; // " format="dimension" />
    int tabContentStart; // " format="dimension" />
    int tabBackground;// " format="reference" />

    // <enum name="scrollable" value="0" />
    // <enum name="fixed" value="1" />
    // </attr>
    int tabGravity; // ">
    //     <enum name="fill" value="0" />
    //     <enum name="center" value="1" />
    // </attr>
    Integer tabMinWidth = null; // " format="dimension" />
    Integer tabMaxWidth = null; // " format="dimension" />
    int tabTextAppearance; // " format="reference" />
    int tabTextColor = 0xff999999; // " format="color" />
    int tabSelectedTextColor = 0xff000000; // " format="color" />
    Integer tabPaddingStart = null; // " format="dimension" />
    Integer tabPaddingTop = null; // " format="dimension" />
    Integer tabPaddingEnd = null; // " format="dimension" />
    Integer tabPaddingBottom = null; // " format="dimension" />
    Integer tabPadding = null; // " format="dimension" />
    Integer itemSpacing = null;
    Integer indicatorWidth = 20;
    Integer indicatorPadding = 20;
    int textSize = 15;
    int tabLayout = R.layout.tab_pager_indicator_default_tab; // " format="reference" />


    /*** -以下是属性- ***/
    String[] tabNames;
    Object[] contents;
    int currentPage = -1;

    /*** -以下是View- ***/
    LinearLayout tabContainer;
    View pagerIndicator;
    OnPageChangeListener onPageChangeListener;

    ViewPager viewPager;

    public TabPagerIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public TabPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TabPagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        CharSequence[] pageNames = null;
        final AtomicInteger viewPagerId = new AtomicInteger(-1);
        setGravity(Gravity.CENTER_VERTICAL);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabPagerIndicator);
            final int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.TabPagerIndicator_tabIndicatorColor) {
                    tabIndicatorColor = a.getColor(attr, tabIndicatorColor);

                } else if (attr == R.styleable.TabPagerIndicator_tabIndicatorHeight) {
                    tabIndicatorHeight = a.getDimensionPixelSize(attr, tabIndicatorHeight);

                } else if (attr == R.styleable.TabPagerIndicator_tabLayoutMode) {
                    tabMode = a.getInt(attr, tabMode);
                } else if (attr == R.styleable.TabPagerIndicator_indicatorMode) {
                    indicatorMode = a.getInt(attr, indicatorMode);
                } else if (attr == R.styleable.TabPagerIndicator_tabContentStart) {
                    tabContentStart = a.getDimensionPixelSize(attr, tabContentStart);

                } else if (attr == R.styleable.TabPagerIndicator_tabBackground) {
                    tabBackground = a.getColor(attr, tabBackground);

                    // case R.styleable.TabPagerIndicator_tabMode: // ">
                    //     tabMode = a.getAny(attr);
                    //     break;
                    // case R.styleable.TabPagerIndicator_tabGravity: // ">
                    //     tabGravity = a.getAny(attr, tabGravity);
                    //     break;
                } else if (attr == R.styleable.TabPagerIndicator_tabMinWidth) {
                    tabMinWidth = a.getDimensionPixelSize(attr, 0);

                } else if (attr == R.styleable.TabPagerIndicator_tabMaxWidth) {
                    tabMaxWidth = a.getDimensionPixelSize(attr, 0);

                } else if (attr == R.styleable.TabPagerIndicator_tabTextAppearance) {
                    tabTextAppearance = a.getResourceId(attr, tabTextAppearance);

                } else if (attr == R.styleable.TabPagerIndicator_tabTextColor) {
                    tabTextColor = a.getColor(attr, tabTextColor);

                } else if (attr == R.styleable.TabPagerIndicator_tabSelectedTextColor) {
                    tabSelectedTextColor = a.getColor(attr, tabSelectedTextColor);

                } else if (attr == R.styleable.TabPagerIndicator_tabPaddingStart) {
                    tabPaddingStart = a.getDimensionPixelSize(attr, 0);

                } else if (attr == R.styleable.TabPagerIndicator_tabPaddingTop) {
                    tabPaddingTop = a.getDimensionPixelSize(attr, 0);

                } else if (attr == R.styleable.TabPagerIndicator_tabPaddingEnd) {
                    tabPaddingEnd = a.getDimensionPixelSize(attr, 0);

                } else if (attr == R.styleable.TabPagerIndicator_tabPaddingBottom) {
                    tabPaddingBottom = a.getDimensionPixelSize(attr, 0);

                } else if (attr == R.styleable.TabPagerIndicator_tabPadding) {
                    tabPadding = a.getDimensionPixelSize(attr, 0);

                } else if (attr == R.styleable.TabPagerIndicator_tabLayout) {
                    tabLayout = a.getResourceId(attr, tabLayout);

                } else if (attr == R.styleable.TabPagerIndicator_tabIndicatorWidth) {
                    indicatorWidth = a.getDimensionPixelSize(attr, tabLayout);

                } else if (attr == R.styleable.TabPagerIndicator_tabItemSpacing) {
                    itemSpacing = a.getDimensionPixelSize(attr, tabLayout);

                } else if (attr == R.styleable.TabPagerIndicator_textSize) {
                    textSize = a.getDimensionPixelSize(attr, textSize);

                } else if (attr == R.styleable.TabPagerIndicator_indicatorPadding) {
                    indicatorPadding = a.getDimensionPixelSize(attr, indicatorPadding);
                } else if (attr == R.styleable.TabPagerIndicator_pageNames) {
                    pageNames = a.getTextArray(attr);
                } else if (attr == R.styleable.TabPagerIndicator_viewPagerId) {
                    viewPagerId.set(a.getResourceId(attr, viewPagerId.get()));
                }
            }
            a.recycle();
        }

        // setBackgroundColor(0xff444444);
        setOrientation(VERTICAL);
        tabContainer = new LinearLayout(context);
        tabContainer.setOrientation(HORIZONTAL);
        LayoutParams params = new LayoutParams(-1, 0);

        if (tabMode == TAB_MODE_FILL) {
            params.weight = 1;
        } else {
            params.height = -2; // wrap_content
        }

        addView(tabContainer, params);

        pagerIndicator = new View(context);
        LayoutParams paramsIndicator = new LayoutParams(indicatorWidth, tabIndicatorHeight);
        pagerIndicator.setBackgroundColor(tabIndicatorColor);
        addView(pagerIndicator, paramsIndicator);

        if (pageNames != null && pageNames.length > 0) {
            String[] pageNameStrings = new String[pageNames.length];
            for (int i = 0; i < pageNames.length; i++) {
                pageNameStrings[i] = pageNames[i].toString();
            }
            setPages(pageNameStrings);
        }


        if (viewPagerId.get() > 0) {

            postDelayed(() -> {
                View rootView = getRootView();
                setViewPager(rootView.findViewById(viewPagerId.get()));

            }, 300);
        }
    }

    private void setViewPager(View pager) {
        if (pager == null || !(pager instanceof ViewPager)) {
            return;
        }
        viewPager = (ViewPager) pager;
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switchTo(position);
            }
        });

    }

    // TODO
    public void initWithViewPager() {

    }

    public int getXInParent(View v, ViewGroup parent) {
        int x = 0;
        do {
            x += v.getX();
            v = (View) v.getParent();
        } while (v != parent);
        return x;
    }

    public void switchTo(int pageIndex) {
        switchTo(pageIndex, false);
    }

    private void switchTo(int pageIndex, boolean force) {
        if (tabContainer.getChildCount() == 0) return;
        if (!force && pageIndex == currentPage) return;
        // if (tabContainer.getChildCount() > 0) return;

        // if (pageIndex == this.currentPage) return;

        int oldIndex = currentPage;
        if (pageIndex < 0) {
            currentPage = 0;
        } else if (pageIndex >= tabContainer.getChildCount()) {
            currentPage = tabContainer.getChildCount() - 1;
        } else
            currentPage = pageIndex;

        // 1.关闭旧页面
        if (oldIndex >= 0 && oldIndex < tabContainer.getChildCount()) {
            TextView tv = (TextView) tabContainer.getChildAt(oldIndex).findViewById(R.id.tv_page_title);
            tv.setTextColor(tabTextColor);
            if (contents != null && contents[oldIndex] instanceof View) {
                ((View) contents[oldIndex]).setVisibility(View.GONE);
            }
        }

        // 2.显示新页面
        TextView tv = tabContainer.getChildAt(currentPage).findViewById(R.id.tv_page_title);
        if (tv.getWidth() == 0) return;

        tv.setTextColor(tabSelectedTextColor);
        if (contents != null && contents[currentPage] instanceof View)
            ((View) contents[currentPage]).setVisibility(View.VISIBLE);

        if (onPageChangeListener != null) {
            onPageChangeListener.onPageChange(currentPage, contents != null ? contents[currentPage] : null);
        }
        // move indicator
        ObjectAnimator translationX;
        int newIndicatorWidth = getIndicatorWidth(tv);

        // if (indicatorWidth == null) {
        //     translationX = ObjectAnimator.ofFloat(pagerIndicator, "translationX",
        //             getXInParent(tv, tabContainer));
        // } else {
        translationX = ObjectAnimator.ofFloat(pagerIndicator, "translationX",
                getXInParent(tv, tabContainer) + (tv.getWidth() - newIndicatorWidth) / 2);
        // }

        ValueAnimator widthAnim = ValueAnimator.ofFloat(pagerIndicator.getWidth(), newIndicatorWidth);

        widthAnim.addUpdateListener(valueAnimator -> {
            Float val = (Float) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = pagerIndicator.getLayoutParams();
            layoutParams.width = val.intValue();
            pagerIndicator.setLayoutParams(layoutParams);
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationX, widthAnim);
        animatorSet.setDuration(300);
        animatorSet.start();
    }


    private int getIndicatorWidth(View view) {
        if (indicatorMode == INDICATOR_MODE_FIXED) {
            return indicatorWidth;
        }

        return view.getWidth() + indicatorPadding * 2;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) {
            switchTo(currentPage, true);
        }
        // L.get().e("TabPagerIndicator", String.format("TabPagerIndicatoronLayout %d,%d,%d,%d,%d", changed ? 1 : 0, l, t, r, b));
    }

    public void setPages(final String[] pageNames) {
        setPages(pageNames, null);
    }

    public void setPages(final String[] pageNames, final int[] weights) {
        setPages(pageNames, weights, false);
    }

    public void setPages(final String[] pageNames, final int[] weights, boolean alignLr) {
        // post(() -> {
        tabContainer.removeAllViews();
        int maxTextWidth = 0;
        int tabWidth = getWidth() / pageNames.length;

        for (int i = 0; i < pageNames.length; i++) {

            String tabName = pageNames[i];
            View oneTab = View.inflate(getContext(), tabLayout, null);
            tabContainer.addView(oneTab);
            LayoutParams params = (LayoutParams) oneTab.getLayoutParams();
            params.width = 0;
            params.weight = (weights != null && i < weights.length) ? weights[i] : 1;
            params.height = -1;
            params.gravity = Gravity.CENTER;
            oneTab.setBackgroundColor(tabBackground);

            TextView tv = (TextView) oneTab.findViewById(R.id.tv_page_title);
            if (null != tabMinWidth) tv.setMinWidth(tabMinWidth);
            if (null != tabMaxWidth) tv.setMaxWidth(tabMaxWidth);

            LayoutParams textLayout = (LayoutParams) tv.getLayoutParams();
            // textLayout.width = tabWidth;
            textLayout.gravity = Gravity.CENTER;

            tv.setTextColor(tabTextColor);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

            if (tabMode == TAB_MODE_TILE) {
                tv.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                params.weight = 0;
                params.width = -2;
                ((LayoutParams) tv.getLayoutParams()).width = -2;
                tv.setLayoutParams(tv.getLayoutParams());
                if (itemSpacing != null && i != 0) {
                    params.leftMargin = itemSpacing;
                }
            } else if (alignLr && i == 0) {
                tv.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                ((LayoutParams) tv.getLayoutParams()).width = -1;
                tv.setLayoutParams(tv.getLayoutParams());
            } else if (alignLr && i == pageNames.length - 1) {
                tv.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                ((LayoutParams) tv.getLayoutParams()).width = -1;
                tv.setLayoutParams(tv.getLayoutParams());
            } else {
                tv.setGravity(Gravity.CENTER);
            }

            tv.setTextSize(tabTextColor);
            tv.setText(tabName);
            oneTab.setTag(i);
            oneTab.setOnClickListener(v -> {
                int pageIndex = (Integer) v.getTag();
                switchTo(pageIndex);
                if (viewPager != null) {
                    viewPager.setCurrentItem(pageIndex);
                }
            });

            if (tabPadding != null)
                tv.setPadding(tabPadding, tabPadding, tabPadding, tabPadding);
            if (tabPaddingStart != null)
                tv.setPadding(tabPaddingStart, tv.getPaddingTop(), tv.getPaddingRight(), tv.getPaddingBottom());
            if (tabPaddingTop != null)
                tv.setPadding(tv.getPaddingLeft(), tabPaddingTop, tv.getPaddingRight(), tv.getPaddingBottom());
            if (tabPaddingEnd != null)
                tv.setPadding(tv.getPaddingLeft(), tv.getPaddingTop(), tabPaddingEnd, tv.getPaddingBottom());
            if (tabPaddingBottom != null)
                tv.setPadding(tv.getPaddingLeft(), tv.getPaddingTop(), tv.getPaddingRight(), tabPaddingBottom);
        }
        switchTo(currentPage < 0 ? 0 : currentPage);
        // });
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    public void setPageContents(Object... pages) {
        contents = pages;
    }

    public interface OnPageChangeListener<T> {
        void onPageChange(int page, T content);
    }

    public int getCurrentPage() {
        return currentPage < 0 ? 0 : currentPage;
    }

    public void disable() {
        disable(this);
    }

    private void disable(ViewGroup v) {
        for (int i = 0; i < v.getChildCount(); i++) {
            View c = v.getChildAt(i);
            c.setEnabled(false);
            if (c instanceof ViewGroup) {
                disable((ViewGroup) c);
            }
        }
    }
}
