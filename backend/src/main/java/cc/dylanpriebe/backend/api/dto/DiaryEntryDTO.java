package cc.dylanpriebe.backend.api.dto;

import cc.dylanpriebe.backend.objects.DiaryEntry;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class DiaryEntryDTO {
    @NotBlank
    private String entry;

    @NotBlank
    private String location;

    @NotNull
    private Instant date;

    public DiaryEntryDTO(DiaryEntry diaryEntry) {
        this.entry = diaryEntry.getEntry();
        this.location = diaryEntry.getLocation();
        this.date = diaryEntry.getDate();
    }
}
