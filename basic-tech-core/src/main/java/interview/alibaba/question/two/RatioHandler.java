package interview.alibaba.question.two;


@FunctionalInterface
public interface RatioHandler {
    int moneyA = 5001;
    int moneyB = 8001;
    int moneyC = 17001;
    int moneyD = 30001;
    int moneyE = 40001;
    int moneyF = 60001;
    int moneyG = 85001;

    void handRequest(Request request, FilterChain filterChain);
}