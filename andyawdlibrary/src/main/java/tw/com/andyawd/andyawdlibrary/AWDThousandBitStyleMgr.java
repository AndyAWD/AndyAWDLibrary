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
    public void setThousandBitStyle(String ThousandPattern) {
        decimalFormat = new DecimalFormat(ThousandPattern);
    }

    /**
     * 得到千分位樣式
     */
    public String getThousandBitStyle(String number) {
        try {
            if (null == decimalFormat) {
                decimalFormat = new DecimalFormat(AWDConstants.THOUSAND_FORMAT_01);
            }

            return decimalFormat.format(Double.parseDouble(String.valueOf(number)));
        } catch (Exception e) {
            return "0";
        }
    }
}
