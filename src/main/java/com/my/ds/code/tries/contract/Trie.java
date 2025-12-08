package com.my.ds.code.tries.contract;

import com.my.ds.code.tries.domain.TrieNode;

import java.util.List;

public interface Trie {
    void insert(String word);

    boolean search(String word);

    boolean startsWith(String prefix);

    boolean delete(String word);

    int idx(char c);

    // recursive delete helper; returns true if parent should delete the link to this node
    default boolean deleteRecursive(TrieNode current, String word, int depth) {
        if (current == null) return false;

        if (depth == word.length()) {
            if (!current.isEndOfWord()) {
                // word not present
                return false;
            }
            // unmark end of word
            current.setEndOfWord(false);
            // if current has no children, parent can delete reference
            return isEmptyNode(current);
        }

        int i = idx(word.charAt(depth));
        TrieNode child = current.getChild(i);
        if (child == null) return false; // word not present

        boolean shouldDeleteChild = deleteRecursive(child, word, depth + 1);

        if (shouldDeleteChild) {
            current.setChild(i, null);
            // if current is not end of another word and has no other children, parent can delete it too
            return !current.isEndOfWord() && isEmptyNode(current);
        }
        return false;
    }

    default boolean isEmptyNode(TrieNode node) {
        for (TrieNode ch : node.getChildren()) if (ch != null) return false;
        return true;
    }

    int countWordsWithPrefix(String prefix);

    default int countWordsFrom(TrieNode node) {
        int count = node.isEndOfWord() ? 1 : 0;
        for (TrieNode child : node.getChildren()) {
            if (child != null) count += countWordsFrom(child);
        }
        return count;
    }

    List<String> listWordsWithPrefix(String prefix);

    default void collect(TrieNode node, StringBuilder prefix, List<String> out) {
        if (node.isEndOfWord()) out.add(prefix.toString());
        for (int i = 0; i < 26; i++) {
            TrieNode child = node.getChild(i);
            if (child != null) {
                prefix.append((char) ('a' + i));
                collect(child, prefix, out);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }
}
