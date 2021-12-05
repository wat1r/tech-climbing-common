package interview.alibaba.question.three;

public class LocalCache {
}

///**
// *
// *
// * //评测题目: 无
// *
// * 1、现输入一个字符串，对字符串进行压缩，如：如字符串"abcdabcd"由于无连续重复字符，压缩后的字符串还是"abcdabcd"，字符串"xxxrryyyyyyz"压缩后就成为"3x2r6yz"。
// *
// *
// * package interview.alibaba.question.one;
// *
// *
// *
// * public class One {
// *
// *
// *     /**
// *      * 1、现输入一个字符串，对字符串进行压缩，如：如字符串"abcdabcd"由于无连续重复字符，压缩后的字符串还是"abcdabcd"，字符串"xxxrryyyyyyz"压缩后就成为"3x2r6yz"。
// *      *
// *      * @param args
// *      */
// *public static void main(String[]args){
//        *One handler=new One();
//        *System.out.printf("%s\n",handler.process("abcdabcd"));
//        *System.out.printf("%s\n",handler.process("xxxrryyyyyyz"));
//        *System.out.printf("%s\n",handler.process("xxxrryyyyyyy"));
//        *
//        *}
//        *
//        *public String process(String src){
//        *char[]chs=src.toCharArray();
//        *StringBuilder res=new StringBuilder();
//        *int count=1;
//        *int slow=0,fast=1;
//        *while(fast<chs.length){
//        *while(fast<chs.length&&chs[fast]==chs[slow]){
//        *fast++;
//        *count++;
//        *}
//        *res.append(count==1?"":count).append(chs[slow]);
//        *slow=fast;
//        *fast++;
//        *count=1;
//        *}
//        *if(slow<chs.length)res.append(count==1?"":count).append(chs[slow]);
//        *return res.toString();
//        *}
//        *
//        *
//        *}
//        *
//        *
//        *
//        *2、实现个税的计算
//        * //1~5000 税率 0
//        * //5001~8000 3%
//        * //8001~17000 10%
//        * //17001~30000 20%
//        * //30001~40000 25%
//        * //40001~60000 30%
//        * //60001~85000 35%
//        * //85001~      45%
//        * //要求F
//        * //1. 逻辑正确，代码优雅
//        * //2. 可扩展性，考虑区间的变化，比如说起征点从5000变成10000等等，或者说85000以上的征税50%。
//        * //这里举个例子，比如说税前10000元，5000部分是不扣税，后面5000，3000扣税3%，2000扣税10%。
//        *
//        * /**
// * ClientTest
// * FilterChain
// * MoneyAHandler
// * MoneyBHandler
// * MoneyCHandler
// * MoneyDHandler
// * MoneyEHandler
// * MoneyFHandler
// * MoneyGHandler
// * RatioHandler
// * Request
// * **/
//        *
//        *
//        *package interview.alibaba.question.two;
//        *
//        *
//
//public class ClientTest {
// *
//
//    public static void main(String[] args) {
// *Request req = new Request("小明", 75000, 0);
// *
// *FilterChain filterChain = new FilterChain(req);
// *
// *filterChain.addHandler(new MoneyAHandler())
//                *                 .addHandler(new MoneyBHandler())
//                *                 .addHandler(new MoneyCHandler())
//                *                 .addHandler(new MoneyDHandler())
//                *                 .addHandler(new MoneyEHandler())
//                *                 .addHandler(new MoneyFHandler())
//                *                 .addHandler(new MoneyGHandler())
//                *                 //使用lambda表达式创建自定义处理类
// *                 .addHandler((Request request, FilterChain filter) -> {
// *filter.doFilter();
// *})
//                *                 .doFilter();
// *System.out.printf("%d", req.getPay());
// *}
// *
//}
// *
//         *
//         *package interview.alibaba.question.two;
//         *
//         *import java.util.ArrayList;
//         *import java.util.List;
//         *
//         *
//         *
//         *
//
//public class FilterChain {
// *
//    private int index = 0;
// *
//         *
//    private Request request;
// *
//         *
//    private List<RatioHandler> ratioHandlerList = new ArrayList<>();
// *
//         *
//
//    public FilterChain(Request request) {
// *this.request = request;
// *}
// *
//         *
//
//    public FilterChain addHandler(RatioHandler ratioHandler) {
// *if (null == ratioHandler) return null;
// *this.ratioHandlerList.add(ratioHandler);
// *return this;
// *}
// *
//         *
//
//    public void doFilter() {
// *if (index == ratioHandlerList.size()) {
// *request.end();
// *return;
// *}
// *ratioHandlerList.get(index++).handRequest(request, this);
// *}
// *
//}
// *
//         *package interview.alibaba.question.two;
//         *
//         *
//         *@FunctionalInterface
// *
//
//public interface RatioHandler {
// *
//    int moneyA = 5001;
// *
//    int moneyB = 8001;
// *
//    int moneyC = 17001;
// *
//    int moneyD = 30001;
// *
//    int moneyE = 40001;
// *
//    int moneyF = 60001;
// *
//    int moneyG = 85001;
// *
//         *
//
//    void handRequest(Request request, FilterChain filterChain);
// *
//}
// *
//         *
//         *
//         *package interview.alibaba.question.two;
//         *
//         *import lombok.AllArgsConstructor;
//         *import lombok.Data;
//         *
//         *
//         *@Data
// *@AllArgsConstructor
// *
//
//public class Request {
// *
//         *
//    private String name;
// *
//         *
//    private Integer sum;
// *
//         *
//    private Integer pay;
// *
//         *
//
//    public void end() {
// *System.out.println("the end");
// *}
// *
//}
// *
//         *
//         *package interview.alibaba.question.two;
//         *
//         *
//         *
//
//public class MoneyAHandler implements RatioHandler {
// *
//         *
//    @Override
// *
//
//    public void handRequest(Request request, FilterChain filterChain) {
// *if (request.getSum() >= moneyA) {
// *int delta = (int) ((request.getSum() > moneyB ? moneyB : request.getSum() - moneyA) * 0.03);
// *request.setPay(request.getPay() + delta);
// *}
// *filterChain.doFilter();
// *}
// *
//}
// *
//         *
//         *package interview.alibaba.question.two;
//         *
//         *
//         *
//
//public class MoneyBHandler implements RatioHandler {
// *
//         *
//    @Override
// *
//
//    public void handRequest(Request request, FilterChain filterChain) {
// *if (request.getSum() >= moneyB) {
// *int delta = (int) ((request.getSum() > moneyC ? moneyC : request.getSum() - moneyB) * 0.10);
// *request.setPay(request.getPay() + delta);
// *}
// *filterChain.doFilter();
// *}
// *
//}
// *
//         *
//         *package interview.alibaba.question.two;
//         *
//         *
//         *
//
//public class MoneyCHandler implements RatioHandler {
// *
//         *
//    @Override
// *
//
//    public void handRequest(Request request, FilterChain filterChain) {
// *if (request.getSum() >= moneyC) {
// *int delta = (int) ((request.getSum() > moneyD ? moneyD : request.getSum() - moneyC) * 0.20);
// *request.setPay(request.getPay() + delta);
// *}
// *filterChain.doFilter();
// *}
// *
//}
// *
//         *
//         *package interview.alibaba.question.two;
//         *
//         *
//         *
//
//public class MoneyDHandler implements RatioHandler {
// *
//         *
//    @Override
// *
//
//    public void handRequest(Request request, FilterChain filterChain) {
// *if (request.getSum() >= moneyD) {
// *int delta = (int) ((request.getSum() >= moneyE ? moneyE : request.getSum() - moneyD) * 0.25);
// *request.setPay(request.getPay() + delta);
// *}
// *filterChain.doFilter();
// *}
// *
//}
// *
//         *
//         *package interview.alibaba.question.two;
//         *
//         *
//         *
//
//public class MoneyEHandler implements RatioHandler {
// *
//         *
//    @Override
// *
//
//    public void handRequest(Request request, FilterChain filterChain) {
// *if (request.getSum() >= moneyE) {
// *int delta = (int) ((request.getSum() > moneyF ? moneyF : request.getSum() - moneyE) * 0.30);
// *request.setPay(request.getPay() + delta);
// *}
// *filterChain.doFilter();
// *}
// *
//}
// *
//         *
//         *package interview.alibaba.question.two;
//         *
//         *
//         *
//
//public class MoneyFHandler implements RatioHandler {
// *
//         *
//    @Override
// *
//
//    public void handRequest(Request request, FilterChain filterChain) {
// *if (request.getSum() >= moneyF) {
// *int delta = (int) ((request.getSum() > moneyG ? moneyG : request.getSum() - moneyF) * 0.35);
// *request.setPay(request.getPay() + delta);
// *}
// *filterChain.doFilter();
// *}
// *
//}
// *
//         *
//         *package interview.alibaba.question.two;
//         *
//         *
//         *
//
//public class MoneyGHandler implements RatioHandler {
// *
//         *
//    @Override
// *
//
//    public void handRequest(Request request, FilterChain filterChain) {
// *if (request.getSum() >= moneyG) {
// *int delta = (int) ((request.getSum() - moneyG) * 0.45);
// *request.setPay(request.getPay() + delta);
// *}
// *filterChain.doFilter();
// *}
// *
//}
// *
//         *
//         *
//         *
//         *
//         *
//         *3、实现一个本地缓存。 （比如 过期时间、数据结构、淘汰策略等）
//         *
//         *LocalCache
//         *LRUCache
//         *
//         *
//         * //数据容量、接口提供、持久化、阻塞机制、线程安全 、ConcurrentHashMap
//         *
//         * //remove put size isEmpty() get(Object key)
//         *
//         * //数据结构 双端链表
//         *
//         * //过期时间 LFU
//         *
//         *package interview.alibaba.question.three;
//         *
//         *
//
//public class LocalCache {
// *
//}
// *
//         *
//         *
//         *package interview.alibaba.question.three;
//         *
//         *import java.util.HashMap;
//         *import java.util.Map;
//         *
//         *
//         *
//
//public class LRUCache {
// *
//         *
//    private DoubleLinkedNode head, tail;
// *
//    private int size;
// *
//    private int capacity;
// *
//    private Map<Integer, DoubleLinkedNode> cache = new HashMap<>();
// *
//         *
//
//    class DoubleLinkedNode {
// *
//        int key;
// *
//        int value;
// *
//        DoubleLinkedNode prev;
// *
//        DoubleLinkedNode next;
// *
//    }
// *
//         *     /**
//  *      * 向队列头部添加一个节点
//  *      *
//  *      * @param node
//  *      */
//         *
//
//    private void addFirst(DoubleLinkedNode node) {
// *node.prev = head;//1
// *node.next = head.next;//2
// *         //断开head指向后面节点，已经后面节点指向head节点
// *head.next.prev = node;//3
// *head.next = node;//4
// *}
// *
//         *
//         *     /**
//  *      * 删除一个节点
//  *      *
//  *      * @param node
//  *      */
//         *
//
//    private void removeNode(DoubleLinkedNode node) {
// *DoubleLinkedNode prev = node.prev;
// *DoubleLinkedNode next = node.next;
// *prev.next = next;//1
// *next.prev = prev;//2
// *}
// *
//         *
//         *     /**
//  *      * 将node节点移到队列的头部位置
//  *      *
//  *      * @param node
//  *      */
//         *
//
//    private void moveToHead(DoubleLinkedNode node) {
// *         //先删除该节点，再插入该节点
// *removeNode(node);
// *addFirst(node);
// *}
// *
//         *     /**
//  *      * 弹出队列尾部节点:tail节点的前驱节点即时最后一个节点，将其移除，返回
//  *      */
//         *
//
//    private DoubleLinkedNode popLast() {
// *DoubleLinkedNode last = tail.prev;
// *removeNode(last);
// *return last;
// *}
// *
//         *
//         *
//
//    public LRUCache(int capacity) {
// *this.size = 0;
// *this.capacity = capacity;
// *head = new DoubleLinkedNode();
// *tail = new DoubleLinkedNode();
// *head.next = tail;
// *tail.prev = head;
// *}
// *
//         *
//
//    public int get(int key) {
// *DoubleLinkedNode node = cache.get(key);
// *if (node == null) return -1;
// *moveToHead(node);
// *return node.value;
// *}
// *
//         *
//
//    public void put(int key, int value, Long expireTime) {
// *DoubleLinkedNode node = cache.get(key);
// *if (node == null) {
// *DoubleLinkedNode tmp = new DoubleLinkedNode();
// *tmp.key = key;
// *tmp.value = value;
// *cache.put(key, tmp);
// *addFirst(tmp);
// *++size;
// *if (size > capacity) {
// *DoubleLinkedNode last = popLast();
// *cache.remove(last.key);
// *--size;
// *}
// *} else {
// *node.value = value;
// *moveToHead(node);
// *}
// *
// *}
// *
//}
//
// *
// **
// */
