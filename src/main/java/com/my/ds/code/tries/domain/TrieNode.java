package com.my.ds.code.tries.domain;

public class TrieNode {
        private final TrieNode[] children;
        private boolean isEndOfWord;

        public TrieNode() {
            this.children = new TrieNode[26]; // a..z
            this.isEndOfWord = false;
        }

        // getters and setters
        public TrieNode getChild(int index) {
            return children[index];
        }

        public void setChild(int index, TrieNode node) {
            children[index] = node;
        }

        public boolean hasChild(int index) {
            return children[index] != null;
        }

        public boolean isEndOfWord() {
            return isEndOfWord;
        }

        public void setEndOfWord(boolean isEnd) {
            this.isEndOfWord = isEnd;
        }

        // convenience: iterate children
        public TrieNode[] getChildren() {
            return children;
        }
    }
