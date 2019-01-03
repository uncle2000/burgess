package com.uncle2000.lib.uitils;//package com.uncle2000.libutils;
//
//import android.os.Handler;
//import android.os.Looper;
//import android.util.Log;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * Created by danger on
// * 16/7/18.
// */
//
//public class RxBus {
//
//    private static final String TAG = RxBus.class.getSimpleName();
//    public static boolean DEBUG = false;
//    private static RxBus instance;
//    private final ConcurrentHashMap<Integer, List<GlobalListener>> listeners = new ConcurrentHashMap<>();
//    private RxBus() {
//    }
////    private ConcurrentHashMap<Object, CompositeSubscription> subscriptionMapper = new ConcurrentHashMap<>();
//
//    public static synchronized RxBus get() {
//        if (null == instance) {
//            instance = new RxBus();
//        }
//        return instance;
//    }
//
//    public GlobalListener register(int eventId, GlobalListener listener) {
//        synchronized (listeners) {
//            List<GlobalListener> subject = listeners.get(eventId);
//
//            if (null == subject) {
//                subject = new ArrayList<>();
//                listeners.put(eventId, subject);
//
//            }
//            subject.remove(listener);
//            subject.add(listener);
//            return listener;
//        }
//    }
//
//    public void unregisterAll(GlobalListener listener) {
//        synchronized (listeners) {
//            for (List<GlobalListener> listenersOfEvent : listeners.values()) {
//                listenersOfEvent.remove(listener);
//            }
//        }
//
//        if (DEBUG) Log.d(TAG, "[unregister]listeners: " + listeners);
//    }
//
//    public void post(final int eventId, final Object... args) {
//        postDelayed(eventId, 0, args);
//    }
//    public void postDelayed(final int eventId,int delay, final Object... args) {
//        new Handler(Looper.getMainLooper()).postDelayed(() -> {
//            synchronized (listeners) {
//                if (!listeners.containsKey(eventId)) {
//                    return;
//                }
//                List<GlobalListener> listenersOfEvent = listeners.get(eventId);
//                for (GlobalListener l : listenersOfEvent) {
//                    l.onGlobalEvent(eventId, args);
//                }
//            }
//        }, delay);
//    }
//
//    public interface GlobalListener {
//        void onGlobalEvent(int eventId, Object... args);
//    }
//
//}
