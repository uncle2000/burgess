package com.uncle2000.lib.base

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.uncle2000.lib.R
import com.uncle2000.lib.base.MyContextWrapper.*
import com.uncle2000.lib.base.MyContextWrapper.Companion.LANGUAGE
import com.uncle2000.lib.base.MyContextWrapper.Companion.LANGUAGE_CHINA
import com.uncle2000.lib.base.MyContextWrapper.Companion.LANGUAGE_ENGLISH
import com.uncle2000.lib.base.MyContextWrapper.Companion.LANGUAGE_HK
import java.util.*

/**
 * 吊炸天的军哥
 * 公元前3000年
 */
open class BaseFragmentActivity : RxAppCompatActivity() {
    protected var minFragments = 0
    private var fragmentRequestCode: Int = 0
    private var fragmentResultCode: Int? = null
    private var fragmentResultData: Intent? = null
    var tipDialog: QMUITipDialog? = null
//    protected var dialog: Dialog? = null

    private var isShowAnimator = true

    //记录手指按下时的横坐标。
    private var xDown: Float = 0.toFloat()

    //记录手指移动时的横坐标。
    private var xMove: Float = 0.toFloat()

    //用于计算手指滑动的速度。
    private var mVelocityTracker: VelocityTracker? = null

    /**
     * 获取手指在content界面滑动的速度。
     *
     * @return 滑动速度，以每秒钟移动了多少像素值为单位。
     */
    private val scrollVelocity: Int
        get() {
            mVelocityTracker!!.computeCurrentVelocity(1000)
            val velocity = mVelocityTracker!!.xVelocity.toInt()
            return Math.abs(velocity)
        }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Density.setOrientation(this, HEIGHT)
        if (intent.hasExtra("isFullScreen") && intent.getBooleanExtra("isFullScreen", false)) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        setContentView(R.layout.activity_empty_container)
        setBackColor()

        onNewIntent(intent)
    }

    private fun setBackColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.WHITE
            QMUIStatusBarHelper.setStatusBarLightMode(this)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            if (intent.hasExtra("fname")) {
                showFragment(intent.getStringExtra("fname"), intent.getBundleExtra("args"))
            } else if (intent.getBooleanExtra("finish", false)) {
                intent.removeExtra("finish")
                finish()
            }
        }
    }

    override fun isDestroyed(): Boolean {
        return isFinishing || Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && super.isDestroyed()
    }

    @JvmOverloads
    fun showFragment(f: Class<out Fragment>, args: Bundle?, reqCode: Int? = null) {
        showFragment(f.name, args, reqCode)
    }

    fun showFragment(fname: String, args: Bundle?) {
        showFragment(Fragment.instantiate(this, fname, args), false, null)
    }

    fun showFragment(fname: String, args: Bundle?, reqCode: Int?) {
        showFragment(Fragment.instantiate(this, fname, args), false, reqCode)
    }

    protected open fun showFragment(fragment: Fragment, replaceMode: Boolean, reqCode: Int?) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (fragmentManager.backStackEntryCount > minFragments) {
            if (isShowAnimator) {
                fragmentTransaction.setCustomAnimations(
                    R.anim.slide_right_in,
                    R.anim.slide_left_out,
                    R.anim.slide_left_in,
                    R.anim.slide_right_out
                )
            }

        }

        if (reqCode != null) {
            var args = fragment.arguments
            if (args == null) {
                args = Bundle()
                fragment.arguments = args
            }
            args.putInt(EXTRA_KEY_REQUEST_CDOE, reqCode)
        }
        val oldFragment = fragmentManager.findFragmentById(R.id.fragment_container)
        if (oldFragment != null) {
            fragmentTransaction.hide(oldFragment)
        }
        val tag = fragment.javaClass.name
        fragmentTransaction.add(R.id.fragment_container, fragment, tag)


        if (!replaceMode) {
            fragmentTransaction.addToBackStack(tag)
        }

        fragmentTransaction.commitAllowingStateLoss()
    }

    fun popTo(fragment: Class<out Fragment>): Boolean {
        return popTo(fragment.name)
    }

    fun popTo(tag: String): Boolean {
        try {
            return supportFragmentManager.popBackStackImmediate(tag, 0)
        } catch (e: Exception) {
            // e.printStackTrace();
            return false
        }

    }

    fun popupFragment() {

        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > minFragments) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            if (isShowAnimator) {
                fragmentTransaction.setCustomAnimations(
                    R.anim.slide_right_in,
                    R.anim.slide_left_out,
                    R.anim.slide_left_in,
                    R.anim.slide_right_out
                )
            }

            fragmentManager.popBackStackImmediate()
            fragmentTransaction.commit()

            if (fragmentResultCode != null) {
                val currentFragment = fragmentManager.findFragmentById(R.id.fragment_container)
                if (currentFragment is BaseFragment) {
                    currentFragment.onFragmentResult(fragmentRequestCode, fragmentResultCode!!, fragmentResultData!!)
                }
            }
        } else {
            if (fragmentResultCode != null) {
                setResult(fragmentResultCode!!, fragmentResultData)
            }
            finish()
        }

        fragmentResultCode = null
        fragmentResultData = null
    }

    fun finishIfNoFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            finish()
        }

    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        val currentFragment = fragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment is BaseFragment) {
            if (currentFragment.onBackPressed(this)) {
                return
            }
        }
        if (fragmentManager.backStackEntryCount > 1) {
            popupFragment()
        } else {
            finish()
        }

    }

    fun setFragmentResult(f: BaseFragment, result: Int, data: Intent?) {
        fragmentRequestCode = -1
        if (f.arguments != null) {
            fragmentRequestCode = f.arguments!!.getInt(EXTRA_KEY_REQUEST_CDOE)
        }

        fragmentResultCode = result
        fragmentResultData = data
        setResult(result, data)
    }

    fun setShowAnimator(showAnimator: Boolean) {
        isShowAnimator = showAnimator
    }

    override fun onPause() {
        super.onPause()
        hideInput()
    }

    //隐藏软键盘
    fun hideInput() {
        try {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (this@BaseFragmentActivity.currentFocus != null)
                inputMethodManager.hideSoftInputFromWindow(
                    this@BaseFragmentActivity.currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    /**
     * 创建VelocityTracker对象，并将触摸content界面的滑动事件加入到VelocityTracker当中。
     *
     * @param event
     */
    private fun createVelocityTracker(event: MotionEvent) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain()
        }
        mVelocityTracker!!.addMovement(event)
    }

    /**
     * 回收VelocityTracker对象。
     */
    private fun recycleVelocityTracker() {
        mVelocityTracker!!.recycle()
        mVelocityTracker = null
    }

    // 转载请说明出处：http://blog.csdn.net/ff20081528/article/details/17845753


//    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
//        createVelocityTracker(event)
//        when (event.action) {
//            MotionEvent.ACTION_DOWN -> xDown = event.rawX
//            MotionEvent.ACTION_MOVE -> if (xDown >= 0 && xDown < 30) {
//                xMove = event.rawX
//                //活动的距离
//                val distanceX = (xMove - xDown).toInt()
//                //获取顺时速度
//                val xSpeed = scrollVelocity
//                //当滑动的距离大于我们设定的最小距离且滑动的瞬间速度大于我们设定的速度时，返回到上一个activity
//                if (distanceX > XDISTANCE_MIN && xSpeed > XSPEED_MIN) {
//                    onBackPressed()
//                    return true
//                }
//            }
//            MotionEvent.ACTION_UP -> {
//                xDown = -1f
//                recycleVelocityTracker()
//            }
//            else -> {
//            }
//        }
//        return super.dispatchTouchEvent(event)
//    }

    companion object {

        private val EXTRA_KEY_REQUEST_CDOE = "request_code_"

        fun getIntent(activity: Context?, f: Class<out Fragment>, args: Bundle?): Intent {
            return getIntent(activity, f.name, args)
        }

        fun getIntent(activity: Context?, f: String, args: Bundle?): Intent {
            val intent = Intent(activity, BaseFragmentActivity::class.java)
            intent.putExtra("fname", f)
            intent.putExtra("args", args)
            return intent
        }

        fun goWithActions(activity: Context, f: Class<out Fragment>, vararg actions: String) {
            val args = Bundle()
            for (action in actions) {
                args.putBoolean(action, true)
            }
            go(activity, f, args)
        }

        fun go(activity: Context?, f: Class<out Fragment>, args: Bundle) {
            go(activity, f.name, args)
        }

        fun go(activity: Context?, f: Class<out Fragment>, str: String) {
            go(activity, f.name, str)
        }


        fun go(activity: Context?, f: Class<out Fragment>) {
            go(activity, f.name, "")
        }

        fun go(activity: Context?, f: Class<out Fragment>, args: Bundle?, fullScreen: Boolean) {
            go(activity, f.name, args, fullScreen)
        }

        fun go(activity: Context?, fname: String, args: Bundle) {
            val intent = Intent(activity, BaseFragmentActivity::class.java)
            intent.putExtra("fname", fname)
            intent.putExtra("args", args)
            activity?.startActivity(intent)
        }

        fun go(activity: Context?, fname: String, str: String) {
            val args = Bundle()
            args.putString("str", str)
            val intent = Intent(activity, BaseFragmentActivity::class.java)
            intent.putExtra("fname", fname)
            intent.putExtra("args", args)
            activity?.startActivity(intent)
        }

        fun go(activity: Context?, fname: String, args: Bundle?, fullScreen: Boolean) {
            val intent = Intent(activity, BaseFragmentActivity::class.java)
            intent.putExtra("fname", fname)
            intent.putExtra("args", args)
            intent.putExtra("isFullScreen", fullScreen)
            activity?.startActivity(intent)
        }



        //手指向右滑动时的最小速度
        private val XSPEED_MIN = 200

        //手指向右滑动时的最小距离
        private val XDISTANCE_MIN = 150
    }

    fun showProgressDialog() {
        showProgressDialog(true, "请稍等")
    }

    fun showProgressDialog(text: String) {
        showProgressDialog(true, text)
    }

    fun showProgressDialog(isCancel: Boolean) {
        showProgressDialog(isCancel, "请稍等")
    }

    fun showProgressDialog(isCancel: Boolean, text: String) {
        if (tipDialog == null)
            tipDialog = QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(text)
                .create()
        tipDialog?.setCancelable(isCancel)
        tipDialog?.show()
    }


    fun dismissDialog() {
        if (tipDialog != null) {
            try {
                tipDialog?.dismiss()
            } catch (e: Exception) {
                // e.printStackTrace();
            }

            tipDialog = null
        }
    }
}
