package com.knoldus.springbootdatajpamysql.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.knoldus.springbootdatajpamysql.model.KnolxTutorial;
import com.knoldus.springbootdatajpamysql.repository.TutorialRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {KnolxTutorialController.class})
@ExtendWith(SpringExtension.class)
class KnolxTutorialControllerTest {
    @Autowired
    private KnolxTutorialController knolxTutorialController;

    @MockBean
    private TutorialRepository tutorialRepository;

    /**
     * Method under test: {@link KnolxTutorialController#getAllTutorials(String)}
     */
    @Test
    void testGetAllTutorials() {
        when(tutorialRepository.findByTitleContaining((String) any())).thenReturn(new ArrayList<>());
        ResponseEntity<List<KnolxTutorial>> actualAllTutorials = knolxTutorialController.getAllTutorials("Dr");
        assertNull(actualAllTutorials.getBody());
        assertEquals(HttpStatus.NO_CONTENT, actualAllTutorials.getStatusCode());
        assertTrue(actualAllTutorials.getHeaders().isEmpty());
        verify(tutorialRepository).findByTitleContaining((String) any());
    }

    /**
     * Method under test: {@link KnolxTutorialController#getAllTutorials(String)}
     */
    @Test
    void testGetAllTutorials2() {
        KnolxTutorial knolxTutorial = new KnolxTutorial();
        knolxTutorial.setDescription("The characteristics of someone or something");
        knolxTutorial.setPublished(true);
        knolxTutorial.setT_id(1L);
        knolxTutorial.setTitle("Dr");

        ArrayList<KnolxTutorial> knolxTutorialList = new ArrayList<>();
        knolxTutorialList.add(knolxTutorial);
        when(tutorialRepository.findByTitleContaining((String) any())).thenReturn(knolxTutorialList);
        ResponseEntity<List<KnolxTutorial>> actualAllTutorials = knolxTutorialController.getAllTutorials("Dr");
        List<KnolxTutorial> body = actualAllTutorials.getBody();
        assertEquals(knolxTutorialList, body);
        assertEquals(1, body.size());
        assertTrue(actualAllTutorials.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAllTutorials.getStatusCode());
        verify(tutorialRepository).findByTitleContaining((String) any());
    }

    /**
     * Method under test: {@link KnolxTutorialController#getAllTutorials(String)}
     */
    @Test
    void testGetAllTutorials3() {
        when(tutorialRepository.findAll()).thenReturn(new ArrayList<>());
        when(tutorialRepository.findByTitleContaining((String) any())).thenReturn(new ArrayList<>());
        ResponseEntity<List<KnolxTutorial>> actualAllTutorials = knolxTutorialController.getAllTutorials(null);
        assertNull(actualAllTutorials.getBody());
        assertEquals(HttpStatus.NO_CONTENT, actualAllTutorials.getStatusCode());
        assertTrue(actualAllTutorials.getHeaders().isEmpty());
        verify(tutorialRepository).findAll();
    }

    /**
     * Method under test: {@link KnolxTutorialController#getTutorialById(long)}
     */
    @Test
    void testGetTutorialById() {
        KnolxTutorial knolxTutorial = new KnolxTutorial();
        knolxTutorial.setDescription("The characteristics of someone or something");
        knolxTutorial.setPublished(true);
        knolxTutorial.setT_id(1L);
        knolxTutorial.setTitle("Dr");
        Optional<KnolxTutorial> ofResult = Optional.of(knolxTutorial);
        when(tutorialRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseEntity<KnolxTutorial> actualTutorialById = knolxTutorialController.getTutorialById(123L);
        assertTrue(actualTutorialById.hasBody());
        assertTrue(actualTutorialById.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualTutorialById.getStatusCode());
        verify(tutorialRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link KnolxTutorialController#getTutorialById(long)}
     */
    @Test
    void testGetTutorialById2() {
        when(tutorialRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseEntity<KnolxTutorial> actualTutorialById = knolxTutorialController.getTutorialById(123L);
        assertNull(actualTutorialById.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualTutorialById.getStatusCode());
        assertTrue(actualTutorialById.getHeaders().isEmpty());
        verify(tutorialRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link KnolxTutorialController#createTutorial(KnolxTutorial)}
     */
    @Test
    void testCreateTutorial() {
        KnolxTutorial knolxTutorial = new KnolxTutorial();
        knolxTutorial.setDescription("The characteristics of someone or something");
        knolxTutorial.setPublished(true);
        knolxTutorial.setT_id(1L);
        knolxTutorial.setTitle("Dr");
        when(tutorialRepository.save((KnolxTutorial) any())).thenReturn(knolxTutorial);

        KnolxTutorial knolxTutorial1 = new KnolxTutorial();
        knolxTutorial1.setDescription("The characteristics of someone or something");
        knolxTutorial1.setPublished(true);
        knolxTutorial1.setT_id(1L);
        knolxTutorial1.setTitle("Dr");
        ResponseEntity<KnolxTutorial> actualCreateTutorialResult = knolxTutorialController.createTutorial(knolxTutorial1);
        assertTrue(actualCreateTutorialResult.hasBody());
        assertTrue(actualCreateTutorialResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.CREATED, actualCreateTutorialResult.getStatusCode());
        verify(tutorialRepository).save((KnolxTutorial) any());
    }

    /**
     * Method under test: {@link KnolxTutorialController#updateTutorial(long, KnolxTutorial)}
     */
    @Test
    void testUpdateTutorial() {
        KnolxTutorial knolxTutorial = new KnolxTutorial();
        knolxTutorial.setDescription("The characteristics of someone or something");
        knolxTutorial.setPublished(true);
        knolxTutorial.setT_id(1L);
        knolxTutorial.setTitle("Dr");
        Optional<KnolxTutorial> ofResult = Optional.of(knolxTutorial);

        KnolxTutorial knolxTutorial1 = new KnolxTutorial();
        knolxTutorial1.setDescription("The characteristics of someone or something");
        knolxTutorial1.setPublished(true);
        knolxTutorial1.setT_id(1L);
        knolxTutorial1.setTitle("Dr");
        when(tutorialRepository.save((KnolxTutorial) any())).thenReturn(knolxTutorial1);
        when(tutorialRepository.findById((Long) any())).thenReturn(ofResult);

        KnolxTutorial knolxTutorial2 = new KnolxTutorial();
        knolxTutorial2.setDescription("The characteristics of someone or something");
        knolxTutorial2.setPublished(true);
        knolxTutorial2.setT_id(1L);
        knolxTutorial2.setTitle("Dr");
        ResponseEntity<KnolxTutorial> actualUpdateTutorialResult = knolxTutorialController.updateTutorial(123L,
                knolxTutorial2);
        assertTrue(actualUpdateTutorialResult.hasBody());
        assertTrue(actualUpdateTutorialResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUpdateTutorialResult.getStatusCode());
        verify(tutorialRepository).save((KnolxTutorial) any());
        verify(tutorialRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link KnolxTutorialController#updateTutorial(long, KnolxTutorial)}
     */
    @Test
    void testUpdateTutorial2() {
        KnolxTutorial knolxTutorial = new KnolxTutorial();
        knolxTutorial.setDescription("The characteristics of someone or something");
        knolxTutorial.setPublished(true);
        knolxTutorial.setT_id(1L);
        knolxTutorial.setTitle("Dr");
        when(tutorialRepository.save((KnolxTutorial) any())).thenReturn(knolxTutorial);
        when(tutorialRepository.findById((Long) any())).thenReturn(Optional.empty());

        KnolxTutorial knolxTutorial1 = new KnolxTutorial();
        knolxTutorial1.setDescription("The characteristics of someone or something");
        knolxTutorial1.setPublished(true);
        knolxTutorial1.setT_id(1L);
        knolxTutorial1.setTitle("Dr");
        ResponseEntity<KnolxTutorial> actualUpdateTutorialResult = knolxTutorialController.updateTutorial(123L,
                knolxTutorial1);
        assertNull(actualUpdateTutorialResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualUpdateTutorialResult.getStatusCode());
        assertTrue(actualUpdateTutorialResult.getHeaders().isEmpty());
        verify(tutorialRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link KnolxTutorialController#deleteTutorial(long)}
     */
    @Test
    void testDeleteTutorial() {
        doNothing().when(tutorialRepository).deleteById((Long) any());
        ResponseEntity<HttpStatus> actualDeleteTutorialResult = knolxTutorialController.deleteTutorial(123L);
        assertNull(actualDeleteTutorialResult.getBody());
        assertEquals(HttpStatus.NO_CONTENT, actualDeleteTutorialResult.getStatusCode());
        assertTrue(actualDeleteTutorialResult.getHeaders().isEmpty());
        verify(tutorialRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link KnolxTutorialController#deleteAllTutorials()}
     */
    @Test
    void testDeleteAllTutorials() {
        doNothing().when(tutorialRepository).deleteAll();
        ResponseEntity<HttpStatus> actualDeleteAllTutorialsResult = knolxTutorialController.deleteAllTutorials();
        assertNull(actualDeleteAllTutorialsResult.getBody());
        assertEquals(HttpStatus.NO_CONTENT, actualDeleteAllTutorialsResult.getStatusCode());
        assertTrue(actualDeleteAllTutorialsResult.getHeaders().isEmpty());
        verify(tutorialRepository).deleteAll();
    }

    /**
     * Method under test: {@link KnolxTutorialController#findByPublished()}
     */
    @Test
    void testFindByPublished() {
        when(tutorialRepository.findByPublished(anyBoolean())).thenReturn(new ArrayList<>());
        ResponseEntity<List<KnolxTutorial>> actualFindByPublishedResult = knolxTutorialController.findByPublished();
        assertNull(actualFindByPublishedResult.getBody());
        assertEquals(HttpStatus.NO_CONTENT, actualFindByPublishedResult.getStatusCode());
        assertTrue(actualFindByPublishedResult.getHeaders().isEmpty());
        verify(tutorialRepository).findByPublished(anyBoolean());
    }

    /**
     * Method under test: {@link KnolxTutorialController#findByPublished()}
     */
    @Test
    void testFindByPublished2() {
        KnolxTutorial knolxTutorial = new KnolxTutorial();
        knolxTutorial.setDescription("The characteristics of someone or something");
        knolxTutorial.setPublished(true);
        knolxTutorial.setT_id(1L);
        knolxTutorial.setTitle("Dr");

        ArrayList<KnolxTutorial> knolxTutorialList = new ArrayList<>();
        knolxTutorialList.add(knolxTutorial);
        when(tutorialRepository.findByPublished(anyBoolean())).thenReturn(knolxTutorialList);
        ResponseEntity<List<KnolxTutorial>> actualFindByPublishedResult = knolxTutorialController.findByPublished();
        assertTrue(actualFindByPublishedResult.hasBody());
        assertTrue(actualFindByPublishedResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualFindByPublishedResult.getStatusCode());
        verify(tutorialRepository).findByPublished(anyBoolean());
    }
}

