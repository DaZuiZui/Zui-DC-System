package com.example.duplicatechecksystem.simHash;

import java.util.*;

public class SimHashPlagiarismDetector {
    private static final int HASH_SIZE = 64; // SimHash位数
    private static final int THRESHOLD = 3; // 阈值，用于判定是否为抄袭

    // 白名单，包含允许的关键字
    private static final Set<String> WHITE_LIST = new HashSet<>();

    private static final Set<String> JAVA_KEYWORDS = new HashSet<>(Arrays.asList(
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
            "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
            "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
            "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
            "volatile", "while","Main"
    ));

    public static void main(String[] args) {
        String studentCode1 = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        String firstName = \"John\";\n" +
                "        String lastName = \"Doe\";\n" +
                "        String fullName = firstName + \" \" + lastName;\n" +
                "        System.out.println(\"Full name: \" + fullName);\n" +
                "    }\n" +
                "}\n";
        String studentCode2 = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        String city = \"New York\";\n" +
                "        String state = \"NY\";\n" +
                "        String location = \"You are in \" + city + \", \" + state;\n" +
                "        System.out.println(location);\n" +
                "    }\n" +
                "}\n";

        long simHash1 = calculateSimHash(studentCode1);
        long simHash2 = calculateSimHash(studentCode2);

        int similarity = hammingDistance(simHash1, simHash2);

        double coverage = 1.0 - (double) similarity / HASH_SIZE;
        System.out.println("代码相似度: " + coverage);

        if (similarity <= THRESHOLD) {
            System.out.println("代码可能存在抄袭！");
        } else {
            System.out.println("代码不太可能存在抄袭。");
        }
    }

    // 计算SimHash值
    private static long calculateSimHash(String code) {
        Map<String, Integer> termFrequency = new HashMap<>();
        String[] terms = code.split("\\s+");

        for (String term : terms) {
            term = term.trim();
            if (!term.isEmpty() && !JAVA_KEYWORDS.contains(term.toLowerCase())) { // 过滤关键字
                termFrequency.put(term, termFrequency.getOrDefault(term, 0) + 1);
            }
        }

        long[] simHash = new long[HASH_SIZE];

        for (Map.Entry<String, Integer> entry : termFrequency.entrySet()) {
            String term = entry.getKey();
            int frequency = entry.getValue();

            // 计算哈希值并合并
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

    // 计算汉明距离
    private static int hammingDistance(long hash1, long hash2) {
        long xor = hash1 ^ hash2;
        int distance = 0;
        while (xor != 0) {
            distance++;
            xor &= (xor - 1);
        }
        return distance;
    }
}
