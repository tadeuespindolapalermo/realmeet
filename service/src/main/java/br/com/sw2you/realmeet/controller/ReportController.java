package br.com.sw2you.realmeet.controller;

import br.com.sw2you.realmeet.api.facade.AllocationsApi;
import br.com.sw2you.realmeet.api.facade.ReportsApi;
import br.com.sw2you.realmeet.service.AllocationService;
import br.com.sw2you.realmeet.service.ReportCreationService;
import br.com.sw2you.realmeet.util.ResponseEntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.concurrent.CompletableFuture.runAsync;

@RestController
public class ReportController implements ReportsApi {
    private final Executor controllersExecutor;
    private final ReportCreationService reportCreationService;

    public ReportController(Executor controllersExecutor, ReportCreationService reportCreationService) {
        this.controllersExecutor = controllersExecutor;
        this.reportCreationService = reportCreationService;
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> createAllocationReport(
        String apiKey,
        String email,
        LocalDate dateFrom,
        LocalDate dateTo,
        String reportFormat
    ) {
        return runAsync(() -> reportCreationService.createAllocationReport(dateFrom, dateTo, email, reportFormat), controllersExecutor)
            .thenApply(ResponseEntityUtils::noContent);
    }

}
