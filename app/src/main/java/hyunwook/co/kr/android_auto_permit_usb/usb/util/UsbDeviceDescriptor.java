package hyunwook.co.kr.android_auto_permit_usb.usb.util;

/**
 * USB Device Identification
 */
public class UsbDeviceDescriptor {

    public String packageName;
    public int deviceClass;
    public int deviceSubClass;
    public int vendorId;
    public int productId;

    @Override
    public String toString() {
        return "packageName:" + packageName
                + ", deviceClass:" + deviceClass
                + ", deviceSubclass: " + deviceSubClass
                + ", vendorId: " + vendorId
                + ", productId: " + productId;
    }
}
