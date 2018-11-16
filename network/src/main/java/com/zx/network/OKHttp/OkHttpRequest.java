package com.zx.network.OKHttp;

import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.api.api.utils.AppLog;
import com.zx.network.NetBean;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Name: OkHttpRequest
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-14 11:28
 */
public class OkHttpRequest<T> implements NetRequest {
    private final String TAG=OkHttpRequest.class.getSimpleName();
    private boolean isDestoryed=false;
    private Subscription mSubscription;
    private Observable mObservable;
    private NetRequestCallBack<T> mCallback;

    public OkHttpRequest(Observable mObservable, NetRequestCallBack<T> mCallback) {
        this.mObservable = mObservable;
        this.mCallback = mCallback;
        start();
    }

    @Override
    public boolean isDestoryed() {
        return isDestoryed;
    }

    @Override
    public void cancel() {
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }
        isDestoryed=true;
        mCallback=null;
    }

    @Override
    public void start() {
        mSubscription= mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);

    }
    Observer<NetBean<T>> mObserver=new Observer<NetBean<T>>() {
        @Override
        public void onCompleted() {
            AppLog.print(TAG,"onCompleted");
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(NetBean<T> tNetBean) {
            if(mCallback!=null){
                if(tNetBean.getCode()==200) {
                    mCallback.onSuccess(tNetBean.getData());
                }else {
                    mCallback.onError(tNetBean.getCode(),tNetBean.getError().getMsg());
                }
            }
        }
    };

}
