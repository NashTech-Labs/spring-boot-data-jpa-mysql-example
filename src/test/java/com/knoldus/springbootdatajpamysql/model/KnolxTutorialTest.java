package com.knoldus.springbootdatajpamysql.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class KnolxTutorialTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link KnolxTutorial#KnolxTutorial()}}
     * </ul>
     */
    @Test
    void testConstructor() {
        KnolxTutorial actualKnolxTutorial = new KnolxTutorial();
        actualKnolxTutorial.setDescription("The characteristics of someone or something");
        actualKnolxTutorial.setPublished(true);
        actualKnolxTutorial.setT_id(1L);
        actualKnolxTutorial.setTitle("Dr");
        String actualToStringResult = actualKnolxTutorial.toString();
        assertEquals("The characteristics of someone or something", actualKnolxTutorial.getDescription());
        assertEquals(0L, actualKnolxTutorial.getId());
        assertEquals(1L, actualKnolxTutorial.getT_id().longValue());
        assertEquals("Dr", actualKnolxTutorial.getTitle());
        assertTrue(actualKnolxTutorial.isPublished());
        assertEquals("Tutorial [id=0, title=Dr, desc=The characteristics of someone or something, published=true]",
                actualToStringResult);
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link KnolxTutorial#KnolxTutorial(String, String, boolean)}
     * </ul>
     */
    @Test
    void testConstructor2() {
        KnolxTutorial actualKnolxTutorial = new KnolxTutorial("Dr", "The characteristics of someone or something", true);
        actualKnolxTutorial.setDescription("The characteristics of someone or something");
        actualKnolxTutorial.setPublished(true);
        actualKnolxTutorial.setT_id(1L);
        actualKnolxTutorial.setTitle("Dr");
        String actualToStringResult = actualKnolxTutorial.toString();
        assertEquals("The characteristics of someone or something", actualKnolxTutorial.getDescription());
        assertEquals(0L, actualKnolxTutorial.getId());
        assertEquals(1L, actualKnolxTutorial.getT_id().longValue());
        assertEquals("Dr", actualKnolxTutorial.getTitle());
        assertTrue(actualKnolxTutorial.isPublished());
        assertEquals("Tutorial [id=0, title=Dr, desc=The characteristics of someone or something, published=true]",
                actualToStringResult);
    }
}

