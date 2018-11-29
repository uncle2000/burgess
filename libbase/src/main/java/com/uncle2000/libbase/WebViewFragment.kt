//package com.uncle2000.libbase
//
//import android.app.Activity
//import android.content.Context
//import android.databinding.DataBindingUtil
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.uncle2000.libbase.databinding.WebviewFragmentBinding
//import com.uncle2000.libviews.webview.MODEL
//import com.uncle2000.libviews.webview.WebViewInterface
//import com.uncle2000.libviews.webview.WebViewModel
////import com.uncle2000.libviews.webview.SendListenerHolder
////import com.uncle2000.libviews.webview.ShareListenerHolder
//import com.uncle2000.libviews.webview.webview.InvokeOutMethod
//
///**
// * Created by wangwei on 2018/3/22/022.
// * 通用的webviewFragemnt
// * 如果需要定制 则可以选择性的继承实现子类
// */
//const val RESULT_WEB_VIEW = 36
//
//open class WebViewFragment : BaseFragment() {
//    protected lateinit var webViewModel: WebViewModel
//    protected lateinit var binding: WebviewFragmentBinding
//    private var loadUrlFinished = false
//
//    companion object {
//
//        /*简单调用*/
//        fun load(context: Context, loadUrl: String, titleStr: String) {
//            val webViewModel = WebViewModel(loadUrl)
//            webViewModel.title = titleStr
//            load(context, webViewModel)
//        }
//
//        fun load(context: Context, webViewModel: WebViewInterface) {
//            load(context, webViewModel.getWebViewModel())
//        }
//
//        fun load(context: Context, webViewModel: WebViewModel) {
//            val bundle = Bundle()
//            bundle.putSerializable(MODEL, webViewModel)
//            BaseFragmentActivity.go(context, WebViewFragment::class.java, bundle)
//        }
//
//        fun loadForResult(activity: Activity, webViewModel: WebViewInterface) {
//            loadForResult(activity, webViewModel.getWebViewModel())
//        }
//
//        fun loadForResult(activity: Activity, webViewModel: WebViewModel) {
//            val bundle = Bundle()
//            bundle.putSerializable(MODEL, webViewModel)
//            val intent = BaseFragmentActivity.getIntent(activity, WebViewFragment::class.java, bundle)
//            activity.startActivityForResult(intent, RESULT_WEB_VIEW)
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if (arguments != null)
//            webViewModel = arguments?.getSerializable(MODEL) as WebViewModel
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = DataBindingUtil.inflate(inflater, R.layout.webview_fragment, null, false)
//        binding.webView.setInvokeOutMethodInterface(object : InvokeOutMethod {
//            override fun finishOut() {
//                finish()
//            }
//
//            override fun showProcessOut() {
//                showProgressDialog()
//            }
//
//            override fun dismissProcessOut() {
//                dismissDialog()
////                if (!loadUrlFinished && webViewModel.listenerHolder is ShareListenerHolder) {
////                    val holder: ShareListenerHolder = webViewModel.listenerHolder as ShareListenerHolder
////                    if (context != null)
////                        holder.initBeforeLoadUrl(context!!, dataBinding.root)
////                }
//                loadUrlFinished = true
//            }
//        })
//
//        binding.titleBar.setLeftDrawableVisible(false)
//        if (webViewModel.title.isNullOrEmpty())
//            binding.titleBar.visibility = View.GONE
//        else {
//            binding.titleBar.setTitleText(webViewModel.title)
////            webViewModel.listenerHolder?.apply {
////                if (this is SendListenerHolder) {
////                    dataBinding.titleBar.setRightText("发送")
////                } else if (this is ShareListenerHolder) {
////                    dataBinding.titleBar.setRightDrawable(R.drawable.svg_share_gray)
////                }
////                dataBinding.titleBar.setRightOnClickListener {
////                    onRightClick(activity as Context, dataBinding.root)
////                }
////            }
//        }
//
//        return binding.root
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        /*写到这里是为了在加载之前可以干点其他事情*/
//        binding.webView.loadUrl(webViewModel.url)
//    }
//}