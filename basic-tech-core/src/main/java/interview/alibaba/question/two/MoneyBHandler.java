package interview.alibaba.question.two;


public class MoneyBHandler implements RatioHandler {

    @Override
    public void handRequest(Request request, FilterChain filterChain) {
        if (request.getSum() >= moneyB) {
            int delta = (int) ((request.getSum() > moneyC ? moneyC : request.getSum() - moneyB) * 0.10);
            request.setPay(request.getPay() + delta);
        }
        filterChain.doFilter();
    }
}
