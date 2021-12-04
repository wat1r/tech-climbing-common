package interview.alibaba.question.two;


public class MoneyCHandler implements RatioHandler {

    @Override
    public void handRequest(Request request, FilterChain filterChain) {
        if (  request.getSum() >= moneyC){
            int delta = (int) ((request.getSum() > moneyD ? moneyD : request.getSum() - moneyC) * 0.20);
            request.setPay(request.getPay() + delta);
        }
        filterChain.doFilter();
    }
}
