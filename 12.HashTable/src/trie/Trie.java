package trie;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Trie<T> {

    private final TrieNode<T> rootNode;

    public Trie() {
        rootNode = new TrieNode<>();
    }

    public void insert(String key, T value) {
        rootNode.insert(key, -1, value);
    }

    public T get(String key) {
        return rootNode.get(key);
    }

    public boolean search(String word) {
        return nonNull(rootNode.search(word));
    }

    public boolean startsWith(String prefix) {
        return rootNode.startWith(prefix);
    }

    public class TrieNode<T> {

        private final int ABC = 128;
        private T storage;

        private TrieNode<T>[] arrayOfNodes;

        public TrieNode() {
            this.arrayOfNodes = new TrieNode[ABC];
        }

        public void insert(String word, int currentIndex, T objectToStore) {
            if (currentIndex == word.length() - 1) {
                storage = objectToStore;
                return;
            }
            currentIndex++;
            if (isNull(arrayOfNodes[ABC - word.charAt(currentIndex)])) {
                arrayOfNodes[ABC - word.charAt(currentIndex)] = new TrieNode<>();
            }
            arrayOfNodes[ABC - word.charAt(currentIndex)].insert(word, currentIndex, objectToStore);
        }

        public boolean search(String word) {
            if (isNull(arrayOfNodes[ABC - word.charAt(0)])) {
                return false;
            }
            TrieNode<T> node = arrayOfNodes[ABC - word.charAt(0)].findNode(word, 0);

            if (nonNull(node) && nonNull(node.getStorage())) {
                return true;
            }
            return false;
        }

        public boolean startWith(String word) {
            if (isNull(arrayOfNodes[ABC - word.charAt(0)])) {
                return false;
            }
            TrieNode<T> node = arrayOfNodes[ABC - word.charAt(0)].findNode(word, 0);
            return nonNull(node);
        }

        public T get(String word) {
            if (isNull(arrayOfNodes[ABC - word.charAt(0)])) {
                return null;
            }
            TrieNode<T> node = arrayOfNodes[ABC - word.charAt(0)].findNode(word, 0);
            if (nonNull(node) && nonNull(node.getStorage())) {
                return node.getStorage();
            }
            return null;
        }

        public TrieNode<T> findNode(String word, int currentIndex) {
            if (currentIndex == word.length() - 1) {
                return this;
            }
            currentIndex++;
            if (isNull(arrayOfNodes[ABC - word.charAt(currentIndex)])) {
                return null;
            }
            return arrayOfNodes[ABC - word.charAt(currentIndex)].findNode(word, currentIndex);
        }

        public T getStorage() {
            return storage;
        }
    }
}
