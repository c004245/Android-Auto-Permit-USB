package hyunwook.co.kr.android_auto_permit_usb.usb;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import hyunwook.co.kr.android_auto_permit_usb.R;

/**
 * 18-12-14 Fri.
 * USB auto-approve as simple as possible
 * added by hyunwook Cho
 * written in Guangzhou, China.
 */
public class UsbActivity extends AppCompatActivity implements UsbContract.View {

    private UsbManager mManager;

    //USB Device Adapter
    private ArrayAdapter<String> mReaderAdaper;

    private UsbContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mManager = (UsbManager) getSystemService(USB_SERVICE);

        mReaderAdaper = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        //Check the USB device connected to the Android Device
        for (UsbDevice device : mManager.getDeviceList().values()) {
            mReaderAdaper.add(device.getDeviceName());
        }

        int deviceCount = mReaderAdaper.getCount();
    }

    @Override
    public void setPresenter(UsbContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
