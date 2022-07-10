package com.knoldus.springbootdatajpamysql.repository;

import com.knoldus.springbootdatajpamysql.model.KnolxTutorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<KnolxTutorial, Long> {
	List<KnolxTutorial> findByPublished(boolean published);
	List<KnolxTutorial> findByTitleContaining(String title);
}
