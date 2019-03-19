package hyunwook.co.kr.android_auto_permit_usb.base;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
