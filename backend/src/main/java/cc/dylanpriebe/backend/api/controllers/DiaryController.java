package cc.dylanpriebe.backend.api.controllers;

import cc.dylanpriebe.backend.api.dto.DiaryEntryDTO;
import cc.dylanpriebe.backend.api.services.DiaryService;
import cc.dylanpriebe.backend.api.services.UserService;
import cc.dylanpriebe.backend.objects.DiaryEntry;
import cc.dylanpriebe.backend.objects.User;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;
    private final UserService userService;

    public DiaryController(DiaryService diaryService, UserService userService) {
        this.diaryService = diaryService;
        this.userService = userService;
    }

    @GetMapping()
    public List<DiaryEntryDTO> getDiary(
            @AuthenticationPrincipal OAuth2User principal,
            @RequestParam(required = false) String location,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant start,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant end
    ) {
        User user = userService.getUser(principal);
        return diaryService.getDiary(user, location, start, end);
    }

    @PostMapping()
    public ResponseEntity<DiaryEntry> createDiary(
            @AuthenticationPrincipal OAuth2User principal,
            @Valid @RequestBody DiaryEntryDTO diaryEntryDTO
            ) {
        User user = userService.getUser(principal);
        DiaryEntry savedEntry = diaryService.createDiary(user, diaryEntryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntry);
    }
}
