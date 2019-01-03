package com.uncle2000.lib.uitils

//package com.mll.verification.tools
//
///**
// * 人民币金额的工具类
// * Created by dengjun on 2017/12/26/026.
// */
//fun showRmbSymbol(amount: Any): String {
//    return "¥" + formatRmb(amount)
//}
//
////object RMB {
////    fun showRmbFenSymbol(amount: Any): String {
////        return "¥" + formatRmbFen(amount)
////    }
////}
//
//
///**
// * @param amount 人民币金额，单位元
// * @param minPrecision 最小精度，小数点后补0
// * @param maxPrecision 最大精度，这个精度后的值会被丢弃
// *
// * formatRmb(1)             gets 1
// * formatRmb(1.2)           gets 1.2
// * formatRmb(1.23)          gets 1.23
// * formatRmb(1.239)         gets 1.23
// *
// * formatRmb(1.2, 2)        gets 1.20
// * formatRmb(1.23, 2)       gets 1.23
// * formatRmb(1.234, 2)      gets 1.23
// *
// * formatRmb(1.2, 3)        gets 1.200
// * formatRmb(1.23, 3)       gets 1.230
// * formatRmb(1.234, 3)      gets 1.234
// * formatRmb(1.2349, 3)     gets 1.234
// *
// * formatRmb(1.2, 0, 3)     gets 1.2
// * formatRmb(1.23, 0, 3)    gets 1.23
// * formatRmb(1.234, 0, 3)   gets 1.234
// * formatRmb(1.2349, 0, 3)  gets 1.234
// */
//
//
//@JvmOverloads
//fun formatRmb(amount: Any?, minPrecision: Int = 0, maxPrecision: Int = 2): String {
//    val maxPrecision = Math.max(minPrecision, maxPrecision)
//
//    var floatVal: Double = when (amount) { // 浮点弄的元金额
//        is String -> return amount.trimEnd('0').trimEnd('.')
//        is Number -> amount.toDouble()
//        else -> return "0"
//    }
//
//    val va = (floatVal * 100).toInt()
//    val builder = String.format("%d.%02d", va / 100, va % 100)
//
//    return builder.trimEnd('0').trimEnd('.')
//}
//
///**
// * @param amount 人民币金额，单位分，其它同
// * @see formatRmb
// * */
//@JvmOverloads
//fun formatRmbFen(amount: Any?, minPrecision: Int = 0, maxPrecision: Int = 2): String {
//    val fen =
//            when (amount) { // 浮点弄的元金额
//                is String -> amount.toInt()
//                is Number -> amount.toInt()
//                else -> 0
//            }
//    val str = String.format("%d.%02d", fen / 100, fen % 100)
//    return str.trimEnd('0').trimEnd('.')
//}
//
//
//fun main(args: Array<String>) {
//    formatRmb("888888.88")
//}