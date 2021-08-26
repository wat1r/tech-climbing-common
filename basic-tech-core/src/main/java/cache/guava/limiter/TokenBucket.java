package cache.guava.limiter;

/**
 * 令牌桶
 */
public class TokenBucket {


    private volatile long curTokenCnt;  // 当前桶中有多少令牌
    private volatile long nextRefreshTime;  // 下一次刷新桶中令牌数量的时间戳
    private final double unitAddNum;    // 单位时间(1s)可以往桶中放令牌数量
    private final long maxTokenNum;      // 令牌桶中最多有多少令牌

    /**
     * @param unitAddNum  1秒增加几个令牌
     * @param maxToken    桶中最大令牌数
     * @param isFullStart 一开始是否是满的
     */
    public TokenBucket(double unitAddNum, long maxToken, boolean isFullStart) {
        if (unitAddNum <= 0 || maxToken <= 0) {
            throw new RuntimeException("unitAddNum and maxToken cannot less than 0");
        }
        if (unitAddNum > 1000) {
            throw new RuntimeException("unitAddNum max is 1000");
        }
        this.unitAddNum = unitAddNum;
        this.maxTokenNum = maxToken;
        if (isFullStart) {
            this.curTokenCnt = maxToken;
        } else {
            this.curTokenCnt = 0;
        }
        this.nextRefreshTime = calculateNextRefreshTime(System.currentTimeMillis());
    }

    public boolean acquire(long needTokenNum) {
        if (needTokenNum > this.maxTokenNum) {
            return false;
        }
        synchronized (this) {
            long currentTimestamp = System.currentTimeMillis();
            this.refreshCurrentTokenCount(currentTimestamp);
            if (needTokenNum <= this.curTokenCnt) {
                return this.doAquire(needTokenNum, currentTimestamp);
            }
            return false;
        }
    }

    private boolean doAquire(long needTokenNum, long currentTimestamp) {
        this.curTokenCnt -= needTokenNum;
        return true;
    }

    /**
     * 刷新桶中令牌数量
     *
     * @param currentTimestamp
     */
    private void refreshCurrentTokenCount(long currentTimestamp) {
        if (this.nextRefreshTime > currentTimestamp) {
            return;
        }
        this.curTokenCnt = Math.min(this.maxTokenNum, this.curTokenCnt + calculateNeedAddTokenNum(currentTimestamp));
        this.nextRefreshTime = calculateNextRefreshTime(currentTimestamp);

    }

    /**
     * 计算当前需要添加多少令牌
     *
     * @param currentTimestamp
     * @return
     */
    private long calculateNeedAddTokenNum(long currentTimestamp) {
        if (this.nextRefreshTime > currentTimestamp) {
            return 0;
        }
        long addOneMs = Math.round(1.0D / this.unitAddNum * 1000D); // 这么久才能加1个令牌
        return (currentTimestamp - this.nextRefreshTime) / addOneMs + 1;
    }

    /**
     * @param curTimestamp
     * @return
     */
    private long calculateNextRefreshTime(long curTimestamp) {
        if (curTimestamp < this.nextRefreshTime) {
            return this.nextRefreshTime;
        }
        long addOneMs = Math.round(1.0D / this.unitAddNum * 1000D); // 添加1个令牌
        long result = 0;
        if (this.nextRefreshTime <= 0) {
            result = curTimestamp + addOneMs;
        } else {
            result = this.nextRefreshTime + ((curTimestamp - this.nextRefreshTime) / addOneMs + 1) * addOneMs;
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucket tokenBucket = new TokenBucket(1D, 1, true);
        for (int i = 0; i < 10; i++) {
            System.out.println(tokenBucket.acquire(1));
            Thread.sleep(500);
        }
    }
}

