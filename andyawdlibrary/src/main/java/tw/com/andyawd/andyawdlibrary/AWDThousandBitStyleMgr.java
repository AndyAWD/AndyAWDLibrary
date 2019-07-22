package tw.com.andyawd.andyawdlibrary;

import java.text.DecimalFormat;

public class AWDThousandBitStyleMgr {

    private DecimalFormat decimalFormat = null;

    public static AWDThousandBitStyleMgr getInstance() {
        return AWDThousandBitStyleHolder.awdThousandBitStyleMgr;
    }

    private static class AWDThousandBitStyleHolder {
        private static final AWDThousandBitStyleMgr awdThousandBitStyleMgr = new AWDThousandBitStyleMgr();
    }

    /**
     * 設定傳入的千分位樣式
     */
    public void setThousandBitStyle(String thousandPattern) {
        decimalFormat = new DecimalFormat(thousandPattern);
    }

    /**
     * 得到千分位樣式
     */
    @SuppressWarnings("unchecked")
    public <T> T getThousandBitStyle(T number) {
        if (null == decimalFormat) {
            decimalFormat = new DecimalFormat(AWDConstants.THOUSAND_FORMAT_01);
        }

        return (T) decimalFormat.format(Double.parseDouble(String.valueOf(number)));
    }
}
