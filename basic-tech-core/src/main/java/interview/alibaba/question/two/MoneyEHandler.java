package interview.alibaba.question.two;


public class MoneyEHandler implements RatioHandler {

    @Override
    public void handRequest(Request request, FilterChain filterChain) {
        if (  request.getSum() >= moneyE){
            int delta = (int) ((request.getSum() > moneyF ? moneyF : request.getSum() - moneyE) * 0.30);
            request.setPay(request.getPay() + delta);
        }
        filterChain.doFilter();
    }
}
