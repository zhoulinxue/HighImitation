package com.zx.network.OKHttp;


import com.zx.api.api.BaseView;
import com.zx.api.netWork.NetRequest;
import com.zx.api.netWork.NetRequestCallBack;
import com.zx.api.utils.AppLog;
import com.zx.network.BaseViewCallBack;
import com.zx.network.NetBean;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Name: BaseRequest
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 15:13
 */
public class BaseRequest<T> implements NetRequest{
    private final String TAG=BaseRequest.class.getSimpleName();
    private NetRequestCallBack<T> mCallback;
    private String requestCode;
    private boolean isDestoryed=false;
    private Subscription mSubscription;


    public BaseRequest(String requestCode,NetRequestCallBack<T> mCallback,String... param) {
        this.mCallback = mCallback;
        this.requestCode = requestCode;
        start(param);
    }

    public BaseRequest(BaseView baseView,String requestCode,String...param) {
        this(requestCode,new BaseViewCallBack<T>(baseView,requestCode),param);
    }

    @Override
    public boolean isDestoryed() {
        return isDestoryed;
    }

    @Override
    public void destory() {
        if(mSubscription!=null) {
            mSubscription.unsubscribe();
        }
        mCallback=null;
        isDestoryed=true;
    }

    @Override
    public void start(String... param) {
       Observable<NetBean<T>> observable= ApiManager.getInstance().start(requestCode,param);
        mSubscription= observable.subscribeOn(Schedulers.io())
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
                mCallback.onSuccess(tNetBean.getData());
            }
        }
    };
}
