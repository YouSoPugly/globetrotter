package cc.dylanpriebe.backend.objects;

import cc.dylanpriebe.backend.api.dto.DiaryEntryDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@Table(name = "diary_entries")
@NoArgsConstructor
public class DiaryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Instant date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank
    private String entry;

    @NotBlank
    private String location;

    public DiaryEntry(DiaryEntryDTO diaryEntryDTO, User user) {
        this.date = diaryEntryDTO.getDate();
        this.entry = diaryEntryDTO.getEntry();
        this.location = diaryEntryDTO.getLocation();
        this.user = user;
    }
}
