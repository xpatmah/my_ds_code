package com.my.ds.code.tries.impl;

import com.my.ds.code.tries.contract.Trie;
import com.my.ds.code.tries.domain.TrieNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Trie (Prefix Tree) implementation for lowercase 'a'..'z'.
 *
 * Note:
 * - Time complexity for insert/search/startsWith: O(L) where L = length of word/prefix.
 * - Memory: O(ALPHABET_SIZE * nodes) for array-based children.
 */
public class TrieImpl implements Trie {

    // --------------------------
    // Trie fields & constructor
    // --------------------------
    private final TrieNode root;

    public TrieImpl() {
        root = new TrieNode();
    }

    // --------------------------
    // Helpers
    // --------------------------
    public int idx(char c) {
        return c - 'a';
    }

    private void validateWord(String word) {
        if (word == null) throw new IllegalArgumentException("word must not be null");
        // optional: enforce lower-case a..z
        for (char c : word.toCharArray()) {
            if (c < 'a' || c > 'z')
                throw new IllegalArgumentException("Only lowercase a-z supported: found '" + c + "'");
        }
    }

    // --------------------------
    // Public operations
    // --------------------------

    /** Inserts a word into the trie. */
    @Override
    public void insert(String word) {
        validateWord(word);
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int i = idx(ch);
            if (!node.hasChild(i)) node.setChild(i, new TrieNode());
            node = node.getChild(i);
        }
        node.setEndOfWord(true);
    }

    /** Returns true if the word is in the trie (exact match). */
    @Override
    public boolean search(String word) {
        validateWord(word);
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int i = idx(ch);
            if (!node.hasChild(i)) return false;
            node = node.getChild(i);
        }
        return node.isEndOfWord();
    }

    /** Returns true if there is any word in the trie that starts with the given prefix. */
    @Override
    public boolean startsWith(String prefix) {
        validateWord(prefix);
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            int i = idx(ch);
            if (!node.hasChild(i)) return false;
            node = node.getChild(i);
        }
        return true;
    }

    /** Deletes a word from the trie. Returns true if the word was present and deleted. */
    @Override
    public boolean delete(String word) {
        validateWord(word);
        return deleteRecursive(root, word, 0);
    }

    /** Count number of words in trie that have the given prefix. */
    @Override
    public int countWordsWithPrefix(String prefix) {
        validateWord(prefix);
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            int i = idx(ch);
            if (!node.hasChild(i)) return 0;
            node = node.getChild(i);
        }
        return countWordsFrom(node);
    }

    /** List all words in the trie that start with the given prefix. */
    @Override
    public List<String> listWordsWithPrefix(String prefix) {
        validateWord(prefix);
        List<String> results = new ArrayList<>();
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            int i = idx(ch);
            if (!node.hasChild(i)) return results; // empty
            node = node.getChild(i);
        }
        collect(node, new StringBuilder(prefix), results);
        return results;
    }

}