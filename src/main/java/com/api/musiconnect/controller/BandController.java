package com.api.musiconnect.controller;


import com.api.musiconnect.dto.request.ConfigUpdateUserRequest;
import com.api.musiconnect.dto.request.CreateBandRequest;
import com.api.musiconnect.dto.request.InviteToBandRequest;
import com.api.musiconnect.dto.response.BandResponse;
import com.api.musiconnect.dto.response.InviteToBandResponse;
import com.api.musiconnect.dto.response.UserResponse;
import com.api.musiconnect.service.BandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bands")
@RequiredArgsConstructor
public class BandController {
    private final BandService bandService;

    @PostMapping
    public ResponseEntity<BandResponse> registerBand(@Valid @RequestBody CreateBandRequest request)
    {
        return new ResponseEntity<>(bandService.registerBand(request), HttpStatus.CREATED);
    }


    @GetMapping(path = "/{bandId}")
    public ResponseEntity<BandResponse> getBandById(@PathVariable Integer bandId)
    {
        return new ResponseEntity<>(bandService.getBandById(bandId), HttpStatus.OK);
    }

    @PutMapping(path = "/{bandId}")
    public ResponseEntity<BandResponse> updateBandById(@PathVariable Integer bandId, @Valid @RequestBody CreateBandRequest request)
    {
        return new ResponseEntity<>(bandService.updateBandById(bandId, request), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{bandId}")
    public ResponseEntity<String> deleteBandById(@PathVariable Integer bandId){
        return new ResponseEntity<>(bandService.deleteBandById(bandId), HttpStatus.OK);
    }

    @PostMapping(path = "/{bandId}/members")
    public ResponseEntity<InviteToBandResponse> inviteToBand(@Valid @RequestBody InviteToBandRequest request) {
        InviteToBandResponse response = bandService.inviteToBand(request);
        return ResponseEntity.ok(response);
    }
}
