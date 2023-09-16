package com.example.duplicatechecksystem.test;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

public class SimilarTextHighlighter {
    public static void main(String[] args) {
        String text1 = "这是一段示例文本，用于测试查重。";
        String text2 = "这是另一段示例文本，用于测试查重。";

        highlightSimilarWords(text1, text2);
    }

    private static void highlightSimilarWords(String text1, String text2) {
        List<Term> termList1 = HanLP.segment(text1);
        List<Term> termList2 = HanLP.segment(text2);

        System.out.println("相似的词语或短语：");
        for (Term term1 : termList1) {
            String word1 = term1.word;
            for (Term term2 : termList2) {
                String word2 = term2.word;
                // 这里可以根据需要定义相似度的判断条件，例如可以使用编辑距离或其他方法来判断相似性
                if (isSimilar(word1, word2)) {
                    System.out.println("文本1：" + word1 + "，文本2：" + word2);
                }
            }
        }
    }

    private static boolean isSimilar(String word1, String word2) {
        // 这里可以根据需要定义相似性的判断条件，例如可以使用编辑距离或其他方法来判断相似性
        // 这个示例中简单地判断词语是否相同
        return word1.equals(word2);
    }
}
