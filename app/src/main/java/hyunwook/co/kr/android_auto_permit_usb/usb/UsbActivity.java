package hyunwook.co.kr.android_auto_permit_usb.usb;

import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Objects;

import hyunwook.co.kr.android_auto_permit_usb.R;

/**
 * 18-12-14 Fri.
 * Refer to https://blog.csdn.net/mlj1668956679/article/details/14122787
 * USB auto-approve as simple as possible
 * added by hyunwook Cho
 * written in Guangzhou, China.
 */
public class UsbActivity extends AppCompatActivity implements UsbContract.View {

    private UsbContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new UsbPresenter(this, this);
        UsbManager mManager = (UsbManager) getSystemService(USB_SERVICE);

        /**
         * Check the USB device connected to the Android Device
         * Assume that only one Usb Device.
         */
        for (UsbDevice device : Objects.requireNonNull(mManager).getDeviceList().values()) {
            mPresenter.onUsbInitPermission(getPackageName(), device);
        }
    }

    @Override
    public void onTransferUsbInfo(Intent intent) {
        sendBroadcast(intent);
    }

    @Override
    public void setPresenter(UsbContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
