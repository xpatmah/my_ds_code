package com.my.ds.code.tries.demo;

import com.my.ds.code.tries.impl.TrieImpl;

public class TriesDemo {

    // --------------------------
    // Demo / quick test
    // --------------------------
    public static void main(String[] args) {
        TrieImpl trie = new TrieImpl();

        String[] words = { "apple", "app", "apply", "apt", "bat", "batch", "baton", "banana" };
        for (String w : words) trie.insert(w);

        System.out.println("search(\"app\") = " + trie.search("app"));         // true
        System.out.println("search(\"apple\") = " + trie.search("apple"));     // true
        System.out.println("search(\"appl\") = " + trie.search("appl"));       // false
        System.out.println("startsWith(\"ap\") = " + trie.startsWith("ap"));   // true
        System.out.println("startsWith(\"ba\") = " + trie.startsWith("ba"));   // true
        System.out.println("startsWith(\"cat\") = " + trie.startsWith("cat")); // false

        System.out.println("\nWords with prefix \"ap\": " + trie.listWordsWithPrefix("ap"));
        System.out.println("Count prefix \"ba\": " + trie.countWordsWithPrefix("ba"));

        System.out.println("\nDeleting 'baton' ... " + trie.delete("baton"));
        System.out.println("startsWith(\"bat\") = " + trie.startsWith("bat"));
        System.out.println("Words with prefix 'bat': " + trie.listWordsWithPrefix("bat"));

        System.out.println("\nDeleting 'batch' ... " + trie.delete("batch"));
        System.out.println("Words with prefix 'bat': " + trie.listWordsWithPrefix("bat"));

        System.out.println("\nDeleting 'bat' ... " + trie.delete("bat"));
        System.out.println("startsWith('bat') = " + trie.startsWith("bat"));
    }

}
