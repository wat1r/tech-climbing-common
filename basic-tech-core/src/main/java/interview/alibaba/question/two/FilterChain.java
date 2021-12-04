package interview.alibaba.question.two;

import java.util.ArrayList;
import java.util.List;



public class FilterChain {
    private int index = 0;

    private Request request;

    private List<RatioHandler> ratioHandlerList = new ArrayList<>();

    public FilterChain(Request request) {
        this.request = request;
    }

    public FilterChain addHandler(RatioHandler ratioHandler) {
        if (null == ratioHandler) return null;
        this.ratioHandlerList.add(ratioHandler);
        return this;
    }

    public void doFilter() {
        if (index == ratioHandlerList.size()) {
            request.end();
            return;
        }
        ratioHandlerList.get(index++).handRequest(request, this);
    }
}