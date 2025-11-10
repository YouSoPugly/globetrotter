package cc.dylanpriebe.backend.api.services;

import cc.dylanpriebe.backend.api.dto.DiaryEntryDTO;
import cc.dylanpriebe.backend.objects.DiaryEntry;
import cc.dylanpriebe.backend.objects.User;
import cc.dylanpriebe.backend.repository.DiaryEntryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    private final DiaryEntryRepository diaryEntryRepository;

    public DiaryService(DiaryEntryRepository diaryEntryRepository) {
        this.diaryEntryRepository = diaryEntryRepository;
    }

    public List<DiaryEntryDTO> getDiary(User user, String location, Instant start, Instant end) {
        List<DiaryEntry> diaryEntries = diaryEntryRepository.findByUserId(user.getId());

        return diaryEntries.stream()
                .filter(d -> (location == null || location.equals(d.getLocation())))
                .filter( d -> (start == null || !d.getDate().isBefore(start)))
                .filter(d -> (end == null || !d.getDate().isAfter(end)))
                .map(DiaryEntryDTO::new).collect(Collectors.toList());
    }

    public DiaryEntry createDiary(User user, DiaryEntryDTO diaryEntryDTO) {
        DiaryEntry newDiaryEntry = new DiaryEntry(diaryEntryDTO, user);
        diaryEntryRepository.save(newDiaryEntry);
        return newDiaryEntry;
    }
}
