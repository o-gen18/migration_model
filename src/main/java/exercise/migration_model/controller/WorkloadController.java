package exercise.migration_model.controller;

import exercise.migration_model.model.Workload;
import exercise.migration_model.repository.WorkloadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/workload")
public class WorkloadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkloadController.class.getName());
    private WorkloadRepository workloadRepository;

    public WorkloadController(WorkloadRepository workloadRepository) {
        this.workloadRepository = workloadRepository;
    }

    @PostMapping("/")
    public ResponseEntity<Workload> addWorkload(@RequestBody Workload workload) {
        workload.setId(UUID.randomUUID());
        try {
            return new ResponseEntity<>(workloadRepository.save(workload), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workload> modifyWorkload(@PathVariable("id") UUID id,
                                                   @RequestBody Workload workload) {
        Optional<Workload> workloadOptional = workloadRepository.findById(id);
        if (workloadOptional.isPresent()) {
            Workload workloadPersisted = workloadOptional.get();
            workloadPersisted.setCredentials(workload.getCredentials());
            workloadPersisted.setVolumes(workload.getVolumes());
            return new ResponseEntity<>(workloadRepository.save(workloadPersisted), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWorkload(@PathVariable("id") UUID id) {
        try {
            workloadRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
