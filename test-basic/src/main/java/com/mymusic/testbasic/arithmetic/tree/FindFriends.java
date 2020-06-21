package com.mymusic.testbasic.arithmetic.tree;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 社交系统中，找出某个用户指定度数的好友
 * Node 标识用户
 * Node[]数组表示所有用户列表， 下标为用户ID， 长度为用户数量
 * 条件： 1. 用户好友不能有重复,使用hashset进行存储
 */
public class FindFriends {
    public static void main(String[] args) {
        //生成20个用户， 每个用户有2-5个好友
        int size = 20;
        People people = new People(size);

        //随机生成好友
        for (Node node : people.user_nodes) {
            System.out.println("---------------");
            int friends_count = (int) (Math.random() * 5 + 2);
            System.out.println("用户：" + node.user_id + ", 好友数量：" + friends_count);
            int[] friends_list = new int[friends_count];
            for (int i = 0; i < friends_count; i++) {
                int friends_id = (int) (Math.random() * size + 1);
                //判断是否有重复的好友
                boolean is_repeat = false;
                for (int friend : friends_list) {
                    if (friend == friends_id) {
                        is_repeat = true;
                        break;
                    }
                }
                if (is_repeat) {
                    continue;
                }
                friends_list[i] = friends_id;
                node.friends.add(friends_id);
            }
            System.out.println("用户：" + node.user_id + "好友：" + Arrays.toString(node.friends.toArray()));
            System.out.println("---------------");
        }

        //查找二度好友
        people.bfs(5);
    }
}

class People {

    public Node[] user_nodes = null;

    public People(int size) {
        this.user_nodes = new Node[size];
        for (int i = 1; i <= 20; i++) {
            add(new Node(i));
        }
    }

    public void add(Node node) {
        this.user_nodes[node.user_id - 1] = node;
    }

    /**
     * 找出互为二度好友的用户
     *
     * @param user_id
     */
    public void bfs(int user_id) {
        System.out.println("----------查找用户: " + user_id + " 的好友-------------");
        //克隆一份数据
        Node[] copy = this.user_nodes.clone();
        Node node = copy[user_id - 1];
        if (node == null) {
            System.out.println("用户：" + user_id + "不存在");
            System.exit(0);
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(user_id);
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, HashSet<Integer>> relation = new HashMap<>();
        int degree = 0;
        HashSet<Integer> tmp = null;
        while (!queue.isEmpty()) {
            int current_user_id = queue.poll();
            Node friends = copy[current_user_id - 1];
            for (int friend_id : friends.friends) {
                if (visited.contains(friend_id)) {
                    continue;
                }
                if (friend_id == current_user_id) {
                    //好友是待搜索人自己，跳过
                    continue;
                }
                degree = friends.degree + 1;
                tmp = new HashSet<>();
                if (relation.containsKey(degree)) {
                    tmp = relation.get(degree);
                }
                tmp.add(friend_id);
                visited.add(friend_id);

                relation.put(degree, tmp);
                copy[friend_id - 1].degree = friends.degree + 1;
                queue.offer(friend_id);
            }
        }

        for (Map.Entry<Integer, HashSet<Integer>> entry : relation.entrySet()) {
            int key = entry.getKey();
            HashSet<Integer> friends = entry.getValue();
            System.out.println(key + "度好友： " + Arrays.toString(friends.toArray()));
        }
    }
}

class Node {
    //用户ID
    public int user_id;
    //好友
    public HashSet<Integer> friends = null;
    //用于存放和给定的用户结点，是几度好友
    public int degree;

    public Node(int user_id) {
        this.user_id = user_id;
        this.friends = new HashSet<>();
        this.degree = 0;
    }
}
