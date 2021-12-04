package interview.alibaba.question.two;

public class ClientTest {
    public static void main(String[] args) {
        Request req = new Request("小明", 75000, 0);

        FilterChain filterChain = new FilterChain(req);

        filterChain.addHandler(new MoneyAHandler())
                .addHandler(new MoneyBHandler())
                .addHandler(new MoneyCHandler())
                .addHandler(new MoneyDHandler())
                .addHandler(new MoneyEHandler())
                .addHandler(new MoneyFHandler())
                .addHandler(new MoneyGHandler())
                //使用lambda表达式创建自定义处理类
                .addHandler((Request request, FilterChain filter) -> {
                    filter.doFilter();
                })
                .doFilter();
        System.out.printf("%d", req.getPay());
    }
}
