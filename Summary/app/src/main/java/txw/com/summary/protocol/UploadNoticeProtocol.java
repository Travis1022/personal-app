package txw.com.summary.protocol;

/**
 * 上传通知协议帧
 * Created by txw on 2018/7/16.
 */
public class UploadNoticeProtocol implements Protocol {


    @Override
    public byte[] outputData() {
        return new byte[0];
    }
}
