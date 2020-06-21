package com.mymusic.testbasic.arithmetic.tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/**
 * 搜索树构建和查询
 */
public class TreeNode {

    //字符
    public char label;
    //前缀
    public String prefix;
    //是否是单词
    public boolean isWord = false;
    //单词解释
    public String explanation = "";
    //子节点
    public HashMap<Character, TreeNode> sons;

    public TreeNode(char label, String prefix, boolean isWord, String explanation) {
        this.label = label;
        this.prefix = prefix;
        this.isWord = isWord;
        this.explanation = explanation;
        this.sons = new HashMap<Character, TreeNode>();
    }
}

class Word {
    String word = "";
    String explanation = "";
}

class SearchTree {

    private TreeNode root = null;

    public SearchTree() {
        this.root = new TreeNode('@', "", false, "");
    }

    /**
     * 构建搜索树
     *
     * @param word
     * @param explanation
     */
    public void build(String word, String explanation) {
        _build(word, explanation, 0, this.root);
    }

    private void _build(String word, String explanation, int index, TreeNode parent) {
        if (index == word.length()) {
            return;
        }
        // 处理当前字符串的第一个字母
        char c = word.toCharArray()[index];
        c = Character.toLowerCase(c);
        TreeNode found = null;
        // 如果字母结点已经存在于当前父结点之下，找出它。否则就新生成一个
        boolean isWord = false;
        String tmpExplain = "";
        if (parent.sons.containsKey(c)) {
            found = parent.sons.get(c);
            if (index == word.length() - 1) {
                found.explanation = explanation;
                found.isWord = true;
                ;
                parent.sons.put(c, found);
            }
        } else {
            String pre = word.substring(0, index).toLowerCase();
            if (index == word.length() - 1) {
                isWord = true;
                tmpExplain = explanation;
            }
            System.out.println(word + ": pre:" + pre + ": c:" + c);

            found = new TreeNode(c, pre, isWord, tmpExplain);
            parent.sons.put(c, found);
        }
        _build(word, explanation, index + 1, found);
    }

    public void printlnNode() {
        //使用栈模拟递归操作，优点：不用存储中间变量
        Stack<TreeNode> stack = new Stack<TreeNode>();
        // 创建堆栈对象，其中每个元素都是TreeNode类型
        stack.push(this.root);    // 初始化的时候，压入根结点

        while (!stack.isEmpty()) {  // 只要栈里还有结点，就继续下去

            TreeNode node = stack.pop();  // 弹出栈顶的结点

            if (node.sons.size() == 0) {
                // 已经到达叶子结点了，输出
                System.out.println("label: " + node.label + ", word: " + node.prefix + node.label);
            } else {
                // 非叶子结点，遍历它的每个子结点
                Iterator<Map.Entry<Character, TreeNode>> iter
                        = node.sons.entrySet().iterator();

                // 注意，这里使用了一个临时的栈stackTemp
                // 这样做是为了保持遍历的顺序，和递归遍历的顺序是一致的
                // 如果不要求一致，可以直接压入stack
                Stack<TreeNode> stackTemp = new Stack<TreeNode>();
                while (iter.hasNext()) {
                    stackTemp.push(iter.next().getValue());
                }
                while (!stackTemp.isEmpty()) {
                    stack.push(stackTemp.pop());
                }
            }
        }
    }

    /**
     * 搜索
     *
     * @param word
     * @return
     */
    public Word search(String word) {
        return _search(word, 0, this.root);
    }

    /**
     * 使用递归进行搜索
     *
     * @param word
     * @param index
     * @param parent
     * @return
     */
    private Word _search(String word, int index, TreeNode parent) {
        if (index == word.length()) {
            return new Word();
        }
        char c = Character.toLowerCase(word.charAt(index));
        TreeNode node = parent.sons.containsKey(c) ? parent.sons.get(c) : null;

        if (node == null) {
            return new Word();
        }
        if (node.isWord && index == word.length() - 1) {
            Word w = new Word();
            w.explanation = node.explanation;
            w.word = word;
            return w;
        }

        return _search(word, index + 1, node);
    }

    /**
     * 使用栈进行搜索
     * @param word
     * @return
     */
    public Word searchWithStask(String word) {
        return _searchWithStack(word, 0, this.root);
    }

    /**
     * 使用栈进行搜索
     *
     * @param word
     * @param index
     * @param root
     * @return
     */
    private Word _searchWithStack(String word, int index, TreeNode root) {
        Word w = new Word();
        // 使用栈来实现深度优先搜索
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.sons.size() == 0) {
                return w;
            }
            if (!node.sons.containsKey(word.charAt(index))) {
                return w;
            }
            TreeNode found = node.sons.get(word.charAt(index));
            //找到单词
            if (found.isWord && index == word.length() - 1) {
                w.explanation = found.explanation;
                w.word = word;
                break;
            }
            stack.push(found);
            index++;
        }
        return w;
    }
}

class Test {
    public static void main(String[] args) {
        SearchTree tree = new SearchTree();
        String[] words = {"Symfony", "sys", "system", "Components", "Framework", "Frame"};
        for (String w : words) {
            tree.build(w, "explain：" + w);
        }
        System.out.println("------打印搜索树------");
        tree.printlnNode();
        System.out.println("------打印搜索树------");

        Word w = tree.search("system");
        System.out.println("搜索system：" + w.word + ", 解释：" + w.explanation);
        w = tree.search("Fram1e");
        System.out.println("搜索Fram1e：" + w.word + ", 解释：" + w.explanation);
        w = tree.searchWithStask("system");
        System.out.println("搜索system：" + w.word + ", 解释：" + w.explanation);
    }
}
