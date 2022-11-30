package com.knoldus.springbootdatajpamysql.controller;


import com.knoldus.springbootdatajpamysql.model.KnolxTutorial;
import com.knoldus.springbootdatajpamysql.repository.TutorialRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * KnolxTutorialController used to manage
 * all activity within knolx Tutorial
 */
public class KnolxTutorialController {
	@Autowired
	TutorialRepository tutorialRepository;

	@GetMapping("/tutorials")
	public ResponseEntity<List<KnolxTutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<KnolxTutorial> tutorials = new ArrayList<KnolxTutorial>();

			if (title == null)
				tutorials.addAll(tutorialRepository.findAll());
			else
				tutorials.addAll(tutorialRepository.findByTitleContaining(title));

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * getting Tutorial data by their ID.
	 * @param id id of tutorial
	 * @return return tutorialData
	 */
	@GetMapping("/tutorials/{id}")
	public ResponseEntity<KnolxTutorial> getTutorialById(@PathVariable("id") long id) {
		Optional<KnolxTutorial> tutorialData = tutorialRepository.findById(id);

		return tutorialData.map(tutorial -> new ResponseEntity<>(tutorial, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/tutorials")
	public ResponseEntity<KnolxTutorial> createTutorial(@RequestBody KnolxTutorial tutorial) {
		try {
			KnolxTutorial _tutorial = tutorialRepository
					.save(new KnolxTutorial(tutorial.getTitle(), tutorial.getDescription(), false));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tutorials/{id}")
	public ResponseEntity<KnolxTutorial> updateTutorial(@PathVariable("id") long id, @RequestBody KnolxTutorial tutorial) {
		Optional<KnolxTutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			KnolxTutorial _tutorial = tutorialData.get();
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());
			return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<List<KnolxTutorial>> findByPublished() {
		try {
			List<KnolxTutorial> tutorials = tutorialRepository.findByPublished(true);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

