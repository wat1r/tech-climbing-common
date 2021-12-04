package interview.alibaba.question.two;


public class MoneyGHandler implements RatioHandler {

    @Override
    public void handRequest(Request request, FilterChain filterChain) {
        if (request.getSum() >= moneyG) {
            int delta = (int) ((request.getSum() - moneyG) * 0.45);
            request.setPay(request.getPay() + delta);
        }
        filterChain.doFilter();
    }
}
