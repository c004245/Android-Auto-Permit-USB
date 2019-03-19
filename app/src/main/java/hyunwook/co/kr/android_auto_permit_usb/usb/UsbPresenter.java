package hyunwook.co.kr.android_auto_permit_usb.usb;

import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;

/**
 * Usb Data Presenter
 */
public class UsbPresenter implements UsbContract.Presenter {

    private final UsbContract.View mView;
    private final Context mContext;

    private static final String ACTION_USB_PERMISSION_APP = "ACTION_USB_PERMISSION_ISSUER";

    UsbPresenter(Context context, UsbContract.View view) {
        mView = view;
        mContext = context;

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onUsbInitPermission(String packageName, UsbDevice device) {
        Intent intent = new Intent();
        intent.setAction(ACTION_USB_PERMISSION_APP);
        intent.putExtra("packageName", packageName);
        intent.putExtra("vendorId", device.getVendorId());
        intent.putExtra("productId", device.getProductId());
        intent.putExtra("deviceClass", device.getDeviceClass());
        intent.putExtra("deviceSubclass", device.getDeviceSubclass());

        mView.onTransferUsbInfo(intent);
    }
}
