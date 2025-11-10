package cc.dylanpriebe.backend.repository;

import cc.dylanpriebe.backend.objects.DiaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, Long> {

    List<DiaryEntry> findByUserId(long userId);
    List<DiaryEntry> findByUserIdAndLocation(long userId, String location);

}
