package com.ceniuch.bluediamondbackend.sessions;

import com.ceniuch.bluediamondbackend.sessions.dtos.CreateSessionDto;
import com.ceniuch.bluediamondbackend.sessions.dtos.GetSessionDto;
import com.ceniuch.bluediamondbackend.sessions.dtos.GetSessionDtoId;
import com.ceniuch.bluediamondbackend.sessions.dtos.UpdateSessionDtoId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
    name = "Session Controller",
    description = "Controller for CRUD operations on the session"
)
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sessions")
public class SessionController{
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Operation(
            summary = "Create a new Session according to a provided CreateSessionDto."
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    GetSessionDto createSession(@RequestBody CreateSessionDto createSessionDto) {
        return sessionService.createSession(createSessionDto);
    }

    @Operation(
            summary = "Get all Sessions for a given userUid."
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<GetSessionDto> getAllSessions(@RequestParam String userUid) {
        return sessionService.getAllSessions(userUid);
    }

    @Operation(
            summary = "Update an existing Session according to a provided GetSessionDtoId." +
                    "Only used for updating the completed status of the Session." +
                    "If the Session with the provided sessionId does not exist, a SessionNotFoundException is thrown."
    )
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    GetSessionDto updateSession(@RequestBody UpdateSessionDtoId updateSessionDtoId) {
        return sessionService.updateSession(updateSessionDtoId);
    }
}
