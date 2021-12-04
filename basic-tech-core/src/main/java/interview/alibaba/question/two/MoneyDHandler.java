package interview.alibaba.question.two;


public class MoneyDHandler implements RatioHandler {

    @Override
    public void handRequest(Request request, FilterChain filterChain) {
        if (request.getSum() >= moneyD) {
            int delta = (int) ((request.getSum() >= moneyE ? moneyE : request.getSum() - moneyD) * 0.25);
            request.setPay(request.getPay() + delta);
        }
        filterChain.doFilter();
    }
}
