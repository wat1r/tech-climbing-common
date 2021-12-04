package interview.alibaba.question.two;


public class MoneyAHandler implements RatioHandler {

    @Override
    public void handRequest(Request request, FilterChain filterChain) {
        if (request.getSum() >= moneyA) {
            int delta = (int) ((request.getSum() > moneyB ? moneyB : request.getSum() - moneyA) * 0.03);
            request.setPay(request.getPay() + delta);
        }
        filterChain.doFilter();
    }
}
