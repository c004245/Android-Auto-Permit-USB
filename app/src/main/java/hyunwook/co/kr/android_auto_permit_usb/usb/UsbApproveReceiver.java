package hyunwook.co.kr.android_auto_permit_usb.usb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.HashMap;

import hyunwook.co.kr.android_auto_permit_usb.usb.util.IUsbManager;
import hyunwook.co.kr.android_auto_permit_usb.usb.util.UsbDeviceDescriptor;

/**
 * When BroadcastAction 'ACTION_USB_PERMISSION_ISSUER'
 */
public class UsbApproveReceiver extends BroadcastReceiver {

    private static final String TAG = UsbApproveReceiver.class.getSimpleName();

    private final String ACTION_USB_PERMISSION_APP = "ACTION_USB_PERMISSION_ISSUER";

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        Log.d(TAG, "action ->" + action);
        if (action != null && action.equals(ACTION_USB_PERMISSION_APP)) {
            try {
                //Set UsbDevice Information
                UsbDeviceDescriptor deviceFilter = new UsbDeviceDescriptor();
                deviceFilter.packageName = intent.getStringExtra("packageName");
                deviceFilter.vendorId = intent.getIntExtra("vendorId", -1);
                deviceFilter.productId = intent.getIntExtra("productId", -1);
                deviceFilter.deviceClass = intent.getIntExtra("deviceClass", -1);
                deviceFilter.deviceSubClass = intent.getIntExtra("deviceSubclass", -1);

                PackageManager pm = context.getPackageManager();
                ApplicationInfo appInfo = pm.getApplicationInfo(deviceFilter.packageName, 0);

                //Usb Service.
                UsbManager manager = (UsbManager) context.getSystemService(Context.USB_SERVICE);

                //Register ServiceManager
                Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);

                //Binder USB
                IBinder binder = (IBinder) method.invoke(null, "usb");
                IUsbManager service = IUsbManager.Stub.asInterface(binder);

                HashMap<String, UsbDevice> deviceList = manager.getDeviceList();

                //Check normal USB Device.
                if ((deviceFilter.vendorId != -1 && deviceFilter.productId != -1) ||
                        (deviceFilter.deviceClass != -1 && deviceFilter.deviceSubClass != -1)) {

                    for (UsbDevice device : deviceList.values()) {
                        //If the connected USB device and find the device looking for same.
                        if ((device.getVendorId() == deviceFilter.vendorId &&
                                device.getProductId() == deviceFilter.productId)) {

                            try {
                                //Try GrantPermission USB Device
                                service.grantDevicePermission(device, appInfo.uid);
                                service.setDevicePackage(device, appInfo.packageName, appInfo.uid);
                            } catch (SecurityException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
