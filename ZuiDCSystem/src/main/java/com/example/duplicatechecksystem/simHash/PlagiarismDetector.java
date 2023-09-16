package com.example.duplicatechecksystem.simHash;

import java.util.*;

public class PlagiarismDetector {
    private static final int HASH_SIZE = 64;
    private static final double JACCARD_THRESHOLD = 0.7; // 阈值，用于判定相似性
    private static Set<String> JAVA_KEYWORDS = new HashSet<>(Arrays.asList(
            "int", "long", "double", "float", "if", "else", "for", "while",
            "break", "continue", "return", "true", "false", "null"
    ));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入第一个代码片段:");
        String code1 = "import java.Ttil.Scanner;\n" +
                "\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        Scanner sc = new Scanner(System.in);\n" +
                "        int n = sc.nextInt();\n" +
                "        int temp = 0;\n" +
                "        int arr[] = new int[n];\n" +
                "        for(int i = 0;i<n;i++){\n" +
                "            arr[i] = sc.nextInt();\n" +
                "        }\n" +
                "        for(int i=0;i<n;i++){\n" +
                "            for(int j = 0;j<n;j++){\n" +
                "                if((j!=i)&&((arr[j]+arr[i])%60==0)){\n" +
                "                    temp++;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        System.out.println(temp/2);\n" +
                "    }\n" +
                "}";

        System.out.println("请输入第二个代码片段:");
            String code2 = "public class SimilarCode2 {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        int y = 20;\n" +
                    "        System.out.println(\"Hello, World!\");\n" +
                    "    }\n" +
                    "}\n";

        long simHash1 = calculateSimHash(code1);
        long simHash2 = calculateSimHash(code2);

        double jaccardSimilarity = calculateJaccardSimilarity(simHash1, simHash2);

        System.out.println("SimHash相似度: " + jaccardSimilarity);

        if (jaccardSimilarity >= JACCARD_THRESHOLD) {
            System.out.println("代码可能存在抄袭！");
        } else {
            System.out.println("代码不太可能存在抄袭。");
        }

        scanner.close();
    }

    // 计算SimHash值
    private static long calculateSimHash(String code) {
        Map<String, Integer> termFrequency = new HashMap<>();
        String[] terms = code.split("\\s+");

        for (String term : terms) {
            term = term.trim().toLowerCase(); // 转换为小写
            if (!term.isEmpty() && !JAVA_KEYWORDS.contains(term)) { // 过滤关键字
                termFrequency.put(term, termFrequency.getOrDefault(term, 0) + 1);
            }
        }

        long[] simHash = new long[HASH_SIZE];

        for (Map.Entry<String, Integer> entry : termFrequency.entrySet()) {
            String term = entry.getKey();
            int frequency = entry.getValue();

            // 计算词的哈希并合并
            long termHash = term.hashCode();
            for (int i = 0; i < HASH_SIZE; i++) {
                long bitMask = 1L << i;
                if ((termHash & bitMask) != 0) {
                    simHash[i] += frequency;
                } else {
                    simHash[i] -= frequency;
                }
            }
        }

        long resultHash = 0;
        for (int i = 0; i < HASH_SIZE; i++) {
            if (simHash[i] > 0) {
                resultHash |= (1L << i);
            }
        }

        return resultHash;
    }

    // 计算Jaccard相似性
    private static double calculateJaccardSimilarity(long hash1, long hash2) {
        Set<Long> set1 = new HashSet<>();
        Set<Long> set2 = new HashSet<>();

        for (int i = 0; i < HASH_SIZE; i++) {
            if (((hash1 >> i) & 1) == 1) {
                set1.add((long) i);
            }
            if (((hash2 >> i) & 1) == 1) {
                set2.add((long) i);
            }
        }

        Set<Long> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<Long> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / union.size();
    }
}
