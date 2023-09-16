package com.example.duplicatechecksystem.simHash;

import java.util.*;

public class CosineSimilarityCalculator {
    public static void main(String[] args) {
        String code1 = "public class Example1 { " +
                "public static void main(String[] args) { " +
                "System.out.println(\"Hello, World!\"); " +
                "} " +
                "}";

        String code2 = "public class Example2 { " +
                "public static void main(String[] args) { " +
                "System.out.println(\"Hello, World!\"); " +
                "} " +
                "}";

        double cosineSimilarity = calculateCosineSimilarity(code1, code2);

        System.out.println("余弦相似度: " + cosineSimilarity);
    }

    // 计算余弦相似性
    private static double calculateCosineSimilarity(String code1, String code2) {
        List<String> vector1 = tokenize(code1);
        List<String> vector2 = tokenize(code2);

        // 创建两个向量的词频向量
        Map<String, Integer> frequencyVector1 = createFrequencyVector(vector1);
        Map<String, Integer> frequencyVector2 = createFrequencyVector(vector2);

        // 计算向量点积
        double dotProduct = calculateDotProduct(frequencyVector1, frequencyVector2);

        // 计算向量的模
        double magnitude1 = calculateMagnitude(frequencyVector1);
        double magnitude2 = calculateMagnitude(frequencyVector2);

        // 计算余弦相似度
        if (magnitude1 == 0 || magnitude2 == 0) {
            return 0.0; // 避免除以零错误
        } else {
            return dotProduct / (magnitude1 * magnitude2);
        }
    }

    // 分词函数
    private static List<String> tokenize(String code) {
        // 简单地将代码字符串按空格分割为单词
        return Arrays.asList(code.split("\\s+"));
    }

    // 创建词频向量
    private static Map<String, Integer> createFrequencyVector(List<String> tokens) {
        Map<String, Integer> frequencyVector = new HashMap<>();

        for (String token : tokens) {
            frequencyVector.put(token, frequencyVector.getOrDefault(token, 0) + 1);
        }

        return frequencyVector;
    }

    // 计算向量点积
    private static double calculateDotProduct(Map<String, Integer> vector1, Map<String, Integer> vector2) {
        double dotProduct = 0.0;

        for (String key : vector1.keySet()) {
            if (vector2.containsKey(key)) {
                dotProduct += vector1.get(key) * vector2.get(key);
            }
        }

        return dotProduct;
    }

    // 计算向量的模
    private static double calculateMagnitude(Map<String, Integer> vector) {
        double magnitudeSquared = 0.0;

        for (int frequency : vector.values()) {
            magnitudeSquared += Math.pow(frequency, 2);
        }

        return Math.sqrt(magnitudeSquared);
    }
}
