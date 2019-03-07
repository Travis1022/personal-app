package txw.com.summary.protocol;

/**
 * 协议帧接口
 * Created by txw on 2018/7/16.
 */
public interface Protocol {

    /**
     * 返回协议帧的字节数组
     *
     * @return
     */
    byte[] outputData();

}
