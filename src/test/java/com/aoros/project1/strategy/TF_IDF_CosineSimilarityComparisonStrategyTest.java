package com.aoros.project1.strategy;

import com.aoros.project1.QuestionCompareLine;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TF_IDF_CosineSimilarityComparisonStrategyTest {

    private final List<QuestionCompareLine> questionData = new ArrayList<>();

    @Test
    public void testAreQuestionsTheSame() {
        // Given 4 questions each with the word 'the' multiple times
        String question1 = "the the the the";
        String question2 = "This sentence has the word 'the' in it, twice.";
        questionData.add(new QuestionCompareLine(1, 1, 2, question1, question2));
        String question3 = "the the the in";
        String question4 = "This sentence also has the word 'the' in it, twice.";
        questionData.add(new QuestionCompareLine(2, 3, 4, question3, question4));
        TF_IDF_CosineSimilarityComparisonStrategy instance = new TF_IDF_CosineSimilarityComparisonStrategy(questionData);

        // When the map is retrieved
        Map<String, Integer> map = instance.getWordToAppearanceCountMap();

        // the count in the map is 4, which represents the four sentences.
        assertNull(map.get("Saturday"));
        assertEquals(new Integer(1), map.get("also"));
        assertEquals(new Integer(2), map.get("twice."));
        assertEquals(new Integer(3), map.get("in"));
        assertEquals(new Integer(4), map.get("the"));
        for (Integer count : map.values()) {
            assertTrue(count <= 4);
        }
    }
}
