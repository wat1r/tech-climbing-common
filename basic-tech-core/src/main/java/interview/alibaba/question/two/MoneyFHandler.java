package interview.alibaba.question.two;


public class MoneyFHandler implements RatioHandler {

    @Override
    public void handRequest(Request request, FilterChain filterChain) {
        if (  request.getSum() >= moneyF){
            int delta = (int) ((request.getSum() > moneyG ? moneyG : request.getSum() - moneyF) * 0.35);
            request.setPay(request.getPay() + delta);
        }
        filterChain.doFilter();
    }
}
