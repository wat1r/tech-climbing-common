package algorithm.design;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2020/2/8 21:54
 * Description
 */
public class LRUCache {

    private DoubleLinkedNode head, tail;
    private int size;
    private int capacity;
    private Map<Integer, DoubleLinkedNode> cache = new HashMap<>();

    class DoubleLinkedNode {
        int key;
        int value;
        DoubleLinkedNode prev;
        DoubleLinkedNode next;
    }

    /**
     * 向队列头部添加一个节点
     *
     * @param node
     */
    private void addFirst(DoubleLinkedNode node) {
        node.prev = head;//1
        node.next = head.next;//2
        //断开head指向后面节点，已经后面节点指向head节点
        head.next.prev = node;//3
        head.next = node;//4
    }


    /**
     * 删除一个节点
     *
     * @param node
     */
    private void removeNode(DoubleLinkedNode node) {
        DoubleLinkedNode prev = node.prev;
        DoubleLinkedNode next = node.next;
        prev.next = next;//1
        next.prev = prev;//2
    }


    /**
     * 将node节点移到队列的头部位置
     *
     * @param node
     */
    private void moveToHead(DoubleLinkedNode node) {
        //先删除该节点，再插入该节点
        removeNode(node);
        addFirst(node);
    }

    /**
     * 弹出队列尾部节点:tail节点的前驱节点即时最后一个节点，将其移除，返回
     */
    private DoubleLinkedNode popLast() {
        DoubleLinkedNode last = tail.prev;
        removeNode(last);
        return last;
    }


    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DoubleLinkedNode();
        tail = new DoubleLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DoubleLinkedNode node = cache.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DoubleLinkedNode node = cache.get(key);
        if (node == null) {
            DoubleLinkedNode tmp = new DoubleLinkedNode();
            tmp.key = key;
            tmp.value = value;
            cache.put(key, tmp);
            addFirst(tmp);
            ++size;
            if (size > capacity) {
                DoubleLinkedNode last = popLast();
                cache.remove(last.key);
                --size;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }

    }
}
