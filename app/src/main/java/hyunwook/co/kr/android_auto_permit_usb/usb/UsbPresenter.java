package hyunwook.co.kr.android_auto_permit_usb.usb;

import android.content.Context;

/**
 * Usb Data Presenter
 */
public class UsbPresenter implements UsbContract.Presenter {

    private final UsbContract.View mView;
    private final Context mContext;

    UsbPresenter(Context context, UsbContract.View view) {
        mView = view;
        mContext = context;

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
