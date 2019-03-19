package hyunwook.co.kr.android_auto_permit_usb.usb;

import android.content.Intent;
import android.hardware.usb.UsbDevice;

import hyunwook.co.kr.android_auto_permit_usb.base.BasePresenter;
import hyunwook.co.kr.android_auto_permit_usb.base.BaseView;

public interface UsbContract {

    interface View extends BaseView<Presenter> {

        //Transfer set USB Information
        void onTransferUsbInfo(Intent intent);
    }

    interface Presenter extends BasePresenter {

        //Usb Permission
        void onUsbInitPermission(String packageName, UsbDevice device);
    }
}
