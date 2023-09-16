package com.example.duplicatechecksystem.simHash;

import java.math.BigInteger;

public class SimHash2 {
    private static final int HASH_SIZE = 64;
    private static final double SIMILARITY_THRESHOLD = 0.8; // 相似度阈值

    public static BigInteger computeSimHash(String text) {
        int[] featureVector = new int[HASH_SIZE];
        String[] words = text.split("\\s+");
        for (String word : words) {
            BigInteger wordHash = hashWord(word);
            for (int i = 0; i < HASH_SIZE; i++) {
                BigInteger bitmask = BigInteger.ONE.shiftLeft(i);
                if (wordHash.and(bitmask).compareTo(BigInteger.ZERO) != 0) {
                    featureVector[i] += 1;
                } else {
                    featureVector[i] -= 1;
                }
            }
        }

        BigInteger simHash = BigInteger.ZERO;
        for (int i = 0; i < HASH_SIZE; i++) {
            if (featureVector[i] > 0) {
                simHash = simHash.add(BigInteger.ONE.shiftLeft(i));
            }
        }

        return simHash;
    }

    private static BigInteger hashWord(String word) {
        return new BigInteger(word.getBytes());
    }

    public static int hammingDistance(BigInteger hash1, BigInteger hash2) {
        return hash1.xor(hash2).bitCount();
    }

    public static double similarity(BigInteger hash1, BigInteger hash2) {
        int distance = hammingDistance(hash1, hash2);
        return 1.0 - (double) distance / HASH_SIZE;
    }

    public static void findDuplicateText(String text1, String text2) {
        BigInteger simHash1 = computeSimHash(text1);
        BigInteger simHash2 = computeSimHash(text2);

        double similarity = similarity(simHash1, simHash2);

        System.out.println("SimHash1: " + simHash1.toString(16));
        System.out.println("SimHash2: " + simHash2.toString(16));
        System.out.println("Similarity: " + similarity);

        if (similarity > SIMILARITY_THRESHOLD) {
            findAndPrintDuplicateWords(text1, text2);
        }
    }

    private static void findAndPrintDuplicateWords(String text1, String text2) {
        String[] words1 = text1.split("\\s+");
        String[] words2 = text2.split("\\s+");

        for (int i = 0; i < words1.length; i++) {
            if (i < words2.length && words1[i].equals(words2[i])) {
                System.out.println("Duplicate word: " + words1[i] + " at position " + i);
            }
        }

    }

    public static void main(String[] args) {
        String text1 = "This is a sample text for testing.";
        String text2 = "This is another sample text for testing.";

        findDuplicateText(text1, text2);
    }
}
