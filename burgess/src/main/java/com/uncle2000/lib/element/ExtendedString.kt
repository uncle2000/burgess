package com.uncle2000.lib.element

import android.text.TextUtils


/**
 * Created by 2000 on 2016/8/5.
 * This class only puts the prefix & suffix & separator.
 */
object ExtendedString {
    val PREF_ACTIVITE_TIME_PREFIX = "offline_active_"
    val NEW_CUS_MSG_KEY = "_new_cus_msg_keys"
    val NEW_CUS_COUNT = "_newc"//新用户数
    val NEW_CUS_FRESH = "_NEW_CUS_FRESH"//客户是否要刷新
    /**
     * prefix
     */
    var LruCachePrefix = "big_"
    var H5Prefix = "h5_"//Must lower case
    /* DbPrefUtil的主键前缀或者后缀☟☟☟ start */
    var NoReplyMessageCount = "no_reply_message_count_"//客户未回复计数Key
    /* DbPrefUtil的主键前缀或者后缀☝☝☝︎ end */

//    fun getPrefKey(extend: String): String {
//        var userId = ""
//        if (TextUtils.isEmpty(extend)) {
//            val userModel = UserManager.getInstance().loginUser
//            if (userModel != null) {
//                userId = userModel.storeUuid
//            }
//        }
//        return userId + extend /*"_newc"*/
//    }
    /*
      suffix
      */


    /*
      separator
      */


}
