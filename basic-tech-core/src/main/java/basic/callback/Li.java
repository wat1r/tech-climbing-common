package basic.callback;

/**
 * Created by FrankCooper
 * Date 2019/2/8 13:54
 * Description
 */
public class Li {
    /**
     * 相当于B类有参数为CallBack callBack的f()---->背景三
     *
     * @param callBack
     * @param question 小王问的问题
     */
    public void executeMessage(CallBack callBack, String question) {
        System.out.println("小王问的问题--->" + question);

        //模拟小李办自己的事情需要很长时间
        for (int i = 0; i < 10000; i++) {

        }

        /**
         * 小李办完自己的事情之后想到了答案是2
         */
        String result = "答案是2";

        /**
         * 于是就打电话告诉小王，调用小王中的方法
         * 这就相当于B类反过来调用A的方法D
         */
        callBack.solve(result);


    }

}
