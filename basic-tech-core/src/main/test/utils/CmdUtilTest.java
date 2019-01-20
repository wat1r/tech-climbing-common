package utils;

import org.junit.Test;

/**
 * Created by FrankCooper
 * Date 2019/1/20 22:43
 * Description
 */
public class CmdUtilTest {


    public static void main(String[] args) {

//        new CmdUtilTest().ping();
//        new CmdUtil(0).execute("ipconfig /all", null);
//        new CmdUtil(0).execute("calc", null);
        new CmdUtil(0).execute("print  /d:\\Device  test.txt", null);
    }


    CmdUtil.CallBack cb = new CmdUtil.CallBack() {
        @Override
        public void onSuccess() {
            System.out.println("onSuccess");
        }

        @Override
        public void onFailed(Exception e) {
            System.out.println("onFailed");
        }

        @Override
        public void onTimeout() {
            System.out.println("onTimeout");
        }

        @Override
        public void stdoutResponse(String stdoutString) {
            System.out.println("stdoutResponse");
        }

        @Override
        public void erroutResponse(String erroutString) {
            System.out.println("erroutResponse");
        }

        @Override
        public void ontherResponse(Exception e) {
            System.out.println("ontherResponse");

        }
    };


    public void ping() {
        String osName = System.getProperty("os.name");
        System.out.println("osName " + osName);

        if (osName.contains("Windows")) {
            pingOnWindow("baidu.com", 20, cb);
        } else {// Linux
            pingOnLinux("baidu.com", 20, cb);
        }

    }

    /**
     * @param host     地址
     * @param count    次数
     * @param callback 回调
     */
    public void pingOnLinux(String host, int count, CmdUtil.CallBack callback) {
        String command = "ping -c " + count + " " + host;
        // new CmdUtil(5000).execute(command, callback);
        new CmdUtil(2000).execute(command, null);
    }

    /**
     * window
     *
     * @param host     地址
     * @param count    次数
     * @param callback 回调
     */
    public void pingOnWindow(String host, int count, CmdUtil.CallBack callback) {
        String command = "cmd /c ping -n " + count + " " + host;
        System.out.println("命令 " + command);
        // new CmdUtil(5000).execute(command, callback);
        new CmdUtil(2000).execute(command, null);
    }


}
