package hyunwook.co.kr.android_auto_permit_usb.usb;

import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.Objects;

import hyunwook.co.kr.android_auto_permit_usb.R;

/**
 * 18-12-14 Fri.
 * USB auto-approve as simple as possible
 * added by hyunwook Cho
 * written in Guangzhou, China.
 */
public class UsbActivity extends AppCompatActivity implements UsbContract.View {

    private static final String TAG = UsbActivity.class.getSimpleName();

    private UsbContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new UsbPresenter(this, this);
        UsbManager mManager = (UsbManager) getSystemService(USB_SERVICE);

        //USB Device Adapter
        ArrayAdapter<String> mReaderAdaper = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        /**
         * Check the USB device connected to the Android Device
         * Assume that only one Usb Device.
         */
        for (UsbDevice device : Objects.requireNonNull(mManager).getDeviceList().values()) {
            mReaderAdaper.add(device.getDeviceName());

            mPresenter.onUsbInitPermission(getPackageName(), device);
        }
    }

    @Override
    public void onTransferUsbInfo(Intent intent) {
        Log.d(TAG, "onTransfer usb ->" + intent.toString());
        sendBroadcast(intent);
    }

    @Override
    public void setPresenter(UsbContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
